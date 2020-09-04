package com.waldo.notesbites;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class SubjectsRepository {
    private SubjectDao subjectDao;
    private LiveData<List<Subject>> allSubjects;
    private LiveData<List<Subject>> allSubjectsSelected;

    public SubjectsRepository(Application application) {
        NBDatabase database = NBDatabase.getInstance(application);
        subjectDao = database.subjectDao();
        allSubjects = subjectDao.getAllSubjects();
        allSubjectsSelected = subjectDao.getAllSubjectsSelected();
    }

    public void insert(Subject subject) {
        new InsertSubjectAsyncTask(subjectDao).execute(subject);

    }

    public void update(Subject subject) {
        new UpdateSubjectAsyncTask(subjectDao).execute(subject);

    }

    public void delete(Subject subject) {
        new DeleteSubjectAsyncTask(subjectDao).execute(subject);

    }

    public void deleteAllSubjects() {
        new DeleteAllSubjectsAsyncTask(subjectDao).execute();

    }


    public LiveData<List<com.waldo.notesbites.Subject>> getAllSubjects() {
        return allSubjects;

    }
    public LiveData<List<com.waldo.notesbites.Subject>> getAllSubjectsSelected() {
        return allSubjectsSelected;

    }

    public void setSelectedTrue(int subjectID) {
        new SetSelectedTrueAsyncTask(subjectDao).execute(subjectID);
    }

    public void setSelectedFalse(int subjectID) {
        new SetSelectedFalseAsyncTask(subjectDao).execute(subjectID);
    }

    public LiveData<Subject> getSubjectByID(int subjectID) {
        return subjectDao.getSubjectByID(subjectID);
    }

    public LiveData<String> getSubjectNameFromID(int subjectID){
        return subjectDao.getSubjectNameByID(subjectID);
    }

    public LiveData<Integer> getImageIDFromSubjectID(int subjectID) {
        return subjectDao.getImageIDFromID(subjectID);
    }

    public LiveData<String> getDescriptionFromSubjectID(int subjectID) {
        return subjectDao.getDescriptionFromID(subjectID);
    }

    public LiveData<String> gerOverviewFromSubjectID(int subjectID) {
        return subjectDao.getOverviewFromSubjectID(subjectID);
    }


    /////////////////////////// ASYNC TASKS ////////////////////////////////////

    private static class InsertSubjectAsyncTask extends AsyncTask<Subject, Void, Void> {
        private SubjectDao subjectDao;

        private InsertSubjectAsyncTask(SubjectDao subjectDao) {
            this.subjectDao = subjectDao;
        }

        @Override
        protected Void doInBackground(Subject... subjects) {
            subjectDao.insert(subjects[0]);
            return null;
        }
    }

    private static class UpdateSubjectAsyncTask extends AsyncTask<Subject, Void, Void> {
        private SubjectDao subjectDao;

        private UpdateSubjectAsyncTask(SubjectDao subjectDao) {
            this.subjectDao = subjectDao;
        }

        @Override
        protected Void doInBackground(Subject... subjects) {
            subjectDao.update(subjects[0]);
            return null;
        }
    }

    private static class DeleteSubjectAsyncTask extends AsyncTask<Subject, Void, Void> {
        private SubjectDao subjectDao;

        private DeleteSubjectAsyncTask(SubjectDao subjectDao) {
            this.subjectDao = subjectDao;
        }

        @Override
        protected Void doInBackground(Subject... subjects) {
            subjectDao.delete(subjects[0]);
            return null;
        }
    }

    private static class DeleteAllSubjectsAsyncTask extends AsyncTask<Void, Void, Void> {
        private SubjectDao subjectDao;

        private DeleteAllSubjectsAsyncTask(SubjectDao subjectDao) {
            this.subjectDao = subjectDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            subjectDao.deleteAllSubjects();
            return null;
        }
    }


    private static class SetSelectedTrueAsyncTask extends AsyncTask<Integer, Void, Void> {
        private SubjectDao subjectDao;

        private SetSelectedTrueAsyncTask(SubjectDao subjectDao) {
            this.subjectDao = subjectDao;
        }

        @Override
        protected Void doInBackground(Integer... subjectsID) {
            subjectDao.setSelectedTrue(subjectsID[0]);
            return null;
        }

    }


    private static class SetSelectedFalseAsyncTask extends AsyncTask<Integer, Void, Void> {
        private SubjectDao subjectDao;

        private SetSelectedFalseAsyncTask(SubjectDao subjectDao) {
            this.subjectDao = subjectDao;
        }

        @Override
        protected Void doInBackground(Integer... subjectsID) {
            subjectDao.setSelectedFalse(subjectsID[0]);
            return null;
        }

    }

}
