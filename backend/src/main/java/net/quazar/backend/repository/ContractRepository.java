package net.quazar.backend.repository;

import net.quazar.backend.entity.Contract;
import net.quazar.backend.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {
    List<Contract> findByDevice(Device device);
}
