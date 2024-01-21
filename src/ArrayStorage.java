import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int STORAGE_SIZE = 10000;
    Resume[] storage = new Resume[STORAGE_SIZE];
    int size = 0;

    void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    void save(Resume r) {
        if (getIndex(r.uuid) == -1 && size < STORAGE_SIZE) {
            storage[size++] = r;
        }
    }

    Resume get(String uuid) {
        int i = getIndex(uuid);
        return i >= 0 ? storage[i] : null;
    }

    void delete(String uuid) {
        int i = getIndex(uuid);
        if (i >= 0) {
            storage[i] = storage[size - 1];
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

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].uuid)) {
                return i;
            }
        }
        return -1;
    }
}
