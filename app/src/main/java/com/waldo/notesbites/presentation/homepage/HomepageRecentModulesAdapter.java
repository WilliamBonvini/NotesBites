package com.waldo.notesbites.presentation.homepage;

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

public class HomepageRecentModulesAdapter extends RecyclerView.Adapter<HomepageRecentModulesAdapter.HomepageHolder> {
    private OnItemClickListener listener;
    private List<Module> recentModules = new ArrayList<>();

    @NonNull
    @Override
    public HomepageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_recent_modules, parent, false);
        return new HomepageHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HomepageHolder holder, int position) {
        Module recentModule = recentModules.get(position);
        holder.recent_module_name.setText(recentModule.getName());
//        boolean selected = recentModule.isSelected();
//        if(selected){
//            holder.recent_modules_relative_layout.setBackgroundResource(R.color.colorAccent);
//        }else{
//            holder.recent_modules_relative_layout.setBackgroundResource(R.color.cardview_light_background);
//        }

    }

    @Override
    public int getItemCount() {
        return recentModules.size();
    }



    public void setModules(List<Module> recentModules) {
        this.recentModules = recentModules;
        notifyDataSetChanged();

    }

    class HomepageHolder extends RecyclerView.ViewHolder {
        private TextView recent_module_name;
        private TextView recent_module_description;
        private RelativeLayout recent_modules_relative_layout;

        public HomepageHolder(View itemView) {
            super(itemView);
            recent_module_name = itemView.findViewById(R.id.recent_module_name);
            recent_modules_relative_layout = itemView.findViewById(R.id.recent_modules_relative_layout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(recentModules.get(position));
                    }
                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(Module module);
    }

    public void setOnItemClickListener(HomepageRecentModulesAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }


}


