import greenfoot.*;
public class GameCharacter extends SmoothMover {
    double precision;
    double cwA;
    double radius;
    double weight;
    double dt; // Delta t
    double xMax; // Breite der Welt
    double yMax; // Hoehe der Welt
    double masse;
    double optimalAngle;
    double g;
    double e;
    double E_0;

    // Konstanten, Physikalische Parameter

    Calculations calc;

    // Ergebnisse:
    double[] angle;
    double[] distance;
    double[] lastArguments;
    double[] speed;
    double[] optimalA;

    // slider
    Slider sliderHeight;
    Slider sliderSpeed;
    Slider eFactor;
    Slider sliderAngle;
    Slider sliderDrag;
    Slider sliderMass;
    Slider sliderG;
    Slider sliderPerc;

    // Helper Slider Air Resistance
    double dragPercent;

    //Toggle functions
    boolean tracing;
    int posNeg;

    //Grafik Bezugspunkte
    int baseX;
    int baseY;
    int gapSize;
    int i = 0;

    GreenfootImage dot= new GreenfootImage(2, 2);

    //Konsturktor und Vorbereitung 
    public GameCharacter() {
        setStart();
        calc = new Calculations();
        tracing = true;
        dot.fill();
    }

    public void act()//Höhe und Winkel kontinuierlich aktualisieren 
    {   this.setLocation(30, yMax-sliderHeight.getValue()*10);
        this. setRotation(-sliderAngle.getValue()*posNeg);
    }

    void tracingTogge(String state) {
        tracing = state == "on";
    }

    //negativer bzw. positiver Winkel
    void negPosTogge(String state) {
        if (state == "pos") {
            posNeg = 1;
        } else {
            posNeg = -1;
        }
    }

    void prepareGameControls() //Steuerungselemente vorbereiten 
    {
        this.getWorld().addObject(sliderHeight, 1400, 477);
        this.getWorld().addObject(sliderSpeed, 1400, 215);
        this.getWorld().addObject(sliderAngle, 1400, 346);
        this.getWorld().addObject(sliderDrag, 1400, 621);
    }

    void setSliders() {
        int baseX = 1200;
        int baseY = 50;

        sliderHeight = new Slider();
        sliderHeight.showPercentage(false);
        sliderHeight.showValue(true);

        sliderHeight.setMaximumValue(58);
        sliderHeight.setValue(30);

        sliderSpeed = new Slider();
        sliderSpeed.showPercentage(false);
        sliderSpeed.showValue(true);

        sliderSpeed.setMaximumValue(80);
        sliderSpeed.setValue(20);

        //fuer Vereinfachung entfernt
        /* 
        eFactor = new Slider();
        eFactor.showPercentage(true);
        eFactor.showValue(true);

        eFactor.setMaximumValue(100);
        eFactor.setValue(75);

        this.getWorld().addObject(eFactor, 1205, 380);
         */

        sliderAngle = new Slider();
        sliderAngle.showPercentage(false);
        sliderAngle.showValue(true);

        sliderAngle.setMaximumValue(90);
        sliderAngle.setValue(30);

        sliderDrag = new Slider();
        sliderDrag.showPercentage(false);
        sliderDrag.showValue(true);

        sliderDrag.setMaximumValue(1000);
        sliderDrag.setValue(100);

        sliderMass = new Slider();
        sliderMass.showPercentage(false);
        sliderMass.showValue(true);

        sliderMass.setMaximumValue(500);
        sliderMass.setValue(50);

        sliderG = new Slider();
        sliderG.showPercentage(false);
        sliderG.showValue(true);

        sliderG.setMaximumValue(2000);
        sliderG.setValue(981);

        sliderPerc = new Slider();
        sliderPerc.showPercentage(false);
        sliderPerc.showValue(true);

        sliderPerc.setMaximumValue(200);
        sliderPerc.setValue(25);

        this.setLocation(0, yMax-sliderHeight.getValue()*10);
    }

    void setStart() {

        //boundaries
        xMax = 1245;
        yMax = 550;

        baseX = 1200;
        baseY = 520;
        gapSize = 20;
    }

    void calculateValues(double v_n, double h_n, double winkel) // Verbindung mit Calculations Klasse
    {
        masse = sliderMass.getValue();

        cwA = (sliderDrag.getValue()) / 100*0.5*0.283;

        double eF = 0.90;
        e = eF;

        double gN = -sliderG.getValue() / 100;
        g = gN;

        double pnN = sliderPerc.getValue();
        precision = pnN;

        double[] results; // Übertragene Ergebnisse speichern 

        calc.setArgument(precision, cwA, masse, 1, v_n, h_n, g); //Anfangswerte übertragen 

        calc.countSteps(Math.toRadians(winkel)); //Schritte zählen 

        calc.CalculateChartRelative(Math.toRadians(winkel), h_n, v_n); //berechnung der Flugbahn

        angle = calc.wK;
        distance = calc.actualDistance;
        lastArguments = calc.lA;
        speed = calc.vA;

    }

    double lastX=0;
    double lastY=0;
    double lastRot = 0;

    //knochen beruehren
    public void touchingObjectB() {  
        Targets Targets = (Targets) getOneIntersectingObject(Targets.class);

        if (Targets != null) 
        {
            Targets.changeImage();
        }

    }    

    //"Herzstueck": Bewegung nach dem Array
    void moveByValues(double sX, double v_n, double h_n, int winkel) {
        calculateValues(v_n, h_n, winkel); //Berechnung des Arrays 
        int times = angle.length; //Anzahl der Schritte

        setLocation(sX, conversion(h_n * 10)); //Anfangsort festlegen
        setRotation(-winkel); //Anfangswinkel festlegen (in Java Winkel vertauscht)

        for (int i = 0; i < times; i++) {

            if (this.getExactX() >= xMax) {//Grenzen ueberpruefen
            } 
            else if (this.getExactY() < 0) { //Auf dem Boden aufgekommen
                moveByValues(this.getExactX(), speed[i] * e, yMax / 10, (int) -Math.toDegrees(angle[i]));
            }
            else {
                if (i >= angle.length){ //Out of Bounds catch
                } else {

                    getWorld().getBackground().drawImage(dot, getX()-1, getY()-1);

                    setRotation(-Math.toDegrees(angle[i])); //Neigung einstellen
                    move(distance[i] * 10); //Bewegen um bestimmte Distanz
                    Greenfoot.delay(1);
                    lastX= this.getExactX();//letzte Werte speichern fuer abprallen
                    lastY=this.getExactY();
                    lastRot=this.getExactRotation();

                    touchingObjectB(); //ueberpruefen ob Objekt beruehrt wurde
                }
            }

        }
        bounce();
    }

    void bounce() // abpralllen
    {
        setRotation(0);
        int lastAngle = (int) (Math.round(Math.toDegrees(Math.abs(lastArguments[1]))));
        double lastSpeed = lastArguments[0] * Math.cos(lastArguments[1]);

        if (this.getExactX() >= xMax || Math.round( lastSpeed) == 0) {
        } else {
            moveByValues(getExactX(), e * lastArguments[0], 0, lastAngle);
            bounce();
        }
    }

    //Prozess in Gang setzen
    void run() { 
        moveByValues(30, sliderSpeed.getValue(), sliderHeight.getValue(), sliderAngle.getValue()*posNeg);
        // double sX, double v_n, double h_n, int winkel
    }

    double startSpeed;
    double startHeight;
    double startAngle;


    double conversion(double y) { //Koordinaten uebersetzen
        return -y + yMax;
    }


    public double runden(double z) {
        return Math.round(z * 100) / 100.0;
    }



}
