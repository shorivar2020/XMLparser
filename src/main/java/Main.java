@lombok.extern.slf4j.Slf4j
public class Main {
    public static void main(String[] args) throws Exception {
//        Scanner scanner = new Scanner(System.in);
//        String path = scanner.nextLine();
        String path = "config.ini";
        log.info("Path was received");
        IniReader ini = new IniReader(log, path);
        String url = ini.getIniURL();
        String urlDB = ini.getUrlDB();
        String username = ini.getUsername();
        String password = ini.getPassword();
        DownloadFileFromUrl dffu = new DownloadFileFromUrl(log, url);
        SAX sax = new SAX();
        sax.SAX(log, dffu.getFileName(), urlDB, username, password);
    }
}
