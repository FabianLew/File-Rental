package com.filerental.filterantal.dto;

import com.filerental.filterantal.model.Client;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ClientDTO extends AbstractDTO<Client>{
    private String name;
    private List<FileAccessDTO> files;

    public ClientDTO(Client entity) {
        super(entity);
    }

    @Override
    protected void fromEntity(Client en) {
        this.name = en.getName();
        this.files = en.getFiles().stream().map(FileAccessDTO::new).collect(Collectors.toList());
    }
}
