package Piece;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import Main.Board;
import Main.GamePanel;

import javax.imageio.ImageIO;

public class Piece {
    public BufferedImage image;
    public int x, y;
    public int col, row, preCol, preRow;
    public int color;
    public Piece OccupiedPiece;

    public Piece(int color, int col, int row) {
        this.color = color;
        this.col = col;
        this.row = row;

        x = getX(col);
        y = getY(row);
        preCol = col;
        preRow = row;
    }

    public BufferedImage getImage(String imagePath) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public int getX(int col) {
        return col * Board.SQUARE_SIZE;
    }

    public int getY(int row) {
        return row * Board.SQUARE_SIZE;
    }

    public int getCol(int x) {
        return (x + Board.HALF_SQUARE_SIZE)/Board.SQUARE_SIZE;
    }

    public int getRow(int y) {
        return (y + Board.HALF_SQUARE_SIZE)/Board.SQUARE_SIZE;
    }

    public int getIndex() {
        for (int i = 0; i < GamePanel.simPieces.size(); i++) {
            if (GamePanel.simPieces.get(i) == this) {
                return i;
            }
        }
        return -1;
    }

    public void updatePosition() {
        x = getX(col);
        y = getY(row);
        preCol = getCol(x);
        preRow = getRow(y);
    }

    public void resetPosition() {
        col = preCol;
        row = preRow;
        x = getX(col);
        y = getY(row);
    }

    public boolean canMove(int targetCol, int targetRow) {
        return false;
    }

    public boolean isInBoard(int targetCol, int targetRow) {
        return targetCol <= 7 && targetCol >= 0 && targetRow <= 7 && targetRow >= 0;
    }

    public boolean isSameSquare(int targetCol, int targetRow) {
        return (targetCol == preCol && targetRow == preRow);
    }

    public Piece getOccupiedPiece(int targetCol, int targetRow) {
        for (Piece piece: GamePanel.simPieces) {
            if (piece.col == targetCol && piece.row == targetRow && piece != this) {
                return piece;
            }
        }
        return null;
    }

    public boolean isValidSquare(int targetCol, int targetRow) {
        OccupiedPiece = getOccupiedPiece(targetCol, targetRow);
        if (OccupiedPiece == null) {
            return true;
        } else {
            if (OccupiedPiece.color != this.color) {
                return true;
            } else {
                OccupiedPiece = null;
            }
        }
        return false;
    }

    public boolean pieceIsOnStraightLine(int targetCol, int targetRow) {
        // When moving left
        for (int i = preCol-1; i > targetCol; i--) {
            for (Piece piece: GamePanel.simPieces) {
                if (piece.col == i && piece.row == targetRow) {
                    OccupiedPiece = piece;
                    return true;
                }
            }
        }
        // When moving right
        for (int i = preCol+1; i < targetRow; i++) {
            for (Piece piece: GamePanel.simPieces) {
                if (piece.col == i && piece.row == targetRow) {
                    OccupiedPiece = piece;
                    return true;
                }
            }
        }
        // When moving up
        for (int r = preRow-1; r > targetRow; r--) {
            for (Piece piece: GamePanel.simPieces) {
                if (piece.col == targetCol && piece.row == r) {
                    OccupiedPiece = piece;
                    return true;
                }
            }
        }
        // When moving down
        for (int r = preRow+1; r < targetRow; r++) {
            for (Piece piece: GamePanel.simPieces) {
                if (piece.col == targetCol && piece.row == r) {
                    OccupiedPiece = piece;
                    return true;
                }
            }
        }
        return false;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, Board.SQUARE_SIZE, Board.SQUARE_SIZE, null);
    }
}
