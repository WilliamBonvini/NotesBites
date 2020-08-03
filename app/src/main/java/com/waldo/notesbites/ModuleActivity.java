package com.waldo.notesbites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ModuleActivity extends AppCompatActivity {

    public static final String EXTRA_MODULEID = "moduleID";
    public static final String EXTRA_SUBJECTID = "subjectID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);

        // Get the moduleID from the intent
        Intent intent = getIntent();
        int moduleID = (Integer) intent.getExtras().get(EXTRA_MODULEID);
        int subjectID = (Integer) intent.getExtras().get(EXTRA_SUBJECTID);
        Module module = SubjectsList.subjects[subjectID].getModules()[moduleID];

        // Populate the module title
        TextView moduleTitle = (TextView)findViewById(R.id.module_title);
        moduleTitle.setText(module.getTitle());

        // Populate the module description
        TextView moduleDescription = (TextView) findViewById(R.id.module_description);
        moduleDescription.setText(module.getDescription());

        // Populate the module image
        ImageView photo = (ImageView) findViewById(R.id.module_image);
        photo.setImageResource(module.getImageResourceID());
        photo.setContentDescription(module.getTitle());

    }
}