package com.waldo.notesbites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ModuleActivity extends AppCompatActivity {

    public static final String EXTRA_MODULEID = "moduleID";
    public static final String EXTRA_SUBJECT_NAME = "subjectName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);


        // Get the moduleID and the subject name from the intent
        Intent intent = getIntent();
        int moduleID = (Integer) intent.getExtras().get(EXTRA_MODULEID);
        String subjectName = (String) intent.getExtras().get(EXTRA_SUBJECT_NAME);



        SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);
        try {
            SQLiteDatabase db = databaseHelper.getReadableDatabase();
            // code to read the data from the database
            Cursor cursor = db.query("MODULE",new String[]{"NAME","DESCRIPTION","IMAGE_RESOURCE_ID","BELONGING_SUBJECT","READABLE_CONTENT"},"_id=?",new String[]{Integer.toString(moduleID)},null,null,null);
            if(cursor.moveToFirst()){
                // get the data from the only tuple you have retrieved
                String titleText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int photoID = cursor.getInt(2);
                String readableContentText = cursor.getString(4);

                // Populate the module title
                TextView moduleTitle = (TextView)findViewById(R.id.module_title);
                moduleTitle.setText(titleText);

                // Populate the module description
                TextView moduleDescription = (TextView) findViewById(R.id.module_description);
                String pippo = "the description should be only the following: '"+ descriptionText + "',\nbut I don't currently have views to display the name of the subject ('"+subjectName+"'), and the readable content('"+readableContentText+"'), so here they are!!!";
                moduleDescription.setText(pippo);

                // Populate the module image
                ImageView photo = (ImageView) findViewById(R.id.module_image);
                photo.setImageResource(photoID);
                photo.setContentDescription(titleText);
            }
            cursor.close();
            db.close();

        }catch(SQLiteException e){
            Toast toast = Toast.makeText(this,
                    "Database Unavailable",
                    Toast.LENGTH_SHORT);
            toast.show();
        }





    }
}