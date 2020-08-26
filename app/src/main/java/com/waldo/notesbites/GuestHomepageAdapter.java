package com.waldo.notesbites;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GuestHomepageAdapter extends RecyclerView.Adapter<GuestHomepageAdapter.GuestHomepageHolder> {
    private List<Subject> subjects = new ArrayList<>();
    private SelectSubjectAdapter.OnItemClickListener listener;

    @NonNull
    @Override
    public GuestHomepageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_select_subjects_single_subject, parent, false);
        return new GuestHomepageAdapter.GuestHomepageHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GuestHomepageHolder holder, int position) {
        Subject currentSubject = subjects.get(position);
        holder.textViewTitle.setText(currentSubject.getName());
        holder.textViewDescription.setText(currentSubject.getDescription());
        holder.infoButton.setId(currentSubject.getSubjectID());  // the line on the left is a masterpiece
        /*boolean selected = currentSubject.isSelected();
        if(selected){
            holder.selectSubjectLayout.setBackgroundResource(R.color.colorAccent);
        }else{
            holder.selectSubjectLayout.setBackgroundResource(R.color.cardview_light_background);
        }*/
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
        notifyDataSetChanged(); // we will replace it later with something better
    }

    class GuestHomepageHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;
        private ImageButton infoButton;
        private LinearLayout selectSubjectLayout;

        public GuestHomepageHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.select_subjects_subject_name);
            textViewDescription = itemView.findViewById(R.id.select_subjects_description);
            selectSubjectLayout = itemView.findViewById(R.id.select_subject_linear_layout);
            infoButton = itemView.findViewById(R.id.select_subjects_info);

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

    public void setOnItemClickListener(SelectSubjectAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }


}


