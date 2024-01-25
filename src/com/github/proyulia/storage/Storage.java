package com.github.proyulia.storage;

import com.github.proyulia.model.Resume;

public interface Storage {
    void clear();

    void save(Resume resume);

    Resume get(String uuid);

    void delete(String uuid);

    Resume[] getAll();

    void update(Resume resume);

    int size();
}
