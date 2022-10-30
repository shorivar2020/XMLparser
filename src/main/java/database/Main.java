package database;

import java.sql.*;

public class Main {

    private static final String URL = "jdbc:mysql://localhost:3306/parserXML";
    private static final String USER = "root";
    private static final String PASS = "root";

    public static void main(String[] args) {
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            if(!connection.isClosed()){
                System.out.println("Соединение с БД установлено");
            }
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP table Products");
         //   statement.executeUpdate("CREATE TABLE IF NOT EXISTS Products (ID INTEGER, EAX VARCHAR(64));");
          statement.executeUpdate("CREATE TABLE IF NOT EXISTS Products (ID INTEGER, EAX VARCHAR(64), PartNumber VARCHAR(64), ProductName VARCHAR(64));");
            statement.executeUpdate("insert into Products (ID) values(12345)");
            statement.executeUpdate("insert into Products set EAX = '12345'");
            connection.close();
            if(connection.isClosed()){
                System.out.println("Соединение с БД закрыто");
            }
        } catch (SQLException e) {
            System.err.println("Ошибка подключения к БД");
        }
  //      Action a = new Action();
//        WorkerEntity w = a.addNewWorker(10, 30, "John White", "RedStreet 22");
//        OrderEntity o = a.addOrder(Time.valueOf("12:00"), 20, 1, "Salad, tea", w);
//        a.removeOrder(o);
//        OrderEntity o2 = a.addOrder(Time.valueOf("13:00"), 20, 1, "Salad, tea", w);
//        a.myCustomer();
//        a.myOrder();
//        a.myCat();
  //      a.close();
    }


}
