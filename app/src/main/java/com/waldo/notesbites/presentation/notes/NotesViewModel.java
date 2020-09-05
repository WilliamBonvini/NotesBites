package com.waldo.notesbites.presentation.notes;

import android.app.Application;

import com.waldo.notesbites.data.repository.ModulesRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class NotesViewModel extends AndroidViewModel {

    private ModulesRepository modulesRepository;
    private LiveData<String> currentModuleMdContentPath;
    public NotesViewModel(@NonNull Application application) {
        super(application);
        modulesRepository = new ModulesRepository(application);



    }


    public void setModuleMdContentPathByModuleID(int moduleID){
        currentModuleMdContentPath = modulesRepository.getModuleContentByModuleID(moduleID);
    }

    public LiveData<String> getCurrentModuleMdContentPath(){
        return currentModuleMdContentPath;
    }
}
