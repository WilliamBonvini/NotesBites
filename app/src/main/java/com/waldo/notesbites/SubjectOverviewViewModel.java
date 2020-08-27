package com.waldo.notesbites;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class SubjectOverviewViewModel extends AndroidViewModel {

    private ModulesRepository modulesRepository;
    private LiveData<List<Module>> allModules;
    private SubjectsRepository subjectsRepository;



    public SubjectOverviewViewModel(@NonNull Application application){
        super(application);
        modulesRepository = new ModulesRepository(application);
        subjectsRepository = new SubjectsRepository(application);
        //allModules = modulesRepository.getAllModulesBySubjectName();
    }

    public LiveData<List<String>> getModulesOfSubject(int subjectId){
        return modulesRepository.getModuleNamesBySubjectID(subjectId);
    }

    public LiveData<List<Module>> getModulesFromSubject(int subjectID){
        return modulesRepository.getModulesBySubjectID(subjectID);
    }

    public LiveData<String> getSubjectNameFromID(int subjectID){
        return subjectsRepository.getSubjectNameFromID(subjectID);
    }

    public LiveData<Integer> getImageIDFromSubjectID(int subjectID){
        return subjectsRepository.getImageIDFromSubjectID(subjectID);
    }

    public LiveData<String> getDescriptionFromSubjectID(int subjectID) {
        return subjectsRepository.getDescriptionFromSubjectID(subjectID);
    }

//    public void insert(Subject subject){
//        modulesRepository.insert(subject);
//    }
//
//    public void update(Subject subject){
//        modulesRepository.update(subject);
//    }
//
//    public void deleteAllSubjects(){
//        modulesRepository.deleteAllSubjects();
//    }
//
//    public LiveData<List<Subject>> getAllSubjects(){
//        return allModules;
//    }
//
//
//    public void setSelectedTrue(int subjectID){
//        modulesRepository.setSelectedTrue(subjectID);
//    }
//
//    public void setSelectedFalse(int subjectID){
//        modulesRepository.setSelectedFalse(subjectID);
//    }
//
//    public LiveData<Subject> getSubjectByID(int subjectID){
//        return modulesRepository.getSubjectByID(subjectID);
//
//    }

}
