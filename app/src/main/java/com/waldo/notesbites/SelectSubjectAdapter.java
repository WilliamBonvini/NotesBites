package com.waldo.notesbites;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class SelectSubjectAdapter extends RecyclerView.Adapter<SelectSubjectAdapter.SelectSubjectHolder> {
    private List<Subject> subjects = new ArrayList<>();
    private OnItemClickListener listener;


    @NonNull
    @Override
    public SelectSubjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_select_subjects_single_subject, parent, false);
        return new SelectSubjectHolder(itemView);
    }

    /**
     * this is where we take care of taking the data from the java object into the views of our holder (name and description into the relative textviews)
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull SelectSubjectHolder holder, int position) {
        Subject currentSubject = subjects.get(position);
        holder.textViewTitle.setText(currentSubject.getName());

        holder.textViewDescription.setText(currentSubject.getDescription());
        holder.infoButton.setId(currentSubject.getSubjectID());  // the line on the left is a masterpiece
        boolean selected = currentSubject.isSelected();
        if(selected){
            holder.selectSubjectLayout.setBackgroundResource(R.color.nb_new_color);
            Animation anim = new AlphaAnimation(0.6f, 1.0f);
            anim.setDuration(500); //You can manage the blinking time with this parameter
            anim.setStartOffset(20);
            anim.setRepeatMode(Animation.REVERSE);
            anim.setRepeatCount(Animation.INFINITE);
            holder.textViewTitle.startAnimation(anim);
        }else{
            holder.selectSubjectLayout.setBackgroundResource(R.color.nb_white);
        }


    }

    /**
     * since we want to always display as many items as are currently stored in the database we:
     *
     * @return the size of the subjects array
     */
    @Override
    public int getItemCount() {
        return subjects.size();
    }

    /**
     * put the list of subjects into the RecyclerView
     *
     * @param subjects
     */
    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
        notifyDataSetChanged(); // we will replace it later with something better
    }

    class SelectSubjectHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;
        private ImageButton infoButton;
        private LinearLayout selectSubjectLayout;

        public SelectSubjectHolder(View itemView) {
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

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
