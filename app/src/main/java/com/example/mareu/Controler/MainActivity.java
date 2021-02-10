package com.example.mareu.Controler;

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
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMeetingListBinding binding;
    
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMeetingListBinding binding = ActivityMeetingListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        FloatingActionButton fab = binding.fab;
        ApiService apiService = DI.getMeetingApiService();
        RecyclerView recyclerView = binding.mainRecyclerView;
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
                updateList();
            }
        });
    }

    private void updateList() {

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}