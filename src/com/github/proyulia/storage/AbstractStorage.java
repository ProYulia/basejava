package com.github.proyulia.storage;

import com.github.proyulia.exception.DuplicateItemException;
import com.github.proyulia.exception.NotFoundStorageException;
import com.github.proyulia.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public final void save(Resume resume) {
        int index = checkDuplicate(resume.getUuid());
        saveData(index, resume);
    }

    @Override
    public final Resume get(String uuid) {
        return getData(checkIfExists(uuid), uuid);
    }


    @Override
    public final void delete(String uuid) {
        deleteData(checkIfExists(uuid), uuid);
    }

    @Override
    public final void update(Resume resume) {
        int index = checkIfExists(resume.getUuid());
        updateData(index, resume);
    }

    private int checkDuplicate(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            throw new DuplicateItemException(uuid);
        }
        return index;
    }

    private int checkIfExists(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            throw new NotFoundStorageException(uuid);
        }
        return index;
    }

    protected abstract int findIndex(String uuid);

    protected abstract void saveData(int index, Resume resume);

    protected abstract Resume getData(int index, String uuid);

    protected abstract void deleteData(int index, String uuid);

    protected abstract void updateData(int index, Resume resume);
}
