package com.waldo.notesbites;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomepageSubjectsAdapter extends RecyclerView.Adapter<HomepageSubjectsAdapter.HomepageHolder> {
    private List<Subject> subjects = new ArrayList<>();
    private OnItemClickListener listener;



    @NonNull
    @Override
    public HomepageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_guest_homepage_single_subject, parent, false);
        return new HomepageHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HomepageHolder holder, int position) {
        Subject currentSubject = subjects.get(position);
        holder.textViewTitle.setText(currentSubject.getName());
        holder.textViewDescription.setText(currentSubject.getDescription());
        boolean selected = currentSubject.isSelected();
        if(selected){
            holder.homepageLayout.setBackgroundResource(R.color.colorAccent);
        }else{
            holder.homepageLayout.setBackgroundResource(R.color.cardview_light_background);
        }

    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
        notifyDataSetChanged(); // we will replace it later with something better
    }



    class HomepageHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;
        private LinearLayout homepageLayout;

        public HomepageHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.guest_homepage_subject_name);
            textViewDescription = itemView.findViewById(R.id.guest_homepage_description);
            homepageLayout = itemView.findViewById(R.id.guest_homepage_linear_layout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(subjects.get(position));
                    }
                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(Subject subject);
    }

    public void setOnItemClickListener(HomepageSubjectsAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }


}


