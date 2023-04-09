package net.quazar.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contract")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Contract {
    private @Id Integer id;
    private @OneToOne FileEntry fileEntry;
    private @OneToOne(mappedBy = "contract") Device device;
}
