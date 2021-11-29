import greenfoot.*;  
public class GuideButtonDE extends Buttons
{
  
    public void act() 
    {
         if (Greenfoot.mouseClicked(this)) {
           Greenfoot.setWorld(new Guide("DE"));
        }
    }    
}
