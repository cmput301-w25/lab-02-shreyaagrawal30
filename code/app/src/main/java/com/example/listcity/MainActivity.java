package com.example.listcity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    EditText cityInput;
    Button addCity, deleteCity, confirmButton;
    String selectedCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        cityList = findViewById(R.id.city_list);
        cityInput = findViewById(R.id.city_input);
        addCity = findViewById(R.id.add_button);
        deleteCity = findViewById(R.id.delete_button);
        confirmButton = findViewById(R.id.confirm_button);



        String []cities = {"Edmonton","Vancouver","Moscow","Sydney","Berlin","Vienna","Tokyo","Beijing","Osaka","New Delhi"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,dataList);
        cityList.setAdapter(cityAdapter);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = cityInput.getText().toString().trim();
                if (!cityName.isEmpty()) {
                    dataList.add(cityName);
                    cityAdapter.notifyDataSetChanged(); 
                    cityInput.setText(""); 
                    Toast.makeText(MainActivity.this, cityName + " added to the list!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a city name", Toast.LENGTH_SHORT).show();
                }
            }
        });


        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedCity = dataList.get(position);
                Toast.makeText(MainActivity.this, "Selected: " + selectedCity, Toast.LENGTH_SHORT).show();
            }
        });


        deleteCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedCity != null) {
                    dataList.remove(selectedCity); 
                    cityAdapter.notifyDataSetChanged(); 
                    selectedCity = null; 
                } else {
                    Toast.makeText(MainActivity.this, "Please select a city to delete", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
