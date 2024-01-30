package com.github.proyulia.storage;

import com.github.proyulia.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_SIZE = 10000;
    protected static final String DUPLICATE_ERROR = "Resume with uuid=%s already exists%n";
    protected static final String NOT_FOUND_ERROR = "Resume with uuid=%s does not exist%n";
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
            System.out.println(NO_STORAGE_ERROR);
        } else if (index >= 0) {
            System.out.printf(DUPLICATE_ERROR, resume.getUuid());
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
            System.out.printf(NOT_FOUND_ERROR, uuid);
            return null;
        }
    }

    @Override
    public final void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.printf(NOT_FOUND_ERROR, uuid);
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
            System.out.printf(NOT_FOUND_ERROR, resume.getUuid());
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
