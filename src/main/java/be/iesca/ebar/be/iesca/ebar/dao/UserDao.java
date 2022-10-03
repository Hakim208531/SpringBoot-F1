package be.iesca.ebar.be.iesca.ebar.dao;

import be.iesca.ebar.be.iesca.ebar.domaine.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, String> {
}
