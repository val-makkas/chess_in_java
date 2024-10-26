package Piece;

import Main.GamePanel;

public class Knight extends Piece {
    public Knight(int color, int col, int row) {
        super(color, col, row);

        if (color == GamePanel.WHITE) {
            image = getImage("/piece/wN");
        } else {
            image = getImage("/piece/bN");
        }
    }

    public boolean canMove(int targetCol, int targetRow) {
        if (isInBoard(targetCol, targetRow)) {
            // knight movement ratio always 2:1 or 1:2
            if (Math.abs(targetCol - preCol) * Math.abs(targetRow - preRow) == 2) {
                return isValidSquare(targetCol, targetRow);
            }
        }
        return false;
    }
}
