package demo;

import com.google.gson.Gson;
import com.indico.IndicoConfig;
import com.indico.IndicoKtorClient;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class Hello {

    private static IndicoConfig loadConfig() throws IOException {
        var properties = new Properties();
        properties.load(new FileReader("config.properties"));

        return new IndicoConfig.Builder()
                .host(properties.getProperty("host"))
                .apiToken(properties.getProperty("indicoToken"))
                .build();
    }

    public static void main(String[] args) throws IOException {
        try (var client = new IndicoKtorClient(loadConfig())) {
            var submissions = client.listSubmissions().query();
            for (var submission : submissions) {
                System.out.println(new Gson().toJson(submission));
            }
        }
    }

}
