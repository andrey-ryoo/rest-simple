package pro.ryoo.requests;

import pro.ryoo.requests.base.Request;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PostRequest extends Request {

    private Map<String, File> files;
    private Map<String, Object> params = new HashMap<String, Object>();

    public PostRequest(String url) {
        this.url = url;
    }

    public PostRequest file(String name, File file) {
        if (!Optional.ofNullable(files).isPresent()) {
            files = new HashMap<String, File>();
        }
        files.put(name, file);
        return this;
    }

    public PostRequest field(String name, Object value) {
        params.put(name, value);

        return this;
    }



}
