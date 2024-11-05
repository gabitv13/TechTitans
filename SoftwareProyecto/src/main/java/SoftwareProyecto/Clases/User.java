package SoftwareProyecto.Clases;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    private String password;
    private String username;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Password> passwords;

    /*
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;
    */

    public User() {}

    public User(String username) {
        this.username = username;
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    /* Para posteriores roles

    public void setRoles(List<String> roles){this.roles = roles;}
    public List<String> getRoles(){return this.roles;}
    */

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Password> getPasswords() {
        return passwords;
    }

    public void setPasswords(List<Password> passwords) {
        this.passwords = passwords;
    }
}
