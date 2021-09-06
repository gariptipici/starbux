package com.bestseller.starbux.common.model;

import java.io.Serializable;
import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    protected BaseEntity() {
    }
}
