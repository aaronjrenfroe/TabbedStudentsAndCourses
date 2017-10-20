package edu.calbaptist.cs.egr423.mydatabase;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

/**
 * Created by AaronR on 10/13/17.
 */

public class TabCoursesFragment extends Fragment {

    private DatabaseHelper dbHelper;
    Button mAddCourseButton;
    Button mDeleteCourseButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v =  inflater.inflate(R.layout.fragment_course_list, container, false);
        mAddCourseButton = (Button) v.findViewById(R.id.add_course_button);
        mDeleteCourseButton = (Button) v.findViewById(R.id.delete_course_button);
        mAddCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCourseButtonClick();
            }
        });
        mDeleteCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCourseButtonClick();
            }
        });

        return v;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dbHelper = DatabaseHelper.getInstance(getActivity().getApplicationContext());
        dbHelper.open();

    }

    @Override
    public void onResume() {
        super.onResume();
        // Changed from getFragmentManager to this vvvvvvvvvvvvvvvvvvvvvvvvv
        ListFragment listFragment = (ListFragment) getChildFragmentManager().findFragmentById(R.id.course_listFragment);
        ArrayAdapter adapter = (ArrayAdapter) listFragment.getListAdapter();
        adapter.clear();
        adapter.addAll(dbHelper.getAllCourses());
        adapter.notifyDataSetChanged();
    }

    public void addCourseButtonClick() {
        Intent implicit = new Intent(getActivity().getApplicationContext(), AddCourseActivity.class);
        startActivity(implicit);
    }

    public void deleteCourseButtonClick() {
        ListFragment listFragment = (ListFragment) getChildFragmentManager().findFragmentById(R.id.course_listFragment);
        ArrayAdapter adapter = (ArrayAdapter) listFragment.getListAdapter();

        if(adapter.getCount() > 0) {
            dbHelper.deleteCourse(((Course) adapter.getItem(0)).getId());
            adapter.remove(adapter.getItem(0));
            adapter.notifyDataSetChanged();
        }
    }

}
