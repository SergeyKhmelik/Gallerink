package domain;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
public abstract class IndexEntity {

    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(unique = true, nullable = false)
    protected String uuid = UUID.randomUUID().toString();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IndexEntity that = (IndexEntity) o;
        return Objects.equal(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }
}
