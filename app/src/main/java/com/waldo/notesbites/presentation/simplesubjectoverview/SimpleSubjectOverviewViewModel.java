package com.waldo.notesbites.presentation.simplesubjectoverview;

import android.app.Application;

import com.waldo.notesbites.data.repository.ModulesRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class SimpleSubjectOverviewViewModel extends AndroidViewModel {

    private ModulesRepository modulesRepository;

    public SimpleSubjectOverviewViewModel(@NonNull Application application) {
        super(application);
        modulesRepository = new ModulesRepository(application);
    }

    public LiveData<List<String>> getModuleNamesBySubjectID(int subjectID){
        return modulesRepository.getModuleNamesBySubjectID(subjectID);
    }
}
