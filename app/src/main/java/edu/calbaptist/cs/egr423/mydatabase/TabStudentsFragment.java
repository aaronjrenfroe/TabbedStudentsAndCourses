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


public class TabStudentsFragment extends Fragment {

    private DatabaseHelper dbHelper;
    Button mAddButton;
    Button mDeleteButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v =  inflater.inflate(R.layout.activity_main, container, false);
        mAddButton = (Button) v.findViewById(R.id.add_student_button);
        mDeleteButton = (Button) v.findViewById(R.id.button);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStudentButtonClick();
            }
        });
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteStudentButtonClick();
            }
        });
        // Change to Tab
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
        ListFragment listFragment = (ListFragment) getChildFragmentManager().findFragmentById(R.id.listFragment);
        ArrayAdapter adapter = (ArrayAdapter) listFragment.getListAdapter();
        adapter.clear();
        adapter.addAll(dbHelper.getAllStudents());
        adapter.notifyDataSetChanged();
    }

    public void addStudentButtonClick() {
        Intent implicit = new Intent(getActivity().getApplicationContext(), AddStudentActivity.class);
        startActivity(implicit);
    }

    public void deleteStudentButtonClick() {
        ListFragment listFragment = (ListFragment) getChildFragmentManager().findFragmentById(R.id.listFragment);
        ArrayAdapter adapter = (ArrayAdapter) listFragment.getListAdapter();
        if(adapter.getCount() > 0) {
            dbHelper.deleteStudent(((Student) adapter.getItem(0)).getId());
            adapter.remove(adapter.getItem(0));
            adapter.notifyDataSetChanged();
        }

    }

}
