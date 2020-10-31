package com.codies.ruqyahcounselling.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codies.ruqyahcounselling.Models.Patient;
import com.codies.ruqyahcounselling.R;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;

public class CounsellorChatsAdapter extends RecyclerView.Adapter<CounsellorChatsAdapter.ChatViewHolder>{

    ChatClickListener clickListener;
    Context context;
    List<Patient> userList;

    public CounsellorChatsAdapter(Context context, ChatClickListener clickListener) {
        this.context = context;
        this.clickListener = clickListener;
        userList = new ArrayList<>();
    }

    public void setList(List<Patient> patientList) {
        this.userList = patientList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chats_counsellor, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        holder.user.setText(userList.get(position).getName());
        Glide.with(context).load(userList.get(position).getImageUrl()).into(holder.circularImageView);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {
        CircularImageView circularImageView;
        TextView user;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            circularImageView = itemView.findViewById(R.id.userIMMG);
            user = itemView.findViewById(R.id.userNAME);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onClick(userList.get(getAdapterPosition()).getUserId(), userList.get(getAdapterPosition()).getName(), userList.get(getAdapterPosition()).getImageUrl());
                }
            });
        }
    }

}
