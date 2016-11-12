package service.file;

import domain.file.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.FileRepository;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Override
    public FileEntity save(FileEntity fileEntity) {
        return fileRepository.save(fileEntity);
    }

    @Override
    public FileEntity get(String uuid) {
        return fileRepository.findByUuid(uuid);
    }

    @Override
    public void delete(FileEntity fileEntity) {
        fileRepository.delete(fileEntity);
    }
}
