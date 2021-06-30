package com.example.myapplication1;

import android.app.AlertDialog;
import android.os.Environment;
import android.text.InputType;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication1.DatabaseHelper;
import com.example.myapplication1.Permissions;
import com.example.myapplication1.R;
import com.example.myapplication1.BackUpFragment;

import java.io.File;

public class BackUpHelper {

    private BackUpFragment backUpFragment;

    public BackUpHelper(BackUpFragment backUpFragment) {
         this.backUpFragment = backUpFragment;
    }
    //ask to the user a name for the backup and perform it. The backup will be saved to a custom folder.
    public void performBackup(final DatabaseHelper db, final String outFileName) {

        Permissions.verifyStoragePermissions(backUpFragment);

        File folder = new File(Environment.getExternalStorageDirectory() + File.separator + backUpFragment.getActivity().getResources().getString(R.string.app_name));

        boolean success = true;
        if (!folder.exists())
            success = folder.mkdirs();
        if (success) {

            AlertDialog.Builder builder = new AlertDialog.Builder(backUpFragment.getActivity());
            builder.setTitle("Backup Name");
            final EditText input = new EditText(backUpFragment.getActivity());
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
            Toast.makeText(backUpFragment.getActivity(), "Unable to create directory. Retry", Toast.LENGTH_SHORT).show();
        }
    }

    //ask to the user what backup to restore
    public void performRestore(final DatabaseHelper db) {

        Permissions.verifyStoragePermissions(backUpFragment);

        File folder = new File(Environment.getExternalStorageDirectory() + File.separator + backUpFragment.getActivity().getResources().getString(R.string.app_name));
        if (folder.exists()) {

            File[] files = folder.listFiles();

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(backUpFragment.getActivity(), android.R.layout.select_dialog_item);
            for (File file : files)
                arrayAdapter.add(file.getName());

            AlertDialog.Builder builderSingle = new AlertDialog.Builder(backUpFragment.getActivity());
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
                            Toast.makeText(backUpFragment.getActivity(), "Unable to restore. Retry", Toast.LENGTH_SHORT).show();
                        }
                    });
            builderSingle.show();
        } else
            Toast.makeText(backUpFragment.getActivity(), "Backup folder not present.\nDo a backup before a restore!", Toast.LENGTH_SHORT).show();
    }
}
