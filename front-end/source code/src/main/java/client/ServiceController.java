/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import javax.net.ssl.SSLContext;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Felipe
 */
@Controller
public class ServiceController {

    private static RestTemplate template;
    private static String url;

    public ServiceController() throws Exception {
        connect(null, null);
        url = "https://localhost:8443/";
    }

    public static void connect(String user, String password) throws Exception {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        if (user != null && password != null) {
            credentialsProvider.setCredentials(AuthScope.ANY,
                    new UsernamePasswordCredentials(user, password));
        }

        SSLContext sslContext = new SSLContextBuilder()
                .loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultCredentialsProvider(credentialsProvider).setSSLContext(sslContext)
                .setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
        ClientHttpRequestFactory rf = new HttpComponentsClientHttpRequestFactory(httpClient);
        template = new RestTemplate(rf);
    }

    public static <T> T post(String path, Object objeto, Class<T> tipo) throws Exception {
        return (T) template.postForObject(url + path, objeto, tipo);
    }

    public static <T> T get(String path, Class<T> tipo) throws Exception {
        return (T) template.getForObject(url + path, tipo);
    }
}
