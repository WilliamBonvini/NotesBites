package com.waldo.notesbites;

import android.app.Application;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import io.noties.markwon.Markwon;
import io.noties.markwon.ext.latex.JLatexMathPlugin;
import io.noties.markwon.html.HtmlPlugin;
import io.noties.markwon.image.ImagesPlugin;
import io.noties.markwon.image.file.FileSchemeHandler;
import io.noties.markwon.inlineparser.MarkwonInlineParserPlugin;

public class NotesViewModel extends AndroidViewModel {

    private ModulesRepository modulesRepository;
    private LiveData<String> currentModuleMdContentPath;
    public NotesViewModel(@NonNull Application application) {
        super(application);
        modulesRepository = new ModulesRepository(application);



    }


    public void setModuleMdContentPathByModuleID(int moduleID){
        currentModuleMdContentPath = modulesRepository.getModuleContentByModuleID(moduleID);
    }

    public LiveData<String> getCurrentModuleMdContentPath(){
        return currentModuleMdContentPath;
    }
}
