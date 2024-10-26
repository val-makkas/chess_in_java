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
}
