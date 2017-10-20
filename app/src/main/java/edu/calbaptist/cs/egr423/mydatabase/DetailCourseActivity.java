package edu.calbaptist.cs.egr423.mydatabase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

public class DetailCourseActivity extends Activity {
    private static final String EXTRA_COURSE_ID = "com.cbu.mapp.extracourseid";

    Course mCourse;
    TextView mCourseName;
    TextView mCourseCode;
    TextView mCourseCredits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        long sId = getIntent().getLongExtra(EXTRA_COURSE_ID, 0);
        mCourse = DatabaseHelper.getInstance(this).getCourseById(sId);

        if (mCourse == null) {
            // Error fetching course details
            detailError();
            return;
        }

        mCourseName = (TextView) findViewById(R.id.course_name_text);
        mCourseCode = (TextView) findViewById(R.id.course_code_text);
        mCourseCredits = (TextView) findViewById(R.id.course_credits_text);

        mCourseName.setText(mCourse.getName());
        mCourseCode.setText(mCourse.getCourseCode());
        mCourseCredits.setText(mCourse.getUnits() + " Units");

    }

    // This can be placed in a separate class specifically for error messages
    private void detailError(){
        Toast.makeText(this, "Could not get course details", Toast.LENGTH_SHORT).show();
        this.finish();
    }

    public static Intent getIntent(Context context, long courseId){
        // Todo: show Course Detail
        // get student from
        Intent intent = new Intent(context, DetailCourseActivity.class);
        intent.putExtra(EXTRA_COURSE_ID, courseId);
        return intent;
    }

}
