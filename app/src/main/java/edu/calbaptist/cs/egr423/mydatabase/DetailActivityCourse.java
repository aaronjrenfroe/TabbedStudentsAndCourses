package edu.calbaptist.cs.egr423.mydatabase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class DetailActivityCourse extends Activity {
    private static final String EXTRA_COURSE_ID = "com.cbu.mapp.extracourseid";

    Course mCourse;
    TextView mCourseNAme;
    TextView mCourseCode;
    TextView mCourseCredits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_course_detail);
        long sId = getIntent().getLongExtra(EXTRA_COURSE_ID, -1);

        if (sId != -1) {
            mCourse =  DatabaseHelper.getInstance(this).getCourseById(sId);
            mCourseNAme = (TextView) findViewById(R.id.course_name_text);
            mCourseCode = (TextView) findViewById(R.id.course_code_text);
            mCourseCredits = (TextView) findViewById(R.id.course_credits_text);

            mCourseNAme.setText(mCourse.getName());
            mCourseCode.setText(mCourse.getCourseCode());
            mCourseCredits.setText(mCourse.getUnits() + " Units");
        }


    }

    public static Intent getIntent(Context context, long courseId){
        // Todo: show Course Detail
        // get student from
        Intent intent = new Intent(context, DetailActivityCourse.class);
        intent.putExtra(EXTRA_COURSE_ID, courseId);
        return intent;
    }

}
