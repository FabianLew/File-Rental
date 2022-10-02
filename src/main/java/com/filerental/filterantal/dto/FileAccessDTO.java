package com.filerental.filterantal.dto;

import com.filerental.filterantal.model.Client;
import com.filerental.filterantal.model.FileAccess;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
public class FileAccessDTO extends AbstractDTO<FileAccess> {
    private FileDTO file;
    private boolean isPermanent;
    private LocalDate rentedFrom;
    private LocalDate rentedTo;

    public FileAccessDTO(FileAccess entity) {
        super(entity);
    }

    @Override
    protected void fromEntity(FileAccess en) {
        this.file = new FileDTO(en.getFile());
        this.isPermanent = en.isPermanent();
        this.rentedFrom = en.getRentedFrom();
        this.rentedTo = en.getRentedTo();
    }
}
