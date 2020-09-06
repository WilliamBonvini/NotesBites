package com.waldo.notesbites.presentation.homepage;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.waldo.notesbites.R;
import com.waldo.notesbites.data.model.Subject;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomepageSubjectsAdapter extends RecyclerView.Adapter<HomepageSubjectsAdapter.HomepageHolder> {
    private List<Subject> subjects = new ArrayList<>();
    private OnItemClickListener listener;
    private int clickedPosition = -1;



    @NonNull
    @Override
    public HomepageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_guest_homepage_single_subject, parent, false);
        return new HomepageHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull HomepageHolder holder, final int position) {
        Subject currentSubject = subjects.get(position);
        holder.textViewTitle.setText(currentSubject.getName());
        holder.textViewDescription.setText(currentSubject.getDescription());

        if (clickedPosition == position){
            holder.homepageLayout.setBackgroundColor(0x360E0305);
        }
        else{
            holder.homepageLayout.setBackgroundColor(0x00000000);
        }









    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
        notifyDataSetChanged();
    }



    class HomepageHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;
        private LinearLayout homepageLayout;
        private RecyclerView homepageSubjectsRecyclerView;


        public HomepageHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.guest_homepage_subject_name);
            textViewDescription = itemView.findViewById(R.id.guest_homepage_description);
            homepageLayout = itemView.findViewById(R.id.guest_homepage_linear_layout);
            homepageSubjectsRecyclerView = itemView.findViewById(R.id.homepage_subjects_recycler_view);
            //textViewTitle.setBackgroundColor(R.color.nb_darkgrey);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if(clickedPosition==position){
                        clickedPosition=-1;
                        notifyDataSetChanged();
                        return;
                    }
                    clickedPosition = position;
                    notifyDataSetChanged();


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


