/*
 * Copyright 2022 The TensorFlow Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *             http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.mediapipe.examples.gesturerecognizer.fragment

import android.annotation.SuppressLint
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.mediapipe.examples.gesturerecognizer.databinding.ItemGestureRecognizerResultBinding
import com.google.mediapipe.tasks.components.containers.Category
import java.util.Locale
import kotlin.math.min
import android.os.Handler
import android.os.Looper

private val textToSpeech: TextToSpeech? = null

class GestureRecognizerResultsAdapter :
    RecyclerView.Adapter<GestureRecognizerResultsAdapter.ViewHolder>() {
    companion object {
        private const val NO_VALUE = "--"
    }

    private var adapterCategories: MutableList<Category?> = mutableListOf()
    private var adapterSize: Int = 0
    private var lastLabel: String? = null
    private var lastLabelSetTime: Long = 0L
    private val handler = Handler(Looper.getMainLooper())

    @SuppressLint("NotifyDataSetChanged")
    fun updateResults(categories: List<Category>?) {
        adapterCategories = MutableList(adapterSize) { null }
        if (categories != null) {
            val sortedCategories = categories.sortedByDescending { it.score() }
            val min = min(sortedCategories.size, adapterCategories.size)
            for (i in 0 until min) {
                adapterCategories[i] = sortedCategories[i]
            }
            adapterCategories.sortedBy { it?.index() }
            notifyDataSetChanged()
        }
    }

    fun updateAdapterSize(size: Int) {
        adapterSize = size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemGestureRecognizerResultBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        adapterCategories[position].let { category ->
            holder.bind(category?.categoryName(), category?.score())
        }
    }

    override fun getItemCount(): Int = adapterCategories.size

    inner class ViewHolder(private val binding: ItemGestureRecognizerResultBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var lastLabelSetTime: Long = 0
        private var isLabelShownForOneSecond = false

        fun bind(label: String?, score: Float?) {
            with(binding) {
                tvLabel.text = label ?: NO_VALUE
                tvScore.text = if (score != null) String.format(Locale.US, "%.2f", score) else NO_VALUE

                // Check if the label has been the same for 1 second
                handler.postDelayed({
                    if (tvLabel.text == label && (System.currentTimeMillis() - lastLabelSetTime) >= 1000) {
                        // Do something after 1 second if the label is the same
                        // For example, change text color to indicate 1-second stability
                        tvLabel.setTextColor(tvLabel.context.getColor(android.R.color.holo_green_dark))
                    } else {
                        tvLabel.setTextColor(tvLabel.context.getColor(android.R.color.black))
                    }
                }, 1000)
            }
        }
    }
}