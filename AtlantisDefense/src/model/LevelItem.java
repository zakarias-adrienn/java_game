package model;

public enum LevelItem {
    SAND(0), TOWER_PLACE(2), ROUTE(1), PEARL(3);
    LevelItem(int rep){ representation = rep; }
    public final int representation;
    int x, y;
//    public int getX(){
//        return x;
//    }
//    public int getY() {
//        return y;
//    }
//    public void setX(int x){
//        this.x = x;
//    }
//    public void setY(int y){
//        this.y = y;
//    }
    
}
