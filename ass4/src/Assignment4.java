import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Assignment 4.
 * @link <a href="https://codeforces.com/group/L9uag4pC5g/   /410764/problem/A">...</a>
 */
public final class Assignment4 {

    /**
     * name of the input file.
     */
    private static final String INPUT = "input.txt";
    /**
     * name of teh output file.
     */
    private static final String OUTPUT = "output.txt";
    /**
     * minimum board size.
     */
    private static final int MIN_BOARD_SIZE = 3;
    /**
     * maximum board size.
     */
    private static final int MAX_BOARD_SIZE = 1000;
    /**
     * index of a piece name.
     */
    private static final int NAME_INDEX = 0;
    /**
     * index of a piece color.
     */
    private static final int COLOR_INDEX = 1;
    /**
     * index of X position of piece.
     */
    private static final int POSITION_X_INDEX = 2;
    /**
     * index of Y position of piece.
     */
    private static final int POSITION_Y_INDEX = 3;
    /**
     * digital representation of a chess board.
     * used to store all figures in current game
     */
    private static Board chessBoard;

    /**
     * used to store of input positions in order.
     */
    private static final ArrayList<PiecePosition> ORDEROFINPUT = new ArrayList<>();

    /**
     * used to store number of KINGS in game.
     */
    private static final int[] KINGS = new int[] {0, 0};

    private Assignment4() { }

    /**
     * Program starts execution from this function.
     * @param args arguments
     * @throws IOException might throw IOException
     */
    public static void main(String[] args) throws IOException {
        // reading input from INPUT
        readInput();

        // calculate MOVES and captures.
        ArrayList<String> results = calculate();

        // write results to OUTPUT
        writeToFile(results);
    }

    /**
     * used to calculate total MOVES and captures of all pieces in the chessBoard.
     * @return List of results - "n_of_MOVES n_of_Captures"
     */
    private static ArrayList<String> calculate() {
        ArrayList<String> results = new ArrayList<>();

        for (PiecePosition inputLine: ORDEROFINPUT) {
            ChessPiece piece = chessBoard.getPiece(inputLine);
            int totalMoves = chessBoard.getPiecePossibleMovesCount(piece);
            int totalCaptures = chessBoard.getPiecePossibleCapturesCount(piece);

            results.add(totalMoves + " " + totalCaptures);
        }

        return results;
    }

    /**
     * Reads all input to chessBoard and writes exceptions to output.txt.
     */
    private static void readInput() throws IOException {

        File file = new File(INPUT);
        Scanner scanner = new Scanner(file);
        try {
            int boardSize;
            int numberOfPieces;
            try {
                boardSize = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                throw new InvalidBoardSizeException();
            }

            if (boardSize < MIN_BOARD_SIZE || boardSize > MAX_BOARD_SIZE) {
                throw new InvalidBoardSizeException();
            }
            chessBoard = new Board(boardSize);
            try {
                numberOfPieces = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                throw new InvalidBoardSizeException();
            }
            if (numberOfPieces < 2 || numberOfPieces > boardSize * boardSize) {
                throw new InvalidNumberOfPiecesException();
            }

            while (scanner.hasNext()) {
                String piece = scanner.nextLine();

                if (piece.equals("") || piece.equals(" ")) {
                    break;
                }
                numberOfPieces--;
                if (numberOfPieces < 0) {
                    throw new InvalidNumberOfPiecesException();
                }

                if (Validator.validatePiece(piece, boardSize, KINGS, chessBoard)) {
                    ChessPiece newPiece = createNewPiece(piece);
                    chessBoard.addPiece(newPiece);
                    ORDEROFINPUT.add(newPiece.getPosition());
                }
            }

            if (numberOfPieces != 0) {
                throw new InvalidNumberOfPiecesException();
            }

            if (KINGS[0] + KINGS[1] != 2) {
                throw new InvalidGivenKingsException();
            }

        } catch (Exception e) {
            String exceptionMessage = e.getMessage();
            writeToFileFatal(exceptionMessage);
            scanner.close();
            System.exit(0);
        }

        scanner.close();
    }

    /**
     * writes exceptions to files.
     * @param message exception message
     * @throws IOException might throw IOException
     */
    public static void writeToFileFatal(String message) throws IOException {
        FileWriter outputFile = new FileWriter(OUTPUT);
        outputFile.write(message);
        outputFile.close();
    }

    /**
     * writes to file only results of, not exceptions.
     * @param results String "n_of_MOVES n_of_captures"
     * @throws IOException might throw IOException
     */
    public static void writeToFile(ArrayList<String> results) throws IOException {
        FileWriter outputFile = new FileWriter(OUTPUT);
        for (String res: results) {
            outputFile.write(res + "\n");
        }
        outputFile.close();
    }

    /**
     * creates a new piece.
     * @param piece String "pieceName pieceColor piecePosition.X piecePosition.Y"
     * @return chessPiece : ChessPiece
     */
    private static ChessPiece createNewPiece(String piece) {

        String[] pieces = piece.split(" ");

        if (pieces[NAME_INDEX].equals("Knight")) {
            return new Knight(createPosition(pieces[POSITION_X_INDEX], pieces[POSITION_Y_INDEX]),
                    PieceColor.parse(pieces[COLOR_INDEX]));
        } else if (pieces[NAME_INDEX].equals("King")) {
            return new King(createPosition(pieces[POSITION_X_INDEX], pieces[POSITION_Y_INDEX]),
                    PieceColor.parse(pieces[COLOR_INDEX]));
        } else if (pieces[NAME_INDEX].equals("Pawn")) {
            return new Pawn(createPosition(pieces[POSITION_X_INDEX], pieces[POSITION_Y_INDEX]),
                    PieceColor.parse(pieces[COLOR_INDEX]));
        } else if (pieces[NAME_INDEX].equals("Bishop")) {
            return new Bishop(createPosition(pieces[POSITION_X_INDEX], pieces[POSITION_Y_INDEX]),
                    PieceColor.parse(pieces[COLOR_INDEX]));

        } else if (pieces[NAME_INDEX].equals("Rook")) {
            return new Rook(createPosition(pieces[POSITION_X_INDEX], pieces[POSITION_Y_INDEX]),
                    PieceColor.parse(pieces[COLOR_INDEX]));
        } else {
            return new Queen(createPosition(pieces[POSITION_X_INDEX], pieces[POSITION_Y_INDEX]),
                    PieceColor.parse(pieces[COLOR_INDEX]));
        }
    }

    /**
     * creates position and returns it.
     * @param positionX x coordinate of position
     * @param positionY y coordinate of position
     * @return position : PiecePosition
     */
    private static PiecePosition createPosition(String positionX, String positionY) {
        return new PiecePosition(Integer.parseInt(positionX), Integer.parseInt(positionY));
    }
}

/**
 * used to validate inputs.
 */
final class Validator {
    /**
     * index of a piece name.
     */
    private static final int NAME_INDEX = 0;
    /**
     * index of a piece color.
     */
    private static final int COLOR_INDEX = 1;
    /**
     * index of X position of piece.
     */
    private static final int POSITION_X_INDEX = 2;
    /**
     * index of Y position of piece.
     */
    private static final int POSITION_Y_INDEX = 3;
    private Validator() { }

    /**
     * used to validate chess piece's name.
     * @param pieceNameString : String chess piece's name
     * @return true is name is valid, otherwise false
     */
    public static boolean validateName(String pieceNameString) {
        String[] pieceNames = new String[] {"Pawn", "Bishop", "Knight", "Rook", "Queen", "King"};

        for (String i: pieceNames) {
            if (i.equals(pieceNameString)) {
                return false;
            }
        }

        return true;
    }

    /**
     * validates chess piece's color.
     * @param pieceColorString : String chess piece's color
     * @return true is color is valid, otherwise false
     */
    public static boolean validateColor(String pieceColorString) {
        String[] colors = new String[] {"White", "Black"};
        for (String i: colors) {
            if (i.equals(pieceColorString)) {
                return false;
            }
        }

        return true;
    }

    /**
     * validates position of a chess piece.
     * @param x : String horizontal position of chess piece
     * @param y : String vertical position of chess piece
     * @param boardSize : int size of the current chess board
     * @param chessBoard : Board current chess board
     * @return true if position is valid, otherwise false
     */
    public static boolean validatePosition(String x, String y, int boardSize, Board chessBoard) {

        int intX;
        int intY;

        try {
            intX = Integer.parseInt(x);
            intY = Integer.parseInt(y);
        } catch (Exception e) {
            return false;
        }

        if (chessBoard.contains(x + " " + y)) {
            return false;
        }

        return intX <= boardSize && intX >= 1 && intY <= boardSize && intY >= 1;
    }

    /**
     * validates all properties of current chess piece.
     * @param piece : String "piece_name piece_color piece_x_position piece_y_position"
     * @param boardSize : int size of the current board
     * @param kings : int[] number of kings in current game
     * @param chessBoard : Board current chess board
     * @return true if all properties of current piece are valid, otherwise false
     */
    public static boolean validatePiece(String piece, int boardSize, int[] kings, Board chessBoard) throws
            InvalidPieceNameException, InvalidPieceColorException,
            InvalidPiecePositionException, InvalidGivenKingsException {
        String[] pieces = piece.split(" ");

        if (validateName(pieces[NAME_INDEX])) {
            throw new InvalidPieceNameException();
        }

        if (validateColor(pieces[COLOR_INDEX])) {
            throw new InvalidPieceColorException();
        }

        if (!validatePosition(pieces[POSITION_X_INDEX], pieces[POSITION_Y_INDEX], boardSize, chessBoard)) {
            throw new InvalidPiecePositionException();
        }

        if (pieces[NAME_INDEX].equals("King")) {
            if (pieces[COLOR_INDEX].equals("White")) {
                if (kings[0] == 1) {
                    throw new InvalidGivenKingsException();
                }

                kings[0] = 1;

            } else if (pieces[COLOR_INDEX].equals("Black")) {
                if (kings[1] == 1) {
                    throw new InvalidGivenKingsException();
                }

                kings[1] = 1;

            }
        }

        return true;

    }
}


/**
 * digital representation of chess board.
 */
class Board {

    /**
     * contains positions and chess pieces on them.
     */
    private final Map<String, ChessPiece> positionToPieces = new HashMap<>();

    /**
     * size of the chess board.
     */
    private final int size;

    /**
     * constructor to initialize.
     * @param boardSize : int size of current chess board
     */
    Board(int boardSize) {
        this.size = boardSize;
    }

    /**
     * returns number possible moves of current chess piece.
     * @param piece : ChessPiece current piece
     * @return int number of moves
     */
    public int getPiecePossibleMovesCount(ChessPiece piece) {
        return piece.getMovesCount(positionToPieces, size);
    }

    /**
     * returns number possible captures of current chess piece.
     * @param piece : ChessPiece current piece
     * @return int number of captures
     */
    public int getPiecePossibleCapturesCount(ChessPiece piece) {
        return piece.getCapturesCount(positionToPieces, size);
    }

    /**
     * adds new chess piece to the chess board.
     * @param piece : ChessPiece new chess piece
     */
    public void addPiece(ChessPiece piece) {
        this.positionToPieces.put(piece.getPosition().toString(), piece);
    }

    /**
     * returns chess piece from a board given its position.
     * @param position : PiecePosition position of chess piece
     * @return ChessPiece
     */
    public ChessPiece getPiece(PiecePosition position) {
        return this.positionToPieces.get(position.toString());
    }

    /**
     * checks whether chess board contains any chess piece in given position.
     * @param position : String "x_position y_position"
     * @return true if chess board has any piece in give position, otherwise false
     */
    public boolean contains(String position) {
        return this.positionToPieces.containsKey(position);
    }
}

/**
 * digital representation of Knight.
 */
class Knight extends ChessPiece {

    /**
     * possible steps of a Knight.
     */
    private static final int[][] MOVES = new int[][] {
            {1, 2},
            {2, 1},
            {2, -1},
            {1, -2},
            {-1, -2},
            {-2, -1},
            {-2, 1},
            {-1, 2},
    };

    /**
     * number of possible moves of a Knight.
     */
    private static final int N_OF_POSSIBLE_MOVES = 8;

    /**
     * horizontal direction of Knight moves.
     */
    private static final int X_POSITION = 0;

    /**
     * vertical direction of Knight moves.
     */
    private static final int Y_POSITION = 1;

    /**
     * min board size.
     */
    private static final int MIN_BOARD_SIZE = 1;
    /**
     * Constructor to initialize Knight.
     * @param piecePosition : PiecePotion position of Knight
     * @param pieceColor : PieceColor color of Knight
     */
    Knight(PiecePosition piecePosition, PieceColor pieceColor) {
        super(piecePosition, pieceColor);
    }

    /**
     * calculates number of moves of Knight.
     * @param positions : Map<String, ChessPiece> all positions of chess pieces in current game
     * @param boardSize : int size of the board
     * @return int number of moves of the current Knight
     */
    @Override
    public int getMovesCount(Map<String, ChessPiece> positions, int boardSize) {

        for (int i = 0; i < N_OF_POSSIBLE_MOVES; i++) {
            int moveX = this.getPosition().getX() + MOVES[i][X_POSITION];
            int moveY = this.getPosition().getY() + MOVES[i][Y_POSITION];
            if (moveX >= MIN_BOARD_SIZE && moveX <= boardSize && moveY >= MIN_BOARD_SIZE && moveY <= boardSize) {
                if (positions.containsKey(moveX + " " + moveY)) {
                    if (positions.get(moveX + " " + moveY).color != this.color) {
                        this.totalCaptures++;
                    }
                } else {
                    this.totalMoves++;
                }
            }
        }

        return this.totalMoves + this.totalCaptures;
    }

    /**
     * returns number of captures of a Knight.
     * @param positions : Map<String, ChessPiece> all positions of chess pieces in current game
     * @param boardSize : int size of the board
     * @return int number of captures of the current Knight
     */
    @Override
    public int getCapturesCount(Map<String, ChessPiece> positions, int boardSize) {
        return totalCaptures;
    }
}

class King extends ChessPiece {

    /**
     * possible steps of a King.
     */
    private static final int[][] MOVES = new int[][] {
            {0, 1},
            {1, 1},
            {1, 0},
            {1, -1},
            {0, -1},
            {-1, -1},
            {-1, 0},
            {-1, 1},
    };

    /**
     * number of possible moves of a King.
     */
    private static final int N_OF_POSSIBLE_MOVES = 8;
    /**
     * horizontal direction of King moves.
     */
    private static final int X_POSITION = 0;

    /**
     * vertical direction of King moves.
     */
    private static final int Y_POSITION = 1;

    /**
     * constructor to initialize a King.
     * @param piecePosition : PiecePosition position of a King
     * @param pieceColor : PieceColor color of the King
     */
    King(PiecePosition piecePosition, PieceColor pieceColor) {
        super(piecePosition, pieceColor);
    }

    /**
     * calculates number of moves of King.
     * @param positions : Map<String, ChessPiece> all positions of chess pieces in current game
     * @param boardSize : int size of the board
     * @return int number of moves of the current King
     */
    @Override
    public int getMovesCount(Map<String, ChessPiece> positions, int boardSize) {

        for (int i = 0; i < N_OF_POSSIBLE_MOVES; i++) {
            int moveX = this.position.getX() + MOVES[i][X_POSITION];
            int moveY = this.position.getY() + MOVES[i][Y_POSITION];
            if (moveX > 0 && moveX <= boardSize && moveY > 0 && moveY <= boardSize) {
                if (positions.containsKey(moveX + " " + moveY)) {
                    if (positions.get(moveX + " " + moveY).color != this.color) {
                        this.totalCaptures++;
                    }
                } else {
                    this.totalMoves++;
                }
            }
        }

        return this.totalMoves + this.totalCaptures;
    }

    /**
     * calculates number of captures of King.
     * @param positions : Map<String, ChessPiece> all positions of chess pieces in current game
     * @param boardSize : int size of the board
     * @return int number of captures of the current King
     */
    @Override
    public int getCapturesCount(Map<String, ChessPiece> positions, int boardSize) {
        return this.totalCaptures;
    }
}

class Pawn extends ChessPiece {
    /**
     * horizontal direction of Pawn moves.
     */
    private static final int X_POSITION = 0;

    /**
     * vertical direction of Pawn moves.
     */
    private static final int Y_POSITION = 1;

    /**
     * min board size.
     */
    private static final int MIN_BOARD_SIZE = 1;

    /**
     * constructor to initialize a Pawn.
     * @param piecePosition : PiecePosition position of a Pawn
     * @param pieceColor : PieceColor color of the Pawn
     */
    Pawn(PiecePosition piecePosition, PieceColor pieceColor) {
        super(piecePosition, pieceColor);
    }

    /**
     * number of moves of Pawn.
     * @param positions : Map<String, ChessPiece> all positions of chess pieces in current game
     * @param boardSize : int size of the board
     * @return int number of moves of the current Pawn
     */
    @Override
    public int getMovesCount(Map<String, ChessPiece> positions, int boardSize) {

        int moveX = this.position.getX();
        int moveY = this.position.getY() + (this.color == PieceColor.WHITE ? 1 : -1);

        if (moveX > 0 && moveX <= boardSize && moveY > 0 && moveY <= boardSize) {
            if (!positions.containsKey(moveX + " " + moveY)) {
                this.totalMoves++;
            }
        }



        int[][] moves = new int[][] {
                {1, (this.color == PieceColor.WHITE ? 1 : -1)},
                {-1, (this.color == PieceColor.WHITE ? 1 : -1)},
        };
        for (int i = 0; i < 2; i++) {
            moveX = this.getPosition().getX() + moves[i][X_POSITION];
            moveY = this.getPosition().getY() + moves[i][Y_POSITION];
            if (moveX >= MIN_BOARD_SIZE && moveX <= boardSize && moveY > MIN_BOARD_SIZE && moveY <= boardSize) {
                if (positions.containsKey(moveX + " " + moveY)
                        && positions.get(moveX + " " + moveY).color != this.color) {
                    this.totalCaptures++;
                }
            }
        }
        return this.totalMoves + this.totalCaptures;
    }

    /**
     * number of captures of Pawn.
     * @param positions : Map<String, ChessPiece> all positions of chess pieces in current game
     * @param boardSize : int size of the board
     * @return int number of captures of the current Pawn
     */
    @Override
    public int getCapturesCount(Map<String, ChessPiece> positions, int boardSize) {
        return this.totalCaptures;
    }
}

/**
 * Digital representation of Bishop.
 */
class Bishop extends ChessPiece implements BishopMovement {

    /**
     * number of possible moves of a Bishop.
     */
    private static final int N_OF_DIRECTIONS = 4;

    /**
     * constructor to initialize a Bishop.
     * @param piecePosition : PiecePosition position of a Bishop
     * @param pieceColor : PieceColor color of the Bishop
     */
    Bishop(PiecePosition piecePosition, PieceColor pieceColor) {
        super(piecePosition, pieceColor);
    }

    /**
     * calculates number of moves of Bishop.
     * @param positions : Map<String, ChessPiece> all positions of chess pieces in current game
     * @param boardSize : int size of the board
     * @return int number of moves of the current Bishop
     */
    @Override
    public int getMovesCount(Map<String, ChessPiece> positions, int boardSize) {
        return getDiagonalMovesCount(this.position, this.color, positions, boardSize);
    }

    /**
     * calculates number of captures of Bishop.
     * @param positions : Map<String, ChessPiece> all positions of chess pieces in current game
     * @param boardSize : int size of the board
     * @return int number of captures of the current Bishop
     */
    @Override
    public int getCapturesCount(Map<String, ChessPiece> positions, int boardSize) {
        return getDiagonalCapturesCount(this.position, this.color, positions, boardSize);
    }

    /**
     * calculates number of diagonal moves of Bishop.
     * @param positions : Map<String, ChessPiece> all positions of chess pieces in current game
     * @param boardSize : int size of the board
     * @return int number of diagonal moves of the current Bishop
     */
    @Override
    public int getDiagonalMovesCount(PiecePosition position, PieceColor color,
                                     Map<String, ChessPiece> positions, int boardSize) {

        int[][] directions = new int[][] {
                {1, 1},
                {1, -1},
                {-1, -1},
                {-1, 1}
        };

        for (int i = 0; i < N_OF_DIRECTIONS; i++) {
            int moveX = this.position.getX() + directions[i][0];
            int moveY = this.position.getY() + directions[i][1];

            while (moveY <= boardSize && moveY > 0 && moveX > 0 && moveX <= boardSize) {

                if (positions.containsKey(moveX + " " + moveY)) {
                    if (positions.get(moveX + " " + moveY).color != this.color) {
                        this.totalCaptures++;
                    }
                    break;
                } else {
                    this.totalMoves++;
                }
                moveX += directions[i][0];
                moveY += directions[i][1];
            }
        }
        return this.totalCaptures + this.totalMoves;
    }

    /**
     * calculates number of diagonal captures of Bishop.
     * @param positions : Map<String, ChessPiece> all positions of chess pieces in current game
     * @param boardSize : int size of the board
     * @return int number of diagonal captures of the current Bishop
     */
    @Override
    public int getDiagonalCapturesCount(PiecePosition position, PieceColor color,
                                        Map<String, ChessPiece> positions, int boardSize) {
        return this.totalCaptures;
    }
}

/**
 * digital representation of Rook.
 */
class Rook extends ChessPiece implements RookMovement {

    /**
     * number of directions Rook can move.
     */
    private static final int N_OF_DIRECTIONS = 4;

    /**
     * constructor to initialize a Rook.
     * @param piecePosition : PiecePosition position of a Rook
     * @param pieceColor : PieceColor color of the Rook
     */
    Rook(PiecePosition piecePosition, PieceColor pieceColor) {
        super(piecePosition, pieceColor);
    }

    /**
     * calculates number of moves of Rook.
     * @param positions : Map<String, ChessPiece> all positions of chess pieces in current game
     * @param boardSize : int size of the board
     * @return int number of moves of the current Rook
     */
    @Override
    public int getMovesCount(Map<String, ChessPiece> positions, int boardSize) {
        return getOrthogonalMovesCount(this.position, this.color, positions, boardSize);
    }

    /**
     * calculates number of captures of Rook.
     * @param positions : Map<String, ChessPiece> all positions of chess pieces in current game
     * @param boardSize : int size of the board
     * @return int number of captures of the current Rook
     */
    @Override
    public int getCapturesCount(Map<String, ChessPiece> positions, int boardSize) {
        return getOrthogonalCapturesCount(this.position, this.color, positions, boardSize);
    }

    /**
     * calculates number of orthogonal captures of Rook.
     * @param positions : Map<String, ChessPiece> all positions of chess pieces in current game
     * @param boardSize : int size of the board
     * @return int number of orthogonal captures of the current Rook
     */
    @Override
    public int getOrthogonalCapturesCount(PiecePosition position, PieceColor color,
                                          Map<String, ChessPiece> positions, int boardSize) {
        return this.totalCaptures;
    }

    /**
     * calculates number of orthogonal moves of Rook.
     * @param positions : Map<String, ChessPiece> all positions of chess pieces in current game
     * @param boardSize : int size of the board
     * @return int number of orthogonal moves of the current Rook
     */
    @Override
    public int getOrthogonalMovesCount(PiecePosition position, PieceColor color,
                                       Map<String, ChessPiece> positions, int boardSize) {
        int[][] directions = new int[][] {
                {0, 1},
                {1, 0},
                {0, -1},
                {-1, 0}
        };

        for (int i = 0; i < N_OF_DIRECTIONS; i++) {
            int moveX = this.position.getX() + directions[i][0];
            int moveY = this.position.getY() + directions[i][1];
            while (moveY <= boardSize && moveY > 0 && moveX <= boardSize && moveX > 0) {

                if (positions.containsKey(moveX + " " + moveY)) {
                    if (positions.get(moveX + " " + moveY).color != this.color) {
                        this.totalCaptures++;
                    }
                    break;
                } else {
                    this.totalMoves++;
                }
                moveX += directions[i][0];
                moveY += directions[i][1];
            }
        }
        return this.totalCaptures + this.totalMoves;
    }
}

/**
 * digital representation of Queen.
 */
class Queen extends ChessPiece implements BishopMovement, RookMovement {

    /**
     * number of orthogonal moves.
     */
    private int orthogonalMovementCount = 0;

    /**
     * number of orthogonal captures.
     */
    private int orthogonalCaptureCount = 0;
    /**
     * half number of possible directions of a Queen.
     */
    private static final int N_OF_DIRECTIONS = 4;

    /**
     * constructor to initialize a Queen.
     * @param piecePosition : PiecePosition position of a Queen
     * @param pieceColor : PieceColor color of the Queen
     */
    Queen(PiecePosition piecePosition, PieceColor pieceColor) {
        super(piecePosition, pieceColor);
    }

    /**
     * calculates number of moves of Queen.
     * @param positions : Map<String, ChessPiece> all positions of chess pieces in current game
     * @param boardSize : int size of the board
     * @return int number of moves of the current Queen
     */
    @Override
    public int getMovesCount(Map<String, ChessPiece> positions, int boardSize) {
        return getDiagonalMovesCount(this.position, this.color, positions, boardSize)
                + getOrthogonalMovesCount(this.position, this.color, positions, boardSize);
    }

    /**
     * calculates number of captures of Queen.
     * @param positions : Map<String, ChessPiece> all positions of chess pieces in current game
     * @param boardSize : int size of the board
     * @return int number of captures of the current Queen
     */
    @Override
    public int getCapturesCount(Map<String, ChessPiece> positions, int boardSize) {
        return getDiagonalCapturesCount(this.position, this.color, positions, boardSize)
                + getOrthogonalCapturesCount(this.position, this.color, positions, boardSize);
    }

    /**
     * calculates number of diagonal moves of Queen.
     * @param positions : Map<String, ChessPiece> all positions of chess pieces in current game
     * @param boardSize : int size of the board
     * @return int number of diagonal moves of the current Queen
     */
    @Override
    public int getDiagonalMovesCount(PiecePosition position, PieceColor color,
                                     Map<String, ChessPiece> positions, int boardSize) {

        int[][] directions = new int[][] {
                {1, 1},
                {1, -1},
                {-1, -1},
                {-1, 1}
        };

        for (int i = 0; i < N_OF_DIRECTIONS; i++) {
            int moveX = this.position.getX() + directions[i][0];
            int moveY = this.position.getY() + directions[i][1];
            while (moveX <= boardSize && moveX > 0 && moveY > 0 && moveY <= boardSize) {

                if (positions.containsKey(moveX + " " + moveY)) {
                    if (positions.get(moveX + " " + moveY).color != this.color) {
                        this.totalCaptures++;
                    }
                    break;
                } else {
                    this.totalMoves++;
                }
                moveX += directions[i][0];
                moveY += directions[i][1];
            }
        }
        return this.totalCaptures + this.totalMoves;
    }

    /**
     * calculates number of diagonal captures of Queen.
     * @param positions : Map<String, ChessPiece> all positions of chess pieces in current game
     * @param boardSize : int size of the board
     * @return int number of diagonal captures of the current Queen
     */
    @Override
    public int getDiagonalCapturesCount(PiecePosition position, PieceColor color,
                                        Map<String, ChessPiece> positions, int boardSize) {
        return this.totalCaptures;
    }

    /**
     * calculates number of orthogonal captures of Queen.
     * @param positions : Map<String, ChessPiece> all positions of chess pieces in current game
     * @param boardSize : int size of the board
     * @return int number of orthogonal captures of the current Queen
     */
    @Override
    public int getOrthogonalCapturesCount(PiecePosition position, PieceColor color,
                                          Map<String, ChessPiece> positions, int boardSize) {
        return this.orthogonalCaptureCount;
    }

    /**
     * calculates number of orthogonal moves of Queen.
     * @param positions : Map<String, ChessPiece> all positions of chess pieces in current game
     * @param boardSize : int size of the board
     * @return int number of orthogonal moves of the current Queen
     */
    @Override
    public int getOrthogonalMovesCount(PiecePosition position, PieceColor color,
                                       Map<String, ChessPiece> positions, int boardSize) {
        int[][] directions = new int[][] {
                {0, 1},
                {1, 0},
                {0, -1},
                {-1, 0}
        };

        for (int i = 0; i < N_OF_DIRECTIONS; i++) {
            int moveX = this.position.getX() + directions[i][0];
            int moveY = this.position.getY() + directions[i][1];
            while (moveX <= boardSize && moveX > 0 && moveY > 0 && moveY <= boardSize) {

                if (positions.containsKey(moveX + " " + moveY)) {
                    if (positions.get(moveX + " " + moveY).color != this.color) {
                        this.orthogonalCaptureCount++;
                    }
                    break;
                } else {
                    this.orthogonalMovementCount++;
                }
                moveX += directions[i][0];
                moveY += directions[i][1];
            }
        }
        return this.orthogonalMovementCount + this.orthogonalCaptureCount;
    }


}

abstract class ChessPiece {
    /**
     * number of total captures of a piece.
     */
    protected int totalCaptures = 0;

    /**
     *  number of total moves of a piece.
     */
    protected int totalMoves = 0;

    /**
     * position of a piece.
     */
    protected PiecePosition position;

    /**
     * color of a piece.
     */
    protected PieceColor color;
    ChessPiece(PiecePosition piecePosition, PieceColor pieceColor) {
        this.position = piecePosition;
        this.color = pieceColor;
    }
    public PiecePosition getPosition() {
        return position;
    }

    public PieceColor getColor() {
        return this.color;
    }

    public abstract int getMovesCount(Map<String, ChessPiece> positions, int boardSize);

    public abstract int getCapturesCount(Map<String, ChessPiece> positions, int boardSize);

}

/**
 *
 */
class PiecePosition {
    /**
     * horizontal position of a piece.
     */
    private final int x;

    /**
     * vertical position of a piece.
     */
    private final int y;

    PiecePosition(int onX, int onY) {

        this.x = onX;
        this.y = onY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return this.x + " " + this.y;
    }

}

/**
 * Used to store color of the piece.
 */
enum PieceColor {
    /**
     * White color.
     */
    WHITE,
    /**
     * black color.
     */
    BLACK;

    /**
     * parse given string to PieceColor.
     * @param enumString color of the piece in String
     * @return PieceColor color of the piece
     */
    public static PieceColor parse(String enumString) {
        if (enumString.compareTo("White") == 0) {
            return PieceColor.WHITE;
        } else {
            return PieceColor.BLACK;
        }
    }
}

/**
 * used to describe bishop movement.
 */
interface BishopMovement {
    int getDiagonalMovesCount(PiecePosition position, PieceColor color,
                              Map<String, ChessPiece> positions, int boardSize);
    int getDiagonalCapturesCount(PiecePosition position, PieceColor color,
                                 Map<String, ChessPiece> positions, int boardSize);
}

/**
 * used to describe rook movement.
 */
interface RookMovement {
    int getOrthogonalMovesCount(PiecePosition position, PieceColor color,
                                Map<String, ChessPiece> positions, int boardSize);
    int getOrthogonalCapturesCount(PiecePosition position, PieceColor color,
                                   Map<String, ChessPiece> positions, int boardSize);
}

/**
 * InvalidBoardSizeException thrown when invalid board size.
 */
class InvalidBoardSizeException extends Exception {
    InvalidBoardSizeException() {
        super("Invalid board size");
    }
}

/**
 * InvalidNumberOfPiecesException thrown when invalid number of chess pieces.
 */
class InvalidNumberOfPiecesException extends Exception {
    InvalidNumberOfPiecesException() {
        super("Invalid number of pieces");
    }
}

/**
 * InvalidPieceNameException thrown when invalid name of the chess piece.
 */
class InvalidPieceNameException extends Exception {
    InvalidPieceNameException() {
        super("Invalid piece name");
    }
}

/**
 * InvalidPieceColorException thrown when invalid color of the chess piece.
 */
class InvalidPieceColorException extends Exception {
    InvalidPieceColorException() {
        super("Invalid piece color");
    }
}

/**
 * InvalidPiecePositionException thrown when invalid position of the chess piece.
 */
class InvalidPiecePositionException extends Exception {
    InvalidPiecePositionException() {
        super("Invalid piece position");
    }
}

/**
 * InvalidGivenKingsException thrown when invalid number of KINGS in the game.
 */
class InvalidGivenKingsException extends Exception {
    InvalidGivenKingsException() {
        super("Invalid given Kings");
    }
}

/**
 * InvalidInputException thrown for other invalid cases.
 */
class InvalidInputException extends Exception {
    InvalidInputException() {
        super("Invalid input");
    }
}
