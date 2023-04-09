package net.quazar.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "role")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {
    private @Id Integer id;
    private @Column String name;
    private @Column String color;
    private @ManyToMany(mappedBy = "roles") List<Account> accounts;
    private @ElementCollection Set<String> permissions;
}
