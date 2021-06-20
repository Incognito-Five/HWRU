package com.example.myapplication1;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Environment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import java.io.File;


public class BackUpFragment extends Fragment {

    SwitchCompat switch_backup, switch_restore;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_back_up, container, false);
        switch_backup = view.findViewById(R.id.switch_backup);
        switch_restore = view.findViewById(R.id.switch_restore);
        final DatabaseHelper db = new DatabaseHelper(getActivity().getApplicationContext());

        switch_backup.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //backup all data and settings
                String outFileName = Environment.getExternalStorageDirectory() + File.separator + getResources().getString(R.string.app_name) + File.separator;
                performBackup(db, outFileName);
            }
        });

        switch_restore.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                performRestore(db);
            }
        });

        return view;
    }


    //ask to the user a name for the backup and perform it. The backup will be saved to a custom folder.
    public void performBackup(final DatabaseHelper db, final String outFileName) {

        com.example.myapplication1.Permissions.verifyStoragePermissions(BackUpFragment.this.getActivity());

        File folder = new File(Environment.getExternalStorageDirectory() + File.separator + BackUpFragment.this.getActivity().getResources().getString(R.string.app_name));

        boolean success = true;
        if (!folder.exists())
            success = folder.mkdirs();
        if (success) {

            AlertDialog.Builder builder = new AlertDialog.Builder(BackUpFragment.this.getActivity());
            builder.setTitle("Backup Name");
            final EditText input = new EditText(BackUpFragment.this.getActivity());
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);
            builder.setPositiveButton("Save", (dialog, which) -> {
                String m_Text = input.getText().toString();
                String out = outFileName + m_Text + ".db";
                db.backup(out);
            });
            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
            builder.show();
        } else {
            Toast.makeText(BackUpFragment.this.getActivity(), "Unable to create directory. Retry", Toast.LENGTH_SHORT).show();
        }
    }

    //ask to the user what backup to restore
    public void performRestore(final DatabaseHelper db) {

        com.example.myapplication1.Permissions.verifyStoragePermissions(BackUpFragment.this.getActivity());

        File folder = new File(Environment.getExternalStorageDirectory() + File.separator + BackUpFragment.this.getActivity().getResources().getString(R.string.app_name));
        if (folder.exists()) {

            final File[] files = folder.listFiles();

            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(BackUpFragment.this.getActivity(), android.R.layout.select_dialog_item);
            for (File file : files)
                arrayAdapter.add(file.getName());

            AlertDialog.Builder builderSingle = new AlertDialog.Builder(BackUpFragment.this.getActivity());
            builderSingle.setTitle("Restore:");
            builderSingle.setNegativeButton(
                    "cancel",
                    (dialog, which) -> dialog.dismiss());
            builderSingle.setAdapter(
                    arrayAdapter,
                    (dialog, which) -> {
                        try {
                            db.importDB(files[which].getPath());
                        } catch (Exception e) {
                            Toast.makeText(BackUpFragment.this.getActivity(), "Unable to restore. Retry", Toast.LENGTH_SHORT).show();
                        }
                    });
            builderSingle.show();
        } else
            Toast.makeText(BackUpFragment.this.getActivity(), "Backup folder not present.\nDo a backup before a restore!", Toast.LENGTH_SHORT).show();
    }

}