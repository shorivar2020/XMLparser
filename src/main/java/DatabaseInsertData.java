import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.HashMap;

public class DatabaseInsertData {
    public void InsertData(org.slf4j.Logger log, Action action, Statement statement,
                           HashMap<Integer, Root> products){
        Timestamp t = new Timestamp(System.currentTimeMillis());
        log.info("InsertData Start");
        //In products check old data and change status
        //Add new data in table
        String timestamp = String.valueOf(t);
        products.forEach((k, r) -> {
            try {
                log.info("Main - Products");
                action.CheckExist(statement, "products", r.getID(), timestamp);
                action.NewRowProducts(statement, timestamp, r.getID(), r.getEAN(), r.getPartNumber(),
                        r.getName(), r.getTitle(), r.getLanguage(),
                        r.getITEMGROUP_ID(), r.getManufacturer(), r.getSupplier(),
                        r.getCountryOfOrigin(), r.getMeasureUnit(),
                        r.getShortDescription().toString(), r.getLargeDescription().toString(),
                        r.getGuarantee(), r.getGuaranteeType(), r.getSeason());

                log.info("Documents");
                action.CheckExist(statement, "documents", r.getID(), timestamp);
                action.NewRowDocuments(statement, timestamp, r.getID(), r.getImageLink().toString(),
                        r.getVideo(), r.getDocumentName(), r.getDocumentLink(),
                        r.getCertificates().getLink(), r.getCertificates().getDescriptions(),
                        r.getCertificates().getImageLink());
                log.info("Availability");
                action.CheckExist(statement, "availability", r.getID(), timestamp);
                action.NewRowAvailability(statement, timestamp, r.getID(),
                        r.getAvailability().getInternal(), r.getAvailability().getExternal(),
                        r.getAvailability().getManufacturer());
                log.info("Conditions");
                action.CheckExist(statement, "conditions", r.getID(), timestamp);
                action.NewRowConditions(statement, timestamp, r.getID(),
                        String.valueOf(r.getConditions().getNew()),
                        String.valueOf(r.getConditions().getSale()),
                        String.valueOf(r.getConditions().getOutlet()));
            } catch (SQLException e) {
                log.error("INSERT ERROR SQL Products, Documents, Availability, Conditions", e);
            }
            r.getUndefinedData().forEach((l, m) -> {
                try {
                    action.CheckExist(statement, "undefineddata", r.getID(), timestamp);
                    action.NewUndefinedData(statement, timestamp, r.getID(), l, m);
                } catch (SQLException e) {
                    log.error("INSERT ERROR SQL Undefined DATA", e);
                }
            });
            r.getAdditionalImageLink().forEach((l) -> {
                try {
                    action.CheckExist(statement, "aditionalimage", r.getID(), timestamp);
                    action.NewRowAditionalImage(statement, timestamp, r.getID(), l);
                } catch (SQLException e) {
                    log.error("INSERT ERROR SQL Aditional Image", e);
                }
            });
            r.getParam().forEach((l) -> {
                try {
                    action.CheckExist(statement, "param", r.getID(), timestamp);
                    action.NewRowParam(statement, timestamp, r.getID(), l.getName(),
                    l.getValue(), l.getUnit());
                } catch (SQLException e) {
                    log.error("INSERT ERROR SQL Params", e);
                }
            });
            r.getRecommendedRetailPriceWithVat().forEach((l, m) -> {
                try {
                    action.CheckExist(statement, "recommendedretailpricewithvat", r.getID(), timestamp);
                    action.NewRowRecommended(statement, timestamp, r.getID(), l, m);
                } catch (SQLException e) {
                    log.error("INSERT ERROR SQL Recommended retail price with VAT", e);
                }
            });
            r.getVATCountry().forEach((l) -> {
                try {
                    action.CheckExist(statement, "vat", r.getID(), timestamp);
                    action.NewRowVAT(statement, timestamp, r.getID(), r.getVATRate(), l);
                } catch (SQLException e) {
                    log.error("INSERT ERROR SQL VAT", e);
                }
            });
        });
        log.info("INSERT NEW DATA WAS SUCCSES");
    }
}
