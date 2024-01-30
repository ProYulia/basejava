package com.github.proyulia.exception;

public class NotFoundStorageException extends StorageException {
    public NotFoundStorageException(String uuid) {
        super("Resume " + uuid + "does not exist", uuid);
    }
}
