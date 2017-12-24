package src.JDBC;
import java.sql.*;
import java.lang.*;
import java.util.Scanner;
//学习JAVA对数据库的操作，插入查询操作。以及为什么主类中函数必须是static，因为main函数是入口此时类并没有创建对象
public class JDBC {
  public static void main(String[] args) {
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://127.0.0.1:3306/test";
    String MysqlUser = "root";
    String password = "12345";
    try {
      Class.forName(driver);//加载驱动类，JVM就知道该类的存在了。
      Connection con = DriverManager.getConnection(url, MysqlUser, password);//连接数据库
      if (!con.isClosed()) {
        System.out.println("succeed connection mysql");
      }
      String sql = "insert into people (id,name) values(?,?)";
      PreparedStatement p = con.prepareStatement(sql);//操作数据库的对象
      update(p);
      que(p);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    //必须捕捉异常，因为sql内置类抛出了异常
    catch (SQLException ee) {
      ee.printStackTrace();
    }
  }

  public static void update(PreparedStatement p) {
    String id;
    String name;
    Scanner sca = new Scanner(System.in);
    while (true) {
      try {
        id = sca.next();
        if (id.equals("exit"))
          break;
        name = sca.next();
        //防止sql注入攻击使用parameterindex，1代表第几个参数，后买呢代表参数内容
        p.setString(1, id);
        p.setString(2, name);
        p.executeUpdate();//更新数据库，执行insert操作。
      }
      catch (SQLException ee) {
        ee.printStackTrace();
      }
    }
  }
//查询函数
  public static void que(PreparedStatement p)
  {
    try{
      ResultSet result=p.executeQuery("select * from people");
      while (result.next())
      {
        System.out.println(result.getString("id")+" "+result.getString("name"));
      }
    }
    catch (SQLException ee) {
      ee.printStackTrace();
    }
  }
}
