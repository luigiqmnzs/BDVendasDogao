package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Sir Parish
 */
public class Conexao {
    static{ 
         try{
             Class.forName("com.mysql.jdbc.Driver");
         }catch(ClassNotFoundException e){
             e.printStackTrace();
         } 
    }
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:mysql://localhost/vendas_dogao", "root", "07304137");
    }
}