package pro.ryoo.requests.base;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;
import java.util.*;

public abstract class Request<T> {

    protected Client client;
    protected WebTarget target;
    protected Invocation.Builder request;
    protected Response response;

    protected String url;

    protected Map<String,String> headers = new HashMap<String,String>();
    protected Set<String> accepts = new HashSet<String>();
    protected Set<Cookie> cookies = new HashSet<Cookie>();

    public T header(String name, String value) {
        headers.put(name, value);
        return (T) this;
    }

    public T headers(Map<String, String> headers) {
        this.headers = headers;
        return (T) this;
    }

    public T accept(String mediaType) {
        accepts.add(mediaType);
        return (T) this;
    }

    public T accepts(Set<String> accepts) {
        this.accepts = accepts;
        return (T) this;
    }

    public T cookie(String name, String value) {
        cookies.add(new Cookie(name, value));
        return (T) this;
    }

    public T cookie(Cookie cookie) {
        cookies.add(cookie);
        return (T) this;
    }

    public T cookies(Set<Cookie> cookies) {
        this.cookies = cookies;
        return (T) this;
    }

    public T clearAccepts() {
        this.accepts = new HashSet<String>();
        return (T) this;
    }

    public T clearHeaders() {
        this.headers = new HashMap<String, String>();
        return (T) this;
    }

    public T clearCookies() {
        this.cookies = new HashSet<Cookie>();
        return (T) this;
    }

    public T clearPreviousSession() {
        clearAccepts();
        clearHeaders();
        clearCookies();
        return (T) this;
    }

    protected void applyProperties() {
        if (accepts.size() > 0) {
            accepts.forEach(request::accept);
        }
        if (headers.size() > 0) {
            headers.forEach(request::header);
        }
        if (cookies.size() > 0) {
            cookies.forEach(request::cookie);
        }
    }

}
