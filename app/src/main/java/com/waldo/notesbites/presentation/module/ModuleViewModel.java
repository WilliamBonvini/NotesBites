package com.waldo.notesbites.presentation.module;

import android.app.Application;

import com.waldo.notesbites.data.model.Module;
import com.waldo.notesbites.data.repository.ModulesRepository;

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

    public void updateLastOpenedDate(int moduleID){
        modulesRepository.updateLastOpenedDate(moduleID);

    }



}
