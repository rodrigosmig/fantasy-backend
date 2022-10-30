package br.com.backend.fantasygame.infrastracture.schema;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.backend.fantasygame.domain.entity.User;
import br.com.backend.fantasygame.domain.vo.Email;
import br.com.backend.fantasygame.domain.vo.Nome;
import br.com.backend.fantasygame.domain.vo.Senha;

@Entity
@Table(name = "users")
public class UserSchema implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60, nullable = false)
    private String nome;

    @Column(nullable = false)
    private String senha;

    @Column(unique = true, nullable = false)
    private String email;
    
    private LocalDateTime criadoEm = LocalDateTime.now();

    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
    @Transient
    private TimeSchema time;

    @ManyToMany(fetch = FetchType.EAGER)
	private List<ProfileSchema> profiles = new ArrayList<>();    

    public UserSchema() {
    }

    public UserSchema(Long id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
    }

    public UserSchema(User user) {
        this.id = user.getId();
        this.nome = user.getNome();
        this.senha = user.getSenha();
        this.email = user.getEmail();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public TimeSchema getTime() {
        return time;
    }

    public List<ProfileSchema> getProfiles() {
        return profiles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.profiles;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User toUser() {
        return new User(
            id, 
            new Nome(nome), 
            new Senha(senha), 
            new Email(email));
    }

    @Override
    public String toString() {
        return "UserSchema [criadoEm=" + criadoEm + ", email=" + email + ", id=" + id + ", nome=" + nome + ", senha="
                + senha + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserSchema other = (UserSchema) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
}
