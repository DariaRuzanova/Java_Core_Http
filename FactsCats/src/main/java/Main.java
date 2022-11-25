import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static final String REMOTE_SERVICE_URI = "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";
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
            HttpGet request = new HttpGet(REMOTE_SERVICE_URI);

            CloseableHttpResponse reponse = httpClient.execute(request);
//            Arrays.stream(reponse.getAllHeaders()).forEach(System.out::println);

//            String body = new String(reponse.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
//            System.out.println(body);

            List<Cat> cats = mapper.readValue(reponse.getEntity().getContent(), new TypeReference<>() {
            });
            cats.stream().filter(x -> (x.getUpvotes() != null && x.getUpvotes() > 0)).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
