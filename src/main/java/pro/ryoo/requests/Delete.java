package pro.ryoo.requests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.client.ClientProperties;
import pro.ryoo.Config;
import pro.ryoo.requests.base.Request;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import java.io.IOException;

import static javax.ws.rs.core.MediaType.WILDCARD;

public class Delete extends Request<Delete> {

    public Delete(String url) {
        this.url = url;
        init();
    }

    public Delete(String url, Config config) {
        this.url = url;
        clearPreviousSession();
        headers(config.getHeaders());
        accepts(config.getAccepts());
        cookies(config.getCookies());
        init();
    }

    public Response getRawResponse() {
        applyProperties();
        return request.delete();
    }

    public String getAsString() {
        return getRawResponse().readEntity(String.class);
    }

    public JsonNode getMapped() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(getAsString());
    }

    private void init() {
        client = ClientBuilder.newBuilder().property(ClientProperties.FOLLOW_REDIRECTS, false).build();
        target = client.target(url);
        request = target.request(WILDCARD);
    }

}
