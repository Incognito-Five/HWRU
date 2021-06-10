package com.example.myapplication1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;


public class AccountFragment extends Fragment {

    ImageView profile;
    TextView prof_name, prof_username;
    Button switch_account, delete_account;
    DatabaseHelper DBase;

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_account, container, false);
        profile = (ImageView) rootView.findViewById(R.id.profileImg);
        prof_name = (TextView) rootView.findViewById(R.id.txt_name);
        prof_username = (TextView) rootView.findViewById(R.id.txt_username);
        switch_account = (Button) rootView.findViewById(R.id.switch_acc);
        delete_account = (Button) rootView.findViewById(R.id.delete_acc);
        DBase = new DatabaseHelper(getActivity());

        Intent intent = getActivity().getIntent();
        prof_name.setText(intent.getStringExtra("name"));
        prof_username.setText(intent.getStringExtra("username"));

        profile.setOnClickListener(v -> {
        });

        switch_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AccountFragment.this.getActivity(), "Logged out!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });

        delete_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String profile_username = prof_username.getText().toString();
                Boolean deleteuser = DBase.deleteData(profile_username);
                if (deleteuser == true){
                    Toast.makeText(AccountFragment.this.getActivity(), "User successfully deleted!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), RegistrationPage.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(AccountFragment.this.getActivity(), "Failed to delete user!", Toast.LENGTH_SHORT).show();
                }
            }
        });



        return rootView;
    }
}