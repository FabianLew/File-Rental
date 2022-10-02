package com.filerental.filterantal.model;

import com.filerental.filterantal.dto.ClientDTO;
import com.filerental.filterantal.dto.FileDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@Table(name = "CLIENT")
public class Client extends AbstractEntity{

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<FileAccess> files;
}
