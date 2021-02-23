package com.example.mareu.Controler;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.mareu.Model.Meeting;
import com.example.mareu.R;
import com.example.mareu.Services.ApiService;
import com.example.mareu.Services.DI;
import com.example.mareu.View.MeetingRecyclerViewAdapter;
import com.example.mareu.databinding.ActivityMeetingListBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMeetingListBinding binding;

    private ApiService apiService;

    private DatePicker mDatePicker;

    private MeetingRecyclerViewAdapter adapter;

    private String RoomPosition;

    private List<Meeting> meetingsByRoom = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMeetingListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        FloatingActionButton fab = binding.fab;
        RecyclerView recyclerView = binding.mainRecyclerView;
        apiService = DI.getMeetingApiService();
        List<Meeting> mMeetings = apiService.getMeetings();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        adapter = new MeetingRecyclerViewAdapter(mMeetings);
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
        //mRecyclerViewAdapter.getFilterMeetings(mRecyclerViewAdapter.getMeetings());
        //noinspection SimplifiableIfStatement
        if (id == R.id.filter_date) {
            return true;
        }
        if (id == R.id.filter_room) {
            return true;
        }
        if (id == R.id.get_meetings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void showRoomList(MenuItem Item) {
        MaterialAlertDialogBuilder materialAlertDialogBuilder =  new MaterialAlertDialogBuilder(this)
                .setTitle(getResources().getString(R.string.dialog_title))
                .setNeutralButton(getResources().getText(R.string.cancel_dialog), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }
                })

                .setPositiveButton(getResources().getText(R.string.OK), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        apiService.getMeetingsByRoom(meetingsByRoom);
                    }
                })
                .setSingleChoiceItems(mRooms, CheckedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RoomPosition =  mRooms[which];
                    }
                });
        materialAlertDialogBuilder.show();
    }

    public void getAllMeetings(MenuItem Item) {
        apiService.getMeetings();
    }

    public String getRoomPosition() {return RoomPosition;}

    private static final String[] mRooms = new String[] {
            "101", "102", "103", "104", "105", "106", "107", "108", "109", "110"
    };

    int CheckedItem = -1;
}