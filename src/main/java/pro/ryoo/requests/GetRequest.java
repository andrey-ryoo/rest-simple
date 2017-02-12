package pro.ryoo.requests;

import org.glassfish.jersey.client.ClientProperties;
import pro.ryoo.requests.base.Request;

import javax.ws.rs.client.ClientBuilder;

import static javax.ws.rs.core.MediaType.*;

public class GetRequest extends Request {

    public GetRequest(String url) {
        client = ClientBuilder.newBuilder().property(ClientProperties.FOLLOW_REDIRECTS, false).build();
        target = client.target(url);
        request = target.request(WILDCARD);
    }

}
