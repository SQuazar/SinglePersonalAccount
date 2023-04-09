package net.quazar.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "device")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Device {
    private @Id Integer id;
    private @Column String name;
    private @Column String status;

    @JoinColumn(name = "contract_id", referencedColumnName = "id")
    private @OneToOne Contract contract;
    private @OneToMany(mappedBy = "device") List<Document> documents;
}