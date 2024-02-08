package com.github.proyulia.storage;

import com.github.proyulia.exception.DuplicateItemException;
import com.github.proyulia.exception.NotFoundStorageException;
import com.github.proyulia.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractStorageTest {
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String UUID_NOT_EXIST = "dummy";
    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final Resume RESUME_3 = new Resume(UUID_3);
    private static final Resume RESUME_4 = new Resume(UUID_4);
    private static final int INITIAL_SIZE = 3;
    protected final Storage storage;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() {
        storage.clear();
        Storage[] empty = new Storage[0];
        assertSize(0);
        Assertions.assertArrayEquals(empty, storage.getAll());
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertAll(
                () -> assertSize(INITIAL_SIZE + 1),
                () -> assertGet(RESUME_4)
        );
    }

    @Test
    public void saveDuplicate() {
        Assertions.assertThrows(DuplicateItemException.class, () -> storage.save(RESUME_1));
    }

    @Test
    public void get() {
        assertAll(
                () -> assertGet(RESUME_1),
                () -> assertGet(RESUME_2),
                () -> assertGet(RESUME_3)
        );
    }

    @Test
    public void getNotFound() {
        Assertions.assertThrows(NotFoundStorageException.class, () -> storage.get(UUID_NOT_EXIST));
    }

    @Test
    public void delete() {
        storage.delete(UUID_1);
        assertAll(
                () -> assertSize(INITIAL_SIZE - 1),
                () -> assertThrows(NotFoundStorageException.class, () -> storage.get(UUID_1))
        );
    }

    @Test
    public void deleteNotFound() {
        Assertions.assertThrows(NotFoundStorageException.class, () -> storage.delete(UUID_NOT_EXIST));
    }

    @Test
    public void getAll() {
        Resume[] all = storage.getAll();
        Resume[] expected = {RESUME_1, RESUME_2, RESUME_3};
        assertAll(
                () -> Assertions.assertEquals(INITIAL_SIZE, all.length),
                () -> Assertions.assertArrayEquals(expected, all)
        );
    }

    @Test
    void update() {
        Resume newResume = new Resume(UUID_1);
        storage.update(newResume);
        Assertions.assertEquals(newResume, storage.get(UUID_1));
    }

    @Test
    void updateNotFound() {
        assertThrows(NotFoundStorageException.class, () -> storage.update(new Resume()));
    }

    @Test
    void size() {
        assertSize(INITIAL_SIZE);
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }
}
