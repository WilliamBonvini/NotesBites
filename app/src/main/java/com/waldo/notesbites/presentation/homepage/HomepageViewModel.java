package com.waldo.notesbites.presentation.homepage;

import android.app.Application;
import android.util.Log;

import com.waldo.notesbites.data.model.Module;
import com.waldo.notesbites.data.repository.ModulesRepository;
import com.waldo.notesbites.data.model.Subject;
import com.waldo.notesbites.data.repository.SubjectsRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

public class HomepageViewModel extends AndroidViewModel {

    private SubjectsRepository subjectsRepository;
    private LiveData<List<Subject>> allSubjectsSelected;
    private ModulesRepository modulesRepository;
    private MediatorLiveData<List<Module>> recentModules;
    private LiveData<List<Module>> oldRecentModules;
    private LiveData<List<Module>> currentRecentModules;
    private int oldSubjectID;
    private boolean subjectJustPressed;






    public HomepageViewModel(@NonNull Application application){
        super(application);




        subjectsRepository = new SubjectsRepository(application);
        allSubjectsSelected = subjectsRepository.getAllSubjectsSelected();
        modulesRepository = new ModulesRepository(application);

        recentModules = new MediatorLiveData<List<Module>>(){};

        oldRecentModules = new LiveData<List<Module>>(){};
        currentRecentModules = new LiveData<List<Module>>() {};
        oldSubjectID=-1;

    }



    public void updateRecentModulesMediator(int subjectID){
        if(subjectID != oldSubjectID){
            Log.w("s","entered if");
            recentModules.removeSource(oldRecentModules);
        }


        currentRecentModules = modulesRepository.getRecentModulesBySubjectID(subjectID);
        recentModules.addSource(currentRecentModules, new Observer<List<Module>>() {
            @Override
            public void onChanged(List<Module> b) {

                recentModules.setValue(b);
            }
        });

        Log.w("s","gonna update oldStuff");
        oldRecentModules = currentRecentModules;
        oldSubjectID = subjectID;

    }

    public boolean isSubjectJustPressed(){
        return subjectJustPressed;
    }

    public void setSubjectJustPressed(boolean value){
        subjectJustPressed = value;
    }













    public void insert(Subject subject){
        subjectsRepository.insert(subject);
    }

    public void update(Subject subject){
        subjectsRepository.update(subject);
    }

    public LiveData<List<Subject>> getAllSubjectsSelected(){
        return allSubjectsSelected;
    }



    public LiveData<List<Module>> getRecentModulesToBeDisplayed(){
        return recentModules;
    }


    /*
    public void setRecentModulesBySubjectID(int subjectID){
        recentModules = modulesRepository.getRecentModulesBySubjectID(subjectID);
    }

     */


}