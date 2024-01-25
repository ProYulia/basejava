package com.github.proyulia;

import com.github.proyulia.model.Resume;
import com.github.proyulia.storage.ArrayStorage;

/**
 * Test for your com.github.proyulia.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    private static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume();
        r1.setUuid("uuid1");
        Resume r2 = new Resume();
        r2.setUuid("uuid2");
        Resume r3 = new Resume();
        r3.setUuid("uuid3");

        System.out.println("===== Test save() ====");
        System.out.println();
        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
        System.out.println("Size: " + ARRAY_STORAGE.size());
        System.out.println();

        System.out.println("==== Test get() ====");
        System.out.println();
        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
        System.out.println();

        System.out.println("==== Test delete() ====");
        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        System.out.println();

        System.out.println("==== Test update() =====");
        Resume r4 = new Resume();
        r4.setUuid("uuid4");
        ARRAY_STORAGE.update(r4);
        printAll();
        System.out.println();

        System.out.println("==== Test clear() ====");
        ARRAY_STORAGE.clear();
        printAll();
        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All:");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
