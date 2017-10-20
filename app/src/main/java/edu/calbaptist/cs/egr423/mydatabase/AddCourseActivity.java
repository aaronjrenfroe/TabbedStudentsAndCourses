package edu.calbaptist.cs.egr423.mydatabase;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by AaronR on 10/6/17.
 */

public class AddCourseActivity extends Activity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.fragment_add_course);
        }

        public void addCourseToDB(View view) {
            DatabaseHelper dbHelper = DatabaseHelper.getInstance(this);
            dbHelper.open();
            int units = Integer.valueOf(((EditText) findViewById(R.id.course_credits)).getText().toString());
            Course course = dbHelper.createCourse(
                    ((EditText) findViewById(R.id.course_name)).getText().toString(),
                    ((EditText) findViewById(R.id.course_code)).getText().toString(),
                    units);

            finish();
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_add_student, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
}
