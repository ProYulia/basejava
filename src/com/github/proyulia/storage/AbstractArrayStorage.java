package com.github.proyulia.storage;

import com.github.proyulia.exception.StorageException;
import com.github.proyulia.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
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
    public final void saveData(int index, Resume resume) {
        if (size >= STORAGE_SIZE) {
            throw new StorageException(NO_STORAGE_ERROR, resume.getUuid());
        }
        insertResume(index, resume);
        size++;
    }

    @Override
    public Resume getData(int index, String uuid) {
        return storage[index];
    }

    @Override
    public final void deleteData(int index, String uuid) {
        squishArray(index, uuid);
        storage[--size] = null;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    public final void updateData(int index, Resume resume) {
        storage[index] = resume;
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract void insertResume(int insertionPoint, Resume resume);

    protected abstract void squishArray(int index, String uuid);
}
