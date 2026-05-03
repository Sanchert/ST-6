package com.mycompany.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    @DisplayName("Тест создания Player")
    void testPlayerCreation() {
        Player player = new Player();
        assertNotNull(player);
        assertEquals(0, player.move);
        assertFalse(player.selected);
        assertFalse(player.win);
    }

    @Test
    @DisplayName("Тест установки символа Player")
    void testPlayerSymbol() {
        Player player = new Player();
        player.symbol = 'X';
        assertEquals('X', player.symbol);

        player.symbol = 'O';
        assertEquals('O', player.symbol);
    }

    @Test
    @DisplayName("Тест модификации всех полей Player")
    void testPlayerModification() {
        Player player = new Player();
        player.symbol = 'X';
        player.move = 5;
        player.selected = true;
        player.win = false;

        assertEquals('X', player.symbol);
        assertEquals(5, player.move);
        assertTrue(player.selected);
        assertFalse(player.win);

        player.symbol = 'O';
        player.move = 3;
        player.selected = false;
        player.win = true;

        assertEquals('O', player.symbol);
        assertEquals(3, player.move);
        assertFalse(player.selected);
        assertTrue(player.win);
    }

    @Test
    @DisplayName("Тест нескольких Player объектов")
    void testMultiplePlayers() {
        Player p1 = new Player();
        Player p2 = new Player();

        p1.symbol = 'X';
        p2.symbol = 'O';

        assertNotEquals(p1.symbol, p2.symbol);

        p1.move = 1;
        p2.move = 2;
        assertNotEquals(p1.move, p2.move);

        p1.selected = true;
        p2.selected = false;
        assertNotEquals(p1.selected, p2.selected);
    }

    @Test
    @DisplayName("Тест значений по умолчанию")
    void testDefaultValues() {
        Player player = new Player();
        assertEquals('\u0000', player.symbol);
        assertEquals(0, player.move);
        assertFalse(player.selected);
        assertFalse(player.win);
    }
}
