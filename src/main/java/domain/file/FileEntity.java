package domain.file;

import domain.IndexEntity;

import javax.persistence.*;

@Entity
@Table(name = "file")
public class FileEntity extends IndexEntity {

    @Column(name = "filename", nullable = false)
    private String filename;

    @Column(name = "extension", nullable = false)
    private String extension;

    public FileEntity() {
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
