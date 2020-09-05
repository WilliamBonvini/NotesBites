package com.waldo.notesbites.presentation.subjectoverview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.waldo.notesbites.R;
import com.waldo.notesbites.data.model.Module;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SubjectOverviewAdapter extends RecyclerView.Adapter<SubjectOverviewAdapter.SubjectOverviewHolder> {
    private List<Module> modules = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public SubjectOverviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_subject_overview_activity, parent, false);
        return new SubjectOverviewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectOverviewHolder holder, int position) {
        Module currentModule = modules.get(position);
        holder.textViewTitle.setText(currentModule.getName());
        holder.subjectOverviewModuleLayout.setId(currentModule.getModuleID());  // the line on the left is a masterpiece
    }

    @Override
    public int getItemCount() {
        return modules.size();
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
        notifyDataSetChanged(); // we will replace it later with something better
    }

    class SubjectOverviewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private RelativeLayout subjectOverviewModuleLayout;


        public SubjectOverviewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.subject_overview_module_name);
            subjectOverviewModuleLayout = itemView.findViewById(R.id.subject_overview_activity_relative_layout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(modules.get(position));
                    }
                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(Module module);
    }

    public void setOnItemClickListener(SubjectOverviewAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }


}


