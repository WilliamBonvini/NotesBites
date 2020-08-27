package com.waldo.notesbites;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ModuleViewModel extends AndroidViewModel {
    private ModulesRepository modulesRepository;

    public ModuleViewModel(@NonNull Application application) {
        super(application);
        modulesRepository = new ModulesRepository(application);
    }

    public LiveData<Module> getModuleByModuleID(int moduleID){
        return modulesRepository.getModuleByModuleID(moduleID);
    }


}
