package com.example.mareu.Controler;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.mareu.Model.Meeting;
import com.example.mareu.Services.ApiService;
import com.example.mareu.Services.DI;
import com.example.mareu.View.MeetingRecyclerViewAdapter;
import com.example.mareu.events.DatePickerEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DatePicker extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    private static String date;

    private MeetingRecyclerViewAdapter mRecyclerViewAdapter;

    private ApiService apiService = DI.getMeetingApiService();

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, dayOfMonth);
    }

    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
        date = dayOfMonth + "/" + (month+1) + "/" + year;
        Toast.makeText(getContext(), "Voici les réunions ayant lieu le " + date, Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new DatePickerEvent(date));
    }
    public static String getDate() {
        return date;
    }
}
