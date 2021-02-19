package com.example.mareu.Controler;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.media.MediaExtractor;
import android.os.Bundle;

import com.example.mareu.Model.Meeting;
import com.example.mareu.R;
import com.example.mareu.Services.ApiService;
import com.example.mareu.Services.DI;
import com.example.mareu.View.MeetingRecyclerViewAdapter;
import com.example.mareu.databinding.ActivityMeetingListBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.nio.file.DirectoryStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMeetingListBinding binding;

    private ApiService apiService = DI.getMeetingApiService();

    private DatePicker mDatePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMeetingListBinding binding = ActivityMeetingListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        FloatingActionButton fab = binding.fab;
        RecyclerView recyclerView = binding.mainRecyclerView;
        ApiService apiService = DI.getMeetingApiService();
        List<Meeting> mMeetings = apiService.getMeetings();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        MeetingRecyclerViewAdapter adapter = new MeetingRecyclerViewAdapter(mMeetings);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        setContentView(view);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateMeetingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void configureSwipeRefreshLayout() {
        binding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                apiService.getMeetings().clear();
                apiService.getMeetings();
            }
        });
    }

    public void showDatePickerDialog(MenuItem item) {
        DialogFragment newFragment = new DatePicker();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public List<Meeting> getFilterMeetings(List<Meeting> meetings) {
        meetings = apiService.getMeetings();
        ArrayList<Meeting> result = new ArrayList<>();

        for (Meeting meeting : meetings) {
            if (meeting.getDate().equals(mDatePicker.getDate())) {
                    result.add(meeting);
            }
                else {
                    return null;
            }
        }
        return result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        getFilterMeetings(apiService.getMeetings());
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}