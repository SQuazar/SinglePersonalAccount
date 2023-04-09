package net.quazar.backend.service;

import net.quazar.backend.entity.FileEntry;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

public interface FileUploadService {
    /**
     * Загружает файл на сервер в папку uploads
     * @param file файл
     * @param displayName отображаемое пользователю имя файла
     * @return запись файла
     */
    FileEntry uploadFile(MultipartFile file, String displayName);

    /**
     * Получает путь к файлу по его имени (в нашем случае по id)
     * @param filename имя файла
     * @return путь к файлу
     */
    Path load(String filename);

    /**
     * Возвращает список всех путей к файлам в папке uploads
     * @return список путей к файлам
     */
    List<Path> loadAll();

    /**
     * Получает файл как ресурс, который отправляется непосредственно пользователю
     * @param filename имя файла
     * @return ресурс файла
     */
    Resource loadAsResource(String filename);

    /**
     * Инициализация сервиса. Создаёт папку uploads
     */
    void init();
}
