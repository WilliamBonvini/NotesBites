package com.waldo.notesbites;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class HomepageViewModel extends AndroidViewModel {

    private SubjectsRepository subjectsRepository;
    private LiveData<List<Subject>> allSubjects;
    private LiveData<List<Subject>> allSubjectsSelected;
    private ModulesRepository modulesRepository;
    private MutableLiveData<List<Module>> recentModules = new MutableLiveData<>();


    public HomepageViewModel(@NonNull Application application){
        super(application);
        subjectsRepository = new SubjectsRepository(application);
        allSubjects = subjectsRepository.getAllSubjects();
        allSubjectsSelected = subjectsRepository.getAllSubjectsSelected();
        modulesRepository = new ModulesRepository(application);
    }

    public void insert(Subject subject){
        subjectsRepository.insert(subject);
    }

    public void update(Subject subject){
        subjectsRepository.update(subject);
    }

    public void deleteAllSubjects(){
        subjectsRepository.deleteAllSubjects();
    }

    public LiveData<List<Subject>> getAllSubjects(){
        return allSubjects;
    }
    public LiveData<List<Subject>> getAllSubjectsSelected(){
        return allSubjectsSelected;
    }

    public void setSelectedTrue(int subjectID){
        subjectsRepository.setSelectedTrue(subjectID);
    }

    public void setSelectedFalse(int subjectID){
        subjectsRepository.setSelectedFalse(subjectID);
    }

    public LiveData<Subject> getSubjectByID(int subjectID){
        return subjectsRepository.getSubjectByID(subjectID);

    }
    public MutableLiveData<List<Module>> getRecentModules(int subjectID){
        recentModules.setValue(modulesRepository.getRecentModules(subjectID).getValue());
        return recentModules;
    }

}
