package exam.urlPinger;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by lei on 24/03/2017.
 */
public interface PingRepository extends MongoRepository<Ping, String>, PingRepositoryCustom {
}
