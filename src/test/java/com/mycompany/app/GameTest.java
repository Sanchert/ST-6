package com.mycompany.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private Game game;
    private Player player1;
    private Player player2;

    @BeforeEach
    void setUp() {
        game = new Game();
        player1 = game.player1;
        player2 = game.player2;
    }

    @Test
    @DisplayName("Тест инициализации игры")
    void testGameInitialization() {
        assertNotNull(game);
        assertNotNull(game.board);
        assertEquals(9, game.board.length);
        assertEquals(State.PLAYING, game.state);
        assertEquals('X', player1.symbol);
        assertEquals('O', player2.symbol);
        for (int i = 0; i < 9; i++) {
            assertEquals(' ', game.board[i]);
        }
    }

    @Test
    @DisplayName("Тест проверки победы X по горизонтали")
    void testCheckStateXWinHorizontal() {
        char[] board = new char[9];
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }
        board[0] = 'X'; board[1] = 'X'; board[2] = 'X';
        game.symbol = 'X';

        State result = game.checkState(board);
        assertEquals(State.XWIN, result);
    }

    @Test
    @DisplayName("Тест проверки победы O по вертикали")
    void testCheckStateOWinVertical() {
        char[] board = new char[9];
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }
        board[0] = 'O'; board[3] = 'O'; board[6] = 'O';
        game.symbol = 'O';

        State result = game.checkState(board);
        assertEquals(State.OWIN, result);
    }

    @Test
    @DisplayName("Тест проверки победы X по диагонали")
    void testCheckStateXWinDiagonal() {
        char[] board = new char[9];
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }
        board[0] = 'X'; board[4] = 'X'; board[8] = 'X';
        game.symbol = 'X';

        State result = game.checkState(board);
        assertEquals(State.XWIN, result);
    }

    @Test
    @DisplayName("Тест проверки победы O по обратной диагонали")
    void testCheckStateOWinReverseDiagonal() {
        char[] board = new char[9];
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }
        board[2] = 'O'; board[4] = 'O'; board[6] = 'O';
        game.symbol = 'O';

        State result = game.checkState(board);
        assertEquals(State.OWIN, result);
    }

    @Test
    @DisplayName("Тест ничьей")
    void testCheckStateDraw() {
        char[] board = {
                'X', 'O', 'X',
                'X', 'O', 'O',
                'O', 'X', 'X'
        };
        game.symbol = 'X';

        State result = game.checkState(board);
        assertEquals(State.DRAW, result);
    }

    @Test
    @DisplayName("Тест игры в процессе")
    void testCheckStatePlaying() {
        char[] board = new char[9];
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }
        board[0] = 'X';
        board[1] = 'O';
        game.symbol = 'X';

        State result = game.checkState(board);
        assertEquals(State.PLAYING, result);
    }

    @Test
    @DisplayName("Тест checkState с пустой доской")
    void testCheckStateEmptyBoard() {
        char[] board = new char[9];
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }
        game.symbol = 'X';

        State result = game.checkState(board);
        assertEquals(State.PLAYING, result);
    }

    @Test
    @DisplayName("Тест checkState победа X по всем линиям")
    void testCheckStateXWinAllLines() {
        game.symbol = 'X';

        char[] boardRow0 = {'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
        assertEquals(State.XWIN, game.checkState(boardRow0));

        char[] boardRow1 = {' ', ' ', ' ', 'X', 'X', 'X', ' ', ' ', ' '};
        assertEquals(State.XWIN, game.checkState(boardRow1));

        char[] boardRow2 = {' ', ' ', ' ', ' ', ' ', ' ', 'X', 'X', 'X'};
        assertEquals(State.XWIN, game.checkState(boardRow2));

        char[] boardCol0 = {'X', ' ', ' ', 'X', ' ', ' ', 'X', ' ', ' '};
        assertEquals(State.XWIN, game.checkState(boardCol0));
    }

    @Test
    @DisplayName("Тест генерации возможных ходов")
    void testGenerateMoves() {
        char[] board = new char[9];
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }
        board[0] = 'X';
        board[4] = 'O';
        board[8] = 'X';

        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(board, moves);

        assertEquals(6, moves.size());
        assertTrue(moves.contains(1));
        assertTrue(moves.contains(2));
        assertTrue(moves.contains(3));
        assertTrue(moves.contains(5));
        assertTrue(moves.contains(6));
        assertTrue(moves.contains(7));
    }

    @Test
    @DisplayName("Тест генерации ходов на пустой доске")
    void testGenerateMovesEmptyBoard() {
        char[] board = new char[9];
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }

        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(board, moves);

        assertEquals(9, moves.size());
    }

    @Test
    @DisplayName("Тест генерации ходов на заполненной доске")
    void testGenerateMovesFullBoard() {
        char[] board = {
                'X', 'O', 'X',
                'X', 'O', 'O',
                'O', 'X', 'X'
        };

        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(board, moves);

        assertEquals(0, moves.size());
    }

    @Test
    @DisplayName("Тест оценки позиции - выигрыш X")
    void testEvaluatePositionXWin() {
        char[] board = new char[9];
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }
        board[0] = 'X'; board[1] = 'X'; board[2] = 'X';

        game.board = board;
        game.symbol = 'X';

        int result = game.evaluatePosition(board, game.player1);
        assertEquals(Game.INF, result);
    }

    @Test
    @DisplayName("Тест оценки позиции - выигрыш O")
    void testEvaluatePositionOWin() {
        char[] board = new char[9];
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }
        board[0] = 'O'; board[1] = 'O'; board[2] = 'O';

        game.board = board;
        game.symbol = 'O';

        int result = game.evaluatePosition(board, game.player2);
        assertEquals(Game.INF, result);
    }

    @Test
    @DisplayName("Тест оценки позиции - проигрыш X")
    void testEvaluatePositionXLoss() {
        char[] board = new char[9];
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }
        board[0] = 'O'; board[1] = 'O'; board[2] = 'O';

        game.board = board;
        game.symbol = 'O';

        int result = game.evaluatePosition(board, game.player1);
        assertEquals(-Game.INF, result);
    }

    @Test
    @DisplayName("Тест оценки позиции - ничья")
    void testEvaluatePositionDraw() {
        char[] board = {
                'X', 'O', 'X',
                'X', 'O', 'O',
                'O', 'X', 'X'
        };

        int result = game.evaluatePosition(board, game.player1);
        assertEquals(0, result);
    }

    @Test
    @DisplayName("Тест MaxMove на начальной позиции")
    void testMaxMoveStartingPosition() {
        char[] board = new char[9];
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }

        int result = game.MaxMove(board, game.player1);
        assertTrue(result >= -Game.INF && result <= Game.INF);
    }

    @Test
    @DisplayName("Тест MinMove на начальной позиции")
    void testMinMoveStartingPosition() {
        char[] board = new char[9];
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }

        int result = game.MinMove(board, game.player1);
        assertTrue(result >= -Game.INF && result <= Game.INF);
    }

    @Test
    @DisplayName("Тест выбора лучшего хода")
    void testMiniMax() {
        game.board = new char[9];
        for (int i = 0; i < 9; i++) {
            game.board[i] = ' ';
        }

        int bestMove = game.MiniMax(game.board, game.player2);
        assertTrue(bestMove >= 1 && bestMove <= 9);
    }

    @Test
    @DisplayName("Тест выбора лучшего хода при почти завершенной игре")
    void testMiniMaxNearEndGame() {
        char[] board = {
                'X', 'X', ' ',
                'O', 'O', ' ',
                ' ', ' ', ' '
        };
        game.board = board.clone();
        game.player2.symbol = 'O';

        int bestMove = game.MiniMax(game.board, game.player2);
        assertTrue(bestMove == 3 || bestMove == 6 || bestMove == 9);
    }

    @Test
    @DisplayName("Тест INF константы")
    void testInfConstant() {
        assertEquals(100, Game.INF);
    }

    @Test
    @DisplayName("Тест q счетчика в minimax")
    void testMinimaxCounter() {
        game.q = 0;
        char[] board = new char[9];
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }
        game.board = board;

        game.MiniMax(game.board, game.player2);
        assertTrue(game.q >= 0);
    }
}
