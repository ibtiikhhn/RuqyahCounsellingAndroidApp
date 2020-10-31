package com.codies.ruqyahcounselling.Utils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codies.ruqyahcounselling.Agora.VideoCallScreenUser;
import com.codies.ruqyahcounselling.Models.Appointment;
import com.codies.ruqyahcounselling.R;

import java.util.ArrayList;
import java.util.List;

public class CounsellorAppointmentsAdapter extends RecyclerView.Adapter<CounsellorAppointmentsAdapter.CounsellorAppointmentViewHolder>{
    List<Appointment> appointmentList;
    Context context;
    AppointmentClickListener onItemClickListener;

    public CounsellorAppointmentsAdapter(Context context, AppointmentClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        this.context = context;
        appointmentList = new ArrayList<>();

    }

    public void setList(List<Appointment> items) {
        appointmentList = new ArrayList<>();
        this.appointmentList = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CounsellorAppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.counsellor_appointment_cardview, parent, false);
        CounsellorAppointmentViewHolder patientAppointmentViewHolder = new CounsellorAppointmentViewHolder(view);
        return patientAppointmentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CounsellorAppointmentViewHolder holder, int position) {
        holder.name.setText(appointmentList.get(position).getStatus());
        holder.time.setText(appointmentList.get(position).getDueTime());
        holder.date.setText(appointmentList.get(position).getDueDate());
        if (appointmentList.get(position).getStatus().equals("declined")) {
            holder.deleteBT.setVisibility(View.INVISIBLE);
            holder.acceptBT.setVisibility(View.INVISIBLE);
            holder.startSession.setVisibility(View.INVISIBLE);
        }
        if (appointmentList.get(position).getStatus().equals("accepted")) {
            holder.startSession.setVisibility(View.VISIBLE);
            holder.deleteBT.setVisibility(View.INVISIBLE);
            holder.acceptBT.setVisibility(View.INVISIBLE);
        }
        if (appointmentList.get(position).getStatus().equals("Pending Approval")) {
            holder.startSession.setVisibility(View.INVISIBLE);
            holder.deleteBT.setVisibility(View.VISIBLE);
            holder.acceptBT.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    public class CounsellorAppointmentViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView time;
        TextView date;
        Button acceptBT;
        Button deleteBT;
        Button startSession;

        public CounsellorAppointmentViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.patientNameAppointment);
            time = itemView.findViewById(R.id.appointmentTimeAppointment);
            date = itemView.findViewById(R.id.appointmentDateAppointment);
            startSession = itemView.findViewById(R.id.startBT);
            acceptBT = itemView.findViewById(R.id.acceptBT);
            deleteBT = itemView.findViewById(R.id.declineBT);
            acceptBT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onAccept(appointmentList.get(getAdapterPosition()).getAppointmentId(),appointmentList.get(getAdapterPosition()).getUserId());
                }
            });
            deleteBT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onDelete(appointmentList.get(getAdapterPosition()).getAppointmentId(), appointmentList.get(getAdapterPosition()).getUserId());
                }
            });
            startSession.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, VideoCallScreenUser.class));
                }
            });

        }
    }
}
