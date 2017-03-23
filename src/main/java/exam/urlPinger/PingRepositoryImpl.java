package exam.urlPinger;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;

import java.util.List;

/**
 * Created by lei on 24/03/2017.
 */
public class PingRepositoryImpl implements PingRepositoryCustom {

    private MongoOperations mongoOperations;

    public PingRepositoryImpl(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }


    @Override
    public List<Ping> findAttributeWithRegex(String attribute, String regex) {
        BasicQuery query = new BasicQuery(String.format("{ %s : {$regex : '.*%s.*'} }", attribute, regex));
        return mongoOperations.find(query, Ping.class);
    }
}
