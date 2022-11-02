import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class DownloadFileFromUrl {
    private final String fileName = "information.xml";
    public DownloadFileFromUrl (org.slf4j.Logger log, String url) throws Exception{
    URL website = new URL(url);
    ReadableByteChannel rbc = Channels.newChannel(website.openStream());
    FileOutputStream fos = new FileOutputStream(fileName);
    fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    log.info("File was download");
    }
    public String getFileName() {
        return fileName;
    }
}
