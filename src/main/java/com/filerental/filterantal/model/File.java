package com.filerental.filterantal.model;

import com.filerental.filterantal.dto.FileDTO;
import com.filerental.filterantal.enu.FileType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "FILE")
public class File extends AbstractEntity{
    @Column(name = "name")
    private String name;

    @Column(name = "extension")
    private String extension;

    @Column(name = "type")
    private FileType fileType;

    @Column(name = "url")
    private String url;
}
