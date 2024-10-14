package SoftwareProyecto.Clases;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Password {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String website;
    private String password;

    public Password() {}

    public Password(String website, String password) {
        super();
        this.website = website;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}