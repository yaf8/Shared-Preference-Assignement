package com.example.assigntry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

public class SearchActivity extends AppCompatActivity {

    private Button btnSearch, btnBack;
    private EditText edtSearch;
    private TextView textSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        btnBack = findViewById(R.id.btnBack);
        btnSearch = findViewById(R.id.btnSearch);
        edtSearch = findViewById(R.id.edtSearch);
        textSearch = findViewById(R.id.textSearch);


        btnSearch.setOnClickListener(v -> {
            searchText();
        });

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

    }

    private void searchText() {

        String searchTxt = edtSearch.getText().toString();
        for (StudentModal str : MainActivity.StudentModalArrayList) {
            if(Objects.equals(str.getID(), searchTxt))
            {
                textSearch.setText( textSearch.getText().toString() +"\n" + "ID :      " + str.getID() + "\n" + "Name :    " + str.getFullName() + "\n");
            }
        }
    }


}