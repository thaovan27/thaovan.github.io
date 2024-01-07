package packagenew;

import java.sql.Connection;
import java.util.ArrayList;

public interface IUser {
    public Connection getCon();

    public ArrayList<User> getAll();

    public ArrayList<User> getStudents(String classId, boolean gender);

    public ArrayList<String> getId();

    public ArrayList<String> getclassId();


    public User getbyId(String id);

    public boolean delStudent(String id, boolean gender);

    public boolean insertStudent(String id, String name,String classId,boolean gender, double mark);
    public boolean updateStudent(String id, String name, String classId, boolean gender, double mark);

    public boolean checkFound(String id);



}
