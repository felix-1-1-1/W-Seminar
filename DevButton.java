import greenfoot.*;  
public class DevButton extends Buttons
{

    public void act() 
    {
         if (Greenfoot.mouseClicked(this)) {
           Greenfoot.setWorld(new DevMode());
        }
    }    
}
