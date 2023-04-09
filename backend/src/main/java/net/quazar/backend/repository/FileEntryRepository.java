package net.quazar.backend.repository;

import net.quazar.backend.entity.FileEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileEntryRepository extends JpaRepository<FileEntry, String> {
}
