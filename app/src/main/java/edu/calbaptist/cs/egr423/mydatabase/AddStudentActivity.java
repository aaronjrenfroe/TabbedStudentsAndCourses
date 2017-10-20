package edu.calbaptist.cs.egr423.mydatabase;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class AddStudentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

    }

    public void addStudentToDB(View view) {
        DatabaseHelper dbHelper = DatabaseHelper.getInstance(this);
        dbHelper.open();

        Student student = dbHelper.createStudent(
                ((EditText) findViewById(R.id.name)).getText().toString(),
                ((EditText) findViewById(R.id.email)).getText().toString(),
                ((EditText) findViewById(R.id.comments)).getText().toString());

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

