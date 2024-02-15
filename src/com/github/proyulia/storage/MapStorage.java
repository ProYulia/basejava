package com.github.proyulia.storage;

import com.github.proyulia.model.Resume;

import java.util.Map;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new TreeMap<>();

    @Override
    protected Object searchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void saveData(Object searchKey, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getData(Object searchKey, String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void deleteData(Object searchKey, String uuid) {
        storage.remove(uuid);
    }

    @Override
    protected void updateData(Object searchKey, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected boolean isExist(Object uuid) {
        return storage.containsKey((String) uuid);
    }

    @Override
    public void clear() {
        storage.clear();

    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
