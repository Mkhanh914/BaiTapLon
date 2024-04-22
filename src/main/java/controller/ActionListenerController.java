package controller;

import view.TetrisFrame;

import javax.print.attribute.IntegerSyntax;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ActionListenerController extends KeyAdapter  implements ActionListener {

    ShapeController controller  ;
    GameSaver gameSaver;


    public ActionListenerController(ShapeController controller) {
        this.controller = controller;
    }


    public  void keyPressed(KeyEvent e){
        if(!controller.isStarted() || controller.inCurrentPieceNoShape()) return;


        int keyCode = e.getKeyCode();
        if (keyCode == 'p' || keyCode == 'P'  ){
            controller.pause();
            return;
        }
//        if (controller.isPaused()) return;

        if (!controller.isPaused()){
            switch (keyCode){
                case KeyEvent.VK_LEFT: controller.moveLeft()  ; System.out.println("left"); break;
                case KeyEvent.VK_RIGHT: controller.moveRight() ;
                    System.out.println("right");break;
                case KeyEvent.VK_DOWN: controller.rotateRight();break;
                case KeyEvent.VK_UP: controller.rotateLeft();break;
                case KeyEvent.VK_SPACE: controller.dropDown();break;

            }
        }





    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
       if ("Pause".equals(e.getActionCommand()) || "Resume".equals(e.getActionCommand())) {
           controller.pause();
           JButton soure = (JButton) e.getSource();
           soure.setText(controller.isPaused() ? "Resume" : "Pause");
           controller.getTetrisBoard().requestFocusInWindow();
           return;
       }
       if ("Exit".equals(e.getActionCommand())){
           controller.pause();
           JOptionPane jOptionPane = new JOptionPane("Are you want to exit the game ? " , JOptionPane.QUESTION_MESSAGE , JOptionPane.YES_NO_OPTION);
           JDialog jDialog = jOptionPane.createDialog("Exit");
           jDialog.addWindowFocusListener(new WindowAdapter() {
               /**
                * Invoked when a window is in the process of being closed.
                * The close operation can be overridden at this point.
                *
                * @param e
                */
               @Override
               public void windowClosing(WindowEvent e) {
                   controller.pause();
                   controller.getTetrisBoard().requestFocusInWindow();
               }
           });
           jDialog.setVisible(true);
           Integer result = (Integer) jOptionPane.getValue();
           if (result !=null && result ==  JOptionPane.YES_OPTION){
               // thuc hien thoat game , ben canh do su luu ten va điểm cho ng  choi
               System.exit(0);
           }
           else {
               controller.pause();
               controller.getTetrisBoard().requestFocusInWindow();
           }

       }
       if ("Tiếp tục".equals(e.getActionCommand())){
           //  read file
           ShapeController shapeController = gameSaver.loadGame("gameState.ser");
           if (shapeController != null){
               controller =  shapeController;
               controller.startGame();
           }

       }

    }
}
