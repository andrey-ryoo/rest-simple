package pro.ryoo.requests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import pro.ryoo.Config;
import pro.ryoo.requests.base.Request;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static javax.ws.rs.core.MediaType.*;

public class Post extends Request<Post> {

    private Map<String, File> files = new HashMap<String, File>();
    private Map<String, String> params = new HashMap<String, String>();

    public Post(String url) {
        this.url = url;
    }

    public Post(String url, Config config) {
        this.url = url;
        clearPreviousSession();
        headers(config.getHeaders());
        accepts(config.getAccepts());
        cookies(config.getCookies());
    }

    public Post field(String name, String value) {
        params.put(name, value);
        return this;
    }

    public Post file(String name, File file) {
        files.put(name, file);
        return this;
    }

    public Post file(File file) {
        files.put("file", file);
        return this;
    }

    public Response getRawResponse() {

        Boolean isMultipart = files.size() > 0;

        if (isMultipart) {
            client = ClientBuilder.newBuilder().register(MultiPartFeature.class).build();
            target = client.target(url).property(ClientProperties.FOLLOW_REDIRECTS, false);
            request = target.request();
            MultiPart form = composeMultipart();
            applyProperties();
            return request.post(Entity.entity(form, MULTIPART_FORM_DATA_TYPE));
        }
        client = ClientBuilder.newBuilder().property(ClientProperties.FOLLOW_REDIRECTS, false).build();
        target = client.target(url);
        request = target.request(WILDCARD);
        Form form = composeForm();
        applyProperties();
        return request.post(Entity.entity(form, APPLICATION_FORM_URLENCODED_TYPE));
    }

    public String getAsString() {
        return getRawResponse().readEntity(String.class);
    }

    public JsonNode getMapped() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(getAsString());
    }

    private Set<FileDataBodyPart> mapFiles() {
        Set<FileDataBodyPart> result = new HashSet<FileDataBodyPart>();
        files.forEach((k, v) -> {
            FileDataBodyPart bodyPart = new FileDataBodyPart(k, v);
            bodyPart.setContentDisposition(FormDataContentDisposition.name(k).fileName(v.getName()).build());
            result.add(bodyPart);
        });
        return result;
    }

    private FormDataMultiPart composeMultipart() {
        FormDataMultiPart result = new FormDataMultiPart();
        if (params != null) {
            params.forEach(result::field);
        }
        mapFiles().forEach(result::bodyPart);
        return result;
    }

    private Form composeForm() {
        Form result = new Form();
        params.forEach(result::param);
        return result;
    }

}
