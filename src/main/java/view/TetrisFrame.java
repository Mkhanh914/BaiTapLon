package view;

import controller.ActionListenerController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TetrisFrame  extends JFrame   {

    private  JLabel statusBar ;
    private  JLabel  textJLabel = new JLabel("Name:");

    private  JLabel pointJLabel = new JLabel("Point:");
    private JButton pauseButton = new JButton() ;
    private JButton exitButton  = new JButton();
    private  JLabel nameJLabel = new JLabel("");
    private  JLabel pointxJlable =  new JLabel() ;
    private  TetrisBoard board ;
    private ActionListenerController actionListenerController ;
    private ActionListenerController actionListenerController1 ;




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
    public TetrisFrame() {
        statusBar = new JLabel();
        board = new TetrisBoard(this);
        board.setTetrisFrame(this);


        actionListenerController = new ActionListenerController(board.getShapeController());
        actionListenerController1 = new ActionListenerController(board.getShapeController() ,board.getTetrisFrame());


    }


    public  void init(){
        setLayout(new BorderLayout());
        add(statusBar ,BorderLayout.SOUTH);
        add(board , BorderLayout.CENTER);
        board.start();
        setSize(300,600);
        setPreferredSize(new Dimension(300,600));
        statusBar.setPreferredSize(new Dimension(300,100));

        statusBar.setBorder(new LineBorder(new Color(0, 0, 0)));

//        nameJLabel.setText( new Login().getUser().getName());
        pointxJlable.setText("0");

        statusBar.setLayout(new GridLayout(3,2));
        statusBar.add(textJLabel);
        statusBar.add(nameJLabel);
        statusBar.add(pointJLabel);
        statusBar.add(pointxJlable);
        statusBar.add(pauseButton);
        statusBar.add(exitButton);
        pauseButton.setText("Pause");
        exitButton.setText("Exit");
        pauseButton.addActionListener(actionListenerController);
        exitButton.addActionListener(actionListenerController1);




        setTitle("TETRIS");
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }
    public   void initGameOver(){

       JOptionPane jOptionPane = new JOptionPane("Game Over , your score : " + getPoint() , JOptionPane.QUESTION_MESSAGE , JOptionPane.YES_NO_OPTION );

        Dialog jDialog = jOptionPane.createDialog("GameOver");
        jDialog.setVisible(true);
        jDialog.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                dispose();
                new MenuView();

            }
        });
        Integer integer = (Integer)  jOptionPane.getValue();
        if (integer !=null && integer == JOptionPane.OK_OPTION){
            board.getShapeController().startGame();
        }else{
            new MenuView();
            dispose();
        }



        // reset game;


    }


    public JLabel getNameJLabel() {
        return nameJLabel;
    }

    public void setNameJLabel(JLabel nameJLabel) {
        this.nameJLabel = nameJLabel;
    }

    public JLabel getPointxJlable() {
        return pointxJlable;
    }

    public  void updateScore(int point){
        pointxJlable.setText(String.valueOf(point));
    }
    public  void updateName(String name){
        nameJLabel.setText(name);
        nameJLabel.revalidate();
        nameJLabel.repaint();
    }

    public TetrisBoard getBoard() {
        return board;
    }
    public String getName(){
        return nameJLabel.getText();
    }
    public int getPoint(){
        return Integer.parseInt(pointxJlable.getText());
    }




}
