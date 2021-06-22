// Generated by view binder compiler. Do not edit!
package com.example.myapplication1.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.example.myapplication1.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentHelpBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final ImageView imgLogoApp;

  @NonNull
  public final ImageView imgLogoIncognito;

  @NonNull
  public final LinearLayout linearLayout;

  @NonNull
  public final TextView txtAboutAppText;

  @NonNull
  public final TextView txtAboutUsText;

  private FragmentHelpBinding(@NonNull ScrollView rootView, @NonNull ImageView imgLogoApp,
      @NonNull ImageView imgLogoIncognito, @NonNull LinearLayout linearLayout,
      @NonNull TextView txtAboutAppText, @NonNull TextView txtAboutUsText) {
    this.rootView = rootView;
    this.imgLogoApp = imgLogoApp;
    this.imgLogoIncognito = imgLogoIncognito;
    this.linearLayout = linearLayout;
    this.txtAboutAppText = txtAboutAppText;
    this.txtAboutUsText = txtAboutUsText;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentHelpBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentHelpBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_help, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentHelpBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.img_logoApp;
      ImageView imgLogoApp = rootView.findViewById(id);
      if (imgLogoApp == null) {
        break missingId;
      }

      id = R.id.img_logoIncognito;
      ImageView imgLogoIncognito = rootView.findViewById(id);
      if (imgLogoIncognito == null) {
        break missingId;
      }

      id = R.id.linearLayout;
      LinearLayout linearLayout = rootView.findViewById(id);
      if (linearLayout == null) {
        break missingId;
      }

      id = R.id.txt_aboutAppText;
      TextView txtAboutAppText = rootView.findViewById(id);
      if (txtAboutAppText == null) {
        break missingId;
      }

      id = R.id.txt_aboutUsText;
      TextView txtAboutUsText = rootView.findViewById(id);
      if (txtAboutUsText == null) {
        break missingId;
      }

      return new FragmentHelpBinding((ScrollView) rootView, imgLogoApp, imgLogoIncognito,
          linearLayout, txtAboutAppText, txtAboutUsText);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}