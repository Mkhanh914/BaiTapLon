package model;

import java.io.Serializable;
import java.util.Random;

public  class Shape implements Serializable {
    public enum Tetrominoes {
        noShape ,  zShape , tShape  , sShape , lShape  , squareShape , lShape1  , lineShape ;
    }
    private Tetrominoes  priceShape ;
    private  int[][] toaDo ;
    private  int[][][] bangToaDo;

    public Shape() {
        //  gọi tọa độ của từng khối của shape;
        toaDo   = new int[4][2];
        //
        bangToaDo  = new int[][][]{
                {{0, 0}, {0, 0}, {0, 0}, {0, 0}},
                {{0, -1}, {0, 0}, {-1, 0}, {-1, 1}},
                {{0, -1}, {0, 0}, {1, 0}, {1, 1}},
                {{0, -1}, {0, 0}, {0, 1}, {0, 2}},
                {{-1, 0}, {0, 0}, {1, 0}, {0, 1}},
                {{0, 0}, {1, 0}, {0, 1}, {1, 1}},
                {{-1, -1}, {0, -1}, {0, 0}, {0, 1}},
                {{1, -1}, {0, -1}, {0, 0}, {0, 1}}
        };
        setHinhDangShape(priceShape.noShape);
    }

    public Tetrominoes getPriceShape() {
        return priceShape;
    }

    public   void setHinhDangShape(Tetrominoes priceShape){
        for (int i = 0; i < 4; i++) {
            // sao chep toaDo tu bangToaDo sang toaDo;
            System.arraycopy(bangToaDo[priceShape.ordinal()][i] , 0 , toaDo[i] , 0  , 2);

        }
        this.priceShape = priceShape;
    }
    public  void setX(int index , int x){ toaDo[index][0] = x;}
    public  void setY(int index , int y){toaDo[index][1]= y ; }
    public int x(int index){
        return toaDo[index][0];
    }
    public int y(int index){
        return  toaDo[index][1];
    }
    //  ham minX , minY dung de tim gia tri toa do nho nhat trong cac khoi hinh
    public  int minX(){
        int  m =  toaDo[0][0];
        for(int i = 0 ; i<4 ; i++){
                m  = Math.min( m ,toaDo[i][0]);
        }
        return m;
    }
    public  int minY(){
        int m = toaDo[0][1];
        for (int i = 0; i < 4; i++) {
            m = Math.min(m , toaDo[i][1]);
        }
        return  m ;
    }
    // ham xoay trai , xoayphai jup cho khoi hinh toa hinh dang khac nhau cua khoi hinh
    public  Shape xoayTrai(){
        if (priceShape ==  Tetrominoes.squareShape) {
            return this;
        }
        Shape shape = new Shape();
        shape.priceShape = priceShape;
        for(int i = 0 ; i<4 ;  i++){
                shape.setX(i , y(i));
                shape.setY(i , -x(i));
        }
        return shape;
    }
    public  Shape xoayPhai(){
        if (priceShape ==  Tetrominoes.squareShape) {
            return this;
        }
        Shape shape = new Shape();
        shape.priceShape = priceShape;
        for(int i = 0 ; i<4 ;  i++){
            shape.setX(i , -y(i));
            shape.setY(i , x(i));
        }
        return shape;
    }
    //  ham random khoi hinh trong enum Tetrominose
    public void setRanDomShape(){
        Random random = new Random();

        int  x =  Math.abs(random.nextInt()) % 7 + 1 ;
        Tetrominoes[] values  =  Tetrominoes.values();
        setHinhDangShape(values[x]);

    }



}
