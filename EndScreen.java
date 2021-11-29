import greenfoot.*; 

public class EndScreen extends World
{

    int points;
    int trys;
    public EndScreen(int points, int trys)
    {    
        super(1500, 700, 1); 

        this.points = points;

        this.trys = trys;

        prepare();
    }
    double score ;
    private void prepare()
    {

        if(trys == 0)
        {score=0;}
        else
        { score =Math.round((points*points*10)/trys);}

        BackButtonInverted backButtonInverted = new BackButtonInverted();
        addObject(backButtonInverted,131,652);

        BigString BigString = new BigString((int)score);

        addObject(BigString,392,228);
    }

}