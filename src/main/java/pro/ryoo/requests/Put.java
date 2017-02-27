package pro.ryoo.requests;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.client.ClientProperties;
import pro.ryoo.Config;
import pro.ryoo.requests.base.Request;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import java.io.IOException;

import static javax.ws.rs.core.MediaType.WILDCARD;
import static javax.ws.rs.core.MediaType.*;

public class Put extends Request<Put> {

    private String payload;

    public Put(String url) {
        this.url = url;
    }

    public Put(String url, Config config) {
        this.url = url;
        clearPreviousSession();
        headers(config.getHeaders());
        accepts(config.getAccepts());
        cookies(config.getCookies());
    }

    public Put payload(String payload) {
        this.payload = payload;
        return this;
    }

    public Response getRawResponse() {
        client = ClientBuilder.newBuilder().property(ClientProperties.FOLLOW_REDIRECTS, false).build();
        target = client.target(url);
        request = target.request(WILDCARD);
        applyProperties();
        response = request.put(Entity.entity(payload, APPLICATION_JSON_TYPE));
        return response;
    }

    public String getAsString() {
        String result = getRawResponse().readEntity(String.class);
        response.close();
        return result;
    }

    public JsonNode getMapped() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(getAsString());
    }

}
