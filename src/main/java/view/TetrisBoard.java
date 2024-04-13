package view;

import controller.ShapeController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TetrisBoard extends JPanel implements ActionListener {
    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    private  final   int    BOARD_WIDTH = 10 ;
    private  final   int    BOARD_HEIGHT = 22 ;
    private JLabel statusBar;
    private ShapeController shapeController;

    TetrisBoard(TetrisFrame tetrisFrame ){
        setFocusable(true);
        shapeController = new ShapeController(BOARD_WIDTH , BOARD_HEIGHT , this);
        statusBar = tetrisFrame.getStatusBar();
        // key  phim tat cho game .
    }
    void start(){shapeController.start();}



    public  void actionPerFormed(ActionEvent e){shapeController.gameAction();}


    public  void paint(Graphics g){
        super.paint(g);
        shapeController.paint(g, getSize().getWidth() ,getSize().getHeight());
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
