package com.github.proyulia.storage;

import com.github.proyulia.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void saveData(Object index, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected Resume getData(Object index, String uuid) {
        return storage.get((Integer) index);
    }

    @Override
    protected void deleteData(Object index, String uuid) {
        storage.remove(((Integer) index).intValue());
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    protected void updateData(Object index, Resume resume) {
        storage.set((Integer) index, resume);
    }

    @Override
    protected boolean isExist(Object index) {
        return index != null;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Integer searchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }
}
