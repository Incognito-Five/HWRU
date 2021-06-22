// Generated by view binder compiler. Do not edit!
package com.example.myapplication1.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.example.myapplication1.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityForgotPasswordPageBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button buttonReset;

  @NonNull
  public final TextView lblHow;

  @NonNull
  public final TextView lblPer;

  @NonNull
  public final TextView lblUsername;

  @NonNull
  public final TextView lblWelc;

  @NonNull
  public final EditText txtUsernameReset;

  private ActivityForgotPasswordPageBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button buttonReset, @NonNull TextView lblHow, @NonNull TextView lblPer,
      @NonNull TextView lblUsername, @NonNull TextView lblWelc,
      @NonNull EditText txtUsernameReset) {
    this.rootView = rootView;
    this.buttonReset = buttonReset;
    this.lblHow = lblHow;
    this.lblPer = lblPer;
    this.lblUsername = lblUsername;
    this.lblWelc = lblWelc;
    this.txtUsernameReset = txtUsernameReset;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityForgotPasswordPageBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityForgotPasswordPageBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_forgot_password_page, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityForgotPasswordPageBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button_reset;
      Button buttonReset = rootView.findViewById(id);
      if (buttonReset == null) {
        break missingId;
      }

      id = R.id.lbl_how;
      TextView lblHow = rootView.findViewById(id);
      if (lblHow == null) {
        break missingId;
      }

      id = R.id.lbl_per;
      TextView lblPer = rootView.findViewById(id);
      if (lblPer == null) {
        break missingId;
      }

      id = R.id.lbl_username;
      TextView lblUsername = rootView.findViewById(id);
      if (lblUsername == null) {
        break missingId;
      }

      id = R.id.lbl_welc;
      TextView lblWelc = rootView.findViewById(id);
      if (lblWelc == null) {
        break missingId;
      }

      id = R.id.txt_username_reset;
      EditText txtUsernameReset = rootView.findViewById(id);
      if (txtUsernameReset == null) {
        break missingId;
      }

      return new ActivityForgotPasswordPageBinding((ConstraintLayout) rootView, buttonReset, lblHow,
          lblPer, lblUsername, lblWelc, txtUsernameReset);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}