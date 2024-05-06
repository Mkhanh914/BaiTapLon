package controller;

import view.TetrisFrame;

import javax.print.attribute.IntegerSyntax;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

public class ActionListenerController extends KeyAdapter implements ActionListener{


    private ShapeController controller;
    private TetrisFrame tetrisFrame;


    public ActionListenerController(ShapeController controller) {
        this.controller = controller;

    }

    public ActionListenerController(ShapeController shapeController, TetrisFrame tetrisFrame) {
        this.controller = shapeController;
        this.tetrisFrame = tetrisFrame;
    }


    public void keyPressed(KeyEvent e) {
        if (!controller.isStarted() || controller.inCurrentPieceNoShape()) return;


        int keyCode = e.getKeyCode();
        if (keyCode == 'p' || keyCode == 'P') {
            controller.pause();
            return;
        }
        if (keyCode == 'D' || keyCode == 'D') {
            controller.oneLineDown();
            return;
        }

        if (!controller.isPaused()) {
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    controller.moveLeft();
                    System.out.println("left");
                    break;
                case KeyEvent.VK_RIGHT:
                    controller.moveRight();
                    System.out.println("right");
                    break;
                case KeyEvent.VK_DOWN:
                    controller.rotateRight();
                    break;
                case KeyEvent.VK_UP:
                    controller.rotateLeft();
                    break;
                case KeyEvent.VK_SPACE:
                    controller.dropDown();
                    break;

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

        }
        if ("Exit".equals(e.getActionCommand())) {

            controller.pause();
            JOptionPane jOptionPane = new JOptionPane("Are you want to exit the game ? ", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
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
            if (result != null && result == JOptionPane.YES_OPTION) {
// sử dụng  khi ng choi bấm yes thì sẽ lưu điểm  của ng chơi vào nêu mà  ng chơi có điểm cao hơn lúc trước
//                còn nếu mà ng chơi mới thì lưu trực tiếp vào.

                new ConnectDB().insertData(tetrisFrame.getName(), tetrisFrame.getPoint());
                System.exit(0);
            } else {
                controller.pause();
                controller.getTetrisBoard().requestFocusInWindow();
            }

        }


    }
}
