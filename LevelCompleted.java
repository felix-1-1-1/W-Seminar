
import greenfoot.*;  

public class LevelCompleted extends World
{

    int level;
    int points;
    int trys;
    public LevelCompleted(int level,int points, int trys)
    {    
        super(1500, 700, 1); 

       
        this.level=level;
        this.points = points;
        this.trys = trys;

        ContinueButton continueButton = new ContinueButton( level, points,  trys);
        addObject(continueButton,707,404);
    }

    void setWorld(int level,int points, int trys)
    {
        Greenfoot.setWorld(new Game(level, points,trys));
    }

}
