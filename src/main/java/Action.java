import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*Class for connection with DB and work only with mySQL Console*/
public class Action {
    org.slf4j.Logger log;
    public Action(org.slf4j.Logger log) {
        this.log = log;
    }

    public Connection getConnection(String URL, String USER, String PASS){
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            if(!connection.isClosed()){
                log.info("Connect DB");
            }
            return connection;
        } catch (SQLException e) {
            log.error("Cannot connect DB", e);
        }
        return null;
    }

    public void destroyConnection(Connection connection) throws SQLException {
        connection.close();
        if(connection.isClosed()){
            log.info("Connection was closed");
        }
    }

    public void dropTables(Statement statement) throws SQLException{
        statement.executeUpdate("DROP table IF EXISTS RecommendedRetailPriceWithVat");
        statement.executeUpdate("DROP table IF EXISTS VAT");
        statement.executeUpdate("DROP table IF EXISTS Documents");
        statement.executeUpdate("DROP table IF EXISTS AditionalImage");
        statement.executeUpdate("DROP table IF EXISTS Availability");
        statement.executeUpdate("DROP table IF EXISTS Conditions");
        statement.executeUpdate("DROP table IF EXISTS Param");
        statement.executeUpdate("DROP table IF EXISTS UndefinedData");
        statement.executeUpdate("DROP table IF EXISTS Products");

        log.info("All tables was dropped");
    }

    //Create all tables
    public void NewProducts(Statement statement) throws SQLException {
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS Products " +
                "(ID INTEGER, EAN VARCHAR(64), PartNumber VARCHAR(64), ProductName VARCHAR(64)," +
                "DocTitle VARCHAR(2560), DocLanguage VARCHAR(64), ITEMGROUP_ID VARCHAR(64)," +
                "Manufacturer VARCHAR(64), Supplier VARCHAR(64), CountryOfOrigin VARCHAR(64)," +
                " MeasureUnit VARCHAR(64), ShortDescription VARCHAR(2560), LargeDescription TEXT," +
                "Guarantee VARCHAR(64), GuaranteeType VARCHAR(64), Season VARCHAR(64)," +
                "StartDate TIMESTAMP, STATUS INTEGER, PRIMARY KEY (ID));");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS RecommendedRetailPriceWithVat " +
                "(ID INTEGER, Price VARCHAR (64), Currency VARCHAR (64)," +
                "StartDate TIMESTAMP, STATUS INTEGER, FOREIGN KEY (ID) REFERENCES Products(ID));");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS VAT " +
                "(ID INTEGER, Rate VARCHAR (64), Country VARCHAR (64)," +
                "StartDate TIMESTAMP, STATUS INTEGER, FOREIGN KEY (ID) REFERENCES Products(ID));");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS Documents " +
                "(ID INTEGER, ImageLink VARCHAR (256), Video VARCHAR (64), DocumentName VARCHAR(64)," +
                " DocumentLink VARCHAR(64), CertificateLink VARCHAR(64)," +
                " CertificateDescription VARCHAR(64)," +
                "CertificateImageLink VARCHAR(64)," +
                "StartDate TIMESTAMP, STATUS INTEGER, FOREIGN KEY (ID) REFERENCES Products(ID));");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS AditionalImage " +
                "(ID INTEGER, AditionalImageLink VARCHAR(256)," +
                "StartDate TIMESTAMP, STATUS INTEGER, FOREIGN KEY (ID) REFERENCES Products(ID));");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS Availability" +
                "(ID INTEGER, availabilityInternal INTEGER, availabilityExternal INTEGER," +
                " availabilityManufacture INTEGER," +
                "StartDate TIMESTAMP, STATUS INTEGER, FOREIGN KEY (ID) REFERENCES Products(ID));");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS Conditions " +
                "(ID INTEGER, IsNew VARCHAR(64), IsSale VARCHAR(64), IsOutlet VARCHAR(64)," +
                "StartDate TIMESTAMP, STATUS INTEGER, FOREIGN KEY (ID) REFERENCES Products(ID));");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS Param " +
                "(ID INTEGER, ParamName VARCHAR(64), Value VARCHAR(256), Unit VARCHAR(64)," +
                "StartDate TIMESTAMP, STATUS INTEGER, FOREIGN KEY (ID) REFERENCES Products(ID));");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS UndefinedData " +
                "(ID INTEGER, Tag VARCHAR(64), ValueOfData VARCHAR(64)," +
                "StartDate TIMESTAMP, STATUS INTEGER, FOREIGN KEY (ID) REFERENCES Products(ID));");
        log.info("All tables was created");
    }



    public void NewRowProducts(Statement statement, String t, Integer ID, String EAX, String PartNumber,
                               String ProductName, String DocTitle, String DocLanguage, Integer ITEMGROUP_ID,
                               String Manufacturer, String Supplier, String CountryOfOrigin,
                               String MeasureUnit, String ShortDescription,
                               String LargeDescription, String Guarantee, String GuaranteeType,
                               String Season) throws SQLException{
        String sql = "INSERT INTO Products (ID, EAN, PartNumber, ProductName, DocTitle, DocLanguage, " +
                "ITEMGROUP_ID," +
                "Manufacturer, Supplier, CountryOfOrigin, MeasureUnit, ShortDescription, LargeDescription, " +
                "Guarantee," +
                "GuaranteeType, Season, StartDate, STATUS) VALUES (" + ID + ", '" + EAX + "', '" +
                PartNumber + "', '" + ProductName.replace("'", "\\'")
                + "', '" + DocTitle.replace("'", "\\'") + "', '" + DocLanguage + "', " +
                ITEMGROUP_ID + ", '" + Manufacturer
                + "', '" + Supplier + "', '" + CountryOfOrigin + "', '" + MeasureUnit + "', '" +
                ShortDescription.replace("'", "\\'")
                + "', '" + LargeDescription.replace("'", "\\'") + "', '" + Guarantee
                + "', '" + GuaranteeType + "', '" + Season + "', '" + t + "', 1)";
        statement.executeUpdate(sql);
        log.info("Insert new Product Data");
    }

    public void NewRowAditionalImage(Statement statement, String t, Integer ID, String AditionalImageLink)
            throws SQLException {
        String sql = "INSERT INTO aditionalimage (ID, AditionalImageLink, StartDate, STATUS)" +
                " VALUES (" + ID + ",'" + AditionalImageLink + "','" + t + "',1)";
        statement.executeUpdate(sql);
        log.info("Insert new AditionalImage Data");
    }

    public void NewUndefinedData(Statement statement, String t, Integer ID, String Tag,
                                 String ValueOfData) throws SQLException {
        String sql = "INSERT INTO undefineddata (ID, Tag, ValueOfData, StartDate, STATUS)" +
                " VALUES (" + ID + ",'" + Tag + "','" + ValueOfData + "','" + t + "',1)";
        statement.executeUpdate(sql);
        log.info("Insert new Undefined Data");
    }

    public void NewRowAvailability(Statement statement, String t, Integer ID, Integer i,
                                   Integer e, Integer m) throws SQLException {
        String sql = "INSERT INTO availability(ID, availabilityInternal, availabilityExternal, " +
                "availabilityManufacture, StartDate, STATUS) VALUES (" + ID + "," + i + ","
                + e + "," + m + ",'" + t + "',1)";
        statement.executeUpdate(sql);
        log.info("Insert new Availability Data");
    }

    public void NewRowConditions(Statement statement, String t, Integer ID, String IsNew,
                                 String IsSale, String IsOutlet) throws SQLException {
        String sql = "INSERT INTO conditions(ID, IsNew, IsSale, IsOutlet, StartDate, STATUS)" +
                " VALUES (" + ID + ",'" + IsNew + "','" + IsSale + "','" + IsOutlet + "','" + t + "',1)";
        statement.executeUpdate(sql);
        log.info("Insert new Conditions Data");
    }

    public void NewRowDocuments(Statement statement, String t, Integer ID, String ImageLink, String Video,
                                String DocumentName, String DocumentLink, String CertificateLink,
                                String CertificateDescription, String CertificateImageLink)
            throws SQLException {
        String sql = "INSERT INTO documents (ID, ImageLink, Video, DocumentName, DocumentLink," +
                " CertificateLink, CertificateDescription, CertificateImageLink, StartDate, STATUS)" +
                " VALUES " +
                "('" + ID + "', '" + ImageLink + "', '" + Video + "', '" + DocumentName
                + "', '" + DocumentLink
                + "', '" + CertificateLink  + "', '" +  CertificateDescription
                + "', '" + CertificateImageLink + "', '" + t + "', 1)";
        statement.executeUpdate(sql);
        log.info("Insert new Documents Data");
    }

    public void NewRowParam(Statement statement, String t, Integer ID, String ParamName,
                            String Value, String Unit) throws SQLException {
        String sql = "INSERT INTO param(ID, ParamName, Value, Unit, StartDate, STATUS) VALUES" +
                " (" + ID + ", '" + ParamName + "', '" + Value + "', '" + Unit + "', '" + t + "',1)";
        statement.executeUpdate(sql);
        log.info("Insert new Param Data");
    }

    public void NewRowRecommended(Statement statement, String t, Integer ID,
                                  String Price, String Currency) throws SQLException {
        String sql = "INSERT INTO recommendedretailpricewithvat" +
                "(ID, Price, Currency, StartDate, STATUS) VALUES" +
                " (" + ID + ",'" + Price + "','" + Currency + "','" + t + "',1)";
        statement.executeUpdate(sql);
        log.info("Insert new Recommended Data");
    }

    public void NewRowVAT(Statement statement, String t, Integer ID, String Rate, String Country)
            throws SQLException {
        String sql = "INSERT INTO vat(ID, Rate, Country, StartDate, STATUS) VALUES" +
                " (" + ID + ",'" + Rate + "','" + Country + "','" + t + "',1)";
        statement.executeUpdate(sql);
        log.info("Insert new VAT Data");
    }

    public void NewRowData(Statement statement, String table, String value, String col, Timestamp t)
            throws SQLException {
        statement.executeUpdate(buildSQLString(table, value, col, t));
        log.info("Insert new Data");
    }

    public String buildSQLString(String table, String value, String col, Timestamp t) {
        String sql = "INSERT INTO" + table + "(" + col +  ", StartDate, STATUS) VALUES ('" + value + ","
                + t + ",1)";
        return sql;
    }

    public void CheckExist(Statement statement, String table, Integer ID, String time) throws SQLException {
        String sql = "SELECT COUNT(1) FROM " + table + " WHERE ID =" + ID
                + " AND STATUS = 1 AND StartDate < '" + time + "'";
        ResultSet r = statement.executeQuery(sql);
        if(r != null){
            log.info("New packet of DATA in" + table);
            String sql1 = "UPDATE " + table  + " SET STATUS = 0 WHERE ID = " + ID;
            statement.executeUpdate(sql1);
            log.info("Update status in " + table);
        }
    }
}