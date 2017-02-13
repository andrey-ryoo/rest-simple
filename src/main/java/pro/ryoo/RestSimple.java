package pro.ryoo;

import pro.ryoo.requests.Delete;
import pro.ryoo.requests.Get;
import pro.ryoo.requests.Post;
import pro.ryoo.requests.Put;

public class RestSimple {

    public static Config config = new Config();

    public static Get get(String url) {
        if (config.isEmpty()) {
            return new Get(url);
        }
        return new Get(url, config);
    }

    public static Post post(String url) {
        if (config.isEmpty()) {
            return new Post(url);
        }
        return new Post(url, config);
    }

    public static Put put(String url) {
        if (config.isEmpty()) {
            return new Put(url);
        }
        return new Put(url, config);
    }

    public static Delete delete(String url) {
        if (config.isEmpty()) {
            return new Delete(url);
        }
        return new Delete(url, config);
    }

}
