import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int STORAGE_SIZE = 10000;
    Resume[] storage = new Resume[STORAGE_SIZE];
    int size;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume r) {
        if (findIndex(r.uuid) == -1 && size < STORAGE_SIZE) {
            storage[size++] = r;
        }
    }

    Resume get(String uuid) {
        int index = findIndex(uuid);
        return index >= 0 ? storage[index] : null;
    }

    void delete(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            storage[index] = storage[size - 1];
            storage[--size] = null;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].uuid)) {
                return i;
            }
        }
        return -1;
    }
}
