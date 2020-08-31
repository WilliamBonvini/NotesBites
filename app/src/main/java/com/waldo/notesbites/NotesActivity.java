package com.waldo.notesbites;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.util.IOUtils;
import com.mukesh.MarkdownView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import io.noties.markwon.Markwon;
import io.noties.markwon.MarkwonPlugin;
import io.noties.markwon.ext.latex.JLatexMathPlugin;
import io.noties.markwon.ext.tables.TablePlugin;
import io.noties.markwon.html.HtmlPlugin;
import io.noties.markwon.image.ImagesPlugin;
import io.noties.markwon.image.file.FileSchemeHandler;
import io.noties.markwon.inlineparser.MarkwonInlineParserPlugin;
import io.noties.markwon.movement.MovementMethodPlugin;

public class NotesActivity extends AppCompatActivity implements View.OnTouchListener {
    public static final String MODULE_ID = "MODULE_ID";
    private NotesViewModel notesViewModel;
    private String mdcontent;
    private TextView markdownTextView;
    // the following field are needed to do the pinch in and pinch out
    int mBaseDistance;
    float mRation = 1.0f;
    float mBaseRatio;
    final static float step = 200;
    private View v;
    private MotionEvent event;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        markdownTextView = findViewById(R.id.markdown_text_view);
        markdownTextView.setTextSize(16);
        markdownTextView.setBackgroundColor(Color.WHITE);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        Intent intent = getIntent();
        int moduleID = (int)intent.getExtras().get(MODULE_ID);

        notesViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(NotesViewModel.class);
        notesViewModel.setModuleMdContentPathByModuleID(moduleID);



        //create instance of Markwon
        final Markwon markwon = Markwon.builder(getApplicationContext())
                .usePlugin(ImagesPlugin.create(new ImagesPlugin.ImagesConfigure(){
                    @Override
                    public void configureImages(@NonNull ImagesPlugin plugin){
                        plugin.addSchemeHandler(FileSchemeHandler.createWithAssets(getApplicationContext()));
                    }
                }))
                .usePlugin(MarkwonInlineParserPlugin.create())
                .usePlugin(JLatexMathPlugin.create(markdownTextView.getTextSize(), new JLatexMathPlugin.BuilderConfigure() {
                    @Override
                    public void configureBuilder(@NonNull JLatexMathPlugin.Builder builder) {
                        //ENABLE inlines
                        builder.inlinesEnabled(true);
                    }
                }))
                .usePlugin(HtmlPlugin.create())
                .usePlugin(MovementMethodPlugin.create(ScrollingMovementMethod.getInstance()))
                .usePlugin(TablePlugin.create(getApplicationContext()))
                .build();

        notesViewModel.getCurrentModuleMdContentPath().observe(this,  new Observer<String>() {
            @Override
            public void onChanged(String mdContentPath) {



                // populate mdContent string
                Scanner s;
                try {
                    s = new Scanner(getAssets().open(mdContentPath)).useDelimiter("\\A");
                    mdcontent = s.hasNext() ? s.next() : "";

                } catch (IOException e) {
                    e.printStackTrace();
                }

                // set markwon's markdown to be mdContent
                markwon.setMarkdown(markdownTextView,mdcontent);


                /*
                Log.w("ss,","in NotesActivity observer");
                MarkdownView markdownView = (MarkdownView) findViewById(R.id.markdown_view);

                //markdownView.setMarkDownText("# Hello World\nThis is a simple markdown"); //Displays markdown text
                markdownView.loadMarkdownFromAssets(module.getMdContent()); //Loads the markdown file from the assets folder

                 */
            }
        });



    }



    // the following methods are needed to do the pinch in and pinch out

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.w("notes","on touch event triggered");
        if (event.getPointerCount() == 2){
            int action = event.getAction();
            int pureaction = action & MotionEvent.ACTION_MASK;
            if(pureaction == MotionEvent.ACTION_POINTER_DOWN){
                mBaseDistance = getDistance(event);
                mBaseRatio = mRation;
            }
            else{
                float delta = (getDistance(event) - mBaseDistance)/step;
                float multi = (float) (Math.pow(2,delta));
                mRation = Math.min(1024.0f,Math.max(0.1f,multi * mBaseRatio));
                markdownTextView.setTextSize(mRation+130);

            }

        }
        return true;
    }

    int getDistance(MotionEvent motionEvent){
        Log.w("notes","getDistance triggered");

        int dx = (int)(motionEvent.getX(0)-motionEvent.getX(1));
        int dy = (int)(motionEvent.getY(0)-motionEvent.getY(1));
        return (int)(Math.sqrt(dx * dx + dy * dy));
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.w("notes","on touch triggered");


        return false;
    }
}





/*

        int subjectID = -1;

        Intent intent = getIntent();
        int moduleID = (int)intent.getExtras().get(MODULE_ID);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query("MODULE",new String[]{"READABLE_CONTENT"},"_id = ?",new String[]{Integer.toString(moduleID)},null,null,null);
        if(cursor.moveToFirst()){
            String readableContent = cursor.getString(0);
            TextView notesTextTextView = findViewById(R.id.notes_text);
            notesTextTextView.setText(readableContent);
        }
        cursor.close();

        DatabaseFunctions dbf = new DatabaseFunctions(this);
        subjectID = dbf.getSubjectID_fromModuleID(moduleID);
//        Cursor cursor1 = db.query("MODULE", null, "_id = ?",new String[]{Integer.toString(moduleID)}, null, null, null, null);
//        if(cursor1.moveToFirst()){
//            subjectID = cursor1.getInt(4);
//        }
//        cursor1.close();
        db.close();
        String subjectName = dbf.getSubjectName_fromID(subjectID);

        MarkdownView markdownView = (MarkdownView) findViewById(R.id.markdown_view);
        //markdownView.setMarkDownText("# Hello World\nThis is a simple markdown"); //Displays markdown text
        //TODO: ora cambia il markdown in base alla materia, deve cambiare in base al modulo della materia; cambiare prima il db
        if(subjectName.equals("AI")) {
            markdownView.loadMarkdownFromAssets("1_Introduction.md"); //Loads the markdown file from the assets folder
        }
        if(subjectName.equals("IOT")) {
            //ci sono due moduli in AI quindi è il terzo
            if(moduleID == 3){
            markdownView.loadMarkdownFromAssets("IOT/IOT_1.md"); //Loads the markdown file from the assets folder
            }
            if(moduleID == 4){
                markdownView.loadMarkdownFromAssets("IOT/IOT_2.md");
            }
            //markdownView.loadMarkdownFromAssets("IOT/IOT_1.md");
        }

        //File markdownFile=new File("filePath");
        //markdownView.loadMarkdownFromFile(markdownFile); //Loads the markdown file.



    }

}




*/