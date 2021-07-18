package com.example.myapplication1.ToDo;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication1.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class ToDoAddTask extends BottomSheetDialogFragment{

    public static  final String TAG = "AddNewTask";

    //widgets
    private EditText toDoListText;
    private Button saveTask;

    private ToDoDBHelper toDoDB;

    public static ToDoAddTask newInstance(){
        return new ToDoAddTask();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.to_do_list_add_task, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toDoListText = view.findViewById(R.id.toDoListText);
        saveTask = view.findViewById(R.id.saveTask);

        toDoDB = new ToDoDBHelper(getActivity());

        boolean isUpdate = false;

        Bundle bundle = getArguments();
        if (bundle != null){
            isUpdate = true;
            String task = bundle.getString("task");
            toDoListText.setText(task);

            if (task.length()==0){
                saveTask.setEnabled(false);
            }
        }
        toDoListText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")){
                    saveTask.setEnabled(false);
                    saveTask.setBackgroundColor(Color.GRAY);
                }
                else{
                    saveTask.setEnabled(true);
                    saveTask.setBackgroundColor(getResources().getColor(R.color.design_default_color_on_primary));

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        final boolean finalIsUpdate = isUpdate;
        saveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = toDoListText.getText().toString();

                if (finalIsUpdate){
                    toDoDB.updateTask(bundle.getInt("id"), text);
                }
                else{
                    ToDoModel item = new ToDoModel();
                    item.setTask(text);
                    item.setStatus(0);
                    toDoDB.addTask(item);
                }
                dismiss();
            }
        });
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Activity activity = getActivity();
        if (activity instanceof OnDialogCloseListener){
            ((OnDialogCloseListener)activity).onDialogClose(dialog);
        }
    }
}
