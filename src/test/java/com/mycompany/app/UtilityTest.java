package com.mycompany.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {

    @Test
    @DisplayName("Тест Utility.print для char массива")
    void testPrintCharArray() {
        char[] board = {'X', 'O', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
        assertDoesNotThrow(() -> Utility.print(board));
    }

    @Test
    @DisplayName("Тест Utility.print для int массива")
    void testPrintIntArray() {
        int[] board = {1, 2, 3, 0, 0, 0, 0, 0, 0};
        assertDoesNotThrow(() -> Utility.print(board));
    }

    @Test
    @DisplayName("Тест Utility.print для ArrayList")
    void testPrintArrayList() {
        ArrayList<Integer> moves = new ArrayList<>();
        moves.add(1);
        moves.add(3);
        moves.add(5);
        assertDoesNotThrow(() -> Utility.print(moves));
    }

    @Test
    @DisplayName("Тест Utility.print с пустым ArrayList")
    void testPrintEmptyArrayList() {
        ArrayList<Integer> emptyList = new ArrayList<>();
        assertDoesNotThrow(() -> Utility.print(emptyList));
    }
}