package org.example;

import org.example.Exception.ElementNotFoundException;
import org.example.Exception.InvalidIndexException;
import org.example.Exception.NullItemException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.Provider;

import static org.example.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

class StringListImplTest {

    StringList service = new StringListImpl();

    @BeforeEach
    public void addString() {
        service.add(ST_1);
        service.add(ST_2);
        service.add(ST_3);
    }

    @Test
    void add() {
        assertEquals(ST_1, service.toArray()[0]);
    }

    @Test
    void add2() {
        service.add(1, ST_1);
        assertEquals(ST_1, service.get(1));
        assertEquals(ST_2, service.get(2));

    }

    @Test
    void add2InvalidException() {
        addString();
        assertThrows(InvalidIndexException.class, () -> service.add(20, ST_1));
    }

    @Test
    void Set() {
        service.set(1, ST_1);
        assertEquals(ST_1, service.get(1));
    }

    @Test
    void SetInvalidException() {
        assertThrows(InvalidIndexException.class, () -> service.set(20, ST_1));
    }

    @Test
    void SetNullException() {
        assertThrows(NullItemException.class, () -> service.set(2, null));
    }

    @Test
    void remove() {
        service.remove(ST_1);
        assertEquals(ST_2, service.get(0));
    }

    @Test
    void removeNotFoundException() {
        assertThrows(ElementNotFoundException.class, () -> service.remove(ST_L));
    }

    @Test
    void remove2() {
        service.remove(1);
        assertEquals(ST_3, service.get(1));
    }

    @Test
    void remove2NotFoundException() {
        service.remove(1);
        assertThrows(InvalidIndexException.class, () -> service.remove(20));
    }

    @Test
    void contains() {
        assertTrue(service.contains(ST_1));
        assertFalse(service.contains(ST_L));
    }

    @Test
    void indexOf() {
        assertEquals(1, service.indexOf(ST_2));
        assertEquals(-1, service.indexOf(ST_L));
    }

    @Test
    void lastIndexOf() {
        assertEquals(1, service.lastIndexOf(ST_2));
        assertEquals(-1, service.lastIndexOf(ST_L));
    }

    @Test
    void get() {
        assertEquals(ST_2, service.get(1));
    }

    @Test
    void getExc() {
        assertThrows(InvalidIndexException.class, () -> service.get(20));
    }

    @Test
    void equals() {
        assertThrows(NullPointerException.class, () -> service.equals(null));
    }

    @Test
    void isEmpty() {
        assertFalse(service.isEmpty());
    }

    @Test
    void size() {
        assertEquals(3, service.size());
    }

}