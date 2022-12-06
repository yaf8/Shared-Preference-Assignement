package com.example.assigntry;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

    @SuppressLint("StaticFieldLeak")
    public static StudentAdapter adapter;
    public static Gson gson;
    public static ArrayList<StudentModal> StudentModalArrayList;
    public static final String STUD_PREF_NAME = "Stud_Prefs";
    public static final String STUD_PREF_KEY = "stud_key";
    public static LayoutInflater liBlue, liRed, liGreen;
    public static View blueToastLayout, redToastLayout, greenToastLayout;
    private static TextView blueToastMessage, redToastMessage, greenToastMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtID = findViewById(R.id.edtID);
        edtFullName = findViewById(R.id.edtFullName);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnSearch = findViewById(R.id.btnSearch);
        btnDeleteAll = findViewById(R.id.btnDeleteAll);
        btnDisplay = findViewById(R.id.btnDisplay);


        loadData();


        liBlue = getLayoutInflater();
        liRed = getLayoutInflater();
        liGreen = getLayoutInflater();

        blueToastLayout = liBlue.inflate(R.layout.blue_toast, findViewById(R.id.blue_toast_layout));
        redToastLayout = liRed.inflate(R.layout.red_toast,  findViewById(R.id.red_toast_layout));
        greenToastLayout = liGreen.inflate(R.layout.green_toast, findViewById(R.id.green_toast_layout));

        blueToastMessage = blueToastLayout.findViewById(R.id.blueToastMessage);
        redToastMessage = redToastLayout.findViewById(R.id.redToastMessage);
        greenToastMessage = greenToastLayout.findViewById(R.id.greenToastMessage);

        btnSave.setOnClickListener(v -> {
            if (!(edtID.getText().toString().isEmpty()) && !(edtFullName.getText().toString()).isEmpty()) {
                StudentModalArrayList.add(new StudentModal(edtID.getText().toString(), edtFullName.getText().toString()));
                adapter.notifyItemInserted(StudentModalArrayList.size());
                saveData();

                greenToastMessage.setText("Saved Successfully");
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.TOP, 0, 150);
                toast.setView(greenToastLayout);
                toast.show();

            }
        });

        btnDisplay.setOnClickListener(v -> {
            Intent intent = new Intent(this, DisplayActivity.class);
            startActivity(intent);
        });

        btnDeleteAll.setOnClickListener(v -> {
            deleteAll();
        });

        btnUpdate.setOnClickListener(v -> {
            Intent intent1 = new Intent(this, UpdateActivity.class);
            startActivity(intent1);
        });

        btnSearch.setOnClickListener(v -> {
            Intent intent2 = new Intent(this, SearchActivity.class);
            startActivity(intent2);
        });
        btnDelete.setOnClickListener(v -> {
            Intent intent3 = new Intent(this, DeleteActivity.class);
            startActivity(intent3);
        });
    }
    private void deleteAll() {
        StudentModalArrayList.clear();

        SharedPreferences sharedPreferences = getSharedPreferences(STUD_PREF_NAME, MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        gson = new Gson();

        String json = gson.toJson(StudentModalArrayList);

        editor.putString(MainActivity.STUD_PREF_KEY, json);

        editor.apply();

        redToastMessage.setText("Data Cleared");
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.TOP, 0, 150);
        toast.setView(redToastLayout);
        toast.show();
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

        gson = new Gson();

        String json = gson.toJson(StudentModalArrayList);

        editor.putString(STUD_PREF_KEY, json);

        editor.apply();

        edtID.setText("");
        edtFullName.setText("");
    }
}
