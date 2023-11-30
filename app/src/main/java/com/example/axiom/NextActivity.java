package com.example.axiom;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class NextActivity extends AppCompatActivity {
    private DatabaseReference databaseReference;
    Button buttonSaveReminder;
    private TextView textViewFirstName;
    private Button buttonPickDate, buttonPickTime;
    public String firstName, reminderDate, reminderTime, reminderDescription;
    private int selectedYear, selectedMonth, selectedDay, selectedHour, selectedMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        databaseReference = FirebaseDatabase.getInstance().getReference("reminders");

        // Retrieve the data from the intent
        String firstName = getIntent().getStringExtra("extra_key");

        // Set the first name in the TextView at the top
        textViewFirstName = findViewById(R.id.textViewFirstName);
        textViewFirstName.setText("Hello, " + firstName + "!");
        buttonSaveReminder=findViewById(R.id.buttonSaveReminder);
        buttonSaveReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get reminder details, including the first name
                String firstName = getIntent().getStringExtra("extra_key");
                String reminderDate = buttonPickDate.getText().toString();
                String reminderTime = buttonPickTime.getText().toString();
                String reminderDescription = "Your reminder description"; // Set this based on your UI

                // Create a unique key for the reminder using push
                String reminderKey = databaseReference.child("reminders").push().getKey();

                // Create a Reminder object
                Remainder reminder = new Remainder(firstName, reminderDate, reminderTime, reminderDescription);

                // Save the reminder to Firebase
                databaseReference.child("reminders").child(reminderKey).setValue(reminder);

                // Optionally, show a success message or navigate to another activity
                Toast.makeText(NextActivity.this, "Reminder saved successfully", Toast.LENGTH_SHORT).show();
            }
        });













        // Initialize UI components
        buttonPickDate = findViewById(R.id.buttonPickDate);
        buttonPickTime = findViewById(R.id.buttonPickTime);

        // Set OnClickListener for date picking
        buttonPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        // Set OnClickListener for time picking
        buttonPickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog();
            }
        });

        // Rest of your code...
    }

    private void showDatePickerDialog() {
        // Get current date
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create DatePickerDialog and show it
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        selectedYear = year;
                        selectedMonth = month;
                        selectedDay = day;
                        updateDateButtonText();
                    }
                },
                year, month, day);

        datePickerDialog.show();
    }

    private void updateDateButtonText() {
        buttonPickDate.setText(String.format("%d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay));
    }

    private void showTimePickerDialog() {
        // Get current time
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Create TimePickerDialog and show it
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        selectedHour = hourOfDay;
                        selectedMinute = minute;
                        updateTimeButtonText();
                    }
                },
                hour, minute, true);

        timePickerDialog.show();
    }

    private void updateTimeButtonText() {
        buttonPickTime.setText(String.format("%02d:%02d", selectedHour, selectedMinute));
    }




    // Rest of your code...
}
