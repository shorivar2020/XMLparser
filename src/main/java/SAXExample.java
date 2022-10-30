import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class SAXExample {
    public static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SAXExample.class);

    public static void main(String[] args) throws ParserConfigurationException, SAXException{

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        Handler handler = new Handler();
        HashMap<Integer, Root> products;
        Action action = new Action();
        File file = new File("information.xml");
        try{
            parser.parse(file, handler);
            products = handler.getProducts();
            log.info("XML parser");
//            System.out.println(products);
//            Connection connection = action.getConnection();
//            log.info("Connection was succefull");
//            Statement statement = connection.createStatement();
//            action.NewProducts(statement);
//            log.info("Create Table");
//            action.NewRow(statement, 228, "228");
//            log.info("INSERT NEW DATA");
//            action.destroyConnection(connection);
//            log.info("Connection was destroyed");
        } catch (SAXException e){
            log.error("OPEN SAX Exception: ", e);
        } catch (IOException e) {
            log.error("OPEN SAX ERROR Exception: ", e);
//        } catch (SQLException throwables) {
//            log.error("SQL Exception: ", throwables);
        }
    }
}