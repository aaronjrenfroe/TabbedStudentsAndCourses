package edu.calbaptist.cs.egr423.mydatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";

    private static DatabaseHelper dbHelper = null;
    private SQLiteDatabase database = null;

    private static final String DATABASE_NAME = "EGR423.db";
    private static final int DATABASE_VERSION = 1;


    public static DatabaseHelper getInstance(Context context) {
        if (dbHelper == null) {
            dbHelper = new DatabaseHelper(context.getApplicationContext());
        }
        return dbHelper;
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open() throws SQLException {
        database = getWritableDatabase();
    }

    public void close() {
        close();
    }


    public Student createStudent(String name, String email, String comment) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("email", email);
        values.put("comments", comment);

        long insertId = database.insert("STUDENTS", null, values);

        if (insertId != -1) {
            return new Student(insertId, name, email, comment);
        }

        Log.e(TAG, "Error inserting Student!");
        return null;
    }

    public Course createCourse(String name, String courseCode, int units) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("course_code", courseCode);
        values.put("units", units);


        long insertId = database.insert("COURSES", null, values);

        if (insertId != -1) {
            return new Course(insertId, name, courseCode, units);
        }

        Log.e(TAG, "Error inserting Course!");
        return null;
    }


    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList();

        Cursor cursor = database.rawQuery("select * from students", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Student student = new Student();
            student.setId(cursor.getLong(0));
            student.setName(cursor.getString(1));
            student.setEmail(cursor.getString(2));
            student.setComment(cursor.getString(3));
            students.add(student);
            cursor.moveToNext();
        }
        cursor.close();
        return students;
    }

    public List<Course> getAllCourses() {


        List<Course> courses = new ArrayList();

        Cursor cursor = database.rawQuery("select * from courses", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Course course = new Course();
            course.setId(cursor.getLong(0));
            course.setName(cursor.getString(1));
            course.setCourseCode(cursor.getString(2));
            course.setUnits(cursor.getInt(3));
            courses.add(course);
            cursor.moveToNext();
        }
        cursor.close();
        return courses;
    }

    public void deleteStudent(long id) {
        database.execSQL("delete from STUDENTS where id = \"" + id + "\"");
    }

    public void deleteCourse(long id) {
        database.execSQL("delete from COURSES where id = \"" + id + "\"");
    }

    public Student getStudentById(long id){
        Cursor cursor = database.rawQuery("select * from STUDENTS",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            Student student = new Student();
            student.setId(cursor.getLong(0));
            student.setName(cursor.getString(1));
            student.setEmail(cursor.getString(2));
            student.setComment(cursor.getString(3));
            if(student.getId() == id) {
                cursor.close();
                return student;
            }
            cursor.moveToNext();
        }
        cursor.close();
        return null;
    }

    public Course getCourseById(long id){
        Cursor cursor = database.rawQuery("select * from COURSES",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            Course course = new Course();
            course.setId(cursor.getLong(0));
            course.setName(cursor.getString(1));
            course.setCourseCode(cursor.getString(2));
            course.setUnits(cursor.getInt(3));

            if(course.getId() == id) {
                cursor.close();
                return course;
            }
            cursor.moveToNext();
        }
        cursor.close();
        return null;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("CREATE TABLE IF NOT EXISTS STUDENTS(" +
                "id INTEGER PRIMARY KEY, " +
                "name TEXT NOT NULL, " +
                "email TEXT NOT NULL, " +
                "comments TEXT);");

        database.execSQL("CREATE TABLE IF NOT EXISTS COURSES(" +
                "id INTEGER PRIMARY KEY, " +
                "course_code TEXT NOT NULL, " +
                "name TEXT NOT NULL, " +
                "units INTEGER NOT NULL);");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "Upgrading database from version " + oldVersion + " to "
                        + newVersion);
        db.execSQL("DROP TABLE IF EXISTS STUDENTS");
        onCreate(db);
    }

}


