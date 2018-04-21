package pl.app.spolecznosciowa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.app.spolecznosciowa.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findAllByUsername(String username);
    List<User> findByFirstName(String name);
    List<User> findByFirstNameAndLastName(String firstName, String lastName);
}
