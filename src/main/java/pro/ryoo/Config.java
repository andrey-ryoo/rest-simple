package pro.ryoo;


import javax.ws.rs.core.Cookie;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class Config {

    private Map<String,String> headers = new HashMap<String,String>();
    private Set<String> accepts = new HashSet<String>();
    private Set<Cookie> cookies = new HashSet<Cookie>();

    public Config addHeader(String name, String value) {
        headers.put(name, value);
        return this;
    }

    public Config addHeaders(HashMap<String,String> headers) {
        this.headers.putAll(headers);
        return this;
    }

    public Config addAccepts(HashSet<String> accepts) {
        this.accepts.addAll(accepts);
        return this;
    }

    public Config addCookies(HashSet<Cookie> cookies) {
        this.cookies.addAll(cookies);
        return this;
    }

    public Config addAccept(String accept) {
        accepts.add(accept);
        return this;
    }

    public Config addCookie(Cookie cookie) {
        cookies.add(cookie);
        return this;
    }

    public Config addCookie(String key, String value) {
        cookies.add(new Cookie(key, value));
        return this;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Set<String> getAccepts() {
        return accepts;
    }

    public Set<Cookie> getCookies() {
        return cookies;
    }

    public Boolean isEmpty() {
        return headers.size() == 0 && accepts.size() == 0 && cookies.size() == 0;
    }
}
