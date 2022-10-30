package database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.HashSet;

@Entity
@Table(name = "Order", schema = "public", catalog = "shorivar")
public class Product {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
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


