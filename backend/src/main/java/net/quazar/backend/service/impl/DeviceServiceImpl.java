package net.quazar.backend.service.impl;

import lombok.AllArgsConstructor;
import net.quazar.backend.entity.Contract;
import net.quazar.backend.entity.Device;
import net.quazar.backend.entity.Document;
import net.quazar.backend.repository.ContractRepository;
import net.quazar.backend.repository.DeviceRepository;
import net.quazar.backend.repository.DocumentRepository;
import net.quazar.backend.service.DeviceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DeviceServiceImpl implements DeviceService {
    private final DeviceRepository deviceRepository;
    private final ContractRepository contractRepository;
    private final DocumentRepository documentRepository;

    @Override
    public Device createDevice(Device device) {
        return deviceRepository.save(device);
    }

    @Override
    public boolean deleteDevice(Device device) {
        deviceRepository.delete(device);
        return true;
    }

    @Override
    public List<Contract> findContracts(Device device) {
        return contractRepository.findByDevice(device);
    }

    @Override
    public List<Document> findDocuments(Device device) {
        return documentRepository.findByDevice(device);
    }

    @Override
    public List<Document> addDocument(Device device, Document document) {
        device.getDocuments().add(document);
        return deviceRepository.save(device).getDocuments();
    }

    @Override
    public List<Document> removeDocument(Device device, Document document) {
        device.getDocuments().remove(document);
        return deviceRepository.save(device).getDocuments();
    }
}
