package pro.ryoo.requests;

import pro.ryoo.requests.base.Request;

import java.io.File;
import java.util.Map;

public class PostRequest extends Request {

    private Map<String, File> files;
    private Map<String, Object> params;

    public PostRequest(String url) {
        this.url = url;
    }

    public PostRequest file(String name, File file) {
        files.put(name, file);
        return this;
    }

    public PostRequest field(String name, Object value) {
        params.put(name, value);
        return this;
    }

}
