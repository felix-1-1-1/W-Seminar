import greenfoot.*;

public class NegSwitch extends Buttons {
    String state;

    public NegSwitch() {
        state = "pos";
    }

    public void act() {
        imageChecker();
        clicked();
    }

    void clicked() {
        if (Greenfoot.mouseClicked(this)) {
            if (state == "pos") {
                state = "neg";
            } else {
                state = "pos";
            }
        }
    }

    void imageChecker() {
        if (state == "pos") {
            setImage("positive.png");
            for (Object obj : getWorld().getObjects(FlugObjekt.class)) {
                FlugObjekt FJ = (FlugObjekt) obj;
                FJ.negPosTogge(state);
            }
        } else {
            setImage("negative.png");
            for (Object obj : getWorld().getObjects(FlugObjekt.class)) {
                FlugObjekt FJ = (FlugObjekt) obj;
                FJ.negPosTogge(state);
            }
        }
    }
}
