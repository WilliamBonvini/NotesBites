package com.waldo.notesbites;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ModuleDao {




    @Insert
    void insert(Module module);
    @Update
    void update(Module module);


    @Query("SELECT * FROM module_table " +
            "WHERE belongingSubjectID = :belongingSubjectID " +
            "ORDER BY priority")
    LiveData<List<Module>> getAllModulesBySubjectID(int belongingSubjectID);

    @Query("SELECT name FROM module_table WHERE belongingSubjectID = :belongingSubjectID")
    LiveData<List<String>> getModuleNamesBySubjectID(int belongingSubjectID);

    @Query("SELECT * FROM module_table WHERE belongingSubjectID = :belongingSubjectID")
    LiveData<List<Module>> getModulesBySubjectID(int belongingSubjectID);


    @Query("SELECT * FROM module_table WHERE moduleID=:moduleID")
    LiveData<Module> getModuleByModuleID(int moduleID);

    //TODO: modificare la query con nuova tabella
    @Query("SELECT * FROM module_table WHERE belongingSubjectID = :belongingSubjectID")
    LiveData<List<Module>> getRecentModulesBySubjectID(int belongingSubjectID);
}
