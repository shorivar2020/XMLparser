//import org.xml.sax.SAXException;
//
//import javax.xml.parsers.SAXParser;
//import javax.xml.parsers.SAXParserFactory;
//import java.io.File;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.HashMap;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///* +gets parameter when executing from commandline (as input parameter)
//   +The parameter contains path to configuration text file
//   +Configuration file has typical *.INI file format  attribute=value
//   +Configuration file contains URL, where your application needs to connect and download a response (the response contains huge XML file) – and connection details needed to connect to DB.
//   ~Application takes this received XML data, parses through the data and saves the data to a DB table.
//   ~DB can be any SQL DB you select – ideally some hosted DB on public space (free unpaid space) or DB installed on your local PC
//   -There needs to be a track of history of such data, so lets say, that each run if the import to DB will create new data set. If I connect later to DB, I should be able to see data set per timestamp of the application run.
//   ~Structure of DB table is up to you. The table should contain all data from the input XML.
//   ~The application should have log to a text file, which reflect log level (ERROR, INFO, WARNING, DEBUG).
//   -Don’t forget to report and somehow “cover” cases when input XML suddenly changes (typically one new parameter will appear.*/
//public class Main {
//    private static Logger log = Logger.getLogger(Main.class.getName());
//    private static boolean isFound;
//
//    public static void main(String[] args) throws Exception {
////        Scanner scanner = new Scanner(System.in);
////        String path = scanner.nextLine();
//        String path = "config.ini";
//        log.log(Level.INFO, "Path was received");
//        IniReader ini = new IniReader(path);
//        String url = ini.url;
//        log.log(Level.INFO, "Data was received from Config file");
//        DownloadFileFromUrl dffu = new DownloadFileFromUrl(url);
//        log.log(Level.INFO, "File was download");
//        File file = new File("information.xml");
//        try{
//            Handler handler = new Handler();
//            SAXParserFactory factory = SAXParserFactory.newInstance();
//            SAXParser parser = factory.newSAXParser();
//            parser.parse(file, handler);
//            HashMap<Integer, Root> products = handler.getProducts();
//            //System.out.println(products);
//            log.log(Level.INFO, "XML parser");
//            Action action = new Action();
//            Connection connection = action.getConnection();
//            log.log(Level.INFO, "Connection was succefull");
//            Statement statement = connection.createStatement();
//            action.NewProducts(statement);
//            log.log(Level.INFO, "Create Table");
//            action.NewRow(statement, 228, "228");
//            log.log(Level.INFO, "INSERT NEW DATA");
//            action.destroyConnection(connection);
//            log.log(Level.INFO, "Connection was destroyed");
//        } catch (SAXException e){
//            log.log(Level.SEVERE, "OPEN SAX Exception: ", e);
//        } catch (IOException e) {
//            log.log(Level.SEVERE, "OPEN SAX ERROR Exception: ", e);
//        } catch (SQLException throwables) {
//            log.log(Level.SEVERE, "SQL Exception: ", throwables);
//        }
//        if (!isFound)
//            log.log(Level.WARNING, "Element not found");
//    }
//}
