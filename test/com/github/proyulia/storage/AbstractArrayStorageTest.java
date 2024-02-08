package com.github.proyulia.storage;

import com.github.proyulia.exception.StorageException;
import com.github.proyulia.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {
    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test
    public void saveExcessStorageSpace() {
        storage.clear();
        try {
            while (storage.size() < AbstractArrayStorage.STORAGE_SIZE) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assertions.fail("Unexpected exception before array is filled");
        }
        Assertions.assertThrows(StorageException.class, () -> storage.save(new Resume()));
    }
}