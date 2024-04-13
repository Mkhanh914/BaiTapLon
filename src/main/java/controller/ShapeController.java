package controller;

import model.Shape;
import model.Tetrominoes;
import view.TetrisBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ShapeController {
    private   TetrisBoard tetrisBoard ;
    private  int width  ;
    private int height ;
    private  boolean isFallingFinish = false;
    private  boolean isStarted = false;
    private  boolean isPaused = false;
     private int numLinesRemoved = 0 ;
     private int currentX = 0;
     private int currentY = 0 ;
     private Timer timer;
     private Shape currentPiece;
     private Tetrominoes[] board;
     public  ShapeController(int width , int height , TetrisBoard tetrisBoard ){
         this.height = height ;
         this.width = width;
         this.tetrisBoard = tetrisBoard;
         currentPiece = new Shape();
         timer = new Timer(400, (ActionListener) tetrisBoard);
         timer.start();
         board = new Tetrominoes[width * height];
         clearBoard();
     }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    private Tetrominoes shapeAt(int x ,int  y){
         return board[y*getWidth() + x ];
    }
   // ham tryMove dùng để kiểm tra xem mot tetromino co the di chuyen den 1 vi tri moi nhu  chi dinh duoc khong

    public  boolean tryMove(Shape newPriece , int newX , int newY){
         // kiem tra gioi  han xem hinh co vuot qua khoi bien hay khong ?
         for (int i = 0 ; i<4 ;i++){
             int x = newX + newPriece.x(i);
             int y = newY- newPriece.y(i);
             if (x < 0  || x >= getWidth()  || y < 0 || y >= getHeight())
                    return false;
             if (shapeAt(x, y) != Tetrominoes.noShape)
                 return false;
         }
         currentPiece = newPriece;
         currentX = newX;
         currentY = newY;
         tetrisBoard.repaint();
         return true;
     }

     // newPiece  su dung de tao 1 tetromino moi  va dat no vao vi tri khoi dau tren  tren board
    private void newPiece() {
         currentPiece.setRanDomShape();
         // thiet lap vi tri moi vao trung tam cua chieu ngang
         currentX = width/2 +1 ;
         //  thiet lap vi tri moi theo chieu rong ow tren cung
         currentY = height-1 + currentPiece.minY();

        // kiem tra xem co dat dc o vi tri khoi dau hay khong
        // neu khong duoc thi su li den phan tro choi ket thuc (game over)
         if (!tryMove(currentPiece , currentX , currentY)){
             currentPiece.setHinhDangShape(Tetrominoes.noShape);
             timer.stop();
             isStarted = false;
             // se hiện bảng game over ở phần này .
         }
    }
    //  hoat dong cua tro choi
    public  void gameAction(){
         if (isFallingFinish){
             isFallingFinish = false;
             newPiece();
         }
         else oneLineDown();
     }

    //  pieceDropped su li viec khi mot hinh roi xuong , dat hinh vao bang , loai bo cac hang day , va tao 1 hinh moi neu can thiet

     private void pieceDropped() {
        for (int i = 0; i < 4; i++) {
            int x = currentX + currentPiece.x(i);
            int y = currentY + currentPiece.y(i);
            board[(y*width)+x] = currentPiece.getPriceShape();
        }
        removeFullLines();
        if(!isFallingFinish) newPiece();
    }

    // di chuyen hinh xuong1 dong
    public void oneLineDown(){
        if (!tryMove(currentPiece , currentX  , currentY-1))
            pieceDropped();
    }
    // cho hinh xuong nhanh cac tot 
    private void dropDown() {
         int newY = currentY;
         while (newY > 0 ){
             if (!tryMove(currentPiece , currentX , newY-1))
                 break;
             --newY;
         }
         pieceDropped();
    }
    private void removeFullLines() {
         int numFullLine = 0 ;
         for(int i =  getHeight() -1 ; i>=0 ; i--){
             boolean lineIsFull = true;
             for (int j = 0; j < getWidth(); j++) {
                 if(shapeAt(j,i) ==  Tetrominoes.noShape){
                     lineIsFull = false;
                     break;

                 }
             }
             if (lineIsFull){
                 ++numFullLine;
                 for (int k = 0; k < getHeight(); k++) {
                     for (int j = 0; j < getWidth(); j++) {
                         board[k*getWidth() + j ] = shapeAt(j  ,  k+1);
                     }
                 }
             }
         }
         if (numFullLine >  0 ){
             numLinesRemoved+= numFullLine;
             // ..........viet tiep phan cong diem vao value khi 1 hang day
             // xoa 1 hang do di
             isFallingFinish = true;
             currentPiece.setHinhDangShape(Tetrominoes.noShape);
             tetrisBoard.repaint();
         }
    }





    private  void clearBoard(){
         for (int i = 0 ; i<height * width ; i++)
             board[i] = Tetrominoes.noShape;
    }

    public  void start(){
         if (isPaused) return ;
         isStarted = true;
         isFallingFinish = false;
         numLinesRemoved = 0 ;
         clearBoard();
         newPiece();
         timer.start();
    }
    // ve lai trang thai hoat dong cua tro choi dua tren 1 foi tuong
    // Graphics
    public  void paint(Graphics g , double width , double height){
         /*tinh toan kich thuc cua moi o tren bang   bang cach chia chieu rong va chieu dai
         * cua vung   ve cho so o rong va cao cua bang ==> xac dinh duoc kich thuc cua moi o tren bang */
         int squareWidth = (int)width/getWidth();
         int squareHeight = (int)height/getHeight();
         /* tinh toan vi tri tren cung cua bang  , bang cach lay chieu cao  cua vung ve  *   voi
         * chieu cao cua bang  nhan voi chieu cao cua moi o */
         int boardTop = (int)height - getHeight() * squareHeight;

/*duyet qua tung o trong bang  tu hang duoi cung , cot trai nhat -----> hang tren cung , cot phai nhat */
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                // hinh dang cua khoi Tetris tai vi tri (j , getHeight - i - 1 )
                Tetrominoes shape = shapeAt(j , getHeight()-i-1);
                // kiem tra xem hinh dang do co phai la noShape khong
                if(shape != Tetrominoes.noShape )
                    // ve 1 o vuong tai vi tri (j *squareWidth,boardTop+i * squareHeight)
                     tetrisBoard.drawSquare(g, j *squareWidth,boardTop+i * squareHeight , shape);
            }
        }
        if (currentPiece.getPriceShape() != Tetrominoes.noShape){
            for (int i = 0; i < 4; i++) {
                int x  = currentX + currentPiece.x(i);
                int y = currentY - currentPiece.y(i);
                tetrisBoard.drawSquare(g, x * width , boardTop+ (getHeight() - y -1 ) * height , currentPiece.getPriceShape() );
            }
        }

    }

    public boolean isPaused() {
        return isPaused;
    }

    public boolean isStarted() {
        return isStarted;
    }
    public  void moveLeft(){
         tryMove(currentPiece , currentX-1 , currentY);
    }public  void moveRight(){
         tryMove(currentPiece , currentX+1 , currentY);
    }public  void rotateLeft(){
         tryMove(currentPiece.xoayTrai(), currentX , currentY);
    }public  void rotateRight(){
         tryMove(currentPiece.xoayPhai() , currentX , currentY);
    }

}
