package com.example.axiom;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.text.DateFormat;
import java.util.Calendar;

public class NextActivity extends AppCompatActivity {

    private TextView textViewFirstName;
    private CardView cardViewDate;
    private Button buttonPickDate;
    private CardView cardViewTime;
    private Button buttonPickTime;
    private CardView cardViewDescription;
    private EditText editTextReminderDescription;
    private CardView cardViewButton;
    private Button buttonSaveReminder;

    private int selectedYear, selectedMonth, selectedDay, selectedHour, selectedMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        // Initialize UI components
        textViewFirstName = findViewById(R.id.textViewFirstName);
        cardViewDate = findViewById(R.id.cardViewDate);
        buttonPickDate = findViewById(R.id.buttonPickDate);
        cardViewTime = findViewById(R.id.cardViewTime);
        buttonPickTime = findViewById(R.id.buttonPickTime);
        cardViewDescription = findViewById(R.id.cardViewDescription);
        editTextReminderDescription = findViewById(R.id.editTextReminderDescription);
        cardViewButton = findViewById(R.id.cardViewButton);
        buttonSaveReminder = findViewById(R.id.buttonSaveReminder);

        // Retrieve the first name from the intent
        String firstName = getIntent().getStringExtra("firstName");

        // Set the first name in the TextView
        textViewFirstName.setText("Hello, " + firstName + "!");

        // Set OnClickListener for date, time, and Save Reminder buttons
        cardViewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        cardViewTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog();
            }
        });

        cardViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveReminder();
            }
        });
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                NextActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        selectedYear = year;
                        selectedMonth = month;
                        selectedDay = day;
                        updateDateButtonText();
                    }
                },
                selectedYear, selectedMonth, selectedDay);

        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                NextActivity.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        selectedHour = hour;
                        selectedMinute = minute;
                        updateTimeButtonText();
                    }
                },
                selectedHour, selectedMinute, false);

        timePickerDialog.show();
    }

    private void updateDateButtonText() {
        buttonPickDate.setText(String.format("%d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay));
    }

    private void updateTimeButtonText() {
        buttonPickTime.setText(String.format("%02d:%02d", selectedHour, selectedMinute));
    }

    private void saveReminder() {
        String description = editTextReminderDescription.getText().toString();
        String date = buttonPickDate.getText().toString();
        String time = buttonPickTime.getText().toString();

        // You can handle saving the reminder data to your storage (e.g., database) here
        // For example, you might want to create a Reminder object and save it to Firebase or SQLite
    }
}
