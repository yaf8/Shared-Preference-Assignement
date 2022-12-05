package com.example.assigntry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Objects;

public class DeleteActivity extends AppCompatActivity {

    private Button btnDelete, btnBack, btnFind;
    private EditText edtFind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        btnFind = findViewById(R.id.btnFind);
        btnBack = findViewById(R.id.btnBack);
        btnDelete = findViewById(R.id.btnDelete);
        edtFind = findViewById(R.id.edtFind);

        btnDelete.setVisibility(View.INVISIBLE);

        btnFind.setOnClickListener(v -> {
            find();
        });

        btnDelete.setOnClickListener(v -> {
            delete();
        });

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

    }

    private void find() {

        String findID = edtFind.getText().toString();
        for (StudentModal str : MainActivity.StudentModalArrayList)
        {
            if(Objects.equals(str.getID(), findID))
            {
                btnDelete.setVisibility(View.VISIBLE);
                Toast.makeText(this, "Found", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void delete() {
        String findID = edtFind.getText().toString();

        for(int i = 0; i < MainActivity.StudentModalArrayList.size(); i++)
        {
            if(Objects.equals(MainActivity.StudentModalArrayList.get(i).getID(), findID))
            {
                MainActivity.StudentModalArrayList.remove(i);

                SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.STUD_PREF_NAME, MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();

                MainActivity.gson = new Gson();

                String json = MainActivity.gson.toJson(MainActivity.StudentModalArrayList);

                editor.putString(MainActivity.STUD_PREF_KEY, json);

                editor.apply();


                Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        }
    }
}