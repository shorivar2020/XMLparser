import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
            //System.out.println(products);
            Connection connection = action.getConnection();
            log.info("Connection was succefull");
            Statement statement = connection.createStatement();
            action.NewProducts(statement);
            log.info("Create Table");
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            //System.out.println(timestamp);
            products.forEach((k, r) -> {
                try {
                    action.NewRowDocuments(statement, timestamp, r.getID(), r.getImageLink().toString(), null,
                            r.getDocumentName(), r.getDocumentLink(), r.getCertificates().getLink(), r.getCertificates().getDescriptions(),
                            r.getCertificates().getImageLink());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

//            action.NewRowDocuments(statement, timestamp, 98265,  "https://www.summitacademy.cz/Files/ProductDocuments/1488378e-48b1-477a-8e33-3771de1d18ee.jpg", null,
//                    null, null, null, null,
//                    null);
               ;
//            products.forEach((k, r) -> System.out.printf(r.getID() + "/" + r.getImageLink().toString() + "/" + r.getVideo(),
//                        r.getDocumentName() + "/" + r.getDocumentLink() + "/" + r.getCertificates().getLink() + "/" + r.getCertificates().getDescriptions() + "/" + r.getCertificates().getImageLink()));
      //          System.out.println( "/" + products.get(i).getID() + "/" +  "/" + "/" + "/");

//            action.NewRow(statement, 228, "228");
//            log.info("INSERT NEW DATA");
//            action.destroyConnection(connection);
//            log.info("Connection was destroyed");
        } catch (SAXException e){
            log.error("OPEN SAX Exception: ", e);
        } catch (IOException e) {
            log.error("OPEN SAX ERROR Exception: ", e);
        } catch (SQLException throwables) {
            log.error("SQL Exception: ", throwables);
        }
    }
}