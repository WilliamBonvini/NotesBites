package com.waldo.notesbites;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ModulesRepository {
    private ModuleDao moduleDao;

    public ModulesRepository(Application application) {
        NBDatabase database = NBDatabase.getInstance(application);
        moduleDao = database.moduleDao();
    }

    public LiveData<List<Module>> getAllModulesBySubjectName(String subjectName){
        return moduleDao.getAllModulesBySubjectName(subjectName);
    }

    public LiveData<List<String>> getModuleNamesBySubjectID(int subjectID){
        return moduleDao.getModuleNamesBySubjectID(subjectID);
    }


    public LiveData<List<Module>> getModulesBySubjectID(int subjectID) {
        return moduleDao.getModulesBySubjectID(subjectID);
    }

    public LiveData<Module> getModuleByModuleID(int moduleID){
        return moduleDao.getModuleByModuleID(moduleID);
    }

    public LiveData<List<Module>> getRecentModulesBySubjectID(int subjectID) {
        return moduleDao.getRecentModulesBySubjectID(subjectID);
    }
}
