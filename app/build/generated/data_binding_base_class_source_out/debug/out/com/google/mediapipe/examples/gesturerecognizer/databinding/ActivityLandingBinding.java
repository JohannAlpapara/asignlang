// Generated by view binder compiler. Do not edit!
package com.google.mediapipe.examples.gesturerecognizer.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.mediapipe.examples.gesturerecognizer.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityLandingBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnLangChange;

  @NonNull
  public final Button btnLaunch;

  @NonNull
  public final TextView result;

  private ActivityLandingBinding(@NonNull ConstraintLayout rootView, @NonNull Button btnLangChange,
      @NonNull Button btnLaunch, @NonNull TextView result) {
    this.rootView = rootView;
    this.btnLangChange = btnLangChange;
    this.btnLaunch = btnLaunch;
    this.result = result;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLandingBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLandingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_landing, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLandingBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnLangChange;
      Button btnLangChange = ViewBindings.findChildViewById(rootView, id);
      if (btnLangChange == null) {
        break missingId;
      }

      id = R.id.btnLaunch;
      Button btnLaunch = ViewBindings.findChildViewById(rootView, id);
      if (btnLaunch == null) {
        break missingId;
      }

      id = R.id.result;
      TextView result = ViewBindings.findChildViewById(rootView, id);
      if (result == null) {
        break missingId;
      }

      return new ActivityLandingBinding((ConstraintLayout) rootView, btnLangChange, btnLaunch,
          result);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
