package com.example.axiom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Inside the OnClickListener for the "Save" button
        Button saveButton = findViewById(R.id.button1);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NextActivity.class);

                // Add any data you want to pass to the next activity using intent extras
                intent.putExtra("extra_key", "extra_value");

                // Start the new activity
                startActivity(intent);
                Intent intent2 = getIntent();
                String firstName = intent.getStringExtra("firstName");

// Assuming you have a TextView at the top of your layout with the id textViewFirstName
                TextView textViewFirstName = findViewById(R.id.editTextFirstName);

// Set the first name at the top of the layout
                textViewFirstName.setText(firstName);

            }
        });

    }
}