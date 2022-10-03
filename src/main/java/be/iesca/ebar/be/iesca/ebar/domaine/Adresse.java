package be.iesca.ebar.be.iesca.ebar.domaine;

import org.springframework.context.annotation.Bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Adresse implements Serializable {
    @Column(nullable = false)
    private String rueNumero;

    @Column(nullable = false)
    private String codePostal;

    @Column(nullable = false)
    private String commune;

    public Adresse(){}

    public Adresse(String rueNumero, String codePostal, String commune){
        this.rueNumero = rueNumero;
        this.codePostal = codePostal;
        this.commune = commune;
    }

    @Bean
    public String getRueNumero() {
        return rueNumero;
    }

    @Bean
    public void setRueNumero(String rueNumero) {
        this.rueNumero = rueNumero;
    }

    @Bean
    public String getCodePostal() {
        return codePostal;
    }

    @Bean
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    @Bean
    public String getCommune() {
        return commune;
    }

    @Bean
    public void setCommune(String commune) {
        this.commune = commune;
    }
}
