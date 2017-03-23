package exam.urlPinger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Created by lei on 24/03/2017.
 */
@Service
public class PingService {

    private RestTemplateBuilder restTemplateBuilder;

    private PingRepository pingRepository;

    @Autowired
    public PingService(RestTemplateBuilder restTemplateBuiler, PingRepository pingRepository) {
        this.restTemplateBuilder = restTemplateBuiler;
        this.pingRepository = pingRepository;
    }

    public void visitUrl (String ... urls){
        RestTemplate restTemplate = restTemplateBuilder.build();
        for(String url : urls){

            Ping ping = new Ping();
            ping.setUrl(url);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            HttpEntity<Void> entity = new HttpEntity<>(headers);
            HttpEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            ping.setResponse(response.toString());
            pingRepository.save(ping);
            if(url.toLowerCase().equals("http://test.com")){
                System.out.println(ping.getResponse());
            }
        }
    }
}
