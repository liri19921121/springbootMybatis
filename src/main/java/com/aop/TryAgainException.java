package com.aop;

import com.aop.exception.ApiException;

public class TryAgainException extends ApiException {

    public TryAgainException(String innerMessage) {
        super(1, innerMessage);
    }
    public TryAgainException(int type, String innerMessage) {
        super(type, innerMessage);
    }

    public TryAgainException(int type, String innerMessage, Object[] objects) {
        super(type, innerMessage, objects);
    }

    public TryAgainException(int type, Exception e) {
        super(type, e);
    }

    public TryAgainException(int type, Exception e, Object[] objects) {
        super(type, e, objects);
    }
}
