package controller;

import java.io.*;

public class GameSaver{
    public  static   void saveGame(ShapeController shapeController  , String fileName){
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(shapeController);
            out.close();


        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static  ShapeController loadGame(String fileName){
        ShapeController controller = null;
        try {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
                controller = (ShapeController) objectInputStream.readObject();
                objectInputStream.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return controller;
    }
}
