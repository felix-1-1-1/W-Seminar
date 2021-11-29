import greenfoot.*;  
public class DeleteTrace extends Buttons {
    public void act() {
        clicked();
    }
    GreenfootImage image = new GreenfootImage("devBackground.png");
    void clicked() {
        if (Greenfoot.mouseClicked(this)) {
            getWorld().setBackground("devBackground.png");
        }
    }
}
