import java.util.HashMap;
import java.util.List;
import java.io.File;
import java.util.Map;
import java.util.Scanner;

class ass4 {

    private static Board chessBoard;

    // white black
    private int[] kings = new int[] {0, 0};
    private File outputFile = null;
    public static void main(String[] args) {

    }

    /**
     * Reads all input to chessBoard and writes exceptions to output.txt
     * @param chessBoard
     */
    private void readInput(Board chessBoard) {
        
        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);
        try {
            int N = 0, actualN = 0;
            int M = 0, actualM = 0;
            N = scanner.nextInt();

            if(N < 3 || N > 1000) {
                throw new InvalidBoardSizeException();
            }

            M = scanner.nextInt();
            if(M < 2 || M > N*N) {
                throw new InvalidNumberOfPiecesException();
            }

            while(scanner.hasNext()) {
                M--;
                String piece = scanner.nextLine();

                if(Validator.validatePiece(piece)) {
                    ChessPiece newPiece = creatNewPiece(piece);
                    chessBoard.addPiece(newPiece);
                }
            }
            
            if(M != 0) {
                InvalidNumberOfPiecesException();
            }

        } catch (Exception e) {
            String exceptionMessage = e.getMessage();
            outputFile = new File("output.txt");
            outputFile.write(exceptionMessage);
            
            scanner.close();
            System.exit(0);
        }
        
        scanner.close();
    }

    private ChessPiece creatNewPiece(String piece) {

        String[] pieces = piece.split(' ');
                    
        if (pieces[0].equals("Knight")) {
            new Knight(createPosition( Arrays.copyOfRange(pieces, 2, 3)), PieceColor.parse(pieces[1]));
        } else if (pieces[0].equals("King")) {
            new King(createPosition( Arrays.copyOfRange(pieces, 2, 3)), PieceColor.parse(pieces[1]));
        } else if (pieces[0].equals("Pawn")) {
            new Pawn(createPosition( Arrays.copyOfRange(pieces, 2, 3)), PieceColor.parse(pieces[1]));
        } else if (pieces[0].equals("Bishop")) {
            new Bishop(createPosition( Arrays.copyOfRange(pieces, 2, 3)), PieceColor.parse(pieces[1]));
        } else if (pieces[0].equals("Rook")) {
            new Rook(createPosition( Arrays.copyOfRange(pieces, 2, 3)), PieceColor.parse(pieces[1]));
        } else {
            new Queen(createPosition( Arrays.copyOfRange(pieces, 2, 3)), PieceColor.parse(pieces[1]));
        }
    }

    private PiecePosition createPosition(String[] positions) {
        PiecePosition position = new PiecePosition(Integer.parseInt(positions[2]), Integer.parseInt(positions[3]));
    }
}

static class Validator {
    public static List<String> pieceNames = new String[] {"Pawn", "Bishop", "Knight", "Rook", "Queen", "King", "White", "Back"};

    public boolean validateNameOrColor(String pieceNameString) {
        return pieceNames.contains(pieceNameString);
    }

    public boolean validatePosition(String x, String y, int N) {

        int intX = 0;
        int intY = 0;

        try {
            intX = Integer.parseInt(x);
            intY = Integer.parseInt(y);
        } catch (Exception e) {
            return false;
        }

        if (intX > N || intX < 1 || intY > N || intY < 1) {
            return false;
        }

        return true;
    }

    public boolean validatePiece(String piece, int boardSize) {
        String[] pieces = piece.split(' ');

        if (!validateNameOrColor(pieces[0])) {
            throw new InvalidPieceNameException();
        }

        if (!validateNameOrColor(pieces[1])) {
            throw new InvalidPieceColorException();
        }

        if (!validatePosition(pieces[2], pieces[3], boardSize)) {
            throw new InvalidPiecePositionException();
        }

        if (pieces[0].equals("King")) {
            if (pieces[1].equals("White")) {
                if (kings[0]) {
                    throw new InvalidGivenKingsException();
                }

                kings[0] = 1;

            } else if (pieces[1].equals("Balck")) {
                if (kings[1]) {
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
    private int size;

    public Board(int boardSize) {
        this.size = boardSize;
    }

    // TO-DO write logic
    public int getPiecePossibleMovesCount(ChessPiece piece) {
        return 0;
    }

    // TO-DO write logic
    public int getPiecePossibleCapturesCount(ChessPiece piece) {
        return 0;
    }

    // TO-DO write logic
    public void addPiece(ChessPiece piece) {

    }

    // TO-DO write logic
    public ChessPiece getPiece(Position position) {
        return null;
    }
}

class Knight extends ChessPiece {

    public Knight(Position piecePosition, PieceColor pieceColor) {
        super(piecePosition, pieceColor);
    }
}

class King extends ChessPiece {

    public King(Position piecePosition, PieceColor pieceColor) {
        super(piecePosition, pieceColor);
    }
}

class Pawn extends ChessPiece {

    public Pawn(Position piecePosition, PieceColor pieceColor) {
        super(piecePosition, pieceColor);
    }
}

class Bishop extends ChessPiece implements BishopMovement{

    public Bishop(Position piecePosition, PieceColor pieceColor) {
        super(piecePosition, pieceColor);
    }

    // TO-DO write logic
    @Override
    public int getDiagonalCapturesCount(Position position, PieceColor color, Map<String, ChessPiece> positions, int boardSize) {
        return 0;
    }

    // TO-DO write logic
    @Override
    public int getDiagonalMovesCount(Position position, PieceColor color, Map<String, ChessPiece> positions, int boardSize) {
        return 0;
    }
}

class Rook extends ChessPiece implements RookMovement {

    public Rook(Position piecePosition, PieceColor pieceColor) {
        super(piecePosition, pieceColor);
    }

    @Override
    public int getOrthogonalCapturesCount(Position position, PieceColor color, Map<String, ChessPiece> positions, int boardSize) {
        return 0;
    }

    @Override
    public int getOrthogonalMovesCount(Position position, PieceColor color, Map<String, ChessPiece> positions, int boardSize) {
        return 0;
    }
}

/**
 * Queen */
class Queen extends ChessPiece implements BishopMovement, RookMovement{

    public Queen(Position piecePosition, PieceColor pieceColor) {
        super(piecePosition, pieceColor);
    }

    // TO-DO write logic
    @Override
    public int getDiagonalCapturesCount(Position position, PieceColor color, Map<String, ChessPiece> positions, int boardSize) {
        return 0;
    }

    // TO-DO write logic
    @Override
    public int getDiagonalMovesCount(Position position, PieceColor color, Map<String, ChessPiece> positions, int boardSize) {
        return 0;
    }

    // TO-DO write logic
    @Override
    public int getOrthogonalCapturesCount(Position position, PieceColor color, Map<String, ChessPiece> positions, int boardSize) {
        return 0;
    }

    // TO-DO write logic
    @Override
    public int getOrthogonalMovesCount(Position position, PieceColor color, Map<String, ChessPiece> positions, int boardSize) {
        return 0;
    }


}

abstract class ChessPiece {
    protected Position position = null;
    protected PieceColor color = PieceColor.WHITE;

    public ChessPiece (Position piecePosition, PieceColor pieceColor) {
        this.position = piecePosition;
        this.color = pieceColor;
    }
    public Position getPosition() {
        return position;
    }

    public PieceColor getColor() {
        return color;
    }

    // TO-DO : write logic to getMovesCount
    public int getMovesCount(Map<String, ChessPiece> positions, int boardSize) {
        return 0;
    }

    // TO-DO : write logic to getCapturesCount
    public int getCapturesCount(Map<String, ChessPiece> positions, int boardSize) {
        return 0;
    }

}

class PiecePosition {
    private int x = 0;
    private int y = 0;

    public Position (int onX, int onY) {

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
    int getDiagonalMovesCount(Position position, PieceColor color, Map<String, ChessPiece> positions, int boardSize);
    int getDiagonalCapturesCount(Position position, PieceColor color, Map<String, ChessPiece> positions, int boardSize);
}

/**
 * RookMovement
 */
interface RookMovement {
    int getOrthogonalMovesCount(Position position, PieceColor color, Map<String, ChessPiece> positions, int boardSize);
    int getOrthogonalCapturesCount(Position position, PieceColor color, Map<String, ChessPiece> positions, int boardSize);
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