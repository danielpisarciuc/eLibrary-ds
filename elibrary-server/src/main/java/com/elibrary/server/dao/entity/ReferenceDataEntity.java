package com.elibrary.server.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "ReferenceDataEntity")
@Table(name = "REFERENCE_DATA")
public class ReferenceDataEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seq;

    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "CODE", unique = true, nullable = false)
    private String code;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "TYPE", unique = true, nullable = false)
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReferenceDataEntity)) return false;
        ReferenceDataEntity that = (ReferenceDataEntity) o;
        return Objects.equals(seq, that.seq) &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getCode(), that.getCode()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getType(), that.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(seq, getId(), getCode(), getDescription(), getType());
    }

    @Override
    public String toString() {
        return "ReferenceDataEntity{" +
                "seq=" + seq +
                ", id=" + id +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
