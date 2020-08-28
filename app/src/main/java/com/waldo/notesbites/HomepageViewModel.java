package com.waldo.notesbites;

import android.app.Application;
import android.util.Log;

import java.util.List;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class HomepageViewModel extends AndroidViewModel {

    private SubjectsRepository subjectsRepository;
    private LiveData<List<Subject>> allSubjects;
    private LiveData<List<Subject>> allSubjectsSelected;
    private ModulesRepository modulesRepository;
    private LiveData<List<Module>> recentModules;



    public HomepageViewModel(@NonNull Application application){
        super(application);
        subjectsRepository = new SubjectsRepository(application);
        allSubjects = subjectsRepository.getAllSubjects();
        allSubjectsSelected = subjectsRepository.getAllSubjectsSelected();
        modulesRepository = new ModulesRepository(application);

        recentModules = new MutableLiveData<List<Module>>(){};

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


    public void setRecentModulesBySubjectID(int subjectID){
        recentModules = modulesRepository.getRecentModulesBySubjectID(subjectID);
    }


}
