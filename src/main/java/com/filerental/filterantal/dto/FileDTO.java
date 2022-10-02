package com.filerental.filterantal.dto;

import com.filerental.filterantal.enu.FileType;
import com.filerental.filterantal.model.File;
import lombok.Data;

import javax.persistence.Column;

@Data
public class FileDTO extends AbstractDTO<File>{
    private String name;
    private String extension;
    private FileType fileType;
    private String url;

    public FileDTO(File entity) {
        super(entity);
    }

    @Override
    protected void fromEntity(File en) {
        this.name = en.getName();
        this.extension = en.getExtension();
        this.fileType = en.getFileType();
        this.url = en.getUrl();
    }
}
