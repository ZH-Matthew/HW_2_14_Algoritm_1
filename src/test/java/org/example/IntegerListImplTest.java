package org.example;

import org.example.Exception.ElementNotFoundException;
import org.example.Exception.InvalidIndexException;
import org.example.Exception.NullItemException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.example.Constants.*;
import static org.example.Constants.INT_2;
import static org.junit.jupiter.api.Assertions.*;

class IntegerListImplTest {
    IntegerList service = new IntegerListImpl();

    @BeforeEach
    public void addString() {
        service.add(INT_1);
        service.add(INT_2);
        service.add(INT_3);
    }

    @Test
    void add() {
        assertEquals(INT_1, service.toArray()[0]);
    }

    @Test
    void add2() {
        service.add(1, INT_1);
        assertEquals(INT_1, service.get(1));
        assertEquals(INT_2, service.get(2));

    }

    @Test
    void add2InvalidException() {
        addString();
        assertThrows(InvalidIndexException.class, () -> service.add(20, INT_1));
    }

    @Test
    void Set() {
        service.set(1, INT_1);
        assertEquals(INT_1, service.get(1));
    }

    @Test
    void SetInvalidException() {
        assertThrows(InvalidIndexException.class, () -> service.set(20, INT_1));
    }

    @Test
    void SetNullException() {
        assertThrows(NullItemException.class, () -> service.set(2, null));
    }

    @Test
    void remove() {
        service.remove(INT_1);
        assertEquals(INT_2, service.get(0));
    }

    @Test
    void removeNotFoundException() {
        assertThrows(ElementNotFoundException.class, () -> service.remove(INT_L));
    }

    @Test
    void remove2() {
        service.remove(1);
        assertEquals(INT_3, service.get(1));
    }

    @Test
    void remove2NotFoundException() {
        service.remove(1);
        assertThrows(InvalidIndexException.class, () -> service.remove(20));
    }

    @Test
    void contains() {
        service.add(444);
        service.add(33333333);
        service.add(44444444);
        service.add(780);
        service.add(1234567);
        service.add(7654321);
        service.add(135890654);
        assertTrue(service.contains(INT_L));
        assertFalse(service.contains(555555));
    }

    @Test
    void indexOf() {
        assertEquals(1, service.indexOf(INT_2));
        assertEquals(-1, service.indexOf(INT_L));
    }

    @Test
    void lastIndexOf() {
        assertEquals(1, service.lastIndexOf(INT_2));
        assertEquals(-1, service.lastIndexOf(INT_L));
    }

    @Test
    void get() {
        assertEquals(INT_2, service.get(1));
    }

    @Test
    void getExc() {
        assertThrows(InvalidIndexException.class, () -> service.get(20));
    }

    @Test
    void equals() {
        assertTrue(service.equals(ARR_1));
    }

    @Test
    void isEmpty() {
        assertFalse(service.isEmpty());
    }

    @Test
    void size() {
        assertEquals(3, service.size());
    }

    @Test
    void sortInsertion() {
        assertEquals(Arrays.toString(ARR_1), Arrays.toString(service.goSortInsertionForTest(ARR_2)));
    }

}