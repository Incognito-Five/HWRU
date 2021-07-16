package com.example.myapplication1;

import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import java.io.File;

public class BackUpFragment extends Fragment {

    BackUpHelper backUpHelper;
    SwitchCompat switch_backup, switch_restore;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_back_up, container, false);
        switch_backup = view.findViewById(R.id.switch_backup);
        switch_restore = view.findViewById(R.id.switch_restore);

        backUpHelper = new BackUpHelper(this);
        final DatabaseHelper db = new DatabaseHelper(getActivity().getApplicationContext());

        switch_backup.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //backup all data and settings
                    String outFileName = Environment.getExternalStorageDirectory() + File.separator + getResources().getString(R.string.app_name) + File.separator;
                    backUpHelper.performBackup(db, outFileName);
                } else {
                    switch_backup.setChecked(false);
                }
            }
        });

        switch_restore.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    backUpHelper.performRestore(db);
                } else {
                    switch_restore.setChecked(false);
                }
            }
        });
        return view;
    }

    /*//ask to the user a name for the backup and perform it. The backup will be saved to a custom folder.
    public void performBackup(final DatabaseHelper db, final String outFileName) {

        Permissions.verifyStoragePermissions(getActivity());

        File folder = new File(Environment.getExternalStorageDirectory() + File.separator + requireActivity().getResources().getString(R.string.app_name));

        boolean success = true;
        if (!folder.exists())
            success = folder.mkdirs();
        if (success) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Backup Name");
            final EditText input = new EditText(getActivity());
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
            Toast.makeText(getActivity(), "Unable to create directory. Retry", Toast.LENGTH_SHORT).show();
            switch_backup.setChecked(false);
        }
    }

    //ask to the user what backup to restore
    public void performRestore(final DatabaseHelper db) {

        Permissions.verifyStoragePermissions(getActivity());

        File folder = new File(Environment.getExternalStorageDirectory() + File.separator + requireActivity().getResources().getString(R.string.app_name));
        if (folder.exists()) {

            File[] files = folder.listFiles();

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.select_dialog_item);
            assert files != null;
            for (File file : files)
                arrayAdapter.add(file.getName());

            AlertDialog.Builder builderSingle = new AlertDialog.Builder(getActivity());
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
                            Toast.makeText(getActivity(), "Unable to restore. Retry", Toast.LENGTH_SHORT).show();
                        }
                    });
            builderSingle.show();
        } else
            Toast.makeText(getActivity(), "Backup folder not present.\nDo a backup before a restore!", Toast.LENGTH_SHORT).show();
            switch_restore.setChecked(false);
    }*/
}
