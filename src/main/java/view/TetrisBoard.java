package view;

import controller.ActionListenerController;
import controller.ShapeController;
import model.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public  class TetrisBoard extends JPanel implements ActionListener {
    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    private  final   int    BOARD_WIDTH = 10 ;
    private  final   int    BOARD_HEIGHT = 22 ;
    private JLabel statusBar;
    private ShapeController shapeController;
    private  ActionListenerController actionListenerController;


    TetrisBoard(TetrisFrame tetrisFrame ){

        setFocusable(true);
        shapeController = new ShapeController(BOARD_WIDTH , BOARD_HEIGHT , this);
//        statusBar = tetrisFrame.getStatusBar();
        addKeyListener(new ActionListenerController(shapeController));
        actionListenerController = new ActionListenerController(shapeController);






        // key  phim tat cho game .
    }
    void start(){shapeController.startGame();}

    public ShapeController getShapeController() {
        return shapeController;
    }

    public  void paint(Graphics g){
        super.paint(g);
        shapeController.paint(g, getSize().getWidth() ,getSize().getHeight());
    }

    private  int squareWidth(){return(int) getSize().getWidth()/BOARD_WIDTH;}
    private  int squareHeight(){return(int) getSize().getHeight()/BOARD_HEIGHT;}


/**
 * drawSquare dung de ve 1 o vuong tren bang voi mau sac tuong ung voi hinh dang cua khoi (enum)
 *
 */
    public void drawSquare(Graphics g, int  x, int  y , Shape.Tetrominoes shape) {
        Color[] color = {new Color(0,0,0) , new Color(204,102,102) ,
                        new Color(102,204,102) , new Color(102,102,204),
                        new Color(204, 204, 102), new Color(204, 102, 204),
                        new Color(102, 204, 204), new Color(218, 170, 0)
        } ;
//        lay mau tuong ung voi hinh dang khoi trong enum
        Color color1 = color[shape.ordinal()];
//        thiet lap mau ve hien tai cho doi tuong
        g.setColor(color1);
//        ve 1 hinh vuong  trong do toa do (x, y ) va chieu rong chieu hai cua hinh vuong do
        g.fillRect(x+1 , y +1 , squareWidth()-2 , squareHeight()-2) ;

/**
 * thiet lap vien cho hinh vuong
 */
        g.setColor(color1.brighter());
//        ve vien cho hinh vuong cac duong ngang va doc ben trai ben phai
        g.drawLine(x , y + squareHeight() -1 ,x , y);
        g.drawLine(x , y , x+squareWidth()-1 , y );


        g.setColor(color1.darker());
        g.drawLine(x+1 , y + squareHeight()-1 , x+squareWidth()-1 , y+squareHeight()-1);
        g.drawLine(x+squareWidth()-1 , y+squareHeight()-1 , x +squareWidth()-1 , y+1 );


    }
//    public  void setStatusBar(String text){statusBar.setText(text);}

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
            shapeController.gameAction();
    }
}
