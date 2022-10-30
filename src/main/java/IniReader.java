import java.io.File;
import org.ini4j.Wini;

/*Read configuration file type IMI*/
public class IniReader {
    public String url;
    String server;
    Integer port;
    String username;

    public IniReader (String path) throws Exception{
        Wini ini = new Wini(new File(path));
        this.url = ini.get("website", "url", String.class);
        this.server = ini.get("database", "server", String.class);
        this.port = ini.get("database", "port", Integer.class);
        this.username = ini.get("database", "username", String.class);
        System.out.println("url=" + url);
        System.out.println("server= " + server);
        System.out.println("port = " + port);
        System.out.println("username = " + username);
    }

    public String getIniURL(){
        return this.url;
    }

    public String getIniServer(){
        return this.server;
    }

    public Integer getIniPort(){
        return this.port;
    }

    public String getUsername(){
        return this.username;
    }
}