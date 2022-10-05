package be.iesca.ebar.be.iesca.ebar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import be.iesca.ebar.be.iesca.ebar.dao.CarteCreditDao;
import be.iesca.ebar.be.iesca.ebar.domaine.CarteCredit;
import be.iesca.ebar.be.iesca.ebar.domaine.User;

/**
 * ajouter : import static org.junit.jupiter.api.Assertions.*;
 *
 */

@SpringBootTest
class TestsCarteCreditDao {

	@Autowired
	CarteCreditDao carteCreditDao;

	@Test
	public void testCarteCreditAdmin() {

		// Vérifiez que la carte portant le numero "123456789-99" est présente en db
		assertEquals("123456789-99", carteCreditDao.findById("123456789-99").get().getNumero());
		// Vérifiez que le nom de son titulaire est "admin1"
		assertEquals("admin1", carteCreditDao.findById("123456789-99").get().getUser().getNom());
	}

	@Test
	public void testCarteCreditMembre() {

		// Vérifiez que la carte portant le numero "987654321-00" est présente en db
		assertEquals("987654321-00", carteCreditDao.findById("987654321-00").get().getNumero());
		// Vérifiez que le nom de son titulaire est "membre1"
		assertEquals("membre1", carteCreditDao.findById("987654321-00").get().getUser().getNom());
	}
}
