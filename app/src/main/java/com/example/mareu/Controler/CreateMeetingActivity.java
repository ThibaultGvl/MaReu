package com.example.mareu.Controler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.example.mareu.Model.Meeting;
import com.example.mareu.Services.ApiService;
import com.example.mareu.Services.DI;
import com.example.mareu.databinding.ActivityCreateMeetingBinding;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;


public class CreateMeetingActivity extends AppCompatActivity {

    private ApiService mApiService = DI.getMeetingApiService();

    private ActivityCreateMeetingBinding binding;

    private int Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getMeetingApiService();
        binding = ActivityCreateMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        AutoCompleteTextView meetingRoom = (AutoCompleteTextView) binding.meetingRoomInput;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mRooms);
        meetingRoom.setAdapter(adapter);
        TextInputLayout meetingName = binding.meetingNameLyt;
        MultiAutoCompleteTextView meetingParticipants = (MultiAutoCompleteTextView) binding.meetingParticipantsInput;
        meetingParticipants.setThreshold(1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mParticipants);
        meetingParticipants.setAdapter(adapter1);
        meetingParticipants.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        AutoCompleteTextView meetingHour = (AutoCompleteTextView) binding.meetingHourInput;
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mHours);
        meetingHour.setAdapter(mAdapter);
        AutoCompleteTextView meetingDate = binding.meetingDate;
        Button createBtn = binding.createBtn;
        meetingRoom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                createBtn.setEnabled(s.length() > 0);
            }
        });
        setContentView(view);
        meetingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dpd = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String date = dayOfMonth + "/" + (month+1)+ "/" + year;
                        binding.meetingDate.setText(date);
                    }
                },year, month, dayOfMonth);
                dpd.show();
            }
        });
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Meeting meeting = new Meeting(
                            Objects.requireNonNull(meetingHour.getText().toString()),
                            Objects.requireNonNull(meetingName.getEditText()).getText().toString(),
                            Objects.requireNonNull(meetingParticipants.getText().toString()),
                            Objects.requireNonNull(meetingRoom.getText().toString()),
                            meetingDate.getText().toString()
                    );
                    mApiService.createMeeting(meeting);
                    Toast.makeText(v.getContext(), "Votre Réunion a bien été créée !", Toast.LENGTH_SHORT).show();
                    finish();
                }
        });
    }

    private static final String[] mRooms = new String[] {
            "101", "102", "103", "104", "105", "106", "107", "108", "109", "110"
    };
    private static final String[] mHours = new String[] {
            "5h00","5h45","6h30","7h15","8h00","8h45","9h30","10h15","11h00","11h45","12h30","13h15","14h00","14h45","15h30","16h15","17h00","17h45","18h30","19h15","20h00","20h45","21h30","22h15","23h","23h45"
    };
    private static final String[] mParticipants = new String[] {
            "Mario@lamzone.com", "Peach@lamzone.com", "Luigi@lamzone.com", "Bowser@lamzone.com", "Wario@lamzone.com", "Waluigi@lamzone.com", "Koopa@lamzone.com", "Goomba@lamzone.com", "Daisy@lamzone.com", "Yoshi@lamzone.com"
    };
}