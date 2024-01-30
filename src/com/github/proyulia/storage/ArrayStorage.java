package com.github.proyulia.storage;

import com.github.proyulia.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    protected int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void insertResume(int insertionPoint, Resume resume) {
        storage[size] = resume;
    }

    @Override
    protected void squishArray(int index, String uuid) {
        storage[index] = storage[size - 1];
    }
}
