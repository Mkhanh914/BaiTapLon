//package controller;
//
//import view.Login;
//import view.MenuView;
//import view.RankView;
//import view.TetrisFrame;
//
//import java.util.Objects;
//import java.util.concurrent.ThreadPoolExecutor;
//
//public class MouseClickController extends Thread {
//    public static void openLogin() {
//        // tạo luôngf chạy song song với luồng chính(main)
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                AudioController audioController = AudioController.getInstance();
//                new Login(audioController).init();
//            }
//        }).start();
//
//    }
//    public static void openMenuView(){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                new MenuView();
//
//            }
//        }).start();
//    }
//    public  static  void exit(){
//
//    }
//    public  static void startGame(){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//              if (Login.login != null)
//                  Login.login.getTetrisFrame().init();
//
//            }
//        }).start();
//    }
//    public  static  void openRank(){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                new RankView();
//            }
//        }).start();
//    }
//
//}
package controller;

import view.Login;
import view.MenuView;
import view.RankView;
import view.TetrisFrame;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MouseClickController {
    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    public static void openLogin() {
        executorService.execute(() -> {
            AudioController audioController = AudioController.getInstance();
            new Login(audioController).init();
        });
    }

    public static void openMenuView() {
        executorService.execute(MenuView::new);
    }

    public static void exit() {
        // Đưa ra logic thoát ứng dụng
    }

    public static void startGame() {
        executorService.execute(() -> {
            if (Login.login != null) {
                Login.login.getTetrisFrame().init();
            }
        });
    }

    public static void openRank() {
        executorService.execute(RankView::new);
    }
}
