package model;

public class Shape  {
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
    public   void setHinhDangShape(Tetrominoes priceShape){
        for (int i = 0; i < 4; i++) {
            // sao chep toaDo tu bangToaDo sang toaDo;
            System.arraycopy(bangToaDo[priceShape.ordinal()][i] , 0 , toaDo[i] , 0  , 2);

        }
        this.priceShape = priceShape;
    }
    public  void setX(int index , int x){ toaDo[index][0] = x;}
    public  void setY(int index , int y){toaDo[1][index]= y ; }
    public int x(int index){
        return toaDo[index][0];
    }
    public int y(int index){
        return  toaDo[1][index];
    }



}
