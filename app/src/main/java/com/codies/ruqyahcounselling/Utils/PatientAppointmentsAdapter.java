package com.codies.ruqyahcounselling.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codies.ruqyahcounselling.Models.Appointment;
import com.codies.ruqyahcounselling.R;

import java.util.ArrayList;
import java.util.List;

public class PatientAppointmentsAdapter extends RecyclerView.Adapter<PatientAppointmentsAdapter.PatientAppointmentViewHolder> {


    List<Appointment> appointmentList;
    Context context;
    OnItemClickListener onItemClickListener;

    public PatientAppointmentsAdapter(Context context, OnItemClickListener onItemClickListener) {
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
    public PatientAppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.patient_appointment_cv, parent, false);
        PatientAppointmentViewHolder patientAppointmentViewHolder = new PatientAppointmentViewHolder(view);
        return patientAppointmentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PatientAppointmentViewHolder holder, int position) {
        holder.date.setText(appointmentList.get(position).getDueDate());
        holder.time.setText(appointmentList.get(position).getDueTime());
        holder.status.setText(appointmentList.get(position).getStatus());
        if (appointmentList.get(position).getStatus().equals("accepted")) {
            holder.beginSession.setVisibility(View.VISIBLE);
        } else {
            holder.beginSession.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    public class PatientAppointmentViewHolder extends RecyclerView.ViewHolder {

        TextView date;
        TextView time;
        TextView status;
        Button beginSession;

        public PatientAppointmentViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.dueDatePatient);
            time = itemView.findViewById(R.id.dueTimePatient);
            status = itemView.findViewById(R.id.appointmentStatusPatient);
            beginSession = itemView.findViewById(R.id.beginSessionBtPatient);

            beginSession.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(appointmentList.get(getAdapterPosition()).getAppointmentId());
                }
            });

        }
    }


}
