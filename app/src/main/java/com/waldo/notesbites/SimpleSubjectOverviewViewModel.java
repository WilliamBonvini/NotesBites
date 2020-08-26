package com.waldo.notesbites;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class SimpleSubjectOverviewViewModel extends AndroidViewModel {

    private SubjectsRepository subjectsRepository;

    public SimpleSubjectOverviewViewModel(@NonNull Application application) {
        super(application);
        subjectsRepository = new SubjectsRepository(application);
        
    }
}
