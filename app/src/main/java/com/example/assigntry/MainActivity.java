package com.example.assigntry;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText edtID, edtFullName;
    private Button btnSave, btnUpdate, btnDelete, btnSearch, btnDeleteAll, btnDisplay;

    public static StudentAdapter adapter;
    public static ArrayList<StudentModal> StudentModalArrayList;
    public static final String STUD_PREF_NAME = "Stud_Prefs";
    public static final String STUD_PREF_KEY = "stud_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtID = findViewById(R.id.edtID);
        edtFullName = findViewById(R.id.edtFullName);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDeleteAll = findViewById(R.id.btnDeleteAll);
        btnDisplay = findViewById(R.id.btnDisplay);


        loadData();


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(edtID.getText().toString().isEmpty()) && !(edtFullName.getText().toString()).isEmpty())
                {
                    StudentModalArrayList.add(new StudentModal(edtID.getText().toString(), edtFullName.getText().toString()));
                    adapter.notifyItemInserted(StudentModalArrayList.size());
                    saveData();

                }
            }
        });

        btnDisplay.setOnClickListener(v -> {
            Intent intent = new Intent(this, DisplayActivity.class);
            startActivity(intent);
        });

        btnDeleteAll.setOnClickListener(v -> {
            deleteAll();
        });

        btnSearch.setOnClickListener(v -> {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        });
        btnDelete.setOnClickListener(v -> {

        });

    private void deleteAll() {
        SharedPreferences settings = getSharedPreferences(STUD_PREF_NAME, MODE_PRIVATE);
        settings.edit().clear().commit();
        Toast.makeText(this, "Data Cleared", Toast.LENGTH_SHORT).show();
    }


    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(STUD_PREF_NAME, MODE_PRIVATE);

        Gson gson = new Gson();

        String json = sharedPreferences.getString(STUD_PREF_KEY, null);

        Type type = new TypeToken<ArrayList<StudentModal>>() {}.getType();

        StudentModalArrayList = gson.fromJson(json, type);

        if (StudentModalArrayList == null) {
            StudentModalArrayList = new ArrayList<>();
        }
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(STUD_PREF_NAME, MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();

        String json = gson.toJson(StudentModalArrayList);

        editor.putString(STUD_PREF_KEY, json);

        editor.apply();

        Toast.makeText(this, "Saved Array List to Shared preferences. ", Toast.LENGTH_SHORT).show();
    }
}
