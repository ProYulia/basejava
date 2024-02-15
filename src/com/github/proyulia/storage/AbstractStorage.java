package com.github.proyulia.storage;

import com.github.proyulia.exception.DuplicateItemException;
import com.github.proyulia.exception.NotFoundStorageException;
import com.github.proyulia.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public final void save(Resume resume) {
        saveData(getNotExistingSearchKey(resume.getUuid()), resume);
    }

    @Override
    public final Resume get(String uuid) {
        return getData(getExistingSearchKey(uuid), uuid);
    }


    @Override
    public final void delete(String uuid) {
        deleteData(getExistingSearchKey(uuid), uuid);
    }

    @Override
    public final void update(Resume resume) {
        updateData(getExistingSearchKey(resume.getUuid()), resume);
    }

    private Object getExistingSearchKey(String uuid) {
        Object searchKey = searchKey(uuid);
        if (!(isExist(searchKey))) {
            throw new NotFoundStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistingSearchKey(String uuid) {
        Object searchKey = searchKey(uuid);
        if (isExist(searchKey)) {
            throw new DuplicateItemException(uuid);
        }
        return searchKey;
    }

    protected abstract Object searchKey(String uuid);

    protected abstract void saveData(Object searchKey, Resume resume);

    protected abstract Resume getData(Object searchKey, String uuid);

    protected abstract void deleteData(Object searchKey, String uuid);

    protected abstract void updateData(Object searchKey, Resume resume);

    protected abstract boolean isExist(Object searchKey);
}
