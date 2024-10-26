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
}
