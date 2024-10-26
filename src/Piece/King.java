package Piece;

import Main.GamePanel;

public class King extends Piece {
    public King(int color, int col, int row) {
        super(color, col, row);

        if (color == GamePanel.WHITE) {
            image = getImage("/piece/wK");
        } else {
            image = getImage("/piece/bK");
        }
    }

    public boolean canMove(int targetCol, int targetRow) {
        if (isInBoard(targetCol, targetRow)) {
            if ((Math.abs(targetCol - preCol) + Math.abs(targetRow - preRow)) == 1 ||
                    (Math.abs(targetCol - preCol) * Math.abs(targetRow - preRow)) == 1) {
                return isValidSquare(targetCol, targetRow);
            }
        }
        return false;
    }
}
