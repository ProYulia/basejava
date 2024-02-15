package com.github.proyulia.storage;

import com.github.proyulia.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer searchKey(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void insertResume(int insertionPoint, Resume resume) {
        insertionPoint = -insertionPoint - 1;
        System.arraycopy(storage, insertionPoint, storage, insertionPoint + 1, size - insertionPoint);
        storage[insertionPoint] = resume;
    }

    @Override
    protected void squishArray(int index, String uuid) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }
}
