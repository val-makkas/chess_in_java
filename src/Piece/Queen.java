package Piece;

import Main.GamePanel;

public class Queen extends Piece {
    public Queen(int color, int col, int row) {
        super(color, col, row);

        if (color == GamePanel.WHITE) {
            image = getImage("/piece/wQ");
        } else {
            image = getImage("/piece/bQ");
        }
    }

    public boolean canMove(int targetCol, int targetRow) {
        if (isInBoard(targetRow, targetCol) && !isSameSquare(targetRow, targetCol)) {
            // Rook Movement
            if (targetCol == preCol || targetRow == preRow) {
                return isValidSquare(targetCol, targetRow) && !pieceIsOnStraightLine(targetCol, targetRow);
            }
            // Bishop Movement
            if (Math.abs(targetCol - preCol) == Math.abs(targetRow - preRow)) {
                return isValidSquare(targetCol, targetRow) && !pieceIsOnDiagonalLine(targetCol, targetRow);
            }
        }
        return false;
    }
}
