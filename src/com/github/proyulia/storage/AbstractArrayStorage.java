package com.github.proyulia.storage;

import com.github.proyulia.exception.DuplicateItemException;
import com.github.proyulia.exception.NotFoundStorageException;
import com.github.proyulia.exception.StorageException;
import com.github.proyulia.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_SIZE = 10000;
    protected static final String NO_STORAGE_ERROR = "No more free storage space left";

    protected final Resume[] storage = new Resume[STORAGE_SIZE];

    public int size;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public final void save(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (size >= STORAGE_SIZE) {
            throw new StorageException(NO_STORAGE_ERROR, resume.getUuid());
        } else if (index >= 0) {
            throw new DuplicateItemException(resume.getUuid());
        } else {
            insertResume(index, resume);
            size++;
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            return storage[index];
        } else {
            throw new NotFoundStorageException(uuid);
        }
    }

    @Override
    public final void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            throw new NotFoundStorageException(uuid);
        } else {
            squishArray(index, uuid);
            storage[--size] = null;
        }
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    public final void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            throw new NotFoundStorageException(resume.getUuid());
        }
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract int findIndex(String uuid);

    protected abstract void insertResume(int insertionPoint, Resume resume);

    protected abstract void squishArray(int index, String uuid);
}
