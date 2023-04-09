package net.quazar.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account {
    private @Id Integer id;
    private @Column String username;
    private @Column String passwordHash;
    private @Column(name = "full_name") String fullName;

    @JoinTable(name = "account_roles",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private @ManyToMany Set<Role> roles;
}