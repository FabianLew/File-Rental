package com.filerental.filterantal.dto;

import com.filerental.filterantal.model.AbstractEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public abstract class AbstractDTO<T extends AbstractEntity> {
    protected UUID uuid;

    protected AbstractDTO(T entity) {
        this.uuid = entity.getUuid();
        fromEntity(entity);
    }

    protected abstract void fromEntity(T en);
}
