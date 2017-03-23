package exam.urlPinger;

import java.util.List;

/**
 * Created by lei on 24/03/2017.
 */
public interface PingRepositoryCustom {

    public List<Ping> findAttributeWithRegex(String attr, String what);

}
