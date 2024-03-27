package view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MenuView extends JFrame {
    private  JButton batDauButton ;
    private  JButton xepHangButton;
    private  JLabel backgroundPanel;
    private JButton thoatButton;
    protected  ImageIcon imageIcon;
    public   MenuView(){
        setLocationRelativeTo(null);

        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initMenu();

        pack();
        setVisible(true);

    }
    public  void initMenu(){
        batDauButton = new JButton("Bắt đầu");
        batDauButton.setBounds(100,100,100,30);
        batDauButton.setBackground(new Color(51, 255, 255));
        batDauButton.setFont(new Font("Arial" , Font.BOLD , 14));


        xepHangButton  = new JButton("Xếp hạng");
        xepHangButton.setBounds(100,150,100,30);
        xepHangButton.setBackground(new Color(51,255,255));
        xepHangButton.setFont(new Font("Arial" , Font.BOLD , 14));

        thoatButton  = new JButton("Thoát");
        thoatButton.setBounds(100,200,100,30);
        thoatButton.setBackground(new Color(51,255,255));
        thoatButton.setFont(new Font("Arial" , Font.BOLD , 14));

        imageIcon = new ImageIcon("D:\\Khoa Hoc May Tinh\\KI_4\\java\\BaiTapLon\\src\\main\\java\\view\\img\\startImg.jpg");

        backgroundPanel = new JLabel();
        backgroundPanel.setIcon(imageIcon);
        backgroundPanel.setLayout(null);
        backgroundPanel.add(batDauButton);
        backgroundPanel.add(xepHangButton);
        backgroundPanel.add(thoatButton);
        setContentPane(backgroundPanel);
    }

    public static void main(String[] args) {
        new MenuView();
    }
}
