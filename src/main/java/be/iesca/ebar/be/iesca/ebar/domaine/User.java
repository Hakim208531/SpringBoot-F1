package be.iesca.ebar.be.iesca.ebar.domaine;

import org.springframework.context.annotation.Bean;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    private String email;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String password;

    private Adresse adresse = new Adresse();

    @OneToOne(cascade = CascadeType.ALL)
    private CarteCredit carteCredit = new CarteCredit();

    public List<Role> getRoles() {
        return roles;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @ManyToMany
    private List<Role> roles;

    public User(){}

    public User(String nom,String email, String password){
        this.email = email;
        this.nom = nom;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }

    @Bean
    public String getEmail() {
        return email;
    }

    @Bean
    public void setEmail(String email) {
        this.email = email;
    }

    @Bean
    public String getNom() {
        return nom;
    }

    @Bean
    public void setNom(String nom) {
        this.nom = nom;
    }

    @Bean
    public String getPassword() {
        return password;
    }

    @Bean
    public void setPassword(String password) {
        this.password = password;
    }

    public void setCarteCredit(CarteCredit carteCredit) {
        this.carteCredit = carteCredit;
    }
}
