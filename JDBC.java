package JDBC;
import java.sql.*;
import java.lang.*;

public class JDBC {
  public static void main(String[] args)
  {
    String driver ="com.mysql.jdbc.Driver";
    String url ="jdbc:mysql://127.0.0.1:3306/test";
    String MysqlUser="root";
    String password="12345";
    try{
      Class.forName(driver);
      Connection con=DriverManager.getConnection(url,MysqlUser,password);
      if(!con.isClosed())
      {
        System.out.println("succeed connection mysql");
      }
      String sql="insert into people (id,name) values(?,?)";
      PreparedStatement pp=con.prepareStatement(sql);
      pp.setString(1,"1");
      pp.setString(2,"zhangsan");
      pp.executeUpdate();
    }
    catch (ClassNotFoundException e)
    {
      e.printStackTrace();
    }
    catch (SQLException ee) {
      ee.printStackTrace();
    }
  }
}
