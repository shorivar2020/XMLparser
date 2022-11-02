import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;
/*Reactions on XML tag by SAX*/
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

    public final List<String> tagsXML = Arrays.asList("B2B", "Product", "ID", "EAN", "PartNumber",
            "Name", "Name2", "Title", "Language", "ITEMGROUP_ID", "Manufacturer", "Supplier",
            "CountryOfOrigin", "MeasureUnit", "RecommendedRetailPriceWithVat", "Price",
            "Currency", "VAT", "Rate", "Country", "ShortDescription", "LargeDescription", "Documents",
            "ImageLink", "AdditionalImageLink", "Document", "Name", "Link", "Certificate", "Description",
            "ImageLink", "Video", "Availability", "internal", "external", "manufacturer",
            "Guarantee", "GuaranteeType", "Conditions", "IsNew", "IsSale", "IsOutlet", "Season",
            "Param", "Name", "Value", "Unit");

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
    public void startDocument() {  products = new HashMap<>();}

    @Override
    public void endDocument() {
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
        }else if(currentTagName.equals("ID")) {
            root.setID(new Integer(value));
        }else if(currentTagName.equals("EAN")){
            root.setEAN((value));
        }else if(currentTagName.equals("PartNumber")){
            root.setPartNumber((value));
        }else if(currentTagName.equals("Name") && !isParam && !isDoc){
            root.setName(value);
        }else if(currentTagName.equals("Title")){
            root.setTitle((value));
        }else if(currentTagName.equals("Language")){
            root.setLanguage((value));
        }else if(currentTagName.equals("ITEMGROUP_ID")){
            root.setITEMGROUP_ID(new Integer(value));
        }else if(currentTagName.equals("Manufacturer")){
            root.setManufacturer((value));
        }else if(currentTagName.equals("Supplier")){
            root.setSupplier((value));
        }else if(currentTagName.equals("CountryOfOrigin")){
            root.setCountryOfOrigin((value));
        }else if(currentTagName.equals("MeasureUnit")){
            root.setMeasureUnit((value));
        }else if(currentTagName.equals("Price")){
               CUR_Price = value;
        }else if(currentTagName.equals("Currency")){
                CUR_Currency = value;
            if(CUR_Price != null && CUR_Currency != null) {
                root.setRecommendedRetailPriceWithVat(CUR_Price, CUR_Currency);
                CUR_Price = null;
                CUR_Currency = null;
            }
        }else if(currentTagName.equals("Rate")){
            root.setVATRate("Základní sazba");
        }else if(currentTagName.equals("Country")){
            root.setVATCountry(value);
        }else if(currentTagName.equals("ShortDescription")){
            if(isShortDescription){
                root.setShortDescription(value);
            }
        }else if(currentTagName.equals("LargeDescription")){
            if(isLargeDescription) {
                root.setLargeDescription(value);
            }
        }else if(currentTagName.equals("ImageLink")&& !isCertificate) {
            root.setImageLink(value);
        }else if(currentTagName.equals("AdditionalImageLink")){
            root.setAdditionalImageLink(value);
        }else if(currentTagName.equals("Name") && !isParam && isDoc){
            root.setDocumentName(value);
        }else if(currentTagName.equals("Link")&& !isParam && isDoc){
            root.setDocumentLink(value);
        }else if(currentTagName.equals("Link") && isCertificate){
            this.CertLink = value;
           // System.out.println(isCertificate);
        }else if(currentTagName.equals("Description") && isCertificate){
            this.CertDescription = value;
            //System.out.println(isCertificate);
        }else if(currentTagName.equals("ImageLink") && isCertificate){
            //System.out.println(isCertificate);
            root.setCertificates(CertLink, CertDescription, value);
        }else if(currentTagName.equals("Video")){
            root.setVideo(value);
        }else if(currentTagName.equals("internal") && isAvailability){
            internal = new Integer(value);
        }else if(currentTagName.equals("external") && isAvailability){
            external = new Integer(value);
        }else if(currentTagName.equals("manufacturer") && isAvailability){
            manufacturer = new Integer(value);
            root.setAvailability(internal, external, manufacturer);
        }else if(currentTagName.equals("Guarantee")){
            root.setGuarantee(value);
        }else if(currentTagName.equals("GuaranteeType")){
            root.setGuaranteeType(value);
        }else if(currentTagName.equals("IsNew")){
            isNew = Boolean.valueOf(value);
        }else if(currentTagName.equals("IsSale")){
            isSale = Boolean.valueOf(value);
        }else if(currentTagName.equals("IsOutlet")){
            isOutlet = Boolean.valueOf(value);
            root.setConditions(isNew, isSale, isOutlet);
        }else if(currentTagName.equals("Season")){
            root.setSeason(value);
        }else if(currentTagName.equals("Name") && isParam){
            nameParam = value;
        }else if(currentTagName.equals("Value")&& isParam){
            valueParam = value;
        }else if(currentTagName.equals("Unit")&& isParam){
            unitParam = value;
            root.setParam(nameParam, valueParam, unitParam);
        }else{
            if(!tagsXML.contains(currentTagName)){
                System.out.println(currentTagName + value);
                root.setUndefinedData(currentTagName, value);
            }
        }
    }

}