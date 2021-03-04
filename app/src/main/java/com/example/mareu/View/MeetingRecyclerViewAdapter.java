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

    protected FragmentMeetingItemBinding mFragmentMeetingItemBinding;

    public ApiService mApiService = DI.getMeetingApiService();

    public MeetingRecyclerViewAdapter(List<Meeting> mMeetings) {
        this.mMeetings = mMeetings;
    }

    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mFragmentMeetingItemBinding = (FragmentMeetingItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        return new MeetingViewHolder(mFragmentMeetingItemBinding);
    }

    @Override
    public void onBindViewHolder(final MeetingViewHolder holder, int position) {
     Meeting meeting = mMeetings.get(position);
     holder.updateWithMeeting(this.mMeetings.get(position));
     holder.deletebtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             mMeetings.remove(meeting);
             mApiService.deleteMeeting(meeting);
             notifyItemRemoved(position);
             Toast.makeText(v.getContext(), "Cette Réunion a bien été supprimée !", Toast.LENGTH_SHORT).show();
         }
     });
    }

    @Override
    public int getItemCount() {
        return this.mMeetings.size();
    }

    public static class MeetingViewHolder extends RecyclerView.ViewHolder {

        private ImageButton deletebtn;

        protected FragmentMeetingItemBinding mFragmentMeetingItemBinding;

        public MeetingViewHolder(FragmentMeetingItemBinding fragmentMeetingItemBinding) {
            super(fragmentMeetingItemBinding.getRoot());
            mFragmentMeetingItemBinding = fragmentMeetingItemBinding;
            deletebtn = mFragmentMeetingItemBinding.deleteBtn;
        }

        public void updateWithMeeting(@NonNull Meeting meeting){
            String information  = meeting.getName() + " - " + meeting.getHour() + " - " + meeting.getRoom();
            mFragmentMeetingItemBinding.meetingsInformation.setText(information);
            if(meeting.getParticipants().endsWith(", ")) {
                meeting.setParticipants(meeting.getParticipants().substring(0, meeting.getParticipants().length() - 2));
            }
            else if (meeting.getParticipants().endsWith(",")) {
                meeting.setParticipants(meeting.getParticipants().substring(0, meeting.getParticipants().length() - 1));
            }
            mFragmentMeetingItemBinding.meetingParticipants.setText(meeting.getParticipants());
        }
    }
}