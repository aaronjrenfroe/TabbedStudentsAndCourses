package edu.calbaptist.cs.egr423.mydatabase;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class ListFragmentCourse extends ListFragment {

    List<Course> courses;

    public ListFragmentCourse() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final DatabaseHelper dbHelper = DatabaseHelper.getInstance(getActivity());
        dbHelper.open();

        courses = dbHelper.getAllCourses();
        setListAdapter(new ArrayAdapter<Course>(getActivity(),
                android.R.layout.simple_list_item_1,
                courses));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Course c = courses.get(position);
        Toast.makeText(this.getActivity(),c.getName() + " is in position " + position, Toast.LENGTH_SHORT).show();
        showDetail(c.getId());
    }

    private void showDetail(long id){
        // Passing courses the ID instead of the course's information because
        // these functions do not need to know what fields course contains
        Intent intent = DetailActivityCourse.getIntent(getActivity(), id);
        startActivity(intent);
    }

}