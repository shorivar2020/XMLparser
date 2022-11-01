import java.sql.*;

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
//        statement.executeUpdate("DROP table IF EXISTS Products");
//        statement.executeUpdate("DROP table IF EXISTS RecommendedRetailPriceWithVat");
//        statement.executeUpdate("DROP table IF EXISTS VAT");
      //  statement.executeUpdate("DROP table IF EXISTS Documents");
 //       statement.executeUpdate("DROP table IF EXISTS AditionalImage");
//        statement.executeUpdate("DROP table IF EXISTS Availability");
//        statement.executeUpdate("DROP table IF EXISTS Conditions");
//        statement.executeUpdate("DROP table IF EXISTS Param");
//        statement.executeUpdate("DROP table IF EXISTS UndefinedData");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS Products " +
                "(ID INTEGER, EAN VARCHAR(64), PartNumber VARCHAR(64), ProductName VARCHAR(64)," +
                "DocTitle VARCHAR(64), DocLanguage VARCHAR(64), ITEMGROUP_ID VARCHAR(64)," +
                "Manufacturer VARCHAR(64), Supplier VARCHAR(64), CountryOfOrigin VARCHAR(64)," +
                " MeasureUnit VARCHAR(64), ShortDescription VARCHAR(256), LargeDescription VARCHAR(256)," +
                "Guarantee VARCHAR(64), GuaranteeType VARCHAR(64), Season VARCHAR(64)," +
                "StartDate TIMESTAMP, STATUS INTEGER);");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS RecommendedRetailPriceWithVat " +
                "(ID INTEGER, Price VARCHAR (64), Currency VARCHAR (64)," +
                "StartDate TIMESTAMP, STATUS INTEGER);");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS VAT " +
                "(ID INTEGER, Rate VARCHAR (64), Country VARCHAR (64)," +
                "StartDate TIMESTAMP, STATUS INTEGER);");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS Documents " +
                "(ID INTEGER, ImageLink VARCHAR (256), Video VARCHAR (64), DocumentName VARCHAR(64)," +
                " DocumentLink VARCHAR(64), CertificateLink VARCHAR(64), CertificateDescription VARCHAR(64)," +
                "CertificateImageLink VARCHAR(64)," +
                "StartDate TIMESTAMP, STATUS INTEGER);");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS AditionalImage " +
                "(ID INTEGER, AditionalImageLink VARCHAR(256)," +
                "StartDate TIMESTAMP, STATUS INTEGER);");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS Availability" +
                "(ID INTEGER, availabilityInternal INTEGER, availabilityExternal INTEGER," +
                " availabilityManufacture INTEGER," +
                "StartDate TIMESTAMP, STATUS INTEGER);");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS Conditions " +
                "(ID INTEGER, IsNew VARCHAR(64), IsSale VARCHAR(64), IsOutlet VARCHAR(64)," +
                "StartDate TIMESTAMP, STATUS INTEGER);");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS Param " +
                "(ID INTEGER, ParamName VARCHAR(64), Value VARCHAR(64), Unit VARCHAR(64)," +
                "StartDate TIMESTAMP, STATUS INTEGER);");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS UndefinedData " +
                "(ID INTEGER, Tag VARCHAR(64), ValueOfData VARCHAR(64)," +
                "StartDate TIMESTAMP, STATUS INTEGER);");
    }



    public void NewRowProducts(Statement statement, Timestamp t, Integer ID, String EAX, Integer PartNumber,
                               String ProductName, String DocTitle, String DocLanguage, String ITEMGROUP_ID,
                               String Manufacturer, String Supplier, String CountryOfOrigin, String MeasureUnit,
                               String ShortDescription, String LargeDescription, String Guarantee, String GuaranteeType,
                               String Season) throws SQLException{
        String sql = "INSERT INTO Products (ID, EAX, PartNumber, ProductName, DocTitle, DocLanguage, ITEMGROUP_ID," +
                "Manufacturer, Supplier, CountryOfOrigin, MeasureUnit, ShortDescription, LargeDescription, Guarantee," +
                "GuaranteeType, Season, StartDate, STATUS) VALUES (" + ID + "," + EAX + "," + PartNumber + "," + ProductName
                + "," + DocTitle + "," + DocLanguage + "," + ITEMGROUP_ID + "," + Manufacturer
                + "," + Supplier + "," + CountryOfOrigin + "," + MeasureUnit + "," + ShortDescription
                + "," + LargeDescription + "," + Guarantee + "," + GuaranteeType + "," + Season + "," + t + ",1)";
        statement.executeUpdate(sql);
        //statement.executeUpdate("insert into Products set EAX = '12345'");
    }

    public void NewRowAditionalImage(Statement statement, Timestamp t, Integer ID, String AditionalImageLink) throws SQLException {
        String sql = "INSERT INTO aditionalimage (ID, AditionalImageLink, StartDate, STATUS) VALUES (" + ID + ",'" + AditionalImageLink + "','" + t + "',1)";
        //System.out.println(sql);
        statement.executeUpdate(sql);
    }

    public void NewUndefinedData(Statement statement, Timestamp t, Integer ID, String Tag, String ValueOfData) throws SQLException {
        String sql = "INSERT INTO undefineddata (ID, Tag, ValueOfData, StartDate, STATUS)" +
                " VALUES (" + ID + ",'" + Tag + "','" + ValueOfData + "','" + t + "',1)";
        //System.out.println(sql);
        statement.executeUpdate(sql);
    }

    public void NewRowAvailability(Statement statement, Timestamp t, Integer ID, Integer i, Integer e, Integer m) throws SQLException {
        String sql = "INSERT INTO availability(ID, availabilityInternal, availabilityExternal, " +
                "availabilityManufacture, StartDate, STATUS) VALUES (" + ID + "," + i + "," + e + "," + m + ",'" + t + "',1)";
        //System.out.println(sql);
        statement.executeUpdate(sql);
    }

    public void NewRowConditions(Statement statement, Timestamp t, Integer ID, String IsNew, String IsSale, String IsOutlet) throws SQLException {
        String sql = "INSERT INTO conditions(ID, IsNew, IsSale, IsOutlet, StartDate, STATUS)" +
                " VALUES (" + ID + ",'" + IsNew + "','" + IsSale + "','" + IsOutlet + "','" + t + "',1)";
        statement.executeUpdate(sql);
    }

    public void NewRowDocuments(Statement statement, Timestamp t, Integer ID, String ImageLink, String Video,
                                String DocumentName, String DocumentLink, String CertificateLink,
                                String CertificateDescription, String CertificateImageLink) throws SQLException {
        String sql = "INSERT INTO documents (ID, ImageLink, Video, DocumentName, DocumentLink," +
                " CertificateLink, CertificateDescription, CertificateImageLink, StartDate, STATUS) VALUES " +
                "('" + ID + "', '" + ImageLink + "', '" + Video + "', '" + DocumentName + "', '" + DocumentLink
                + "', '" + CertificateLink  + "', '" +  CertificateDescription  + "', '" + CertificateImageLink + "', '" + t + "', 1)";
       // System.out.println(sql);
        statement.executeUpdate(sql);
    }

    public void NewRowParam(Statement statement, Timestamp t, Integer ID, String ParamName, String Value, String Unit) throws SQLException {
        String sql = "INSERT INTO param(ID, ParamName, Value, Unit, StartDate, STATUS) VALUES" +
                " (" + ID + ",'" + ParamName + "','" + Value + "','" + Unit + "','" + t + "',1)";
        statement.executeUpdate(sql);
    }

    public void NewRowRecommended(Statement statement, Timestamp t, Integer ID, String Price, String Currency) throws SQLException {
        String sql = "INSERT INTO recommendedretailpricewithvat(ID, Price, Currency, StartDate, STATUS) VALUES" +
                " (" + ID + ",'" + Price + "','" + Currency + "','" + t + "',1)";
        statement.executeUpdate(sql);
    }

    public void NewRowVAT(Statement statement, Timestamp t, Integer ID, String Rate, String Country) throws SQLException {
        String sql = "INSERT INTO vat(ID, Rate, Country, StartDate, STATUS) VALUES" +
                " (" + ID + ",'" + Rate + "','" + Country + "','" + t + "',1)";
        statement.executeUpdate(sql);
    }

    public void NewRowData(Statement statement, String table, String value, String col, Timestamp t) throws SQLException {
        statement.executeUpdate(buildSQLString(table, value, col, t));
    }

    public String buildSQLString(String table, String value, String col, Timestamp t) {
        String sql = "INSERT INTO" + table + "(" + col +  ", StartDate, STATUS) VALUES ('" + value + "," + t + ",1)";
        return sql;
    }

    public void CheckExist(Statement statement, String table, Integer ID) throws SQLException {
        ResultSet r = statement.executeQuery("SELECT COUNT(1) FROM " + table + " WHERE ID =" + ID + " AND STATUS = 1");
        if(r != null){
            String sql = "UPDATE " + table  + " SET STATUS = 0 WHERE ID = " + ID;
            statement.executeUpdate(sql);
            //System.out.println(sql);
        }
        //IF (SELECT COUNT(1) FROM [Table] where [field]='abcd')>0
    }
}