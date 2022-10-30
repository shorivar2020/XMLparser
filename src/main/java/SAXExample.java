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
    private static boolean isFound;


    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        Handler handler = new Handler();
        HashMap<Integer, Root> products;
        Action action = new Action();
        File file = new File("information.xml");
        try{
            parser.parse(file, handler);
            products = handler.getProducts();
            //System.out.println(products);
            Connection connection = action.getConnection();
            Statement statement = connection.createStatement();
            action.NewProducts(statement);
            action.NewRow(statement, 228, "228");
            action.destroyConnection(connection);
        } catch (SAXException e){
            System.out.println("OPEN SAX ERROR" + e.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (!isFound)
            System.out.println("Элемент не был найден.");
    }
}