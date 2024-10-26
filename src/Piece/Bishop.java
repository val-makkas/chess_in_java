package Piece;

import Main.GamePanel;

public class Bishop extends Piece {
    public Bishop(int color, int col, int row) {
        super(color, col, row);

        if (color == GamePanel.WHITE) {
            image = getImage("/piece/wB");
        } else {
            image = getImage("/piece/bB");
        }
    }

    public boolean canMove(int targetCol, int targetRow) {
        if (isInBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
            // When moving diagonally ratio is always 1:1
            if (Math.abs(targetCol - preCol) == Math.abs(targetRow - preRow)) {
                return isValidSquare(targetCol, targetRow) && !pieceIsOnDiagonalLine(targetCol, targetRow);
            }
        }
        return false;
    }
}
