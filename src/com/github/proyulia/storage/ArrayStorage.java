package com.github.proyulia.storage;

import com.github.proyulia.model.Resume;

import java.util.Arrays;

public class ArrayStorage {
    private static final int STORAGE_SIZE = 10000;
    private static final String DUPLICATE_ERROR = "Resume with uuid=%s already exists%n";
    private static final String NOT_FOUND_ERROR = "Resume with uuid=%s does not exist%n";
    private static final String NO_STORAGE_ERROR = "No more free storage space left";

    private final Resume[] storage = new Resume[STORAGE_SIZE];

    protected int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (size >= STORAGE_SIZE) {
            System.out.println(NO_STORAGE_ERROR);
        } else if (index >= 0) {
            System.out.printf(DUPLICATE_ERROR, resume.getUuid());
        } else {
            storage[size++] = resume;
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            return storage[index];
        } else {
            System.out.printf(NOT_FOUND_ERROR, uuid);
            return null;
        }
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            storage[index] = storage[size - 1];
            storage[--size] = null;
        } else {
            System.out.printf(NOT_FOUND_ERROR, uuid);
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            System.out.printf(NOT_FOUND_ERROR, resume.getUuid());
        }
    }

    public int size() {
        return size;
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
