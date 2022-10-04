package be.iesca.ebar.be.iesca.ebar.dao;

import be.iesca.ebar.be.iesca.ebar.domaine.Role;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface RoleDao extends JpaRepository<Role,String> {
}
