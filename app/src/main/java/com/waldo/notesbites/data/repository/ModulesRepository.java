package com.waldo.notesbites.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.waldo.notesbites.data.local.NBDatabase;
import com.waldo.notesbites.data.daos.ModuleDao;
import com.waldo.notesbites.data.model.Module;

import java.util.Date;
import java.util.List;

import androidx.lifecycle.LiveData;

public class ModulesRepository {
    private ModuleDao moduleDao;

    public ModulesRepository(Application application) {
        NBDatabase database = NBDatabase.getInstance(application);
        moduleDao = database.moduleDao();
    }

    public LiveData<List<Module>> getAllModulesBySubjectID(int subjectID){
        return moduleDao.getAllModulesBySubjectID(subjectID);
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

    public LiveData<String> getModuleContentByModuleID(int moduleID){
        return moduleDao.getModuleContentByModuleID(moduleID);
    }

    public void updateLastOpenedDate(int moduleID){
        new UpdateLastOpenedDateAsyncTask(moduleDao).execute(moduleID);
    }



    /////////////////////////// ASYNC TASKS ////////////////////////////////////

    private static class UpdateLastOpenedDateAsyncTask extends AsyncTask<Integer, Void, Void> {
        private ModuleDao moduleDao;

        private UpdateLastOpenedDateAsyncTask(ModuleDao moduleDao) {
            this.moduleDao = moduleDao;
        }

        @Override
        protected Void doInBackground(Integer... moduleIDs) {
            Date lastOpenedDate = new Date();
            moduleDao.updateLastOpenedDate(moduleIDs[0],lastOpenedDate);
            return null;
        }
    }
}




