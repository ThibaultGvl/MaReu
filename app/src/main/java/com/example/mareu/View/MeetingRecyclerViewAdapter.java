package com.example.mareu.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.Model.Meeting;
import com.example.mareu.Services.ApiService;
import com.example.mareu.Services.DI;
import com.example.mareu.databinding.FragmentMeetingItemBinding;

import java.util.List;

public class MeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.MeetingViewHolder> {

    private final List<Meeting> mMeetings;

    public ApiService mApiService = DI.getMeetingApiService();

    private FragmentMeetingItemBinding binding;

    public MeetingRecyclerViewAdapter(List<Meeting> mMeetings) {
        this.mMeetings = mMeetings;
    }

    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = (FragmentMeetingItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        return new MeetingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final MeetingViewHolder holder, int position) {
     Meeting meeting = mMeetings.get(position);
     holder.updateWithMeeting(this.mMeetings.get(position));
     holder.deletebtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             mApiService.deleteMeeting(meeting);
             Toast.makeText(v.getContext(), "Cette Réunion a bien été supprimée !", Toast.LENGTH_SHORT).show();
             notifyItemRemoved(position);
         }
     });
    }

    @Override
    public int getItemCount() {
        return this.mMeetings.size();
    }

    public class MeetingViewHolder extends RecyclerView.ViewHolder {

        private ImageButton deletebtn = binding.deleteBtn;

        public MeetingViewHolder(FragmentMeetingItemBinding fragmentMeetingItemBinding) {
            super(fragmentMeetingItemBinding.getRoot());
        }

        public void updateWithMeeting(@NonNull Meeting meeting){
            String information  = meeting.getName() + " - " + meeting.getHour() + " - " + meeting.getRoom() + " - " + meeting.getDate();
            binding.meetingsInformation.setText(information);
            if(meeting.getParticipants().endsWith(",")) {
                meeting.getParticipants().substring(1, meeting.getParticipants().length() - 1);
            }
            binding.meetingParticipants.setText(meeting.getParticipants());
        }
    }
}