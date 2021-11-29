import greenfoot.*;

public class NegSwitchGame extends Buttons {
    String state;

    public NegSwitchGame() {
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
            for (Object obj : getWorld().getObjects(GameCharacter.class)) {
                GameCharacter gC = (GameCharacter) obj;
                gC.negPosTogge(state);
            }
        } else {
            setImage("negative.png");
            for (Object obj : getWorld().getObjects(GameCharacter.class)) {
                GameCharacter gC = (GameCharacter) obj;
                gC.negPosTogge(state);
            }
        }
    }
}
