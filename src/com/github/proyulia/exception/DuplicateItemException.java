package com.github.proyulia.exception;

public class DuplicateItemException extends StorageException {
    public DuplicateItemException(String uuid) {
        super("Resume " + uuid + "already exists", uuid);
    }
}
