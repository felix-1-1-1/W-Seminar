import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class BackButtonInverted extends Buttons
{
    
    public void act() 
    {
         if (Greenfoot.mouseClicked(this)) {
           Greenfoot.setWorld(new StartScreen());
        }
    } 
    
    
}
