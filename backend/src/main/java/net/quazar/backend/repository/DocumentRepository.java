package net.quazar.backend.repository;

import net.quazar.backend.entity.Device;
import net.quazar.backend.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {
    List<Document> findByDevice(Device device);
}
