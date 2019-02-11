package tests;

import com.google.gson.*;
import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Common {
    private final static Logger LOGGER = Logger.getLogger(DemoTest.class.getName());

    public static URI create_url (String protocol, String host, String path) throws URISyntaxException {
        URI uri = new URIBuilder()
                .setScheme(protocol)
                .setHost(host)
                .setPath(path)
                .build();
        LOGGER.log(Level.FINE, uri + " was formed");
        return uri;
    }

    public static JsonElement execute_get_request() throws IOException, URISyntaxException {
        HttpGet httpget = new HttpGet(create_url("https","credapi.credify.tech" ,"api/loanapp/v1/states"));
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(httpget);
        BasicResponseHandler responseHandler = new BasicResponseHandler();
        assertEquals(200, response.getStatusLine().getStatusCode(), "Status code is not 200");
        JsonParser parser = new JsonParser();
        String response_body = responseHandler.handleResponse(response);
        LOGGER.log(Level.FINE, response_body);
        JsonElement json_response =  parser.parse( response_body);
        return json_response;

    }
}
