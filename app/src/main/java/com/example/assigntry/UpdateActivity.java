package com.example.assigntry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

public class UpdateActivity extends AppCompatActivity {

    private Button btnUpdate, btnBack;
    private EditText edtUpdate, edtUpdateID, edtUpdateFullName;
    private TextView textSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        btnBack = findViewById(R.id.btnBack);
        btnUpdate = findViewById(R.id.btnUpdate);
        edtUpdate = findViewById(R.id.edtUpdate);
        edtUpdateID = findViewById(R.id.edtUpdateID);
        edtUpdateFullName = findViewById(R.id.edtUpdateFullName);
        textSearch = findViewById(R.id.textSearch);

        edtUpdateID.setVisibility(View.INVISIBLE);
        edtUpdateFullName.setVisibility(View.INVISIBLE);



        btnUpdate.setOnClickListener(v -> {
            update();
        });

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

    }

    private void update() {

        String searchID = edtUpdate.getText().toString();
        for (StudentModal str : MainActivity.StudentModalArrayList) {
            if(Objects.equals(str.getID(), searchID))
            {

            }
        }
    }


}