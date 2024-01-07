package packagenew;

import java.sql.*;
import java.util.ArrayList;

public class MyUser implements IUser{
    @Override
    public Connection getCon(){
        Connection cn = null;
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mariadb://localhost/dbhi", "root", "vanngu");
        }catch (ClassNotFoundException | SQLException e) {
        }
        return cn;
    }

    @Override
    public ArrayList<User> getAll(){
        Connection con = getCon();
        String sql = "Select * from tbstudent ORDER BY id ASC";
        ArrayList<User> stu = new ArrayList<>();
        try{
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                User t = new User();
                t.setId(rs.getString("id"));
                t.setName(rs.getString("name"));
                t.setClassId(rs.getString("classId"));
                t.setGender(rs.getBoolean("gender"));
                t.setMark(rs.getDouble("Mark"));
                stu.add(t);
            }
        }catch (SQLException e) {}
        return  stu;
    }

    @Override
    public ArrayList<User> getStudents(String classId, boolean gender){
        Connection con = getCon();
        String sql = "SELECT * FROM tbstudent WHERE classId = ? AND gender = ?";
        ArrayList<User> stu = new ArrayList<>();

        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, classId);
            ps.setBoolean(2, gender);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                User t = new User();
                t.setId(rs.getString("id"));
                t.setName(rs.getString("name"));
                t.setClassId(rs.getString("classId"));
                t.setGender(rs.getBoolean("gender"));
                t.setMark(rs.getDouble("Mark"));
                stu.add(t);
            }
        }catch (SQLException e) {}
        return  stu;
    }

    @Override
    public User getbyId(String id){
        Connection con = getCon();
        User e = new User();
        String strSql = "SELECT * FROM tbstudent WHERE id = ?";
        try
        {
            PreparedStatement pe = (PreparedStatement) con.prepareStatement(strSql);
            pe.setString(1,id);
            ResultSet rs = pe.executeQuery();

            rs.next();
            e.setId(rs.getString("id"));
            e.setName(rs.getString("name"));
            e.setClassId(rs.getString("classId"));
            e.setGender(rs.getBoolean("gender"));
            e.setMark(rs.getDouble("mark"));
        }
        catch (SQLException ex){}

        return e;
    }

    @Override
    public boolean delStudent(String id, boolean gender) {
        Connection con = getCon();
        String sql = "DELETE FROM tbstudent WHERE id = ? AND gender = ?";
        int rowsAffected = 0;
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, id);
            ps.setBoolean(2, gender);
            rowsAffected = ps.executeUpdate();

        }catch (SQLException e) {}
        return rowsAffected >0;
    }

    @Override
    public boolean insertStudent(String id, String name,String classId,boolean gender, double mark){
        Connection con = getCon();
        String sql = "Insert into tbstudent (Id, Name, classId, Gender, Mark) VALUES (?, ?, ?, ?, ?)";
        int rowsAffected = 0;
        try{
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, classId);
            ps.setBoolean(4, gender);
            ps.setDouble(5, mark);
            rowsAffected = ps.executeUpdate();
        }catch (SQLException e) {}
        return rowsAffected >0;
    }

    @Override
    public boolean updateStudent(String id, String name, String classId, boolean gender, double mark){
        Connection con = getCon();
        String sql = "UPDATE tbstudent SET name=?, classId=?, gender=?, mark=? WHERE id=?";
        int rowsAffected = 0;

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, classId);
            ps.setBoolean(3, gender);
            ps.setDouble(4, mark);
            ps.setString(5, id);
            rowsAffected = ps.executeUpdate();
        }catch (SQLException e) {}
        return rowsAffected >0;
    }

    @Override
    public ArrayList<String> getId(){
        Connection con = getCon();
        String sql = "SELECT id FROM tbstudent";
        ArrayList<String> ids = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ids.add(rs.getString("id"));
            }
        } catch (SQLException e) {}
        return ids;
    }

    @Override
    public ArrayList<String> getclassId(){
        Connection con = getCon();
        String sql = "SELECT classId FROM tbstudent GROUP BY classId";
        ArrayList<String> classIDs = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                classIDs.add(rs.getString("classId"));
            }
        } catch (SQLException e) {}
        return classIDs;
    }

    @Override
    public boolean checkFound(String id) {
        ArrayList<String> lID = getId();//lay danh sach id
        Connection con = getCon();
        for(String i: lID)//duyt qua tung phan tu trong danh sach lid lay tu danh sach id tu csdl
        {
            if (i.equals(id)) return true;
        }
        return false;
    }


}
