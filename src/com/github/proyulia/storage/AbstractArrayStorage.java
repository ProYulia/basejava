package com.github.proyulia.storage;

import com.github.proyulia.model.Resume;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_SIZE = 10000;
    protected static final String DUPLICATE_ERROR = "Resume with uuid=%s already exists%n";
    protected static final String NOT_FOUND_ERROR = "Resume with uuid=%s does not exist%n";
    protected static final String NO_STORAGE_ERROR = "No more free storage space left";

    protected final Resume[] storage = new Resume[STORAGE_SIZE];

    public int size;

    @Override
    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            return storage[index];
        } else {
            System.out.printf(NOT_FOUND_ERROR, uuid);
            return null;
        }
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract int findIndex(String uuid);
}
