import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;

public class Handler extends DefaultHandler {

    private Root root;
    private HashMap<Integer, Root> products;

    private String currentTagName;
    private String CUR_Price;
    private String CUR_Currency;
    private String DocName;
    private String CertLink;
    private String CertDescription;
    private Integer external;
    private Integer internal;
    private Integer manufacturer;
    private Boolean isNew;
    private Boolean isSale;
    private Boolean isOutlet;
    private String nameParam;
    private String valueParam;
    private String unitParam;

    private Boolean isParam = false;
    private Boolean isDoc = false;
    private Boolean isShortDescription = false;
    private Boolean isLargeDescription = false;
    private Boolean isCertificate = false;
    private Boolean isAvailability = false;

    public HashMap<Integer, Root> getProducts(){
        return products;
    }

    @Override
    public void startDocument() {
        System.out.println("Start Document");
        products = new HashMap<>();
    }

    @Override
    public void endDocument() {
        System.out.println("End Document");
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
        }if(qName.equals("Param")){
            isParam = true;
        }if(qName.equals("Document")){
            isDoc = true;
        }if(qName.equals("ShortDescription")){
            isShortDescription = true;
        }if(qName.equals("LargeDescription")){
            isLargeDescription = true;
        }if(qName.equals("Availability")){
            isAvailability = true;
        }if(qName.equals("Certificate")){
            isCertificate= true;
        }
    }

    @Override
    public void endElement(String uri,
                           String localName,
                           String qName) {
        if(qName != null){
            if(qName.equals("Product")){
                products.put(root.getID(), root);
            }if(qName.equals("Param")){
                isParam = false;
            }if(qName.equals("Document")){
                isDoc = false;
            }if(qName.equals("ShortDescription")){
                isShortDescription = false;
            }if(qName.equals("LargeDescription")){
                isLargeDescription = false;
            }if(qName.equals("Availability")){
                isAvailability = false;
            }if(qName.equals("Certificate")){
                isCertificate= false;
            }
       }
        currentTagName = null;
    }

    @Override
    public void characters(char ch[], int start, int length) {
        String value = new String(ch, start, length);
        if(currentTagName == null){
            return;
        }if(currentTagName.equals("ID")) {
            root.setID(new Integer(value));
        }if(currentTagName.equals("EAN")){
            root.setEAN((value));
        }if(currentTagName.equals("PartNumber")){
            root.setPartNumber((value));
        }if(currentTagName.equals("Name") && !isParam && !isDoc){
            root.setName(value);
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
        }if(currentTagName.equals("Price")){
               CUR_Price = value;
        }if(currentTagName.equals("Currency")){
                CUR_Currency = value;
            if(CUR_Price != null && CUR_Currency != null) {
                root.setRecommendedRetailPriceWithVat(CUR_Price, CUR_Currency);
                CUR_Price = null;
                CUR_Currency = null;
            }
        }if(currentTagName.equals("Rate")){
            root.setVATRate("Základní sazba");
        }if(currentTagName.equals("Country")){
            root.setVATCountry(value);
        }if(currentTagName.equals("ShortDescription")){
            //System.out.println(value);
            if(isShortDescription){
                root.setShortDescription(value);
            }
        }if(currentTagName.equals("LargeDescription")){
            if(isLargeDescription) {
                root.setLargeDescription(value);
            }
        }if(currentTagName.equals("ImageLink")) {
            root.setImageLink(value);
        }if(currentTagName.equals("AdditionalImageLink")){
            root.setAdditionalImageLink(value);
        }if(currentTagName.equals("Name") && !isParam && isDoc){
            DocName = value;
        }if(currentTagName.equals("Link")&& !isParam && isDoc){
            root.setDocument(DocName,value);
        }if(currentTagName.equals("Link")&& isCertificate){
            CertLink = value;
        }if(currentTagName.equals("Description") && isCertificate){
            CertDescription = value;
        }if(currentTagName.equals("ImageLink") && isCertificate){
            root.setCertificates(CertLink, CertDescription, value);
        }if(currentTagName.equals("Video")){
            root.setVideo(value);
        }if(currentTagName.equals("internal") && isAvailability){
            internal = new Integer(value);
        }if(currentTagName.equals("external") && isAvailability){
            external = new Integer(value);
        }if(currentTagName.equals("manufacturer") && isAvailability){
            manufacturer = new Integer(value);
            root.setAvailability(internal, external, manufacturer);
        }if(currentTagName.equals("Guarantee")){
            root.setGuarantee(value);
        }if(currentTagName.equals("GuaranteeType")){
            root.setGuaranteeType(value);
        }if(currentTagName.equals("IsNew")){
            isNew = Boolean.valueOf(value);
        }if(currentTagName.equals("IsSale")){
            isSale = Boolean.valueOf(value);
        }if(currentTagName.equals("IsOutlet")){
            isOutlet = Boolean.valueOf(value);
            root.setConditions(isNew, isSale, isOutlet);
        }if(currentTagName.equals("Season")){
            root.setSeason(value);
        }if(currentTagName.equals("Name") && isParam){
            nameParam = value;
        }if(currentTagName.equals("Value")&& isParam){
            valueParam = value;
        }if(currentTagName.equals("Unit")&& isParam){
            unitParam = value;
            root.setParam(nameParam, valueParam, unitParam);

        }
    }

}