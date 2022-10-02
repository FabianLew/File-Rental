package com.filerental.filterantal.enu;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FileType {
    IMAGE(0),
    AUDIO(1);

    private final int index;
}
