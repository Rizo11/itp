import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.File;

class ass4 {

    private static Board chessBoard;
    private static final String INPUT = "input.txt";
    private static final String OUTPUT = "output.txt";

    private static int size = 0;
    private static ArrayList<PiecePosition> orderOfInput = new ArrayList<PiecePosition>();

    // white black
    public static int[] kings = new int[] {0, 0};
    private static FileWriter outputFile = null;

    public static void main(String[] args) throws IOException {
        readInput();
        ArrayList<String> results = calculate();
        writeToFile(results);
    }

    private static ArrayList<String> calculate() {
        ArrayList<String > results = new ArrayList<String>();
        for (PiecePosition inputLine: orderOfInput ) {
            int totalMoves = 0;
            int totalCaptures = 0;

            ChessPiece piece = chessBoard.getPiece(inputLine);
            totalMoves = chessBoard.getPiecePossibleMovesCount(piece);
            totalCaptures = chessBoard.getPiecePossibleCapturesCount(piece);

            results.add(totalMoves + " " + totalCaptures);
        }

        return results;
    }

    /**
     * Reads all input to chessBoard and writes exceptions to output.txt
     */
    private static void readInput() throws IOException {

        File file = new File(INPUT);
        Scanner scanner = new Scanner(file);
        try {
            int N = 0;
            int M = 0, actualM = 0;
            try {
                N = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                throw new InvalidBoardSizeException();
            }

            if(N < 3 || N > 1000) {
                throw new InvalidBoardSizeException();
            }
            chessBoard = new Board(N);
            size = N;
            try {
                M = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                throw new InvalidBoardSizeException();
            }
            if(M < 2 || M > N*N) {
                throw new InvalidNumberOfPiecesException();
            }

            while(scanner.hasNext()) {
                String piece = scanner.nextLine();

                if (piece.equals("") || piece.equals(" ")) {
                    break;
                }
                M--;
                if(M < 0) {
                    throw new InvalidNumberOfPiecesException();
                }

                if(Validator.validatePiece(piece, N, kings, chessBoard)) {
                    ChessPiece newPiece = createNewPiece(piece);
                    chessBoard.addPiece(newPiece);
                    orderOfInput.add(newPiece.getPosition());
                }
            }

            if (M != 0) {
                throw new InvalidNumberOfPiecesException();
            }

            if (kings[0] + kings[1] != 2) {
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

    public static void writeToFileFatal(String message) throws IOException {
        outputFile = new FileWriter(OUTPUT);
        outputFile.write(message);
        outputFile.close();
    }

    public static void writeToFile(ArrayList<String> results) throws IOException {
        outputFile = new FileWriter(OUTPUT);
        for (String res: results) {
            outputFile.write(res + "\n");
        }
        outputFile.close();
    }

    private static ChessPiece createNewPiece(String piece) {

        String[] pieces = piece.split(" ");

        if (pieces[0].equals("Knight")) {
            return new Knight(createPosition(pieces[2], pieces[3]), PieceColor.parse(pieces[1]));
        } else if (pieces[0].equals("King")) {
            return new King(createPosition(pieces[2], pieces[3]), PieceColor.parse(pieces[1]));
        } else if (pieces[0].equals("Pawn")) {
            return new Pawn(createPosition(pieces[2], pieces[3]), PieceColor.parse(pieces[1]));
        } else if (pieces[0].equals("Bishop")) {
            return new Bishop(createPosition(pieces[2], pieces[3]), PieceColor.parse(pieces[1]));

        } else if (pieces[0].equals("Rook")) {
            return new Rook(createPosition(pieces[2], pieces[3]), PieceColor.parse(pieces[1]));
        } else {
            return new Queen(createPosition(pieces[2], pieces[3]), PieceColor.parse(pieces[1]));
        }
    }

    private static PiecePosition createPosition(String positionX, String positionY) {
        return new PiecePosition(Integer.parseInt(positionX), Integer.parseInt(positionY));
    }
}

class Validator {
    public static String[] pieceNames = new String[] {"Pawn", "Bishop", "Knight", "Rook", "Queen", "King"};

    public static boolean validateName(String pieceNameString) {
        for (String i: pieceNames) {
            if (i.equals(pieceNameString)) {
                return false;
            }
        }

        return true;
    }

    public static boolean validateColor(String pieceColorString) {
        String[] colors = new String[] {"White", "Black"};
        for (String i: colors) {
            if (i.equals(pieceColorString)) {
                return false;
            }
        }

        return true;
    }

    public static boolean validatePosition(String x, String y, int N, Board chessBoard) {

        int intX = 0;
        int intY = 0;

        try {
            intX = Integer.parseInt(x);
            intY = Integer.parseInt(y);
        } catch (Exception e) {
            return false;
        }

        if(chessBoard.contains(x + " " + y)) {
            return false;
        }

        return intX <= N && intX >= 1 && intY <= N && intY >= 1;
    }

    public static boolean validatePiece(String piece, int boardSize, int[] kings, Board chessBoard) throws InvalidPieceNameException, InvalidPieceColorException, InvalidPiecePositionException, InvalidGivenKingsException {
        String[] pieces = piece.split(" ");

        if (validateName(pieces[0])) {
            throw new InvalidPieceNameException();
        }

        if (validateColor(pieces[1])) {
            throw new InvalidPieceColorException();
        }

        if (!validatePosition(pieces[2], pieces[3], boardSize, chessBoard)) {
            throw new InvalidPiecePositionException();
        }

        if (pieces[0].equals("King")) {
            if (pieces[1].equals("White")) {
                if (kings[0] == 1) {
                    throw new InvalidGivenKingsException();
                }

                kings[0] = 1;

            } else if (pieces[1].equals("Black")) {
                if (kings[1] == 1) {
                    throw new InvalidGivenKingsException();
                }

                kings[1] = 1;

            }
        }

        return true;

    }
}

class Board {
    private Map<String, ChessPiece> positionToPieces = new HashMap<String, ChessPiece>();
    private final int size;

    public Board(int boardSize) {
        this.size = boardSize;
    }

    // TO-DO write logic
    public int getPiecePossibleMovesCount(ChessPiece piece) {
        return piece.getMovesCount(positionToPieces, size);
    }

    // TO-DO write logic
    public int getPiecePossibleCapturesCount(ChessPiece piece) {
        return piece.getCapturesCount(positionToPieces, size);
    }

    // TO-DO write logic
    public void addPiece(ChessPiece piece) {
        positionToPieces.put(piece.getPosition().toString(), piece);
    }

    // TO-DO write logic
    public ChessPiece getPiece(PiecePosition position) {
        return positionToPieces.get(position.toString());
    }

    public boolean contains(String position) {
        return positionToPieces.containsKey(position);
    }
}

class Knight extends ChessPiece {

    public Knight(PiecePosition piecePosition, PieceColor pieceColor) {
        super(piecePosition, pieceColor);
    }

    /**
     * @param positions
     * @param boardSize
     * @return
     */
    @Override
    public int getMovesCount(Map<String, ChessPiece> positions, int boardSize) {

        int[][] moves = new int[][] {
                {1, 2},
                {2, 1},
                {2, -1},
                {1, -2},
                {-1, -2},
                {-2, -1},
                {-2, 1},
                {-1, 2},
        };

        for (int i = 0; i < 8; i++) {
            int moveX = this.getPosition().getX() + moves[i][0];
            int moveY = this.getPosition().getY() + moves[i][1];
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
     * @param positions
     * @param boardSize
     * @return
     */
    @Override
    public int getCapturesCount(Map<String, ChessPiece> positions, int boardSize) {
        return totalCaptures;
    }
}

class King extends ChessPiece {

    public King(PiecePosition piecePosition, PieceColor pieceColor) {
        super(piecePosition, pieceColor);
    }

    /**
     * @param positions
     * @param boardSize
     * @return
     */
    @Override
    public int getMovesCount(Map<String, ChessPiece> positions, int boardSize) {
        int[][] moves = new int[][] {
                {0, 1},
                {1, 1},
                {1, 0},
                {1, -1},
                {0, -1},
                {-1, -1},
                {-1, 0},
                {-1, 1},
        };

        for (int i = 0; i < 8; i++) {
            int moveX = this.position.getX() + moves[i][0];
            int moveY = this.position.getY() + moves[i][1];
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
     * @param positions
     * @param boardSize
     * @return
     */
    @Override
    public int getCapturesCount(Map<String, ChessPiece> positions, int boardSize) {
        return this.totalCaptures;
    }
}

class Pawn extends ChessPiece {

    public Pawn(PiecePosition piecePosition, PieceColor pieceColor) {
        super(piecePosition, pieceColor);
    }

    /**
     * @param positions
     * @param boardSize
     * @return
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
            moveX = this.getPosition().getX() + moves[i][0];
            moveY = this.getPosition().getY() + moves[i][1];
            if (moveX > 0 && moveX <= boardSize && moveY > 0 && moveY <= boardSize) {
                if (positions.containsKey(moveX + " " + moveY) && positions.get(moveX + " " + moveY).color != this.color) {
                    this.totalCaptures++;
                }
            }
        }
        return this.totalMoves + this.totalCaptures;
    }

    /**
     * @param positions
     * @param boardSize
     * @return
     */
    @Override
    public int getCapturesCount(Map<String, ChessPiece> positions, int boardSize) {
        return this.totalCaptures;
    }
}

class Bishop extends ChessPiece implements BishopMovement{

    public Bishop(PiecePosition piecePosition, PieceColor pieceColor) {
        super(piecePosition, pieceColor);
    }

    /**
     * @param positions
     * @param boardSize
     * @return
     */
    @Override
    public int getMovesCount(Map<String, ChessPiece> positions, int boardSize) {
        return getDiagonalMovesCount(this.position, this.color, positions, boardSize);
    }

    /**
     * @param positions
     * @param boardSize
     * @return
     */
    @Override
    public int getCapturesCount(Map<String, ChessPiece> positions, int boardSize) {
        return getDiagonalCapturesCount(this.position, this.color, positions, boardSize);
    }

    // TO-DO write logic
    @Override
    public int getDiagonalMovesCount(PiecePosition position, PieceColor color, Map<String, ChessPiece> positions, int boardSize) {

        int[][] directions = new int[][] {
                {1, 1},
                {1, -1},
                {-1, -1},
                {-1, 1}
        };

        for (int i = 0; i < 4; i++) {
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

    // TO-DO write logic
    @Override
    public int getDiagonalCapturesCount(PiecePosition position, PieceColor color, Map<String, ChessPiece> positions, int boardSize) {
        return this.totalCaptures;
    }
}

class Rook extends ChessPiece implements RookMovement {

    public Rook(PiecePosition piecePosition, PieceColor pieceColor) {
        super(piecePosition, pieceColor);
    }

    /**
     * @param positions
     * @param boardSize
     * @return
     */
    @Override
    public int getMovesCount(Map<String, ChessPiece> positions, int boardSize) {
        return getOrthogonalMovesCount(this.position, this.color, positions, boardSize);
    }

    /**
     * @param positions
     * @param boardSize
     * @return
     */
    @Override
    public int getCapturesCount(Map<String, ChessPiece> positions, int boardSize) {
        return getOrthogonalCapturesCount(this.position, this.color, positions, boardSize);
    }

    @Override
    public int getOrthogonalCapturesCount(PiecePosition position, PieceColor color, Map<String, ChessPiece> positions, int boardSize) {
        return this.totalCaptures;
    }

    @Override
    public int getOrthogonalMovesCount(PiecePosition position, PieceColor color, Map<String, ChessPiece> positions, int boardSize) {
        int[][] directions = new int[][] {
                {0, 1},
                {1, 0},
                {0, -1},
                {-1, 0}
        };

        for (int i = 0; i < 4; i++) {
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
 * Queen */
class Queen extends ChessPiece implements BishopMovement, RookMovement{

    private int orthogonalMovementCount = 0;
    private int orthogonalCaptureCount = 0;
    public Queen(PiecePosition piecePosition, PieceColor pieceColor) {
        super(piecePosition, pieceColor);
    }

    /**
     * @param positions
     * @param boardSize
     * @return
     */
    @Override
    public int getMovesCount(Map<String, ChessPiece> positions, int boardSize) {
        return getDiagonalMovesCount(this.position, this.color, positions, boardSize)
                + getOrthogonalMovesCount(this.position, this.color, positions, boardSize);
    }

    /**
     * @param positions
     * @param boardSize
     * @return
     */
    @Override
    public int getCapturesCount(Map<String, ChessPiece> positions, int boardSize) {
        return getDiagonalCapturesCount(this.position, this.color, positions, boardSize)
                + getOrthogonalCapturesCount(this.position, this.color, positions, boardSize);
    }

    @Override
    public int getDiagonalMovesCount(PiecePosition position, PieceColor color, Map<String, ChessPiece> positions, int boardSize) {

        int[][] directions = new int[][] {
                {1, 1},
                {1, -1},
                {-1, -1},
                {-1, 1}
        };

        for (int i = 0; i < 4; i++) {
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
    @Override
    public int getDiagonalCapturesCount(PiecePosition position, PieceColor color, Map<String, ChessPiece> positions, int boardSize) {
        return this.totalCaptures;
    }

    @Override
    public int getOrthogonalCapturesCount(PiecePosition position, PieceColor color, Map<String, ChessPiece> positions, int boardSize) {
        return this.orthogonalCaptureCount;
    }

    @Override
    public int getOrthogonalMovesCount(PiecePosition position, PieceColor color, Map<String, ChessPiece> positions, int boardSize) {
        int[][] directions = new int[][] {
                {0, 1},
                {1, 0},
                {0, -1},
                {-1, 0}
        };

        for (int i = 0; i < 4; i++) {
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
    protected int totalCaptures = 0;
    protected int totalMoves = 0;
    protected PiecePosition position = null;
    protected PieceColor color = PieceColor.WHITE;

    public ChessPiece (PiecePosition piecePosition, PieceColor pieceColor) {
        this.position = piecePosition;
        this.color = pieceColor;
    }
    public PiecePosition getPosition() {
        return position;
    }

    public PieceColor getColor() {
        return color;
    }

    // TO-DO : write logic to getMovesCount
    public abstract int getMovesCount(Map<String, ChessPiece> positions, int boardSize);

    // TO-DO : write logic to getCapturesCount
    public abstract int getCapturesCount(Map<String, ChessPiece> positions, int boardSize);

}

class PiecePosition {
    private int x = 0;
    private int y = 0;

    public PiecePosition (int onX, int onY) {

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

enum PieceColor {
    WHITE,
    BLACK;

    public static PieceColor parse(String enum_str) {
        if (enum_str.compareTo("White") == 0) {
            return PieceColor.WHITE;
        } else {
            return PieceColor.BLACK;
        }
    }
}

/**
 * BishopMovement
 */
interface BishopMovement {
    int getDiagonalMovesCount(PiecePosition position, PieceColor color, Map<String, ChessPiece> positions, int boardSize);
    int getDiagonalCapturesCount(PiecePosition position, PieceColor color, Map<String, ChessPiece> positions, int boardSize);
}

/**
 * RookMovement
 */
interface RookMovement {
    int getOrthogonalMovesCount(PiecePosition position, PieceColor color, Map<String, ChessPiece> positions, int boardSize);
    int getOrthogonalCapturesCount(PiecePosition position, PieceColor color, Map<String, ChessPiece> positions, int boardSize);
}

/**
 * InvalidBoardSizeException
 */
class InvalidBoardSizeException extends Exception{
    public InvalidBoardSizeException() {
        super("Invalid board size");
    }
}

/**
 * InvalidNumberOfPiecesException
 */
class InvalidNumberOfPiecesException extends Exception{
    public InvalidNumberOfPiecesException() {
        super("Invalid number of pieces");
    }
}

/**
 * InvalidPieceNameException
 */
class InvalidPieceNameException extends Exception{
    public InvalidPieceNameException() {
        super("Invalid piece name");
    }
}

/**
 * InvalidPieceColorException
 */
class InvalidPieceColorException extends Exception{
    public InvalidPieceColorException() {
        super("Invalid piece color");
    }
}

/**
 * InvalidPiecePositionException
 */
class InvalidPiecePositionException extends Exception{
    public InvalidPiecePositionException() {
        super("Invalid piece position");
    }
}

/**
 * InvalidGivenKingsException
 */
class InvalidGivenKingsException extends Exception{
    public InvalidGivenKingsException() {
        super("Invalid given Kings");
    }
}

/**
 * InvalidInputException
 */
class InvalidInputException extends Exception{
    public InvalidInputException() {
        super("Invalid input");
    }
}
