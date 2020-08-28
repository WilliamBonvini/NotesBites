package com.waldo.notesbites;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class NotesViewModel extends AndroidViewModel {

    private ModulesRepository modulesRepository;
    private LiveData<Module> currentModule;
    public NotesViewModel(@NonNull Application application) {
        super(application);
        modulesRepository = new ModulesRepository(application);

    }


    public void setModuleByModuleID(int moduleID){
        currentModule = modulesRepository.getModuleByModuleID(moduleID);
    }

    public LiveData<Module> getCurrentModule(){
        return currentModule;
    }
}
