package texas.sbv.car.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    User findByUserid(String userid);
    User findByUseridAndPasswd(String userid, String passwd);
    User findByUserSeq(long seq);

}
