package com.example.mareu.Controler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.example.mareu.Model.Meeting;
import com.example.mareu.Services.ApiService;
import com.example.mareu.Services.DI;
import com.example.mareu.databinding.ActivityCreateMeetingBinding;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;


public class CreateMeetingActivity extends AppCompatActivity {

    private ApiService mApiService;

    private ActivityCreateMeetingBinding binding;

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
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mParticipants);
        meetingParticipants.setAdapter(adapter1);
        meetingParticipants.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        AutoCompleteTextView meetingHour = (AutoCompleteTextView) binding.meetingHourInput;
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mHours);
        meetingHour.setAdapter(mAdapter);
        Button createBtn = binding.createBtn;
        setContentView(view);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Meeting meeting = new Meeting(
                            Objects.requireNonNull(meetingName.getEditText()).getText().toString(),
                            meetingParticipants.toString(),
                            meetingHour.toString(),
                            meetingRoom.toString()
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
            "5h","5h45","6h30","7h15","8h","8h45","9h30","10h15","11h","11h45","12h30","13h15","14h","14h45","15h30","16h15","17h","17h45","18h30","19h15","20h","20h45","21h30","22h15","23h","23h45"
    };
    private static final String[] mParticipants = new String[] {
            "Mario@lamzone.com", "Peach@lamzone.com", "Luigi@lamzone.com", "Bowser@lamzone.com", "Wario@lamzone.com", "Waluigi@lamzone.com", "Koopa@lamzone.com", "Goomba@lamzone.com", "Daisy@lamzone.com"
    };
}