package be.iesca.ebar.be.iesca.ebar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import be.iesca.ebar.be.iesca.ebar.dao.UserDao;
import be.iesca.ebar.be.iesca.ebar.domaine.Adresse;
import be.iesca.ebar.be.iesca.ebar.domaine.Role;
import be.iesca.ebar.be.iesca.ebar.domaine.User;
import be.iesca.ebar.be.iesca.ebar.ConfigJpa1;

@SpringBootTest
class TestsUserDao {

	@Autowired
	UserDao userDao;

	@Autowired
	List<User> users;

	@Autowired
	List<Role> roles;

	@Test
	public void testUserAdmin() {

		// Vérifiez que l'utilisateur dont l'email est "admin1@gmail.com" est présent en db
		assertEquals("admin1@gmail.com",userDao.findById("admin1@gmail.com").get().getEmail());
		// Vérifiez que cet utilisateur porte le nom de "admin1"
		assertEquals("admin1",userDao.findById("admin1@gmail.com").get().getNom());
		// Vérifiez qu'il habite à "rue de l'admin" à  "1000", "Bruxelles"
		assertEquals("rue de l'admin",userDao.findById("admin1@gmail.com").get().getAdresse().getRueNumero());
		assertEquals("1000",userDao.findById("admin1@gmail.com").get().getAdresse().getCodePostal());
		assertEquals("Bruxelles",userDao.findById("admin1@gmail.com").get().getAdresse().getCommune());
	}

	@Test
	public void testUserMembre() {

		// Vérifiez que l'utilisateur dont l'email est "membre1@gmail.com" est présent en db
		assertEquals("membre1@gmail.com",userDao.findById("membre1@gmail.com").get().getEmail());
		// Vérifiez que cet utilisateur porte le nom de "membre1"
		assertEquals("membre1",userDao.findById("membre1@gmail.com").get().getNom());
		// Vérifiez qu'il habite à "rue du membre" à "5550", "Membre"
		assertEquals("rue du membre",userDao.findById("membre1@gmail.com").get().getAdresse().getRueNumero());
		assertEquals("5550",userDao.findById("membre1@gmail.com").get().getAdresse().getCodePostal());
		assertEquals("Membre",userDao.findById("membre1@gmail.com").get().getAdresse().getCommune());
	}

	@Test
	public void testMdpAdmin() {

		// Vérifiez que l'utilisateur dont l'email est "admin1@gmail.com" puisse se connecter avec le mot de passe "1234"
		// astuce : Utilisez "passwordEncoder.matches()"
		ConfigJpa1 configJpa1 = new ConfigJpa1();
		assertEquals(true, configJpa1.passwordEncoder().matches("1234",userDao.findById("admin1@gmail.com").get().getPassword()));
	}

	@Test
	public void testMdpMembre() {

		// Vérifiez que l'utilisateur dont l'email est "membre1@gmail.com" puisse se connecter avec le mot de passe "5678"
		// astuce : Utilisez "passwordEncoder.matches()"
		ConfigJpa1 configJpa1 = new ConfigJpa1();
		assertEquals(true, configJpa1.passwordEncoder().matches("5678",userDao.findById("membre1@gmail.com").get().getPassword()));
	}

	@Test
	public void testCarteCreditAdmin() {

		// Vérifiez que la carte de crédit de l'utilisateur ayant pour email "admin1@gmail.com"
		// porte le numéro "123456789-99"
		assertEquals("123456789-99", userDao.findById("admin1@gmail.com").get().getCarteCredit().getNumero());
		// sa banque est "Fortis"
		assertEquals("Fortis", userDao.findById("admin1@gmail.com").get().getCarteCredit().getBanque());
	}

	@Test
	public void testCarteCreditMembre() {

		// Vérifiez que la carte de crédit de l'utilisateur ayant pour email "membre1@gmail.com"
		// porte le numéro "987654321-00"
		assertEquals("987654321-00", userDao.findById("membre1@gmail.com").get().getCarteCredit().getNumero());
		// sa banque est "BeoBank"
		assertEquals("BeoBank", userDao.findById("membre1@gmail.com").get().getCarteCredit().getBanque());
	}

	@Test
	@Transactional // car dans Users, roles est en FetchType.Lazy
	public void testRolesUserAdmin1() {

		// Vérifiez que l'utilisateur ayant pour email "admin1@gmail.com"
		// peut jouer les rôles "administrateur" et "membre"
		assertEquals("administrateur", userDao.findById("admin1@gmail.com").get().getRoles().get(0).getNom());
		assertEquals("membre", userDao.findById("admin1@gmail.com").get().getRoles().get(1).getNom());
	}

	@Test
	@Transactional // car dans Users, roles est en FetchType.Lazy
	public void testRolesUserMembre1() {

		// Vérifiez que l'utilisateur ayant pour email "membre1@gmail.com"
		// peut jouer uniquement le rôle de "membre"
		assertEquals("membre", userDao.findById("membre1@gmail.com").get().getRoles().get(0).getNom());
	}

}
