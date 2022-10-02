package com.filerental.filterantal.model;

import com.filerental.filterantal.dto.AbstractDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractEntity{

    @Id
    @Column(updatable = false, nullable = false, unique = true, columnDefinition = "uuid")
    @GeneratedValue(generator = "UUID")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID uuid;

}
