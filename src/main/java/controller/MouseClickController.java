package controller;

import view.Login;
import view.MenuView;
import view.RankView;
import view.TetrisFrame;

import java.util.Objects;
import java.util.concurrent.ThreadPoolExecutor;

public class MouseClickController extends Thread {
    public static void openLogin() {
        // tạo luôngf chạy song song với luồng chính(main)
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Login().init();
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
    public  static void startGame(){
        new Thread(new Runnable() {
            @Override
            public void run() {
              if (Login.login != null)
                  Login.login.getTetrisFrame().init();

            }
        }).start();
    }
    public  static  void openRank(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                new RankView();
            }
        }).start();
    }

}
