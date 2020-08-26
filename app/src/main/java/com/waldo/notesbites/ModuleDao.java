package com.waldo.notesbites;

import java.util.List;

import androidx.lifecycle.LiveData;
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
            "WHERE belongingSubject = :belongingSubject " +
            "ORDER BY priority")
    LiveData<List<Module>> getAllModulesBySubjectName(String belongingSubject);

}
