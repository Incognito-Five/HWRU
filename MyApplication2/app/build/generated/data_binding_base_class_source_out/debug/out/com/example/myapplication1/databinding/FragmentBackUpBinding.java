// Generated by view binder compiler. Do not edit!
package com.example.myapplication1.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.example.myapplication1.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentBackUpBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView backupAbt;

  @NonNull
  public final ConstraintLayout relativeLayout;

  @NonNull
  public final TextView restoreAbt;

  @NonNull
  public final SwitchCompat switchBackup;

  @NonNull
  public final SwitchCompat switchRestore;

  @NonNull
  public final TextView txtBackup;

  @NonNull
  public final TextView txtBackupbar;

  @NonNull
  public final TextView txtRestore;

  private FragmentBackUpBinding(@NonNull ConstraintLayout rootView, @NonNull TextView backupAbt,
      @NonNull ConstraintLayout relativeLayout, @NonNull TextView restoreAbt,
      @NonNull SwitchCompat switchBackup, @NonNull SwitchCompat switchRestore,
      @NonNull TextView txtBackup, @NonNull TextView txtBackupbar, @NonNull TextView txtRestore) {
    this.rootView = rootView;
    this.backupAbt = backupAbt;
    this.relativeLayout = relativeLayout;
    this.restoreAbt = restoreAbt;
    this.switchBackup = switchBackup;
    this.switchRestore = switchRestore;
    this.txtBackup = txtBackup;
    this.txtBackupbar = txtBackupbar;
    this.txtRestore = txtRestore;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentBackUpBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentBackUpBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_back_up, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentBackUpBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.backup_abt;
      TextView backupAbt = rootView.findViewById(id);
      if (backupAbt == null) {
        break missingId;
      }

      ConstraintLayout relativeLayout = (ConstraintLayout) rootView;

      id = R.id.restore_abt;
      TextView restoreAbt = rootView.findViewById(id);
      if (restoreAbt == null) {
        break missingId;
      }

      id = R.id.switch_backup;
      SwitchCompat switchBackup = rootView.findViewById(id);
      if (switchBackup == null) {
        break missingId;
      }

      id = R.id.switch_restore;
      SwitchCompat switchRestore = rootView.findViewById(id);
      if (switchRestore == null) {
        break missingId;
      }

      id = R.id.txt_backup;
      TextView txtBackup = rootView.findViewById(id);
      if (txtBackup == null) {
        break missingId;
      }

      id = R.id.txt_backupbar;
      TextView txtBackupbar = rootView.findViewById(id);
      if (txtBackupbar == null) {
        break missingId;
      }

      id = R.id.txt_restore;
      TextView txtRestore = rootView.findViewById(id);
      if (txtRestore == null) {
        break missingId;
      }

      return new FragmentBackUpBinding((ConstraintLayout) rootView, backupAbt, relativeLayout,
          restoreAbt, switchBackup, switchRestore, txtBackup, txtBackupbar, txtRestore);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
