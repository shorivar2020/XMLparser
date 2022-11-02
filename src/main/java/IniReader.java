import java.io.File;
import org.ini4j.Wini;

/*Read configuration file type IMI*/
public class IniReader {
    public String url;
    String username;
    String password;
    String urlDB;

    public IniReader (org.slf4j.Logger log, String path) throws Exception{
        Wini ini = new Wini(new File(path));
        this.url = ini.get("website", "url", String.class);
        this.username = ini.get("database", "username", String.class);
        this.urlDB = ini.get("database", "urlDB", String.class);
        this.password = ini.get("database", "password", String.class);
        log.info("Data was received from Config file");
    }

    public String getIniURL(){
        return this.url;
    }

    public String getUsername(){
        return this.username;
    }

    public String getUrlDB(){return this.urlDB;}

    public String getPassword() {return this.password;}
}