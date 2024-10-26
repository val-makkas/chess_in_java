package Piece;

import Main.GamePanel;

public class Rook extends Piece {
    public Rook(int color, int col, int row) {
        super(color, col, row);

        if (color == GamePanel.WHITE) {
            image = getImage("/piece/wR");
        } else {
            image = getImage("/piece/bR");
        }
    }

    public boolean canMove(int targetCol, int targetRow) {
        if (isInBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow))  {
            // Rook moves on same col or row
            if (targetCol == preCol || targetRow == preRow) {
                return isValidSquare(targetCol, targetRow) && !pieceIsOnStraightLine(targetCol, targetRow);
            }
        }
        return false;
    }
}
