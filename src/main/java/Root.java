import java.util.*;

/*Struct of Product*/
public class Root {
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
    private Map<String, String> RecommendedRetailPriceWithVat = new HashMap<>();
    private String VATRate;
    private ArrayList<String> VATCountry = new ArrayList<>();
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
    private String DocumentName;
    private String DocumentLink;

    public HashMap<String, String> getUndefinedData() {
        return undefinedData;
    }

    public void setUndefinedData(String tag, String data) {
        this.undefinedData.put(tag, data);
    }

    private HashMap<String, String> undefinedData = new HashMap<>();

    public String getDocumentName() {
        return DocumentName;
    }

    public String getDocumentLink() {
        return DocumentLink;
    }

    public String getVATRate() {
        return VATRate;
    }
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

    public void setVATCountry(String Country) {
        this.VATCountry.add(Country);
    }

    public void setVATRate(String Rate) {
        this.VATRate = Rate;
    }

    public void setShortDescription(String shortDescription) {
        this.ShortDescription.add(shortDescription);
    }

    public void setLargeDescription(String largeDescription) {
        this.LargeDescription.add(largeDescription);
    }

    public void setImageLink(String imageLink) {
        this.ImageLink.add(imageLink);
    }

    public void setAdditionalImageLink(String link) {this.AdditionalImageLink.add(link);}

    public void setDocumentName(String name) { this.DocumentName = name;}
    public void setDocumentLink(String link) { this.DocumentLink = link;}

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

    public Integer getID() {
        return this.ID;
    }

    public String getEAN() {
        return this.EAN;
    }

    public String getPartNumber() {
        return this.PartNumber;
    }

    public String getName() { return this.Name;}

    public String getTitle() { return this.Title;}

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
        return this.CountryOfOrigin;
    }

    public String getMeasureUnit() {
        return this.MeasureUnit;
    }

    public Map<String, String> getRecommendedRetailPriceWithVat() {
        return this.RecommendedRetailPriceWithVat;
    }

    public ArrayList<String> getVATCountry() {
        return this.VATCountry;
    }

    public ArrayList<String> getShortDescription() {
        return this.ShortDescription;
    }

    public ArrayList<String> getLargeDescription() {
        return this.LargeDescription;
    }

    public ArrayList<String> getImageLink() {
        return this.ImageLink;
    }

    public HashSet<String> getAdditionalImageLink() {
        return this.AdditionalImageLink;
    }

    public HashMap<String, String> getDocument() {
        return this.Document;
    }

    public Certificate getCertificates() {
        return this.certificates;
    }

    public String getVideo() {
        return this.Video;
    }

    public Availability getAvailability() {
        return this.availability;
    }

    public String getGuarantee() {
        return this.Guarantee;
    }

    public String getGuaranteeType() {
        return this.GuaranteeType;
    }

    public Conditions getConditions() {
        return this.Conditions;
    }

    public String getSeason() {
        return this.Season;
    }

    public HashSet<Param> getParam() {
        return this.Param;
    }
}

class Certificate{
    public String Link;
    public String Descriptions;
    public String ImageLink;
    public Certificate(String link, String descriptions, String imageLink) {
        this.Link = link;
        this.Descriptions = descriptions;
        this.ImageLink = imageLink;
    }
    public String getLink() {
        return this.Link;
    }
    public String getDescriptions() {
        return this.Descriptions;
    }
    public String getImageLink() {
        return this.ImageLink;
    }
}

class Param{
    public String Name;
    public String Value;
    public String Unit;
    public Param(String name, String value, String unit) {
        Name = name;
        Value = value;
        Unit = unit;
    }
    public String getValue() {
        return Value;
    }
    public String getUnit() {
        return Unit;
    }
    public String getName() {
        return Name;
    }
}

class Conditions{
    public Boolean IsNew;
    public Boolean IsSale;
    public Boolean IsOutlet;
    public Conditions(Boolean isNew, Boolean isSale, Boolean isOutlet) {
        IsNew = isNew;
        IsSale = isSale;
        IsOutlet = isOutlet;
    }
    public Boolean getNew() {
        return IsNew;
    }
    public Boolean getSale() {
        return IsSale;
    }
    public Boolean getOutlet() {
        return IsOutlet;
    }
}

class Availability{
    public Integer internal;
    public Integer external;
    public Integer manufacturer;
    public Availability(Integer internal, Integer external, Integer manufacturer) {
        this.internal = internal;
        this.external = external;
        this.manufacturer = manufacturer;
    }
    public Integer getInternal() {
        return internal;
    }
    public Integer getExternal() {
        return external;
    }
    public Integer getManufacturer() {
        return manufacturer;
    }
}

