package be.iesca.ebar.be.iesca.ebar.dao;

import be.iesca.ebar.be.iesca.ebar.domaine.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserDao extends JpaRepository<User, String> {
}
