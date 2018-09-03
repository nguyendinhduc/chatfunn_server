package com.ducnd.chattfunn.model.out;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ContentCommonId implements Serializable {
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
