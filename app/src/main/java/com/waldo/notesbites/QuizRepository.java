package com.waldo.notesbites;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;



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
                //return quizDao.getQuizQuestionsListByQuizID(quizID);
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



}