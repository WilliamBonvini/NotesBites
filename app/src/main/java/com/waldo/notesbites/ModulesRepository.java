package com.waldo.notesbites;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.LiveData;

public class ModulesRepository {
    private ModuleDao moduleDao;

    public ModulesRepository(Application application) {
        NBDatabase database = NBDatabase.getInstance(application);
        moduleDao = database.moduleDao();
    }

    public LiveData<List<Module>> getAllModulesBySubjectName(String subjectName){
        return moduleDao.getAllModulesBySubjectName(subjectName);
    }


}
