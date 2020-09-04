package com.waldo.notesbites;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface SubjectDao {

    @Insert
    void insert(Subject subject);

    @Update
    void update(Subject subject);

    @Delete
    void delete(Subject subject);

    @Query("DELETE FROM subject_table")
    void deleteAllSubjects();

    @Query("SELECT * FROM subject_table ORDER BY name ASC")
    LiveData<List<Subject>> getAllSubjects();

    @Query("SELECT * FROM subject_table WHERE selected == 1 ORDER BY name ASC")
    LiveData<List<Subject>> getAllSubjectsSelected();

    @Query("UPDATE subject_table SET selected = 1 WHERE subjectID = :subjectID")
    void setSelectedTrue(int subjectID);

    @Query("UPDATE subject_table SET selected = 0 WHERE subjectID = :subjectID")
    void setSelectedFalse(int subjectID);

    @Query("SELECT * FROM subject_table WHERE subjectID = :subjectID")
    LiveData<Subject> getSubjectByID(int subjectID);

    @Query("SELECT name FROM subject_table WHERE subjectID = :subjectID")
    LiveData<String> getSubjectNameByID(int subjectID);

    @Query("SELECT imageResourceID FROM subject_table WHERE subjectID = :subjectID")
    LiveData<Integer> getImageIDFromID(int subjectID);

    @Query("SELECT description FROM subject_table WHERE subjectID = :subjectID")
    LiveData<String> getDescriptionFromID(int subjectID);

    @Query("SELECT subjectID FROM subject_table WHERE name = :subjectName")
    int getSubjectIDbySubjectName(String subjectName);

    @Query("SELECT overview FROM subject_table WHERE subjectID=:subjectID")
    LiveData<String> getOverviewFromSubjectID(int subjectID);
}





