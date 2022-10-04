package be.iesca.ebar.be.iesca.ebar;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import be.iesca.ebar.be.iesca.ebar.dao.RoleDao;
import be.iesca.ebar.be.iesca.ebar.dao.UserDao;
import be.iesca.ebar.be.iesca.ebar.domaine.Adresse;
import be.iesca.ebar.be.iesca.ebar.domaine.CarteCredit;
import be.iesca.ebar.be.iesca.ebar.domaine.Role;
import be.iesca.ebar.be.iesca.ebar.domaine.User;

@Configuration
public class ConfigJpa1 {

	@Bean
	List<Role> initialiserLesRoles(RoleDao roleDao) {
		List<Role> roles = new ArrayList<Role>(2);
		// role administrateur
		Role roleAdmin = new Role("administrateur");
		roles.add(roleAdmin);
		roleAdmin=roleDao.save(roleAdmin);
		// role membre
		Role roleMembre = new Role("membre");
		roles.add(roleMembre);
		roleMembre=roleDao.save(roleMembre);
		return roles;
	}

	@Bean
	public List<User> initialiserLesUsers(UserDao userDao, List<Role> roles, RoleDao roleDao) {
		List<User> users = new ArrayList<>(2);
		// admin1
		User admin = new User("admin1", "admin1@gmail.com", "1234");

		Adresse adresseAdmin = new Adresse("rue de l'admin", "1000", "Bruxelles");
		admin.setAdresse(adresseAdmin);
		users.add(admin);
		CarteCredit carteAdmin = new CarteCredit(admin, "123456789-99", "Fortis");
		admin.setCarteCredit(carteAdmin);
		carteAdmin.setUser(admin);
		admin.setRoles(roles);
		userDao.save(admin);
		Role roleAdministrateur = roles.get(0);
		List<User> users1 = new ArrayList<User>(1);
		users1.add(admin);
		roleAdministrateur.setUsers(users1);
		roleAdministrateur=roleDao.save(roleAdministrateur);
		// membre1
		User membre = new User("membre1", "membre1@gmail.com", "5678");
		Adresse adresseMembre = new Adresse("rue du membre", "5550", "Membre");
		membre.setAdresse(adresseMembre);
		users.add(membre);
		CarteCredit carteMembre = new CarteCredit(membre, "987654321-00", "BeoBank");
		membre.setCarteCredit(carteMembre);
		carteMembre.setUser(membre);
		List<Role> rolesMembre = new ArrayList<Role>(1);
		rolesMembre.add(roles.get(1));
		membre.setRoles(rolesMembre);
		userDao.save(membre);
		Role roleMembre = roles.get(1);
		users1.add(membre);
		roleMembre.setUsers(users1);
		roleDao.save(roleMembre);
		return users;
	}
}
