package pro.ryoo.requests.base;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.*;

public class Request {

    protected Client client;
    protected WebTarget target;
    protected Invocation.Builder request;

    protected String url;

    protected Map<String,String> headers;
    protected Set<String> accepts;
    protected Set<Cookie> cookies;

    public <C extends Request> C header(String name, String value) {
        checkHeaders();
        headers.put(name, value);
        return (C) this;
    }

    public <C extends Request> C headers(Map<String, String> headers) {
        this.headers = headers;
        return (C) this;
    }

    public <C extends Request> C accept(String mediaType) {
        checkAccepts();
        accepts.add(mediaType);
        return (C) this;
    }

    public <C extends Request> C accepts(Set<String> accepts) {
        this.accepts = accepts;
        return (C) this;
    }

    public <C extends Request> C cookie(String name, String value) {
        checkCookies();
        cookies.add(new Cookie(name, value));
        return (C) this;
    }

    public <C extends Request> C cookie(Cookie cookie) {
        checkCookies();
        cookies.add(cookie);
        return (C) this;
    }

    public <C extends Request> C cookies(Set<Cookie> cookies) {
        this.cookies = cookies;
        return (C) this;
    }

    private void checkHeaders() {
        if (!Optional.ofNullable(headers).isPresent()) {
            headers = new HashMap<String, String>();
        }
    }

    private void checkAccepts() {
        if (!Optional.ofNullable(accepts).isPresent()) {
            accepts = new HashSet<String>();
        }
    }

    private void checkCookies() {
        if (!Optional.ofNullable(cookies).isPresent()) {
            cookies = new HashSet<Cookie>();
        }
    }

    public Response getRawResponse() {
        Optional.ofNullable(accepts).ifPresent(a -> a.forEach(request::accept));
        Optional.ofNullable(headers).ifPresent(h -> h.forEach(request::header));
        Optional.ofNullable(cookies).ifPresent(c -> c.forEach(request::cookie));
        return request.get();
    }

    public String getAsString() {
        return getRawResponse().readEntity(String.class);
    }

    public JsonNode getMapped() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(getAsString());
    }

}
