package net.quazar.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity @Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileEntry {
    @Id
    private String name;
    private String displayName;
    @Column(unique = true)
    private String path;
    private LocalDateTime uploadDate;
}