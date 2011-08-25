import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import com.google.common.io.CharStreams;

/**
 *
 * @author Arjun Satyapal
 */
public class MyMain {
    public static void main(String[] args) throws Exception {
        InputStream inputStream =
                MyMain.class.getClassLoader().getResourceAsStream("config.properties");
        String response = CharStreams.toString(new InputStreamReader(inputStream));

        Properties configProperties = new Properties();
        configProperties.load(new ByteArrayInputStream(response.getBytes()));
        System.out.println(configProperties.get("cnx.repo.server.url"));
    }

}
