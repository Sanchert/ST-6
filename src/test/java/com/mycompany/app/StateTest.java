package com.mycompany.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class StateTest {

    @Test
    @DisplayName("Тест всех значений State enum")
    void testStateEnumValues() {
        State[] states = State.values();
        assertEquals(4, states.length);
        assertTrue(containsState(states, State.PLAYING));
        assertTrue(containsState(states, State.OWIN));
        assertTrue(containsState(states, State.XWIN));
        assertTrue(containsState(states, State.DRAW));
    }

    private boolean containsState(State[] states, State target) {
        for (State s : states) {
            if (s == target) return true;
        }
        return false;
    }

    @Test
    @DisplayName("Тест получения State по имени")
    void testStateValueOf() {
        assertEquals(State.PLAYING, State.valueOf("PLAYING"));
        assertEquals(State.XWIN, State.valueOf("XWIN"));
        assertEquals(State.OWIN, State.valueOf("OWIN"));
        assertEquals(State.DRAW, State.valueOf("DRAW"));
    }

    @Test
    @DisplayName("Тест уникальности значений State")
    void testStateUniqueness() {
        assertNotEquals(State.PLAYING, State.XWIN);
        assertNotEquals(State.PLAYING, State.OWIN);
        assertNotEquals(State.PLAYING, State.DRAW);
        assertNotEquals(State.XWIN, State.OWIN);
        assertNotEquals(State.XWIN, State.DRAW);
        assertNotEquals(State.OWIN, State.DRAW);
    }

    @Test
    @DisplayName("Тест ordinal значений State")
    void testStateOrdinal() {
        assertEquals(0, State.PLAYING.ordinal());
        assertEquals(1, State.OWIN.ordinal());
        assertEquals(2, State.XWIN.ordinal());
        assertEquals(3, State.DRAW.ordinal());
    }

    @Test
    @DisplayName("Тест toString значений State")
    void testStateToString() {
        assertEquals("PLAYING", State.PLAYING.toString());
        assertEquals("OWIN", State.OWIN.toString());
        assertEquals("XWIN", State.XWIN.toString());
        assertEquals("DRAW", State.DRAW.toString());
    }
}
