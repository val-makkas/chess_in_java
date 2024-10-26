package Piece;

import Main.GamePanel;

public class Pawn extends Piece {
    public Pawn(int color, int col, int row) {
        super(color, col, row);

        if (color == GamePanel.WHITE) {
            image = getImage("/piece/wP");
        } else {
            image = getImage("/piece/bP");
        }
    }

    public boolean canMove(int targetCol, int targetRow) {
        if (isInBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
            int moveInt;
            if (color == GamePanel.WHITE) {
                moveInt = -1;
            } else {
                moveInt = 1;
            }
            OccupiedPiece = getOccupiedPiece(targetCol, targetRow);

            if (targetCol == preCol && targetRow == preRow + moveInt && OccupiedPiece == null) {
                return true;
            }
            if (targetCol == preCol && targetRow == preRow + moveInt*2 && OccupiedPiece == null && !hasMoved && !pieceIsOnStraightLine(targetCol, targetRow)) {
                return true;
            }
            if (Math.abs(targetCol - preCol) == 1 && targetRow == preRow + moveInt && OccupiedPiece != null && OccupiedPiece.color != color) {
                return true;
            }
        }
        return false;
    }
}
