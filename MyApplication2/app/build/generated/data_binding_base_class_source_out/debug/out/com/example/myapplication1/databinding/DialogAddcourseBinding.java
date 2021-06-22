// Generated by view binder compiler. Do not edit!
package com.example.myapplication1.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.example.myapplication1.R;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DialogAddcourseBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final TextInputLayout addCourseCode;

  @NonNull
  public final TextInputLayout addCourseName;

  @NonNull
  public final TextInputLayout addDescription;

  @NonNull
  public final TextInputLayout addEndTime;

  @NonNull
  public final TextInputLayout addProfessor;

  @NonNull
  public final TextInputLayout addStartTime;

  private DialogAddcourseBinding(@NonNull RelativeLayout rootView,
      @NonNull TextInputLayout addCourseCode, @NonNull TextInputLayout addCourseName,
      @NonNull TextInputLayout addDescription, @NonNull TextInputLayout addEndTime,
      @NonNull TextInputLayout addProfessor, @NonNull TextInputLayout addStartTime) {
    this.rootView = rootView;
    this.addCourseCode = addCourseCode;
    this.addCourseName = addCourseName;
    this.addDescription = addDescription;
    this.addEndTime = addEndTime;
    this.addProfessor = addProfessor;
    this.addStartTime = addStartTime;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogAddcourseBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogAddcourseBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_addcourse, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogAddcourseBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.add_course_code;
      TextInputLayout addCourseCode = rootView.findViewById(id);
      if (addCourseCode == null) {
        break missingId;
      }

      id = R.id.add_course_name;
      TextInputLayout addCourseName = rootView.findViewById(id);
      if (addCourseName == null) {
        break missingId;
      }

      id = R.id.add_description;
      TextInputLayout addDescription = rootView.findViewById(id);
      if (addDescription == null) {
        break missingId;
      }

      id = R.id.add_end_time;
      TextInputLayout addEndTime = rootView.findViewById(id);
      if (addEndTime == null) {
        break missingId;
      }

      id = R.id.add_professor;
      TextInputLayout addProfessor = rootView.findViewById(id);
      if (addProfessor == null) {
        break missingId;
      }

      id = R.id.add_start_time;
      TextInputLayout addStartTime = rootView.findViewById(id);
      if (addStartTime == null) {
        break missingId;
      }

      return new DialogAddcourseBinding((RelativeLayout) rootView, addCourseCode, addCourseName,
          addDescription, addEndTime, addProfessor, addStartTime);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}