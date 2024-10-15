package SoftwareProyecto.Clases;

import jakarta.persistence.*;

@Entity
public class Password {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String website;
    private String password;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Password() {}

    public Password(String website, String password, User user) {
        this.website = website;
        this.password = password;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}