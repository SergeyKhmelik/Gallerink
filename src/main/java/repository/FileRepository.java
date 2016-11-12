package repository;

import domain.file.FileEntity;
import org.springframework.data.repository.CrudRepository;

public interface FileRepository extends CrudRepository<FileEntity, Long> {

    FileEntity findByUuid(String uuid);

}
