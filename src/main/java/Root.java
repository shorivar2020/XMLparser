import java.util.HashMap;
import java.util.HashSet;

public class Root {
    @Override
    public String toString() {
        return "Root{ID" + ID + "/" + EAN + "/" + PartNumber + "/"
                + Name + "/" + Title + "/" + Language + "/"
                + ITEMGROUP_ID + "/" + Manufacturer + "/" + Supplier + "/"
                + CountryOfOrigin + "/" + MeasureUnit + "/"
                + RecommendedRetailPriceWithVat + "/" + VAT + "/" + ShortDescription + "/"
                + LargeDescription + "/" + ImageLink + "/" + AdditionalImageLink + Document + "}";
    }

    public Integer getID() {
        return ID;
    }

    private Integer ID;
    private String EAN;
    private String PartNumber;
    private String Name;
    private String Title;
    private String Language;
    private Integer ITEMGROUP_ID;
    private String Manufacturer;
    private String Supplier;
    private String CountryOfOrigin;
    private String MeasureUnit;
    private HashMap<String, String> RecommendedRetailPriceWithVat = new HashMap<String, String>();
    private HashMap<String, String> VAT;
    private String ShortDescription;
    private String LargeDescription;
    //Documents
    private String ImageLink;
    private HashSet<String> AdditionalImageLink;
    private HashMap<String, String> Document;



    private Certificate certificates;
    private String Video;
    private HashSet<Availability> Availability;
    private String Guarantee;
    private String GuaranteeType;
    private HashSet<Conditions> Conditions;
    private String Season;
    private HashSet<Param> Param;

    public void setEAN(String EAN) {
        this.EAN = EAN;
    }

    public void setPartNumber(String partNumber) {
        this.PartNumber = partNumber;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public void setITEMGROUP_ID(Integer ITEMGROUP_ID) {
        this.ITEMGROUP_ID = ITEMGROUP_ID;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public void setSupplier(String supplier) {
        Supplier = supplier;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        CountryOfOrigin = countryOfOrigin;
    }

    public void setMeasureUnit(String measureUnit) {
        MeasureUnit = measureUnit;
    }

    public void setRecommendedRetailPriceWithVat(String Price, String Currency) {
        this.RecommendedRetailPriceWithVat.put(Price, Currency);
    }

    public void clearRecommendedRetailPriceWithVat() {
        this.RecommendedRetailPriceWithVat.clear();
    }

    public void setVAT(String Rate, String Country) {
        this.VAT.put(Rate, Country);
    }

    public void setShortDescription(String shortDescription) {
        ShortDescription = shortDescription;
    }

    public void setLargeDescription(String largeDescription) {
        LargeDescription = largeDescription;
    }

    public void setImageLink(String imageLink) {
        ImageLink = imageLink;
    }

    public void setAdditionalImageLink(HashSet<String> additionalImageLink) {
        AdditionalImageLink = additionalImageLink;
    }

    public void setDocument(HashMap<String, String> document) {
        Document = document;
    }

    public void setCertificates(Certificate certificates) {
        this.certificates = certificates;
    }

    public void setVideo(String video) {
        Video = video;
    }

    public void setAvailability(Availability availability) {
        Availability.add(availability);
    }

    public void setGuarantee(String guarantee) {
        Guarantee = guarantee;
    }

    public void setGuaranteeType(String guaranteeType) {
        GuaranteeType = guaranteeType;
    }

    public void setConditions(Conditions conditions) {
        Conditions.add(conditions);
    }

    public void setSeason(String season) {
        Season = season;
    }

    public void setParam(Param param) {
        Param.add(param);
    }
    public void setID(Integer ID) { this.ID = ID;}


}
class Certificate{
    public String Link;
    public String Descriptions;
    public String ImageLink;
}

class Param{
    public String Name;
    public String Value;
    public String Unit;
}

class Conditions{
    public Boolean IsNew;
    public Boolean IsSale;
    public Boolean IsOutlet;
}

class Availability{
    public Integer internal;
    public Integer external;
    public Integer manufacturer;
}

