package com.filerental.filterantal.command;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class RentFileCommand {
    private String fileUuid;
    private boolean isPermanent;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date rentedTo;
}
