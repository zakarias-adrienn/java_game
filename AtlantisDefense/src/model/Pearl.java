package model;

public class Pearl {
    private static int life = 100;
    private static int x = 0;
    private static int y = 0;
    
    public static int getLife(){
        return life;
    }
    
    public static void decreaseLife(){
        life -= 10;
        if (life <= 0)
        {
            Model.gameOver();
        }
    }
    
    public static void setX(int xx){
        x = xx;
    }
    
    public static void setY(int yy){
        y = yy;
    }
    
    public static int getX(){
        return x;
    }
    
    public static int getY(){
        return y;
    }
    
    public static void setLife(int life){
        Pearl.life = life;
    }
}
