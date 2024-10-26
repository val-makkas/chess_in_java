package Main;

import java.awt.*;

public class Board {
    final int _COL = 8;
    final int _ROW = 8;
    public static final int SQUARE_SIZE = 100;
    public static final int HALF_SQUARE_SIZE = SQUARE_SIZE / 2;

    public void draw(Graphics2D g2) {
        int color = 0;
        for (int row = 0; row < _ROW; row++) {
            for (int col = 0; col < _COL; col++) {
                if (color % 2 == 0) {
                    g2.setColor(new Color(118,150,85));
                } else {
                    g2.setColor(new Color(238,238,210));
                }
                color++;
                g2.fillRect(col * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
            // So the board colors are inputted correctly after each row
            color++;
        }
    }
}
