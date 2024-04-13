package view;

import javax.swing.*;
import java.awt.*;

public class TetrisFrame  extends JFrame {
    private  JLabel statusBar ;
    private  TetrisBoard board ;

    /**
     * Constructs a new frame that is initially invisible.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     *
     * @throws HeadlessException if GraphicsEnvironment.isHeadless()
     *                           returns true.
     * @see GraphicsEnvironment#isHeadless
     * @see Component#setSize
     * @see Component#setVisible
     * @see JComponent#getDefaultLocale
     */
    public TetrisFrame() throws HeadlessException {
        statusBar = new JLabel(" 0");
        board = new TetrisBoard(this);
    }
    public  void init(){
        setLayout(new BorderLayout());
        add(statusBar ,BorderLayout.SOUTH);
        add(board , BorderLayout.CENTER);
        board.start();
        setSize(300,600);
        setPreferredSize(new Dimension(300,600));
        setTitle("TETRIS");
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }
    JLabel getStatusBar(){
        return statusBar;
    }
}
