import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Handler extends DefaultHandler {

    private Root root;
    private HashMap<Integer, Root> products;
    private List<Integer> IDlist;
    private List<Root> prod;
    private String currentTagName;
    private String CUR_Price;
    private String CUR_Currency;
    private String Rate;
    private String Country;

    public HashMap<Integer, Root> getProducts(){
        return products;
    }

    @Override
    public void startDocument() {
        System.out.println("Start Document");
        products = new HashMap<>();
        IDlist = new ArrayList<>();
        prod = new ArrayList<>();
    }

    @Override
    public void endDocument() {
        System.out.println("End Document");
            //System.out.println(products);
        for (int i= 0; i<10; i++){
            //System.out.println(IDlist.get(i));
            //System.out.println(products.get(IDlist.get(i)));
            //System.out.println(prod.get(i));
        }
       // System.out.println(products);
       // System.out.println(prod);
    }

    @Override
    public void startElement(
            String uri,
            String localName,
            String qName,
            Attributes attributes) {
            currentTagName = qName;
        if(qName.equals("Product")) {
            root = new Root();
        }
            //root.clearRecommendedRetailPriceWithVat();
            //root = new Root();
    }

    @Override
    public void endElement(String uri,
                           String localName,
                           String qName) {
        if(qName != null){
            if(qName.equals("Product")){
                //System.out.println(root);
                products.put(root.getID(), root);
                IDlist.add(root.getID());
                prod.add(root);
                //System.out.println(prod);
                root.clearRecommendedRetailPriceWithVat();
            }
       }
        currentTagName = null;
    }

    @Override
    public void characters(char ch[], int start, int length) {
        String value = new String(ch, start, length);
        if(currentTagName == null){
            return;
        }
        if(currentTagName.equals("ID")) {
            root.setID(new Integer(value));
        }if(currentTagName.equals("EAN")){
            root.setEAN((value));
        }if(currentTagName.equals("PartNumber")){
            root.setPartNumber((value));
        }if(currentTagName.equals("Name")){
            root.setName((value));
        }if(currentTagName.equals("Title")){
            root.setTitle((value));
        }if(currentTagName.equals("Language")){
            root.setLanguage((value));
        }if(currentTagName.equals("ITEMGROUP_ID")){
            root.setITEMGROUP_ID(new Integer(value));
        }if(currentTagName.equals("Manufacturer")){
            root.setManufacturer((value));
        }if(currentTagName.equals("Supplier")){
            root.setSupplier((value));
        }if(currentTagName.equals("CountryOfOrigin")){
            root.setCountryOfOrigin((value));
        }if(currentTagName.equals("MeasureUnit")){
            root.setMeasureUnit((value));
        }if(currentTagName.equals("ITEMGROUP_ID")){
            root.setITEMGROUP_ID(new Integer(value));
        }if(currentTagName.equals("Price")){
               CUR_Price = (new String(value));
        }if(currentTagName.equals("Currency")){
                CUR_Currency = value;
            if(CUR_Price != null && CUR_Currency != null)
                root.setRecommendedRetailPriceWithVat(CUR_Price, CUR_Currency);
            CUR_Price = null;
        }if(currentTagName.equals("Rate")){
                Rate = (value);
//        }if(currentTagName.equals("Country")){
//                Country = (value);
//                if(Rate != null && Country != null){
//                    root.setVAT(Rate, Country);
//                }
//        }if(currentTagName.equals("ITEMGROUP_ID")){
//            root.setITEMGROUP_ID(new Integer(value));
//        }if(currentTagName.equals("ITEMGROUP_ID")){
//            root.setITEMGROUP_ID(new Integer(value));
//        }if(currentTagName.equals("ITEMGROUP_ID")){
//            root.setITEMGROUP_ID(new Integer(value));
//        }if(currentTagName.equals("ITEMGROUP_ID")){
//            root.setITEMGROUP_ID(new Integer(value));
        }
    }

}