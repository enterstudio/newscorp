package exam.urlPinger;

import org.springframework.data.annotation.Id;

/**
 * Created by lei on 24/03/2017.
 */
public class Ping {
    @Id
    private String id;
    private String url;
    private String response;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
