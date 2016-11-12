package dto.file;

import domain.file.FileEntity;

public class _FileEntity {

    public String uuid;

    public String extension;

    public _FileEntity(FileEntity fileEntity) {
        setUuid(fileEntity.getUuid());
        setExtension(fileEntity.getExtension());
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
