package com.easeArch.common.util;

public class TrayException extends RuntimeException {
    private static final long serialVersionUID=4321L;

    public TrayException(String message) {
        super(message);
    }

    public TrayException(String message, Throwable cause) {
        super(message, cause);
    }

    public TrayException(Throwable cause) {
        super(cause);
    }
}
