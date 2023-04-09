package net.quazar.backend.service.impl;

import net.quazar.backend.config.DocumentsStorageProperties;
import net.quazar.backend.entity.FileEntry;
import net.quazar.backend.exception.UploadFileException;
import net.quazar.backend.repository.FileEntryRepository;
import net.quazar.backend.service.FileUploadService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    private final FileEntryRepository fileEntryRepository;
    private final Path rootPath;

    public FileUploadServiceImpl(FileEntryRepository fileEntryRepository, DocumentsStorageProperties properties) {
        this.fileEntryRepository = fileEntryRepository;
        this.rootPath = Paths.get(properties.getLocation());
    }

    @Override
    public FileEntry uploadFile(MultipartFile file, String displayName) {
        if (file.isEmpty())
            throw new UploadFileException("Cannot upload empty file.", UploadFileException.Reason.IS_EMPTY);
        String name = UUID.randomUUID().toString().replace("-", "");
        Path destinationFile = this.rootPath.resolve(name + "." + FilenameUtils.getExtension(file.getOriginalFilename()))
                .normalize().toAbsolutePath();
        if (!destinationFile.getParent().equals(this.rootPath.toAbsolutePath()))
            throw new UploadFileException(
                    "Cannot store file outside current directory.", UploadFileException.Reason.OUTSIDE_ROOT);
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, destinationFile,
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new UploadFileException("Cannot upload file to file system", e, UploadFileException.Reason.INVALID);
        }
        return fileEntryRepository.save(FileEntry.builder()
                .name(name).displayName(displayName)
                .uploadDate(LocalDateTime.now()).path(destinationFile.toFile().getPath())
                .build());
    }

    @Override
    public Path load(String filename) {
        FileEntry entry = fileEntryRepository.findById(filename).orElseThrow(() ->
                new UploadFileException("Cannot find file with this name", UploadFileException.Reason.NOT_FOUND));
        return rootPath.resolve(entry.getPath());
    }

    @Override
    public List<Path> loadAll() {
        try (var files = Files.list(rootPath)){
            return files.toList();
        } catch (IOException e) {
            throw new UploadFileException("Failed read upload directory", e, UploadFileException.Reason.INVALID);
        }
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable())
                return resource;
            throw new UploadFileException("Cannot read file: " + filename, UploadFileException.Reason.INVALID);
        } catch (MalformedURLException e) {
            throw new UploadFileException("Cannot read file: " + filename, e, UploadFileException.Reason.INVALID);
        }
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
