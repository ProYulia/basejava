package com.github.proyulia.storage;

import com.github.proyulia.exception.DuplicateItemException;
import com.github.proyulia.exception.NotFoundStorageException;
import com.github.proyulia.exception.StorageException;
import com.github.proyulia.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final Resume[] arr = {new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void clear() {
        storage.clear();
        Assertions.assertEquals(0, storage.size());
    }

    @Test
    public void save() {
        Resume newResume = new Resume();
        storage.save(newResume);
        assertAll(
                () -> Assertions.assertEquals(4, storage.size()),
                () -> Assertions.assertEquals(newResume, storage.get(newResume.getUuid()))
        );
    }

    @Test
    public void saveExcessStorageSpace() {
        try {
            while (storage.size() < AbstractArrayStorage.STORAGE_SIZE) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assertions.fail("Unexpected exception before array is filled");
        }
        Assertions.assertThrows(StorageException.class, () -> storage.save(new Resume()));
    }

    @Test
    public void saveDuplicate() {
        Assertions.assertThrows(DuplicateItemException.class, () -> storage.save(new Resume(UUID_1)));
    }

    @Test
    public void get() {
        assertAll(
                () -> assertEquals(new Resume(UUID_1), storage.get(UUID_1)),
                () -> assertEquals(new Resume(UUID_2), storage.get(UUID_2)),
                () -> assertEquals(new Resume(UUID_3), storage.get(UUID_3))
        );
    }

    @Test
    public void getNotFound() {
        Assertions.assertThrows(NotFoundStorageException.class, () -> storage.get("dummy"));
    }

    @Test
    public void delete() {
        storage.delete(UUID_1);
        assertAll(
                () -> Assertions.assertEquals(2, storage.size()),
                () -> assertThrows(NotFoundStorageException.class, () -> storage.get(UUID_1))
        );
    }

    @Test
    public void deleteNotFound() {
        Assertions.assertThrows(NotFoundStorageException.class, () -> storage.delete("dummy"));
    }

    @Test
    public void getAll() {
        Resume[] all = storage.getAll();
        assertAll(
                () -> Assertions.assertEquals(3, all.length),
                () -> Assertions.assertArrayEquals(arr, all)
        );
    }

    @Test
    void updateNotFound() {
        assertThrows(NotFoundStorageException.class, () -> storage.update(new Resume()));
    }

    @Test
    void size() {
        Assertions.assertEquals(3, storage.size());
    }
}