// Generated by view binder compiler. Do not edit!
package com.example.myapplication1.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.example.myapplication1.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityHeaderBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ImageView imageProfile;

  @NonNull
  public final TextView profileName;

  @NonNull
  public final TextView profileUsername;

  private ActivityHeaderBinding(@NonNull LinearLayout rootView, @NonNull ImageView imageProfile,
      @NonNull TextView profileName, @NonNull TextView profileUsername) {
    this.rootView = rootView;
    this.imageProfile = imageProfile;
    this.profileName = profileName;
    this.profileUsername = profileUsername;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityHeaderBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityHeaderBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_header, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityHeaderBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.imageProfile;
      ImageView imageProfile = rootView.findViewById(id);
      if (imageProfile == null) {
        break missingId;
      }

      id = R.id.profile_name;
      TextView profileName = rootView.findViewById(id);
      if (profileName == null) {
        break missingId;
      }

      id = R.id.profile_username;
      TextView profileUsername = rootView.findViewById(id);
      if (profileUsername == null) {
        break missingId;
      }

      return new ActivityHeaderBinding((LinearLayout) rootView, imageProfile, profileName,
          profileUsername);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}