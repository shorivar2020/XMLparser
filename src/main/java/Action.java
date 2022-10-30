import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Action {
    private static final String URL = "jdbc:mysql://localhost:3306/parserXML";
    private static final String USER = "root";
    private static final String PASS = "root";

    public Connection getConnection(){
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            Statement statement = connection.createStatement();
            if(!connection.isClosed()){
                System.out.println("Соединение с БД установлено");
            }
            return connection;
        } catch (SQLException e) {
            System.err.println("Ошибка подключения к БД");
        }
        return null;
    }

    public void destroyConnection(Connection connection) throws SQLException {
        connection.close();
        if(connection.isClosed()){
            System.out.println("Соединение с БД закрыто");
        }
    }

    public void NewProducts(Statement statement) throws SQLException {
        statement.executeUpdate("DROP table Products");
        //   statement.executeUpdate("CREATE TABLE IF NOT EXISTS Products (ID INTEGER, EAX VARCHAR(64));");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS Products (ID INTEGER, EAX VARCHAR(64), PartNumber VARCHAR(64), ProductName VARCHAR(64));");
    }

    public void NewRow(Statement statement, Integer ID, String EAX) throws SQLException{
        String sql = "INSERT INTO Products (ID, EAX) VALUES (" + ID + "," + EAX + ")";
        statement.executeUpdate(sql);
        //statement.executeUpdate("insert into Products set EAX = '12345'");
    }

    public String buildInsert(String firstName, String lastName, String phone, String email) {
        String sql = "INSERT INTO Products (FIRST_NAME, LAST_NAME, PHONE, EMAIL) VALUES ('" + firstName + "','" + lastName + "','" + phone + "','" + email + ")";
        return sql;
    }
}