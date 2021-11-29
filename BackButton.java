import greenfoot.*; 
public class BackButton extends Buttons
{

    public void act() 
    {
         if (Greenfoot.mouseClicked(this)) {
           Greenfoot.setWorld(new StartScreen());
        }
    } 

}
