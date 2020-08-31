
package com.waldo.notesbites;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SelectSubjectsActivity extends AppCompatActivity{
    private SelectSubjectsViewModel selectSubjectsViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate((savedInstanceState));
        setContentView(R.layout.activity_select_subjects);

        TextView title = findViewById(R.id.title);
        //blink animation
        Animation anim = new AlphaAnimation(0.8f, 1.0f);
        anim.setDuration(500); //You can manage the blinking time with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        title.startAnimation(anim);

        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final SelectSubjectAdapter adapter = new SelectSubjectAdapter();
        recyclerView.setAdapter(adapter);

        selectSubjectsViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(SelectSubjectsViewModel.class);
        selectSubjectsViewModel.getAllSubjects().observe(this, new Observer<List<Subject>>() {
            @Override
            public void onChanged(List<Subject> subjects) {
                // update RecyclerView when data in the subjects data changes (the change could occur only to the column "selected")
                adapter.setSubjects(subjects);
            }
        });


        adapter.setOnItemClickListener(new SelectSubjectAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Subject subject) {
                if (!subject.isSelected()){
                    selectSubjectsViewModel.setSelectedTrue(subject.getSubjectID());
                    Log.w("com.waldo.notesbites","true!");
                }else{
                    selectSubjectsViewModel.setSelectedFalse(subject.getSubjectID());
                    Log.w("com.waldo.notesbites","false!");

                }


            }
        });

    }

    public void onClickStartSimpleSubjectOverview(View view) {

        Intent intent = new Intent(SelectSubjectsActivity.this, SimpleSubjectOverviewActivity.class);
        intent.putExtra(SimpleSubjectOverviewActivity.EXTRA_SUBJECT_ID,view.getId());
        startActivity(intent);
    }

    public void onClickConfirmSelectedSubjects(View view) throws InterruptedException {
        final Button button = findViewById(R.id.button_confirm);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        myAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                button.setBackgroundResource(R.color.colorAccent);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                button.setBackgroundResource(R.color.cardview_light_background);
                Intent intent = new Intent(SelectSubjectsActivity.this,SignInActivity.class);
                startActivity(intent);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        button.startAnimation(myAnim);


    }


}
