package com.waldo.notesbites;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class GuestHomepageViewModel extends AndroidViewModel {
    private SubjectsRepository subjectsRepository;
    private ModulesRepository modulesRepository;

    public GuestHomepageViewModel(@NonNull Application application) {
        super(application);

    }
}
