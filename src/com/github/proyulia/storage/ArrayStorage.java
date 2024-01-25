package com.github.proyulia.storage;

import com.github.proyulia.model.Resume;

import java.util.Arrays;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
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

    @Override
    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            storage[index] = storage[size - 1];
            storage[--size] = null;
        } else {
            System.out.printf(NOT_FOUND_ERROR, uuid);
        }
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    public void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            System.out.printf(NOT_FOUND_ERROR, resume.getUuid());
        }
    }

    protected int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
