package com.waldo.notesbites.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.waldo.notesbites.data.local.NBDatabase;
import com.waldo.notesbites.data.utils.QuizIDAndQuizQuestionCountTuple;
import com.waldo.notesbites.data.model.QuizQuestion;
import com.waldo.notesbites.data.daos.QuizDao;

import androidx.lifecycle.LiveData;

import java.util.List;


public class QuizRepository {
    private final QuizDao quizDao;
    private List<QuizQuestion> quizQuestionList;

    public QuizRepository(Application application) {
        NBDatabase database = NBDatabase.getInstance(application);
        quizDao = database.quizDao();
        quizQuestionList = null;
    }




    public LiveData<String> getModuleNameByID(int moduleID){
        return quizDao.getModuleNameByModuleID(moduleID);
    }

    public LiveData<Integer> getQuizIDByModuleID(int moduleID) {
        return quizDao.getQuizID_liveData_byModuleID(moduleID);
    }

    public LiveData<List<QuizQuestion>> getQuestionsByQuizID(int quizID) {
        return quizDao.getQuestionsByQuizID(quizID);
    }


    public LiveData<QuizQuestion> getCurrentQuizQuestionByQuizIDAndPriority(int quizID,int priority){
        return quizDao.getCurrentQuizQuestionByQuizIDAndPriority(quizID,priority);

    }

    public List<QuizQuestion> getQuizQuestionsListByQuizID(int quizID){
        try {
            if(quizQuestionList == null){
                return new GetQuizQuestionsListByQuizIDAsyncTask(quizDao).execute(quizID).get();
            }
            return quizQuestionList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public LiveData<QuizIDAndQuizQuestionCountTuple> getQuizIDAndNumberOfQuestionsByModuleID(int moduleID) {
        return quizDao.getQuizIDAndNumberOfQuestionsByModuleID(moduleID);
    }

    public LiveData<Integer> getCorrectQuestionsByModuleID(int moduleID) {
        return quizDao.getCorrectQuestionsByModuleID(moduleID);
    }

    public void updateCorrectQuestionsByModuleID(int moduleID, int highscoreNew) {
        new UpdateCorrectQuestionsByModuleIDAsyncTask(quizDao).execute(moduleID,highscoreNew);
    }








    /////////////////////////// ASYNC TASKS ////////////////////////////////

    private static class GetQuizQuestionsListByQuizIDAsyncTask extends AsyncTask<Integer, Void,List<QuizQuestion>>
    {
        private QuizDao quizDao;
        private GetQuizQuestionsListByQuizIDAsyncTask(QuizDao quizDao){
            this.quizDao = quizDao;
        }
        @Override
        protected List<QuizQuestion> doInBackground(Integer... quizID) {
            return quizDao.getQuizQuestionsListByQuizID(quizID[0]);
        }
    }




    private static class UpdateCorrectQuestionsByModuleIDAsyncTask extends AsyncTask<Integer, Void, Void> {
        private QuizDao quizDao;



        private UpdateCorrectQuestionsByModuleIDAsyncTask(QuizDao quizDao) {
            this.quizDao = quizDao;
        }

        @Override
        protected Void doInBackground(Integer... params) {
            quizDao.updateCorrectQuestionsByModuleID(params[0],params[1]);
            return null;
        }
    }



}