package com.mycompany.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeCellTest {

    @Test
    @DisplayName("Тест создания ячейки")
    void testCellCreation() {
        TicTacToeCell cell = new TicTacToeCell(0, 0, 0);
        assertNotNull(cell);
        assertEquals(0, cell.getNum());
        assertEquals(0, cell.getRow());
        assertEquals(0, cell.getCol());
        assertEquals(' ', cell.getMarker());
    }

    @Test
    @DisplayName("Тест создания ячейки с разными координатами")
    void testCellWithDifferentCoordinates() {
        TicTacToeCell cell = new TicTacToeCell(4, 1, 1);
        assertEquals(4, cell.getNum());
        assertEquals(1, cell.getRow());
        assertEquals(1, cell.getCol());
    }

    @Test
    @DisplayName("Тест установки маркера X")
    void testSetMarkerX() {
        TicTacToeCell cell = new TicTacToeCell(0, 0, 0);
        cell.setMarker("X");
        assertEquals('X', cell.getMarker());
        assertFalse(cell.isEnabled());
    }

    @Test
    @DisplayName("Тест установки маркера O")
    void testSetMarkerO() {
        TicTacToeCell cell = new TicTacToeCell(0, 0, 0);
        cell.setMarker("O");
        assertEquals('O', cell.getMarker());
        assertFalse(cell.isEnabled());
    }

    @Test
    @DisplayName("Тест всех ячеек доски")
    void testAllBoardCells() {
        TicTacToeCell[] cells = new TicTacToeCell[9];
        for (int i = 0; i < 9; i++) {
            cells[i] = new TicTacToeCell(i, i % 3, i / 3);
            assertNotNull(cells[i]);
        }

        for (int i = 0; i < 9; i++) {
            assertEquals(i, cells[i].getNum());
            assertEquals(i % 3, cells[i].getCol());
            assertEquals(i / 3, cells[i].getRow());
        }
    }

    @Test
    @DisplayName("Тест текста кнопки после установки маркера")
    void testButtonText() {
        TicTacToeCell cell = new TicTacToeCell(0, 0, 0);
        assertEquals(" ", cell.getText());

        cell.setMarker("X");
        assertEquals("X", cell.getText());

        cell.setMarker("O");
        assertEquals("O", cell.getText());
    }
}
