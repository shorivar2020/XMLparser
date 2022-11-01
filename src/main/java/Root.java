import java.util.*;

public class Root {
//    @Override
//    public String toString() {
//        return "Root{" + ID + "/" + EAN + "/" + PartNumber + "/"
//                + Name + "/" + Title + "/" + Language + "/"
//                + ITEMGROUP_ID + "/" + Manufacturer + "/" + Supplier + "/"
//                + CountryOfOrigin + "/" + MeasureUnit + "/"
//                + RecommendedRetailPriceWithVat + "/" + VATRate +"/" + VATCountry + "/" + ShortDescription + "/"
//                + LargeDescription + "}";
//    }
//    @Override
//    public String toString() {
//        return "Doc{" + ImageLink + "/" + AdditionalImageLink + "/" + Document + "/"
//                + certificates + "/" + Video + "/" + availability + "/"
//                + Guarantee + "/" + GuaranteeType + "/" + Conditions + "/"
//                + Season + "/" + Param + "}";
//    }

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
    private Map<String, String> RecommendedRetailPriceWithVat = new HashMap<String, String>();
    private String WithVat;
    private String VATRate;
    private Set<String> VATCountry = new HashSet<>();
    private ArrayList<String> ShortDescription = new ArrayList<>();
    private ArrayList<String> LargeDescription = new ArrayList<>();
    //Documents
    private ArrayList<String> ImageLink  = new ArrayList<>();
    private HashSet<String> AdditionalImageLink = new HashSet<>();
    private HashMap<String, String> Document = new HashMap<>();
    private Certificate certificates;
    private String Video;
    private Availability availability;
    private String Guarantee;
    private String GuaranteeType;
    private Conditions Conditions;
    private String Season;
    private HashSet<Param> Param  = new HashSet<>();

    public void setID(Integer ID) { this.ID = ID;}

    public void setEAN(String EAN) {
        this.EAN = EAN;
    }

    public void setPartNumber(String partNumber) {
        this.PartNumber = partNumber;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public void setLanguage(String language) {
        this.Language = language;
    }

    public void setITEMGROUP_ID(Integer ITEMGROUP_ID) {this.ITEMGROUP_ID = ITEMGROUP_ID;}

    public void setManufacturer(String manufacturer) {
        this.Manufacturer = manufacturer;
    }

    public void setSupplier(String supplier) {
        this.Supplier = supplier;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.CountryOfOrigin = countryOfOrigin;
    }

    public void setMeasureUnit(String measureUnit) {
        this.MeasureUnit = measureUnit;
    }

    public void setRecommendedRetailPriceWithVat(String Price, String Currency) {
        this.RecommendedRetailPriceWithVat.put(Price, Currency);
    }

    public void clearRecommendedRetailPriceWithVat() {
        this.RecommendedRetailPriceWithVat.clear();
    }

    public void setVATCountry(String Country) {
        this.VATCountry.add(Country);
    }
    public void setVATRate(String Rate) {
        this.VATRate = Rate;
    }


    public void setShortDescription(String shortDescription) {
        this.ShortDescription.add(shortDescription);
        //System.out.println(shortDescription);
    }

    public void setLargeDescription(String largeDescription) {
        this.LargeDescription.add(largeDescription);
    }

    public void setImageLink(String imageLink) {
        this.ImageLink.add(imageLink);
    }

    public void setAdditionalImageLink(String link) {this.AdditionalImageLink.add(link);}

    public void clearAdditionalImageLink(){ this.AdditionalImageLink.clear();}

    public void setDocument(String name, String link) { this.Document.put(name, link);}

    public void clearDocument(){ this.Document.clear();}

    public void setCertificates(String Link, String Description, String ImageLink) {
        this.certificates = new Certificate(Link, Description, ImageLink);
    }

    public void setVideo(String video) {
        this.Video = video;
    }

    public void setAvailability(Integer i, Integer e, Integer m) {
        this.availability = new Availability(i, e, m);
    }

    public void setGuarantee(String guarantee) {
        this.Guarantee = guarantee;
    }

    public void setGuaranteeType(String guaranteeType) {
        this.GuaranteeType = guaranteeType;
    }

    public void setConditions(Boolean New, Boolean Sale, Boolean Outlet) {
        this.Conditions = new Conditions(New, Sale, Outlet);
    }

    public void setSeason(String season) {
        Season = season;
    }

    public void setParam(String Name, String Value, String Unit) {
        Param param = new Param(Name, Value, Unit);
        this.Param.add(param);
    }

    public void clearParam(){ Param.clear();}

    public Integer getID() {
        return ID;
    }

    public String getEAN() {
        return EAN;
    }

    public String getPartNumber() {
        return PartNumber;
    }

    public String getName() {
        return Name;
    }

    public String getTitle() {
        return Title;
    }

    public String getLanguage() {
        return Language;
    }

    public Integer getITEMGROUP_ID() {
        return ITEMGROUP_ID;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public String getSupplier() {
        return Supplier;
    }

    public String getCountryOfOrigin() {
        return CountryOfOrigin;
    }

    public String getMeasureUnit() {
        return MeasureUnit;
    }

    public Map<String, String> getRecommendedRetailPriceWithVat() {
        return RecommendedRetailPriceWithVat;
    }

    public Set getVATCountry() {
        return VATCountry;
    }

    public ArrayList<String> getShortDescription() {
        return ShortDescription;
    }

    public ArrayList<String> getLargeDescription() {
        return LargeDescription;
    }

    public ArrayList<String> getImageLink() {
        return ImageLink;
    }

    public HashSet<String> getAdditionalImageLink() {
        return AdditionalImageLink;
    }

    public HashMap<String, String> getDocument() {
        return Document;
    }

    public Certificate getCertificates() {
        return certificates;
    }

    public String getVideo() {
        return Video;
    }

    public Availability getAvailability() {
        return availability;
    }

    public String getGuarantee() {
        return Guarantee;
    }

    public String getGuaranteeType() {
        return GuaranteeType;
    }

    public Conditions getConditions() {
        return Conditions;
    }

    public String getSeason() {
        return Season;
    }

    public HashSet<Param> getParam() {
        return Param;
    }
}

class Certificate{
    public Certificate(String link, String descriptions, String imageLink) {
        Link = link;
        Descriptions = descriptions;
        ImageLink = imageLink;
    }

    public String Link;
    public String Descriptions;
    public String ImageLink;
}

class Param{
    public Param(String name, String value, String unit) {
        Name = name;
        Value = value;
        Unit = unit;
    }

    public String Name;
    public String Value;
    public String Unit;
}

class Conditions{
    public Conditions(Boolean isNew, Boolean isSale, Boolean isOutlet) {
        IsNew = isNew;
        IsSale = isSale;
        IsOutlet = isOutlet;
    }

    public Boolean IsNew;
    public Boolean IsSale;
    public Boolean IsOutlet;
}

class Availability{
    public Availability(Integer internal, Integer external, Integer manufacturer) {
        this.internal = internal;
        this.external = external;
        this.manufacturer = manufacturer;
    }

    public Integer internal;
    public Integer external;
    public Integer manufacturer;
}

