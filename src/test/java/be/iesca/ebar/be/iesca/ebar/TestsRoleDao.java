package be.iesca.ebar.be.iesca.ebar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import be.iesca.ebar.be.iesca.ebar.dao.RoleDao;
import be.iesca.ebar.be.iesca.ebar.domaine.Role;
import be.iesca.ebar.be.iesca.ebar.domaine.User;

@SpringBootTest
class TestsRoleDao {

	@Autowired
	RoleDao roleDao;

	@Test
	@Transactional // car dans Role, users est en FetchType.Lazy
	public void testRoleAdministrateur() {

		// Vérifiez que le rôle "administrateur" est présent en db
		assertEquals("administrateur",roleDao.findById("administrateur").get().getNom());
		// Vérifiez que seul l'utilisateur ayant l'email "admin1@gmail.com" puisse exercer ce rôle
		assertEquals("admin1@gmail.com",roleDao.findById("administrateur").get().getUsers().get(0).getEmail());
	}

	@Test
	@Transactional // car dans Role, users est en FetchType.Lazy
	public void testRoleMembre() {

		// Vérifiez que le rôle "membre" est présent en db
		assertEquals("membre",roleDao.findById("membre").get().getNom());
		// Vérifiez que seuls les utilisateurs ayant les emails "admin1@gmail.com" et "membre1@gmail.com" puissent exercer ce rôle
		assertEquals("admin1@gmail.com",roleDao.findById("membre").get().getUsers().get(0).getEmail());
		assertEquals("membre1@gmail.com",roleDao.findById("membre").get().getUsers().get(1).getEmail());
	}

}
