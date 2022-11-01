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
        statement.executeUpdate("DROP table IF EXISTS Products");
        statement.executeUpdate("DROP table IF EXISTS RecommendedRetailPriceWithVat");
        statement.executeUpdate("DROP table IF EXISTS VAT");
        statement.executeUpdate("DROP table IF EXISTS Documents");
        statement.executeUpdate("DROP table IF EXISTS AditionalLink");
        statement.executeUpdate("DROP table IF EXISTS Availability");
        statement.executeUpdate("DROP table IF EXISTS Conditions");
        statement.executeUpdate("DROP table IF EXISTS Param");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS Products " +
                "(ID INTEGER, EAN VARCHAR(64), PartNumber VARCHAR(64), ProductName VARCHAR(64)," +
                "DocTitle VARCHAR(64), DocLanguage VARCHAR(64), ITEMGROUP_ID VARCHAR(64)," +
                "Manufacturer VARCHAR(64), Supplier VARCHAR(64), CountryOfOrigin VARCHAR(64)," +
                " MeasureUnit VARCHAR(64), ShortDescription VARCHAR(256), LargeDescription VARCHAR(256)," +
                "Guarantee VARCHAR(64), GuaranteeType VARCHAR(64), Season VARCHAR(64)," +
                "StartDate TIMESTAMP, EndDate TIMESTAMP,  IsCurrent VARCHAR(64));");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS RecommendedRetailPriceWithVat " +
                "(ID INTEGER, Price VARCHAR (64), Currency VARCHAR (64)," +
                "StartDate TIMESTAMP, EndDate TIMESTAMP,  IsCurrent VARCHAR(64));");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS VAT " +
                "(ID INTEGER, Rate VARCHAR (64), Country VARCHAR (64)," +
                "StartDate TIMESTAMP, EndDate TIMESTAMP,  IsCurrent VARCHAR(64));");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS Documents " +
                "(ID INTEGER, ImageLink VARCHAR (64), Video VARCHAR (64), DocumentName VARCHAR(64)," +
                " DocumentLink VARCHAR(64), CertificateLink VARCHAR(64), CertificateDescription VARCHAR(64)," +
                "CertificateImageLink VARCHAR(64)," +
                "StartDate TIMESTAMP, EndDate TIMESTAMP,  IsCurrent VARCHAR(64));");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS AditionalImage " +
                "(ID INTEGER, AditionalImageLink VARCHAR (64)," +
                "StartDate TIMESTAMP, EndDate TIMESTAMP,  IsCurrent VARCHAR(64));");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS Availability" +
                "(ID INTEGER, availabilityInternal INTEGER, availabilityExternal INTEGER," +
                " availabilityManufacture INTEGER," +
                "StartDate TIMESTAMP, EndDate TIMESTAMP,  IsCurrent VARCHAR(64));");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS Conditions " +
                "(ID INTEGER, IsNew VARCHAR(64), IsSale VARCHAR(64), IsOutlet VARCHAR(64)," +
                "StartDate TIMESTAMP, EndDate TIMESTAMP,  IsCurrent VARCHAR(64));");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS Param " +
                "(ID INTEGER, ParamName VARCHAR(64), Value VARCHAR(64), Unit VARCHAR(64)," +
                "StartDate TIMESTAMP, EndDate TIMESTAMP,  IsCurrent VARCHAR(64));");
    }

    public void NewRowProducts(Statement statement, Integer ID, String EAX, Integer PartNumber,
                       String ProductName, String DocTitle, String DocLanguage, String ITEMGROUP_ID,
                       String Manufacturer, String Supplier, String CountryOfOrigin, String MeasureUnit,
                       String ShortDescription, String LargeDescription, String Guarantee, String GuaranteeType,
                       String Season) throws SQLException{
        String sql = "INSERT INTO Products (ID, EAX, PartNumber, ProductName, DocTitle, DocLanguage, ITEMGROUP_ID," +
                "Manufacturer, Supplier, CountryOfOrigin, MeasureUnit, ShortDescription, LargeDescription, Guarantee," +
                "GuaranteeType, Season) VALUES (" + ID + "," + EAX + "," + PartNumber + "," + ProductName
                + "," + DocTitle + "," + DocLanguage + "," + ITEMGROUP_ID + "," + Manufacturer
                + "," + Supplier + "," + CountryOfOrigin + "," + MeasureUnit + "," + ShortDescription
                + "," + LargeDescription + "," + Guarantee + "," + GuaranteeType + "," + Season + ")";
        statement.executeUpdate(sql);
        //statement.executeUpdate("insert into Products set EAX = '12345'");
    }

    public void NewRowAditionalImage(Statement statement, Integer ID, String AditionalImageLink) throws SQLException {
        String sql = "INSERT INTO aditionalimage (ID, AditionalImageLink) VALUES (" + ID + "," + AditionalImageLink + ")";
        statement.executeUpdate(sql);
    }

    public void NewRowAvailability(Statement statement, Integer ID, Integer i, Integer e, Integer m) throws SQLException {
        String sql = "INSERT INTO availability(ID, availabilityInternal, availabilityExternal, " +
                "availabilityManufacture) VALUES (" + ID + "," + i + "," + e + "," + m + ")";
        statement.executeUpdate(sql);
    }

    public void NewRowConditions(Statement statement, Integer ID, String IsNew, String IsSale, String IsOutlet) throws SQLException {
        String sql = "INSERT INTO conditions(ID, IsNew, IsSale, IsOutlet) VALUES (" + ID + "," + IsNew + "," + IsSale + "," + IsOutlet + ")";
        statement.executeUpdate(sql);
    }

    public void NewRowDocuments(Statement statement, Integer ID, String ImageLink, String Video,
                                String DocumentName, String DocumentLink, String CertificateLink,
                                String CertificateDescription, String CertificateImageLink) throws SQLException {
        String sql = "INSERT INTO documents(ID, ImageLink, Video, DocumentName, DocumentLink," +
                "CertificateLink, CertificateDescription, CertificateImageLink) VALUES " +
                "(" + ID + "," + ImageLink + "," + Video + "," + DocumentName + "," + DocumentLink
                + "," + CertificateLink  + "," +  CertificateDescription  + "," + CertificateImageLink + ")";
        statement.executeUpdate(sql);
    }

    public void NewRowParam(Statement statement, Integer ID, String ParamName, String Value, String Unit) throws SQLException {
        String sql = "INSERT INTO param(ID, ParamName, Value, Unit) VALUES" +
                " (" + ID + "," + ParamName + "," + Value + "," + Unit + ")";
        statement.executeUpdate(sql);
    }

    public void NewRowRecommended(Statement statement, Integer ID, String Price, String Currency) throws SQLException {
        String sql = "INSERT INTO recommendedretailpricewithvat(ID, Price, Currency) VALUES" +
                " (" + ID + "," + Price + "," + Currency + ")";
        statement.executeUpdate(sql);
    }

    public void NewRowVAT(Statement statement, Integer ID, String Rate, String Country) throws SQLException {
        String sql = "INSERT INTO vat(ID, Rate, Country) VALUES" +
                " (" + ID + "," + Rate + "," + Country + ")";
        statement.executeUpdate(sql);
    }

    public void NewRowData(Statement statement, String table, String value) throws SQLException {
        statement.executeUpdate(buildSQLString(table, value));
    }

    public String buildSQLString(String table, String value) {
        String sql = "INSERT INTO" + table + "(col) VALUES ('" + value + ")";
        return sql;
    }
}