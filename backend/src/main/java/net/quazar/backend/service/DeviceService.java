package net.quazar.backend.service;

import net.quazar.backend.entity.Contract;
import net.quazar.backend.entity.Device;
import net.quazar.backend.entity.Document;

import java.util.List;

public interface DeviceService {
    Device createDevice(Device device);
    boolean deleteDevice(Device device);
    List<Contract> findContracts(Device device);
    List<Document> findDocuments(Device device);
    List<Document> addDocument(Device device, Document document);
    List<Document> removeDocument(Device device, Document document);
}
