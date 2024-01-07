package packagenew;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test {
    public static Connection getCon(){
        Connection cn = null;
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mariadb://localhost/dbnew", "root", "vanngu");
        }catch (ClassNotFoundException | SQLException e) {
        }
        return cn;
    }

    public static void main(String[] args) {
        System.out.println(getCon());
    }
}
