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

            if (!hasMoved) {
                // Right castle
                if (targetCol == preCol+2 && targetRow == preRow && !pieceIsOnStraightLine(targetCol, targetRow)) {
                    for (Piece piece: GamePanel.simPieces) {
                        if (piece.col == preCol+3 && piece.row == preRow && !piece.hasMoved) {
                            GamePanel.castlePiece = piece;
                            return true;
                        }
                    }
                }
                // Left castle
                if (targetCol == preCol-2 && targetRow == preRow && !pieceIsOnStraightLine(targetCol, targetRow)) {
                    Piece[] p = new Piece[2];
                    for (Piece piece: GamePanel.simPieces) {
                        if (piece.col == preCol-3 && piece.row == targetRow) {
                            p[0] = piece;
                        }
                        if (piece.col == preCol-4 && piece.row == targetRow) {
                            p[1] = piece;
                        }
                        if (p[0] == null && p[1] != null && !p[1].hasMoved) {
                            GamePanel.castlePiece = p[1];
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
