import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class DownloadFileFromUrl {
    public DownloadFileFromUrl (String url) throws Exception{
    URL website = new URL(url);
    ReadableByteChannel rbc = Channels.newChannel(website.openStream());
    FileOutputStream fos = new FileOutputStream("information.xml");
    fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    System.out.println("File was Downloaded");
    }
}
