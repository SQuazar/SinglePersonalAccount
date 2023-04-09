package net.quazar.backend.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("documents-storage")
@Getter
@Setter
public class DocumentsStorageProperties {
    private String location = "uploads/documents";
}
