package pl.app.spolecznosciowa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.app.spolecznosciowa.model.Friends;
import pl.app.spolecznosciowa.model.User;

import java.util.List;

@Repository
public interface FriendsRepository extends JpaRepository<Friends,Long> {
    List<Friends> findByUserFriend_Id(Long id);
}
