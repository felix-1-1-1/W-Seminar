import greenfoot.*;  
public class GuideButtonEN extends Buttons
{
 
    public void act() 
    {
         if (Greenfoot.mouseClicked(this)) {
           Greenfoot.setWorld(new Guide("EN"));
        }
    }    
}
