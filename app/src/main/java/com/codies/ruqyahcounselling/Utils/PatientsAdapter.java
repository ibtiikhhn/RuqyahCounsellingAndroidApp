package com.codies.ruqyahcounselling.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codies.ruqyahcounselling.Models.Patient;
import com.codies.ruqyahcounselling.R;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;

public class PatientsAdapter extends RecyclerView.Adapter<PatientsAdapter.PatientsViewHolder>{

    List<Patient> patientList;
    Context context;
    OnItemClickListener onItemClickListener;

    public PatientsAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        this.context = context;
        patientList = new ArrayList<>();

    }

    public void setList(List<Patient> items) {
        patientList = new ArrayList<>();
        this.patientList = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PatientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.patients_cv, parent, false);
        PatientsViewHolder viewHolder = new PatientsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PatientsViewHolder holder, int position) {
        Glide.with(context).load(patientList.get(position).getImageUrl()).into(holder.patientIMG);
        holder.name.setText(patientList.get(position).getName());
        holder.gender.setText(patientList.get(position).getGender());
        holder.age.setText(patientList.get(position).getAge());
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }

    public class PatientsViewHolder extends RecyclerView.ViewHolder {

        CircularImageView patientIMG;
        TextView name;
        TextView age;
        TextView gender;
        ImageView more;

        public PatientsViewHolder(@NonNull View itemView) {
            super(itemView);
            patientIMG = itemView.findViewById(R.id.patientImgRv);
            name = itemView.findViewById(R.id.patientNameRv);
            age = itemView.findViewById(R.id.patientAgeRv);
            gender = itemView.findViewById(R.id.patientGenderRv);
            more = itemView.findViewById(R.id.patientDetailRV);

            more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(patientList.get(getAdapterPosition()).getUserId());
                }
            });
        }
    }

}
