package com.mlh.goalsops.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mlh.goalsops.Models.Resolution;
import com.mlh.goalsops.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ResolutionsAdapter extends RecyclerView.Adapter<ResolutionsAdapter.ViewHolder> {

    private Context context;

    private List<Resolution> resolutions;

    public ResolutionsAdapter(Context context, List<Resolution> resolutions) {
        this.context = context;
        this.resolutions = resolutions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.resolution_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Resolution resolution = resolutions.get(position);
        holder.titleTV.setText(resolution.getTitle());
        holder.descriptionTV.setText(resolution.getDescription());
        holder.deadlineTV.setText(resolution.getDeadline());

        long timeStamp = resolution.getTimeStamp();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = formatter.format(new Date(timeStamp));
        holder.deadlineTV.setText(dateString);
    }

    @Override
    public int getItemCount() {
        return resolutions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public CardView resCard;
        public CheckBox checkRes;
        public TextView titleTV;
        public TextView dateCreatedTV;
        public TextView descriptionTV;
        public TextView deadlineTV;
        public CardView deadlineCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            resCard = itemView.findViewById(R.id.resolution_card);
            checkRes = itemView.findViewById(R.id.check_resolution);
            titleTV = itemView.findViewById(R.id.title_tv_res);
            dateCreatedTV = itemView.findViewById(R.id.date_tv_res);
            descriptionTV = itemView.findViewById(R.id.description_tv_res);
            deadlineTV = itemView.findViewById(R.id.dead_date_tv_res);
            deadlineCard = itemView.findViewById(R.id.deadline_card_res);


        }
    }
}
