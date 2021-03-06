package com.example.mareu.Controler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.example.mareu.Model.Meeting;
import com.example.mareu.R;
import com.example.mareu.Services.ApiService;
import com.example.mareu.Services.DI;
import com.example.mareu.databinding.ActivityCreateMeetingBinding;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Objects;

import top.defaults.colorpicker.ColorPickerPopup;


public class CreateMeetingActivity extends AppCompatActivity {

    private ApiService mApiService = DI.getMeetingApiService();

    private ActivityCreateMeetingBinding binding;

    private TextInputEditText meetingName;

    private MultiAutoCompleteTextView meetingParticipants;

    private AutoCompleteTextView meetingHour;

    private AutoCompleteTextView meetingDate;

    private AutoCompleteTextView meetingRoom;

    private AutoCompleteTextView meetingColor;

    private Button createBtn;

    private int mColorChoose;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getMeetingApiService();
        binding = ActivityCreateMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        meetingRoom = binding.meetingRoomInput;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mRooms);
        meetingRoom.setAdapter(adapter);
        meetingName = binding.meetingNameInput;
        meetingParticipants = binding.meetingParticipantsInput;
        meetingParticipants.setThreshold(1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mParticipants);
        meetingParticipants.setAdapter(adapter1);
        meetingParticipants.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        meetingHour = binding.meetingHourInput;
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mHours);
        meetingHour.setAdapter(mAdapter);
        meetingDate = binding.meetingDate;
        meetingColor = binding.meetingColor;
        createBtn = binding.createBtn;
        mColorChoose = 0xFAAC3CC7;
        inputComplete();
        setContentView(view);
        meetingColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorPicker();
            }
        });
        meetingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker();
            }
        });
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    createMeeting();
                }
        });
    }

    public void createMeeting() {
        Meeting meeting = new Meeting(
                mColorChoose,
                Objects.requireNonNull(meetingHour.getText().toString()),
                Objects.requireNonNull(meetingName.getText()).toString(),
                Objects.requireNonNull(meetingParticipants.getText().toString()),
                Objects.requireNonNull(meetingRoom.getText().toString()),
                meetingDate.getText().toString()
        );

        mApiService.createMeeting(meeting);
        Toast.makeText(this, getString(R.string.create_meeting_toast), Toast.LENGTH_SHORT).show();
        finish();
    }

    public void inputComplete() {
        meetingRoom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {createBtn.setEnabled(true);}});
    }

    public void colorPicker() {
        new ColorPickerPopup.Builder(this)
                .enableBrightness(true)
                .enableAlpha(true)
                .showIndicator(true)
                .showValue(true)
                .build()
                .show(
                        new ColorPickerPopup.ColorPickerObserver() {
                            @Override
                            public void
                            onColorPicked(int color) {
                                mColorChoose = color;
                                meetingColor.setBackgroundColor(mColorChoose);
                            }
                        });
    }


    public void datePicker() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "/" + (month+1)+ "/" + year;
                binding.meetingDate.setText(date);
            }
        },year, month, dayOfMonth);
        dpd.show();
    }

    private static final String[] mRooms = new String[] {
            "101", "102", "103", "104", "105", "106", "107", "108", "109", "110"
    };
    private static final String[] mHours = new String[] {
            "5h00","5h45","6h30","7h15","8h00","8h45","9h30","10h15","11h00","11h45","12h30","13h15","14h00","14h45","15h30","16h15","17h00","17h45","18h30","19h15","20h00","20h45","21h30","22h15","23h","23h45"
    };
    private static final String[] mParticipants = new String[] {
            "Mario@lamzone.com", "Peach@lamzone.com", "Luigi@lamzone.com", "Bowser@lamzone.com", "Wario@lamzone.com", "Waluigi@lamzone.com", "Koopa@lamzone.com", "Goomba@lamzone.com", "Daisy@lamzone.com", "Yoshi@lamzone.com", "Lakitu@lamzone.com"
    };
}