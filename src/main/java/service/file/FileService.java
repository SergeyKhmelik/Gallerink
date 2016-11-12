package service.file;


import domain.file.FileEntity;

public interface FileService {

    FileEntity save(FileEntity fileEntity);

    FileEntity get(String uuid);

    void delete(FileEntity fileEntity);

}
