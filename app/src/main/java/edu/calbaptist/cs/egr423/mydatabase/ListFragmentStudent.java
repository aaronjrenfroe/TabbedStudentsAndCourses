package edu.calbaptist.cs.egr423.mydatabase;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ListFragmentStudent extends ListFragment {

    List<Student> students;

    public ListFragmentStudent() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final DatabaseHelper dbHelper = DatabaseHelper.getInstance(getActivity());
        dbHelper.open();


        students = dbHelper.getAllStudents();
        setListAdapter(new ArrayAdapter<Student>(getActivity(),
                android.R.layout.simple_list_item_1,
                students));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Student s = students.get(position);
        Toast.makeText(this.getActivity(),s.getName() + " is in position " + position, Toast.LENGTH_SHORT).show();
        showDetail(s.getId());
    }

    private void showDetail(long id){
        // Passing students the ID instead of the students information because
        // these functions do not need to know what fields student contains

        Intent intent = DetailStudentActivity.getIntent(getActivity(), id);
        startActivity(intent);
    }


}
