package net.quazar.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "document")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Document {
    private @Id Integer id;
    private @OneToOne FileEntry fileEntry;
    @JoinColumn(name = "device_id")
    private @ManyToOne Device device;
}