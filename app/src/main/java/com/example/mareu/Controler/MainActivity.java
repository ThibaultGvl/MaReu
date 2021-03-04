package com.example.mareu.Controler;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.OrientationEventListener;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMeetingListBinding binding;

    private final ApiService apiService = DI.getMeetingApiService();

    private List<Meeting> mMeetings = new ArrayList<>();

    private MeetingRecyclerViewAdapter adapter;

    private Configuration config;

    private String RoomPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMeetingListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        FloatingActionButton fab = binding.fab;
        RecyclerView recyclerView = binding.mainRecyclerView;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        adapter = new MeetingRecyclerViewAdapter(mMeetings);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        configureSwipeRefreshLayout();
        config = getResources().getConfiguration();
        landscape();
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
                returnResult(apiService.getMeetings());
            }
        });
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
                    public void onClick(DialogInterface dialog, int which) {}
                })
                .setPositiveButton(getResources().getText(R.string.OK), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        returnResult(apiService.getMeetingsByRoom(RoomPosition));
                        Toast.makeText(getBaseContext(), "Voici les Réunions ayant lieu en Salle " + RoomPosition, Toast.LENGTH_SHORT).show();
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

    public void showDatePicker(MenuItem Item) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                String dateToShow = dayOfMonth + "/" + (month+1)+ "/" + year;
                Toast.makeText(getBaseContext(), "Voici les Réunions ayant Lieu le " + dateToShow, Toast.LENGTH_SHORT).show();
                returnResult(apiService.getMeetingsByDate(dateToShow));
            }
        },year, month, dayOfMonth);
        datePickerDialog.show();
    }



    @Override
    protected void onResume() {
        super.onResume();
        returnResult(apiService.getMeetings());
    }

    public void getAllMeetings(MenuItem Item) {
        returnResult(apiService.getMeetings());
    }

    public void returnResult (List<Meeting> meetings) {
        mMeetings.clear();
        mMeetings.addAll(meetings);
        adapter.notifyDataSetChanged();
    }

    public void landscape() {
        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mMeetings.clear();
            apiService.getMeetings().clear();
        }
    }

    private static final String[] mRooms = new String[] {
            "101", "102", "103", "104", "105", "106", "107", "108", "109", "110"
    };

    int CheckedItem = 0;
}