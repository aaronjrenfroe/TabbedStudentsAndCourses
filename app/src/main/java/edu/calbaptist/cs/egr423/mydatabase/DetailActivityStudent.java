package edu.calbaptist.cs.egr423.mydatabase;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivityStudent extends Activity{

    private static final String EXTRA_STUDENT_ID = "com.cbu.mapp.extrastudentid";
    Student mStudent;
    TextView mName;
    TextView mId;
    TextView mEmail;
    TextView mComments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);
        long sId = getIntent().getLongExtra(EXTRA_STUDENT_ID, 0);
        mStudent = DatabaseHelper.getInstance(this).getStudentById(sId);

        mName = (TextView) findViewById(R.id.nameText);
        mId = (TextView) findViewById(R.id.idTextView);
        mEmail = (TextView) findViewById(R.id.emailTextView);
        mComments = (TextView) findViewById(R.id.commentsTextView);
        mName.setText("bla");
        if (mStudent != null){
            mName.setText(mStudent.getName());
            mId.setText("ID: " + String.format("%06d", mStudent.getId()));
            mEmail.setText(mStudent.getEmail());
            mComments.setText(mStudent.getComment());
        }
    }

    public static Intent getIntent(Context context, long studentId){
        // Todo: show Student Detail
        // get student from
        Intent intent = new Intent(context, DetailActivityStudent.class);
        intent.putExtra(EXTRA_STUDENT_ID, studentId);
        return intent;
    }
}
