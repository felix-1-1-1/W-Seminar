import greenfoot.*;

public class FlugObjekt extends SmoothMover {
    double percision;
    double cwA;
    double radius;
    double weight;
    double p;
    double dt; // Delta t
    double xMax; // Breite der Welt
    double yMax; // Höhe der Welt
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

    GreenfootImage dot= new GreenfootImage(2, 2);

    // Helper Slider Air Resistance
    double dragPercent;

    //Toggle functions
    boolean tracing;
    int posNeg;

    //Graphic Anchor 
    int baseX;
    int baseY;
    int gapSize;
    int i = 0;

    //experimental feature 

    boolean interruption;

    public FlugObjekt(String Style) {
        setStart();
        calc = new Calculations();
        tracing = true;

        interruption = false;

        GreenfootImage image = new GreenfootImage(Style);
        this.setImage(image);

        dot.fill();

    }
    public void act() {
        this.setLocation(0, yMax-sliderHeight.getValue()*10);
    }

    void tracingTogge(String state) {
        tracing = state == "on";

    }

    void toggleChecker() {
        for (Object obj : getWorld().getObjects(Toggle.class)) {
            Toggle tT = (Toggle) obj;
            tT.clicked();
            tT.imageChecker();
        }
    }

    void negPosTogge(String state) {
        if (state == "pos") {
            posNeg = 1;
        } else {
            posNeg = -1;
        }
    }

    void setSliders() {
        //Slider instanziieren und setzen
        int baseX = 1400;
        int baseY = 50;

        sliderHeight = new Slider();
        sliderHeight.showPercentage(false);
        sliderHeight.showValue(true);

        sliderHeight.setMaximumValue(70);
        sliderHeight.setValue(40);

        this.getWorld().addObject(sliderHeight, 1405, 180);

        sliderSpeed = new Slider();
        sliderSpeed.showPercentage(false);
        sliderSpeed.showValue(true);

        sliderSpeed.setMaximumValue(80);
        sliderSpeed.setValue(25);

        this.getWorld().addObject(sliderSpeed, 1405, 280);

        eFactor = new Slider();
        eFactor.showPercentage(true);
        eFactor.showValue(true);

        eFactor.setMaximumValue(100);
        eFactor.setValue(75);

        this.getWorld().addObject(eFactor, 1405, 380);

        sliderAngle = new Slider();
        sliderAngle.showPercentage(false);
        sliderAngle.showValue(true);

        sliderAngle.setMaximumValue(90);
        sliderAngle.setValue(45);

        this.getWorld().addObject(sliderAngle, 1405, 480);

        sliderDrag = new Slider();
        sliderDrag.showPercentage(false);
        sliderDrag.showValue(true);

        sliderDrag.setMaximumValue(1000);
        sliderDrag.setValue(100);

        this.getWorld().addObject(sliderDrag, 800, 50);

        sliderMass = new Slider();
        sliderMass.showPercentage(false);
        sliderMass.showValue(true);

        sliderMass.setMaximumValue(500);
        sliderMass.setValue(100);

        this.getWorld().addObject(sliderMass, 600, 50);

        sliderG = new Slider();
        sliderG.showPercentage(false);
        sliderG.showValue(true);

        sliderG.setMaximumValue(2000);
        sliderG.setValue(981);

        this.getWorld().addObject(sliderG, 400, 50);

        sliderPerc = new Slider();
        sliderPerc.showPercentage(false);
        sliderPerc.showValue(true);

        sliderPerc.setMaximumValue(1000);
        sliderPerc.setValue(25);

        this.getWorld().addObject(sliderPerc, 200, 50);

        this.setLocation(0, 700-sliderHeight.getValue()*10);
    }

    void setStart() {
        //Anfangswerte
        p = 1;

        //Grenzen des Hintergrunds
        xMax = 1300;
        yMax = 700;

        //Anker für Grafik
        baseX = 1400;
        baseY = 510;
        gapSize = 20;
    }

    void calculateValues(double v_n, double h_n, double winkel) // utilizes calc class
    {
        double startTime = System.nanoTime();
        masse = sliderMass.getValue();

        cwA = (sliderDrag.getValue()) / 100;

        double eF = eFactor.getValue() * 0.01;
        e = eF;

        double gN = -sliderG.getValue() / 100;
        g = gN;

        double pnN = sliderPerc.getValue();
        percision = pnN;

        double[] results; // required for calc class, transfer of information

        calc.setArgument(percision, cwA, masse, 1, v_n, h_n, g);

        //double percision, double cwA, double masse, double Luftdichte,double Geschwindigkeit, double hoehe, double g

        results = calc.optimalerWinkel();
        optimalAngle = results[0];
        calc.countSteps(Math.toRadians(winkel));
        // below: helper for calc

        calc.CalculateChartRelative(Math.toRadians(winkel), h_n, v_n);

        getWorld().showText(null, baseX - 235, baseY - 470 + gapSize);

        optimalA = calc.optimalerWinkel();
        // System.out.println("Optimaler Winkel: " + Math.toDegrees(optimalAngle)+"°");
        angle = calc.wK;
        distance = calc.actualDistance;
        lastArguments = calc.lA;
        speed = calc.vA;
        double endTime = System.nanoTime();
        getWorld().showText("Computing Time: "+runden((endTime-startTime)/1000000)+" ms", baseX - 235, baseY - 470 + gapSize);
        startTime = 0;
        endTime = 0;

    }

    public double runden(double z) {
        return Math.round(z * 100) / 100.0;
    }

    void stopTest(int i)
    {
        if(i>10)
        {
            interruption=true;}
    }
    double lastX=0;
    double lastY=0;
    double lastRot = 0;

    public void touchingObjectB() {  
        Targets Targets = (Targets) getOneIntersectingObject(Targets.class);
        //if (targets != null && targets.getScale() >= .5)
        //{
        getWorld().removeObject(Targets);
        //}
    }    

    void moveByValues(double sX, double v_n, double h_n, int winkel) {
        calculateValues(v_n, h_n, winkel);
        int times = angle.length;

        setLocation(sX, conversion(h_n * 10));
        setRotation(-winkel);
        // tracing
        E_0 = (0.5 * masse * v_n * v_n) + (masse * Math.abs(g) * h_n);

        for (int i = 0; i < times; i++) {
            // toggleChecker();
            //stopTest(i);
            if (this.getExactX() >= xMax) {

            } 
            else if(interruption)
            {
                this.setLocation(lastX, lastY);
                this.setRotation(lastRot);
            }else if (this.getExactY() < 0) {
                moveByValues(this.getExactX(), speed[i] * e, yMax / 10, (int) -Math.toDegrees(angle[i]));
            }

            else {
                // times = angle.length;
                if (i >= angle.length) {
                } else {
                    if (tracing) {
                        getWorld().getBackground().drawImage(dot, getX(), getY());
                    }

                    int angleRC = (int) (Math.round(Math.toDegrees(angle[i])));
                    setRotation(-Math.toDegrees(angle[i]));

                    GreenfootImage gI = this.getImage();

                    // if(i==0)
                    // {}
                    // else{
                    // gI.rotate((int)Math.toDegrees(angle[i])-(int)Math.toDegrees(angle[i-1]));}
                    //zoom factor
                    move(distance[i] * 10);
                    setRotation(0);
                    performaceMetrics(i, angleRC, speed[i]);
                    Greenfoot.delay(1);

                    lastX= this.getExactX();
                    lastY=this.getExactY();
                    lastRot=this.getExactRotation();

                    touchingObjectB();
                }
            }

        }
        bounce();
    }

    void bounce() // abpralllen
    {
        int lastAngle = (int) (Math.round(Math.toDegrees(Math.abs(lastArguments[1]))));
        double lastSpeed = lastArguments[0] * Math.cos(lastArguments[1]);

        // getImage().rotate(-lastAngle);

        // e Koeffizient

        //this.getExactX() >= xMax ||
        //|| lastSpeed < 0.5
        if (this.getExactX() >= xMax || Math.round( lastSpeed) == 0) {
        } else {
            moveByValues(getExactX(), e * lastArguments[0], 0, lastAngle);
            bounce();
        }
    }

    void run() {
        moveByValues(0, sliderSpeed.getValue(), sliderHeight.getValue(), sliderAngle.getValue() * posNeg);
        // double sX, double v_n, double h_n, int winkel
    }

    double startSpeed;
    double startHeight;
    double startAngle;

    void startingParamters()
    {
        if(sliderSpeed.getValue()>=0)
        {
            startSpeed=sliderSpeed.getValue();
        }
        else
        {
            startSpeed = 25;
        }
    }

    void printArray(double[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    double conversion(double y) {
        return -y + yMax;
    }

    void performaceMetrics(int i, double angle, double speed) {

        getWorld().showText(null, baseX, baseY);
        getWorld().showText("angle: " + angle + "°", baseX, baseY);

        getWorld().showText(null, baseX, baseY + gapSize);
        getWorld().showText("speed: " + runden(speed) + "m/s", baseX, baseY + gapSize);

        double height = (yMax - this.getExactY()) / 10;
        getWorld().showText(null, baseX, baseY + 2 * gapSize);
        getWorld().showText("height: " + runden(height) + "m", baseX, baseY + 2 * gapSize);

        double Ekin = Math.abs(0.5 * speed * speed * masse);
        double Epot = Math.abs(masse * g * height);
        double Eges = Ekin + Epot;

        getWorld().showText(null, baseX, baseY + 4 * gapSize);
        getWorld().showText("E_kin: " + runden(Ekin) + "J", baseX, baseY + 4 * gapSize);

        getWorld().showText(null, baseX, baseY + 5 * gapSize);
        getWorld().showText("E_pot: " + runden(Epot) + "J", baseX, baseY + 5 * gapSize);

        getWorld().showText(null, baseX, baseY + 6 * gapSize);
        getWorld().showText("E_ges: " + runden(Eges) + "J", baseX, baseY + 6 * gapSize);

        getWorld().showText(null, baseX, baseY + 7 * gapSize);
        getWorld().showText("E_0: " + runden(E_0) + "J", baseX, baseY + 7 * gapSize);

        getWorld().showText(null, baseX, baseY + 8 * gapSize);
        getWorld().showText("Ratio: " + Math.round((Eges / E_0) * 100) + "%", baseX, baseY + 8 * gapSize);

        getWorld().showText(null, baseX - 200, baseY - 500 + gapSize);
        getWorld().showText("Optimal Angle: " + optimalA[1] + "°", baseX - 200, baseY - 500 + gapSize);
    }

    void prepareText() {
        getWorld().showText(null, baseX - 235, baseY - 470 + gapSize);

        getWorld().showText("Computing Time: --- ms", baseX - 235, baseY - 470 + gapSize);

        getWorld().showText(null, baseX, baseY);
        getWorld().showText("angle: " + "---" + "°", baseX, baseY);

        getWorld().showText(null, baseX, baseY + gapSize);
        getWorld().showText("speed: " + "---" + "m/s", baseX, baseY + gapSize);

        getWorld().showText(null, baseX, baseY + 2 * gapSize);
        getWorld().showText("height: " + "---" + "m", baseX, baseY + 2 * gapSize);

        getWorld().showText(null, baseX, baseY + 4 * gapSize);
        getWorld().showText("E_kin: " + "---" + "J", baseX, baseY + 4 * gapSize);

        getWorld().showText(null, baseX, baseY + 5 * gapSize);
        getWorld().showText("E_pot: " + "---" + "J", baseX, baseY + 5 * gapSize);

        getWorld().showText(null, baseX, baseY + 6 * gapSize);
        getWorld().showText("E_ges: " + "---" + "J", baseX, baseY + 6 * gapSize);

        getWorld().showText(null, baseX, baseY + 7 * gapSize);
        getWorld().showText("E_0: " + "---" + "J", baseX, baseY + 7 * gapSize);

        getWorld().showText(null, baseX, baseY + 8 * gapSize);
        getWorld().showText("Ratio: " + "---" + "%", baseX, baseY + 8 * gapSize);

        getWorld().showText(null, baseX - 200, baseY - 500 + gapSize);
        getWorld().showText("Optimal Angle: " + "---" + "°", baseX - 200, baseY - 500 + gapSize);
    }

   
}
