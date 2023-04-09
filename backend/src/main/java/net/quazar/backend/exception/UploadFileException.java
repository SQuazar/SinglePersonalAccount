package net.quazar.backend.exception;

import lombok.Getter;

@Getter
public class UploadFileException extends RuntimeException {
    private final Reason reason;

    public UploadFileException(Reason reason) {
        this.reason = reason;
    }

    public UploadFileException(String message, Reason reason) {
        super(message);
        this.reason = reason;
    }

    public UploadFileException(String message, Throwable cause, Reason reason) {
        super(message, cause);
        this.reason = reason;
    }

    public UploadFileException(Throwable cause, Reason reason) {
        super(cause);
        this.reason = reason;
    }

    public enum Reason {
        NOT_FOUND,
        IS_EMPTY,
        OUTSIDE_ROOT,
        INVALID
    }
}
