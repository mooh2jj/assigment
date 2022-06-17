package com.dsg.wemakeprice.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RegisterStatus {

    REQUEST("요청"),
    APPROVAL("승인");

    private final String message;
}
