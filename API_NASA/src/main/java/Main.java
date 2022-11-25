import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class Main {
    private static final String API_KEY = "myavlOuTv1WFt2sVf3jj53wy3FtK40C6WSkMFuAO";
    private static final String REMOTE_SERVICE_URI = "https://api.nasa.gov/planetary/apod?api_key=";
    public static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();

        try {
            HttpGet request = new HttpGet(REMOTE_SERVICE_URI + API_KEY);

            CloseableHttpResponse reponse = httpClient.execute(request);
            Arrays.stream(reponse.getAllHeaders()).forEach(System.out::println);

//            String body = new String(reponse.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
//            System.out.println(body);

            Post posts = mapper.readValue(reponse.getEntity().getContent(), new TypeReference<>() {
            });
            System.out.println(posts.toString());
            String fileUrl = posts.getUrl();
            System.out.println(fileUrl);

            request = new HttpGet(fileUrl);
            reponse = httpClient.execute(request);
            Arrays.stream(reponse.getAllHeaders()).forEach(System.out::println);

            String[] arrStr = fileUrl.split("/");
            String fileName = arrStr[arrStr.length - 1];
            try (FileOutputStream fos = new FileOutputStream(fileName)) {
                fos.write(reponse.getEntity().getContent().readAllBytes());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
