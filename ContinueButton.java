import greenfoot.*;  
public class ContinueButton extends Buttons
{
    int level;
    int points;
    int trys;
    public ContinueButton( int level,int points,int trys)
    {   this.level=level;
        this.points = points;
        this.trys = trys;
    }

    public void act() 
    {
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(new Game(level,points,trys));
        }
    }    
}
