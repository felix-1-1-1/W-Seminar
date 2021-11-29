import greenfoot.*;

public class Game extends World {
    GameCharacter GameCharacter;

    int level;
    int trys;
    int points;

    BigString BsPoints; 
    BigString BsLevel;
    BigString BsTrys;
    BigString BsCurrentScore;

    public Game(int level, int points, int trys) {
        super(1500, 700, 1);

        Greenfoot.setSpeed(55);

        GameCharacter =  new GameCharacter();

        this.level=level;
        this.points = points;
        this.trys = trys;

        prepareUser();
        prepareSlider();
        prepareLevel(level);

        BsPoints = new BigString(points, 18);
        addObject(BsPoints, 517,54);

        BsLevel = new BigString(level, 18);
        addObject(BsLevel, 178,54);

        BsTrys = new BigString(trys, 18);
        addObject(BsTrys, 352,54);

        double score = 0;
        if(trys == 0)
        {score=0;}
        else
        { score =Math.round((points*points*10)/trys);}

        BsCurrentScore = new BigString((int)score, 18);
        addObject(BsCurrentScore, 781,57);

    }
    public void act()
    {
        checkLevel();
        showMetrics(level);
    }

    void run()
    {
        trys++;
        showMetrics(level);
        checkScore();
        GameCharacter.run();
    }

    void prepareUser()
    {
        int baseX = 1150;
        int baseY = 50;
        int distance = 20;

        addObject(GameCharacter, baseX, baseY);

        StartButton StartButton = new StartButton();
        addObject(StartButton,1396, 104);

        NegSwitchGame NegSwitchGame = new NegSwitchGame();
        addObject(NegSwitchGame, 1453, 384);

        AbortGame AbortGame = new AbortGame();
        addObject(AbortGame,342, 656);

        BackButtonInverted BackButtonInverted = new BackButtonInverted();
        addObject(BackButtonInverted,112,656);

    }

    void points()
    {
        points++;
        BsPoints.refresh(points, 18);
        checkScore();
    }

    void prepareSlider() {
        for (Object obj : this.getObjects(GameCharacter.class)) {
            GameCharacter FJ = (GameCharacter) obj;
            FJ.setSliders();
            FJ.prepareGameControls();
        }
    }

    void showMetrics(int activeLevel)
    {
        BsTrys.refresh(trys, 18);
    }

    void checkScore()
    {
        double score = 0;
        if(trys == 0)
        {score=0;}
        else
        { score =Math.round((points*points*10)/trys);}
        BsCurrentScore.refresh((int)score,18);
    }

    void checkLevel()
    {
        if(level ==1 && points == 7 )
        {Greenfoot.setWorld(new LevelCompleted(2, points, trys));}
        else if(level == 2 && points == 14)
        {Greenfoot.setWorld(new LevelCompleted(3, points, trys));}
        else if(level ==3 && points == 21)
        {Greenfoot.setWorld(new LevelCompleted(4, points, trys));}
        else if(level ==4&&points == 28)
        {Greenfoot.setWorld(new LevelCompleted(5, points, trys));}
        else if(level ==5 && points == 35)
        {Greenfoot.setWorld(new LevelCompleted(6, points, trys));}
        else if(level == 6 && points == 42)
        {Greenfoot.setWorld(new LevelCompleted(7, points, trys));}
        else if(level == 7 && points == 49)
        {Greenfoot.setWorld(new LevelCompleted(8, points, trys));}
        else if(level == 8 && points == 56)
        {Greenfoot.setWorld(new LevelCompleted(9, points, trys));}
        else if(level == 9 &&points == 63)
        {Greenfoot.setWorld(new LevelCompleted(10, points, trys));}
        else if(level == 10 &&points == 70)
        {Greenfoot.setWorld(new EndScreen(points,trys));}
    }

    void prepareLevel(int activeLevel)
    {
        Targets Targets;

        if(activeLevel == 1)
        {
            addObject(new Targets(), 107, 221);
            addObject(new Targets(), 311, 216);
            addObject(new Targets(), 483,318 );
            addObject(new Targets(), 837, 351);
            addObject(new Targets(), 664, 554);
            addObject(new Targets(), 999, 268);
            addObject(new Targets(), 1202, 355);

        }
        else if(activeLevel == 2)
        {addObject(new Targets(), 188, 296);
            addObject(new Targets(), 377, 266);
            addObject(new Targets(), 545,258 );
            addObject(new Targets(), 735, 276);
            addObject(new Targets(), 907, 316);
            addObject(new Targets(), 1035, 360);
            addObject(new Targets(), 1204, 465);}
        else if(activeLevel == 3)
        {addObject(new Targets(), 123, 159);
            addObject(new Targets(), 240, 346);
            addObject(new Targets(), 365,475 );
            addObject(new Targets(), 570, 123);
            addObject(new Targets(), 849, 99);
            addObject(new Targets(), 1048, 417);
            addObject(new Targets(), 1209, 306);}
        else if(activeLevel == 4)
        {addObject(new Targets(), 94, 389);
            addObject(new Targets(), 332, 12);
            addObject(new Targets(), 449,169 );
            addObject(new Targets(), 699, 320);
            addObject(new Targets(), 877, 68);
            addObject(new Targets(), 1236, 243);
            addObject(new Targets(), 561, 393);}
        else if(activeLevel == 5)
        {addObject(new Targets(), 198, 167);
            addObject(new Targets(), 468, 87);
            addObject(new Targets(), 694,172 );
            addObject(new Targets(), 773, 277);
            addObject(new Targets(), 833, 444);
            addObject(new Targets(), 979, 275);
            addObject(new Targets(), 1217, 287);}
        else if(activeLevel == 6)
        {addObject(new Targets(), 161, 562);
            addObject(new Targets(), 381, 15);
            addObject(new Targets(), 620,561 );
            addObject(new Targets(), 714, 297);
            addObject(new Targets(), 839, 13);
            addObject(new Targets(), 1083, 556);
            addObject(new Targets(), 1246, 118);}
        else if(activeLevel == 7)
        {addObject(new Targets(), 209, 552);
            addObject(new Targets(), 365, 304);
            addObject(new Targets(), 555,21 );
            addObject(new Targets(), 756, 301);
            addObject(new Targets(), 885, 552);
            addObject(new Targets(), 1036, 262);
            addObject(new Targets(), 1139, 87);}
        else if(activeLevel == 8)
        {addObject(new Targets(), 159, 536);
            addObject(new Targets(), 300, 536);
            addObject(new Targets(), 500,536 );
            addObject(new Targets(), 700, 536);
            addObject(new Targets(), 900, 536);
            addObject(new Targets(), 1100, 536);
            addObject(new Targets(), 1200, 536);}
        else if(activeLevel == 9)
        {addObject(new Targets(), 144, 475);
            addObject(new Targets(), 276, 397);
            addObject(new Targets(), 429,337 );
            addObject(new Targets(), 627, 302);
            addObject(new Targets(), 820, 315);
            addObject(new Targets(), 1056, 395);
            addObject(new Targets(), 1200, 500);}
        else if(activeLevel == 10)
        {addObject(new Targets(), 143, 553);
            addObject(new Targets(), 263, 480);
            addObject(new Targets(), 547,553 );
            addObject(new Targets(), 727, 505);
            addObject(new Targets(), 902, 558);
            addObject(new Targets(), 1080, 544);
            addObject(new Targets(), 1241, 561);}
    }

}
