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
}
