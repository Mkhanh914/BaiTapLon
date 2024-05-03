package controller;

import java.io.*;

public class GameSaver{
    public  static   void saveGame(ShapeController shapeController ){
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("gameState.ser"));
            out.writeObject(shapeController);
            out.close();


        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static  ShapeController loadGame(File nameFile){
        ShapeController controller = null;
        try {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(nameFile));
                controller = (ShapeController) objectInputStream.readObject();
                objectInputStream.close();

        }
         catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return controller;
    }
}
