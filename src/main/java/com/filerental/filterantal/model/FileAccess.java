package com.filerental.filterantal.model;

import com.filerental.filterantal.dto.FileAccessDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "FILE_ACCESS")
public class FileAccess extends AbstractEntity{
    @ManyToOne
    @JoinColumn(name = "file_id")
    private File file;

    @Column(name = "permanent")
    private boolean isPermanent;

    @Column(name = "rented_from")
    private LocalDate rentedFrom;

    @Column(name = "rented_to")
    private LocalDate rentedTo;

    @ManyToOne
    private Client client;
}
