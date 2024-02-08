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
    protected void saveData(int index, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected Resume getData(int index, String uuid) {
        return storage.get(index);
    }

    @Override
    protected void deleteData(int index, String uuid) {
        storage.remove(index);
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    protected void updateData(int index, Resume resume) {
        storage.set(index, resume);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected int findIndex(String uuid) {
        return storage.indexOf(new Resume(uuid));
    }
}
