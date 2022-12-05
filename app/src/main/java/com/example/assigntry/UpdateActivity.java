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
import android.widget.Toast;

import java.util.Objects;

public class UpdateActivity extends AppCompatActivity {

    private Button btnUpdate, btnBack, btnFind;
    private EditText edtUpdate, edtUpdateID, edtUpdateFullName;
    private TextView textSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        btnFind = findViewById(R.id.btnFind);
        btnBack = findViewById(R.id.btnBack);
        btnUpdate = findViewById(R.id.btnUpdate);
        edtUpdate = findViewById(R.id.edtUpdate);
        edtUpdateID = findViewById(R.id.edtUpdateID);
        edtUpdateFullName = findViewById(R.id.edtUpdateFullName);
        textSearch = findViewById(R.id.textSearch);

        edtUpdateID.setVisibility(View.INVISIBLE);
        edtUpdateFullName.setVisibility(View.INVISIBLE);
        btnUpdate.setVisibility(View.INVISIBLE);


        btnFind.setOnClickListener(v -> {
            find();
        });

        btnUpdate.setOnClickListener(v -> {
            update();
        });

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

    }

    private void find() {

        String searchID = edtUpdate.getText().toString(), newID = edtUpdateID.getText().toString(), newFullName = edtUpdateFullName.getText().toString();
        for (StudentModal str : MainActivity.StudentModalArrayList)
        {
            if(Objects.equals(str.getID(), searchID))
            {
                edtUpdateID.setVisibility(View.VISIBLE);
                edtUpdateFullName.setVisibility(View.VISIBLE);
                btnUpdate.setVisibility(View.VISIBLE);
                Toast.makeText(this, "Found", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void update() {
        String findID = edtUpdate.getText().toString(), newID = edtUpdateID.getText().toString(), newFullName = edtUpdateFullName.getText().toString();

                for(int i = 0; i < MainActivity.StudentModalArrayList.size(); i++)
                {
                    if(Objects.equals(MainActivity.StudentModalArrayList.get(i).getID(), findID))
                    {
                        MainActivity.StudentModalArrayList.get(i).setID(newID);
                        MainActivity.StudentModalArrayList.get(i).setFullName(newFullName);

                        Toast.makeText(this, "Update", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                    }
                }
                //str.setID(newID);
                //str.setFullName(newFullName);



    }


}