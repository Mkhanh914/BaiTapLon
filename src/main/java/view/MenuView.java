package view;

import controller.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.EventListener;


public class MenuView extends JFrame  {
    private AudioController audioController = AudioController.getInstance();

    private  JButton continueGame;
    private  JButton batDauButton ;
    private  JButton xepHangButton;
    protected   JLabel backgroundPanel;
    private JButton thoatButton;
    protected  ImageIcon imageIcon;
//    private ActionListenerController actionListenerController = new ActionListenerController();
//    protected  static  int x = 300 , y =  400;

    public   MenuView(){



        setLocation(400,200);

        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initMenu();
        pack();
        setVisible(true);




    }
    public  void initMenu(){
        audioController = AudioController.getInstance();
        audioController.audioInGame("src/main/java/controller/audio/audioGame.wav");
        setTitle("Xếp Gạch");
        batDauButton = new JButton("Chơi mới");
        batDauButton.setBounds(100,100,100,30);
        batDauButton.setBackground(new Color(51, 255, 255));
        batDauButton.setFont(new Font("Arial" , Font.BOLD , 14));
        batDauButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MouseClickController.openLogin();
                dispose(); // close MenuView
            }
        });





//        continueGame = new JButton("Tiếp tục");
//        continueGame.setBounds(100,50,100,30);
//        continueGame.setBackground(new Color(51,255,255));
//        continueGame.setFont(new Font("Arial" , Font.BOLD , 14));
//        continueGame.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                File gameStateFile  =  new File("gameState.ser");
//
//                if (gameStateFile.exists()){
//                    ShapeController shapeController = gameSaver.loadGame(gameStateFile);
//                    if (shapeController != null){
//                        TetrisFrame tetrisFrame = new TetrisFrame();
//
//                        tetrisFrame.getBoard().setShapeController(shapeController);
//                        tetrisFrame.getBoard().getShapeController().startGame();
//                        tetrisFrame.init();
//                    }
//                    else{
//                        JOptionPane.showConfirmDialog(MenuView.this,"không tải được trò chơi.", "Lỗi" , JOptionPane.ERROR_MESSAGE);
//                    }
//                }else{
//                    JOptionPane.showConfirmDialog(MenuView.this,"không tìm thấy file .", "Lỗi" , JOptionPane.ERROR_MESSAGE);
//                }
//            }
//        });


        xepHangButton  = new JButton("Xếp hạng");
        xepHangButton.setBounds(100,150,100,30);
        xepHangButton.setBackground(new Color(51,255,255));
        xepHangButton.setFont(new Font("Arial" , Font.BOLD , 14));
        xepHangButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MouseClickController.openRank();
                dispose();
            }
        });

        thoatButton  = new JButton("Thoát");
        thoatButton.setBounds(100,200,100,30);
        thoatButton.setBackground(new Color(51,255,255));
        thoatButton.setFont(new Font("Arial" , Font.BOLD , 14));
        thoatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

//        imageIcon = new ImageIcon("D:\\Khoa Hoc May Tinh\\KI_4\\java\\BaiTapLon\\src\\main\\java\\view\\img\\startImg.jpg");

        backgroundPanel = new JLabel();
        //  them background cho menu
        backgroundPanel.setIcon(new ImageIcon("src/main/java/view/img/startImg.jpg"));
        backgroundPanel.setLayout(null);
//        backgroundPanel.add(continueGame);
        backgroundPanel.add(batDauButton);
        backgroundPanel.add(xepHangButton);
        backgroundPanel.add(thoatButton);
        setContentPane(backgroundPanel);
    }
    // sử lý dữ kiện tao luong chay chay login



    public JButton getBatDauButton() {
        return batDauButton;
    }

    public void setBatDauButton(JButton batDauButton) {
        this.batDauButton = batDauButton;
    }

    public JButton getXepHangButton() {
        return xepHangButton;
    }

    public void setXepHangButton(JButton xepHangButton) {
        this.xepHangButton = xepHangButton;
    }

    public JLabel getBackgroundPanel() {
        return backgroundPanel;
    }

    public void setBackgroundPanel(JLabel backgroundPanel) {
        this.backgroundPanel = backgroundPanel;
    }

    public JButton getThoatButton() {
        return thoatButton;
    }

    public void setThoatButton(JButton thoatButton) {
        this.thoatButton = thoatButton;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    public AudioController getAudioController() {
        return audioController;
    }


    public static void main(String[] args) {
        new MenuView();



    }
}
