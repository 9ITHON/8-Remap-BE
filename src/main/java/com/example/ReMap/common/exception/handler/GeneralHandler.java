package com.example.ReMap.common.exception.handler;

import com.example.ReMap.common.apiPayload.code.BaseErrorCode;
import com.example.ReMap.common.exception.GeneralException;

public class GeneralHandler extends GeneralException {

    public GeneralHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
