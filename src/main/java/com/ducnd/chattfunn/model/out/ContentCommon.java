package com.ducnd.chattfunn.model.out;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class ContentCommon {
    @EmbeddedId
    private ContentCommonId id;

    public ContentCommonId getId() {
        return id;
    }

    public void setId(ContentCommonId id) {
        this.id = id;
    }
}
