package controller;

import view.Login;
import view.MenuView;

import java.util.Objects;
import java.util.concurrent.ThreadPoolExecutor;

public class MouseClickController extends Thread {
    public static void openLogin() {
        // tạo luôngf chạy song song với luồng chính(main)
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Login();
            }
        }).start();

    }
    public static void openMenuView(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                new MenuView();
            }
        }).start();
    }
    public  static  void exit(){

    }

}
