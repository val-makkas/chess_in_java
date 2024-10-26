package Main;

import Piece.Piece;
import Piece.Pawn;
import Piece.Rook;
import Piece.Knight;
import Piece.Bishop;
import Piece.Queen;
import Piece.King;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    public static final int WIDTH = 1100;
    public static final int HEIGHT = 800;
    final int FPS = 60;
    Thread gameThread;
    Board board = new Board();
    Mouse mouse = new Mouse();

    public static ArrayList<Piece> pieces = new ArrayList<>();
    public static ArrayList<Piece> simPieces = new ArrayList<>();
    Piece activePiece;

    public static final int WHITE = 0;
    public static final int BLACK = 1;
    int currentColor = WHITE;

    boolean canMove;
    boolean validSquare;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        addMouseMotionListener(mouse);
        addMouseListener(mouse);

        setPieces();
        copyPieces(pieces, simPieces);
    }

    public void launch() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setPieces() {
        // WHITE
        pieces.add(new Pawn(WHITE, 0, 6));
        pieces.add(new Pawn(WHITE, 1, 6));
        pieces.add(new Pawn(WHITE, 2, 6));
        pieces.add(new Pawn(WHITE, 3, 6));
        pieces.add(new Pawn(WHITE, 4, 6));
        pieces.add(new Pawn(WHITE, 5, 6));
        pieces.add(new Pawn(WHITE, 6, 6));
        pieces.add(new Pawn(WHITE, 7, 6));
        pieces.add(new Rook(WHITE, 0, 7));
        pieces.add(new Rook(WHITE, 7, 4));
        pieces.add(new Knight(WHITE, 1,7));
        pieces.add(new Knight(WHITE, 6,7));
        pieces.add(new Bishop(WHITE, 2,7));
        pieces.add(new Bishop(WHITE, 5,7));
        pieces.add(new Queen(WHITE,3,4));
        pieces.add(new King(WHITE,4,7));

        // BLACK
        pieces.add(new Pawn(BLACK, 0, 1));
        pieces.add(new Pawn(BLACK, 1, 1));
        pieces.add(new Pawn(BLACK, 2, 1));
        pieces.add(new Pawn(BLACK, 3, 1));
        pieces.add(new Pawn(BLACK, 4, 1));
        pieces.add(new Pawn(BLACK, 5, 1));
        pieces.add(new Pawn(BLACK, 6, 1));
        pieces.add(new Pawn(BLACK, 7, 1));
        pieces.add(new Rook(BLACK, 0, 0));
        pieces.add(new Rook(BLACK, 7, 0));
        pieces.add(new Knight(BLACK, 1,0));
        pieces.add(new Knight(BLACK, 6,0));
        pieces.add(new Bishop(BLACK, 2,0));
        pieces.add(new Bishop(BLACK, 5,0));
        pieces.add(new Queen(BLACK,3,0));
        pieces.add(new King(BLACK,4,0));
    }

    private void copyPieces(ArrayList<Piece> source, ArrayList<Piece> target) {
        target.clear();
        target.addAll(source);
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 /FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread.isAlive()) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    private void update() {
        if (mouse.pressed) {
            if (activePiece == null) {
                for (Piece piece : simPieces) {
                    if (piece.color == currentColor &&
                            piece.col == mouse.x/Board.SQUARE_SIZE &&
                            piece.row == mouse.y/Board.SQUARE_SIZE) {
                        activePiece = piece;
                    }
                }
            } else {
                simulate();
            }
        }

        if (!mouse.pressed) {
            if (activePiece != null) {
                if (validSquare) {


                    copyPieces(simPieces, pieces);
                    activePiece.updatePosition();
                } else {

                    copyPieces(pieces, simPieces);
                    activePiece.resetPosition();
                    activePiece = null;
                }
            }
        }
    }

    private void simulate() {
        canMove = false;
        validSquare = false;

        copyPieces(pieces, simPieces);

        activePiece.x = mouse.x - Board.HALF_SQUARE_SIZE;
        activePiece.y = mouse.y - Board.HALF_SQUARE_SIZE;
        activePiece.col = activePiece.getCol(activePiece.x);
        activePiece.row = activePiece.getRow(activePiece.y);

        if (activePiece.canMove(activePiece.col, activePiece.row)) {
            canMove = true;
            validSquare = true;

            if (activePiece.OccupiedPiece != null) {
                simPieces.remove(activePiece.OccupiedPiece.getIndex());
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        board.draw(g2d);

        for (Piece p : simPieces) {
            p.draw(g2d);
        }

        if (activePiece != null) {
            if (canMove) {
                g2d.setColor(Color.YELLOW);
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
                g2d.fillRect(activePiece.col * Board.SQUARE_SIZE, activePiece.row * Board.SQUARE_SIZE, Board.SQUARE_SIZE, Board.SQUARE_SIZE);
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            }

            activePiece.draw(g2d);
        }
    }
}
