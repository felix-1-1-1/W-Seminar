import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class AbortGame extends Buttons
{
    public void act() 
    {
        if (Greenfoot.mouseClicked(this)) {
            int score = ((Game) getWorld()).points;
            int trys = ((Game) getWorld()).trys;
            Greenfoot.setWorld(new EndScreen(score, trys));
        }
    }    
}
