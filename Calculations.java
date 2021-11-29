import greenfoot.*;

import java.lang.Math;

/**
 * Berechnung der Flugbahn mithilfe numerischer Loesung
 */
public class Calculations extends Actor {

    // Variablen definieren
    double g;
    double cwA;
    double r;
    double m;
    double p;
    double v_0;
    double h_0;
    double t;
    double k_m;
    int counter;

    double[] wK; // winkel
    double[] vA;
    double[] lA; // Werte am Ende
    double[] actualDistance;

    // Variablen anhand von User Input Werte zuweisen
    public void setArgument(double percision, double cwA, double masse, double Luftdichte,
    double Geschwindigkeit, double hoehe, double g) {
        this.t = 1 / percision;
        this.cwA = cwA;
        this.m = masse;
        this.p = Luftdichte;
        this.v_0 = Geschwindigkeit;
        this.h_0 = hoehe;
        this.g = g;
        double pi = Math.PI;
        counter = 0;

        k_m = (0.5 * cwA * p) / m;

        if (h_0 <= 0 && k_m != 0) {
            h_0 = 0.00001;
        }
    }

    // Wurfweite ausgeben mit Winkel als Parameter
    double wurfweiteBerechnen(double winkel) {
        if (h_0 <= 0) {
            h_0 = 0.00001;
        }

        double x = 0;
        double y = h_0;
        double V = v_0;
        double vX = Math.cos(winkel) * v_0;
        double vY = Math.sin(winkel) * v_0;

        while (y > 0) {
            double aX = V * V * k_m * Math.cos(winkel) *Math.cos(winkel)* -1;
            double aY = Math.signum(vY)*V * V * k_m * Math.sin(winkel) *Math.sin(winkel) * -1 + g; 

            x += 0.5 * aX * t * t + vX * t;
            y += 0.5 * aY * t * t + vY * t;
            vX += t * aX;
            vY += t * aY;
            winkel = Math.atan(vY / vX);
            V = Math.sqrt(vX * vX + vY * vY);
        }
        return x;
    }

    //berechnet wie viele Stellen das Array haben muss 
    void countSteps(double winkel) {
        double x = 0;
        double y = h_0;
        double V = v_0;
        double vX = Math.cos(winkel) * v_0;
        double vY = Math.sin(winkel) * v_0;

        while (y > 0) {
            double aX;
            if(vX==0)
            { aX=0;}
            else{ aX = V * V * k_m * Math.cos(winkel) *Math.cos(winkel)* -1;}
            double aY = Math.signum(vY)*V * V * k_m * Math.sin(winkel) *Math.sin(winkel) * -1 + g; 

            x += 0.5 * aX * t * t + vX * t;
            y += 0.5 * aY * t * t + vY * t;

            vX += t * aX;
            vY += t * aY;

            winkel = Math.atan(vY / vX);
            V = Math.sqrt(vX * vX + vY * vY);
            counter++;
        }
    }

    // Optimalen Winkel (DEG/RAD) + zugehoerige Wurfweite in einem double Array ausgeben
    double[] optimalerWinkel() {
        //increase speed of calculation 

        t = t * 1.25;

        double[] ergebnisse = new double[3];
        double optimalerWinkel = -1;
        double vergleich = -1;

        if (k_m == 0) {
            double sqrt;

            sqrt = Math.sqrt((((2 * -g * h_0) / (v_0 * v_0))) + 2);

            optimalerWinkel = Math.asin(1 / (sqrt));
            vergleich = wurfweiteBerechnen(optimalerWinkel);

            ergebnisse[0] = runden(optimalerWinkel);
            ergebnisse[1] = runden(Math.toDegrees(optimalerWinkel));
            ergebnisse[2] = runden(vergleich);

            return ergebnisse;

        } else {
            for (double i = 0; i < Math.PI / 2; i += 0.001) {
                double Wurfweite = wurfweiteBerechnen(i);
                if (Wurfweite > vergleich) {
                    vergleich = Wurfweite;
                    optimalerWinkel = i;

                }
            }

            ergebnisse[0] = runden(optimalerWinkel);
            ergebnisse[1] = runden(Math.toDegrees(optimalerWinkel));
            ergebnisse[2] = runden(vergleich);

            return ergebnisse;
        }

    }

    public double runden(double z) {
        return Math.round(z * 100) / 100.0;
    }
    
  

    //Winkel und Bewegungsdistanz werden  mit dieser Methode bestimmt 
    void CalculateChartRelative(double winkel, double h, double v) {
        wK = new double[counter]; //winkel
        vA = new double[counter]; //geschwindigkeit 
        lA = new double[2];        //letzte Werte
        actualDistance = new double[counter];

        double x = 0;
        double y = h;
        double V = v;
        double vX = Math.cos(winkel) * v_0;
        double vY = Math.sin(winkel) * v_0;

        int aI = 0;
        if (h_0 <= 0) {
            h_0 = 0.00001;
        }

        while (y >= 0 && aI < counter) {
            double aX;
            //Beschleunigungen berechnen, vgl. Gleichung 2.7, 2.8 und 2.9
            if(vX==0)
            { aX=0;}
            else{ aX = V * V * k_m * Math.cos(winkel) *Math.cos(winkel)* -1;}
            double aY = Math.signum(vY)*V * V * k_m * Math.sin(winkel) *Math.sin(winkel) * -1 + g; 
            //Distanz berechnen, vgl. Gleichung 2.12 und 2.13
            double dx = 0.5 * aX * t * t + vX * t;                                
            double dy = 0.5 * aY * t * t + vY * t;
            //Tatsaechlich zurueckgelegt, vgl. Gleichung 2.17
            double aD = Math.sqrt(dx * dx + dy * dy); 
            if (y < 0) //Falls das Objekt den Boden beruehrt 
            {
                break;
            }
            //Neue Geschwindigkeiten berechnen, vgl. Gleichung 2.15 und 2.16
            vX += t * aX; 
            vY += t * aY;
            //Neuen winkel berechnen, vgl. Gleichung 2.10
            winkel = Math.atan(vY / vX); 
            //Speichern und uebertragen, vgl. Array 2.18
            wK[aI] = winkel; 
            actualDistance[aI] = aD;
            //Neue Geschwindigkeit berechnen, vgl. Gleichung 2.11
            V = Math.sqrt(vX * vX + vY * vY); 
            vA[aI] = V;
            aI++;
        }
        //Letzten Argumente werden weitergegeben um ein abprallen zu ermoeglichen 
        lA[0] = V;
        lA[1] = winkel;
    }

    void printArray(double[] array)
    {
        for(int i=0; i<array.length; i++){
            System.out.print(array[i]+" ");
        }
        System.out.println("");
    }
}