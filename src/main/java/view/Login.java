package view;

import com.sun.tools.javac.Main;
import controller.AudioController;
import controller.ConnectDB;
import controller.MouseClickController;
import model.User;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;


public class Login extends JFrame {
    public static Login login;

    private AudioController audioController;


    private TetrisFrame tetrisFrame = new TetrisFrame();

    private User user = new User("", 0);
    private JTextField userName;
    private JButton OkButton;
    private JButton exitButton;
    private JLabel backgroundPanel;

    public Login(AudioController audioController) {
        this.audioController = audioController;
        setTitle("Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        login = this;

        setLocation(400, 200);

        // code

    }

    public void init() {
//        audioController.stopAudio();

        JLabel login = new JLabel("Login");
        login.setBounds(140, 150, 100, 30);
        login.setForeground(new Color(51, 255, 255));
        login.setFont(new Font("Arial", Font.BOLD, 20));

        userName = new JTextField(15);
        userName.setBounds(100, 200, 150, 30);


        OkButton = new JButton("OK");
        OkButton.setBounds(100, 250, 150, 30);
        OkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if (!userName.getText().isEmpty()) {
                    System.out.println("ten vua nhap la : " + userName.getText());
                    user.setName(userName.getText());
                    System.out.println("ten vua luu  duoc la : " + user.getName() + user.getPoint());
                    tetrisFrame.updateName(user.getName());
                    System.out.println(tetrisFrame.getNameJLabel().getText());
                    try {
                        if (new ConnectDB().checkName(user.getName())) {
                            JOptionPane optionPane = new JOptionPane("Tên " + user.getName() + " đã tồn tại , bạn có muốn chơi  dưới tên này không ?", JOptionPane.QUESTION_MESSAGE);
                            JDialog jDialog = optionPane.createDialog("OK");
                            jDialog.setVisible(true);
                            jDialog.addWindowListener(new WindowAdapter() {
                                /**
                                 * Invoked when a window is in the process of being closed.
                                 * The close operation can be overridden at this point.
                                 *
                                 * @param e
                                 */
                                @Override
                                public void windowClosing(WindowEvent e) {
                                    jDialog.dispose();
                                }


                            });
                            Integer result = (Integer) optionPane.getValue();
                            if (result != null &&  result == JOptionPane.OK_OPTION) {
                                MouseClickController.startGame();
                                audioController.stopAudio();
                                dispose();
                            }
                        } else  {
                            MouseClickController.startGame();
                            audioController.stopAudio();
                            dispose();
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }




                }
                //  su li su kien khi ma ng dung khong nhap ten va ng dung nhap trung ten da co san
                else {
                    if (userName.getText().isEmpty()) {
                        //  ơ đây con 1 truong hop nua la khi nhap tên đã có trong sql
                        // khi có tên đấy thì ta cần phải sử lý dữ kiện so sánh xong
                        // cuối cùng đưa ra thông báo cho ng dùng biết được.

                        JOptionPane optionPane = new JOptionPane("Vui lòng nhập tên", JOptionPane.QUESTION_MESSAGE);
                        JDialog jDialog = optionPane.createDialog("OK");
                        jDialog.setVisible(true);
                    }

                }
            }
        });


        //  button tro ve man hinh chinh . :
        exitButton = new JButton();
        exitButton.setIcon(new ImageIcon("src/main/java/view/img/exit.png"));
        exitButton.setBounds(0, 0, 30, 30);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MouseClickController.openMenuView();
                dispose();
            }
        });

        backgroundPanel = new JLabel();
        //  them background cho menu
        backgroundPanel.setIcon(new ImageIcon("src/main/java/view/img/startImg.jpg"));
        backgroundPanel.setLayout(null);
        backgroundPanel.add(login);
        backgroundPanel.add(userName);
        backgroundPanel.add(OkButton);
        backgroundPanel.add(exitButton);
        setContentPane(backgroundPanel);

        pack();
        setResizable(false);
        setVisible(true);
    }

    public JTextField getUserName() {
        return userName;
    }

    public User getUser() {
        return user;
    }

    public TetrisFrame getTetrisFrame() {
        return tetrisFrame;
    }

//    public static void main(String[] args) {
//        new Login().init();
//    }
}
