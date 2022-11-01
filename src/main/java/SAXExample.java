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
           // action.CheckExist(statement, "vat", 81925);
            //System.out.println(timestamp);
            products.forEach((k, r) -> {
                r.getUndefinedData().forEach((l, m) -> {
                    try {
                        action.CheckExist(statement, "undefineddata", r.getID());
                        action.NewUndefinedData(statement, timestamp, r.getID(), l, m);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            });
//            products.forEach((k, r) -> {
//                try {
//                    action.CheckExist(statement, "documents", r.getID());
//                    action.NewRowDocuments(statement, timestamp, r.getID(), r.getImageLink().toString(), null,
//                            r.getDocumentName(), r.getDocumentLink(), r.getCertificates().getLink(), r.getCertificates().getDescriptions(),
//                            r.getCertificates().getImageLink());
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            });
//            products.forEach((k, r) -> {
//                r.getAdditionalImageLink().forEach((l) -> {
//                    try {
//                        action.CheckExist(statement, "aditionalimage", r.getID());
//                        action.NewRowAditionalImage(statement, timestamp, r.getID(), l);
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                });
//            });
//            products.forEach((k, r) -> {
//                try {
//                        action.CheckExist(statement, "availability", r.getID());
//                    action.NewRowAvailability(statement, timestamp, r.getID(), r.getAvailability().getInternal(), r.getAvailability().getExternal(),
//                            r.getAvailability().getManufacturer());
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            });
//            products.forEach((k, r) -> {
//                try {
//                        action.CheckExist(statement, "conditions", r.getID());
//                    action.NewRowConditions(statement, timestamp, r.getID(), String.valueOf(r.getConditions().getNew()),
//                            String.valueOf(r.getConditions().getSale()), String.valueOf(r.getConditions().getOutlet()));
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            });
//            products.forEach((k, r) -> {
//                r.getParam().forEach((l) -> {
//                    try {
//                        action.CheckExist(statement, "param", r.getID());
//                        action.NewRowParam(statement, timestamp, r.getID(), l.getName(), l.getValue(), l.getUnit());
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                });
//            });
//            products.forEach((k, r) -> {
//                r.getRecommendedRetailPriceWithVat().forEach((l, m) -> {
//                    try {
//                        action.CheckExist(statement, "recommendedretailpricewithvat", r.getID());
//                        action.NewRowRecommended(statement, timestamp, r.getID(), l, m);
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                });
//            });
//            products.forEach((k, r) -> {
//                r.getVATCountry().forEach((l) -> {
//                    try {
//                        action.CheckExist(statement, "vat", r.getID());
//                        action.NewRowVAT(statement, timestamp, r.getID(), r.getVATRate(), l);
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                });
//            });
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