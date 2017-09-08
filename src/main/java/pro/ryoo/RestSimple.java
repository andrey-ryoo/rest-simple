package pro.ryoo;

import pro.ryoo.requests.Delete;
import pro.ryoo.requests.Get;
import pro.ryoo.requests.Post;
import pro.ryoo.requests.Put;

public class RestSimple {

    public static Config config = new Config();

    public static Get get(String url) {
        return config.isEmpty() ? new Get(url) : new Get(url, config);
    }

    public static Post post(String url) {
        return config.isEmpty() ? new Post(url) : new Post(url, config);
    }

    public static Put put(String url) {
        return config.isEmpty() ? new Put(url) : new Put(url, config);
    }

    public static Delete delete(String url) {
        return config.isEmpty() ? new Delete(url) : new Delete(url, config);
    }
}
