import java.util.*;
import greenfoot.*; 
public class BigString extends Actor
{
    private GreenfootImage image;

    public BigString(int score)
    {
        image = new GreenfootImage(120,50);
        this.setImage(image);

        this.getImage().setFont(new Font("Helvetica",30));

        this.getImage().drawString(String.valueOf(score),0,30);

    }

    public BigString(int score, int size)
    {

        image = new GreenfootImage(120,50);

        this.setImage(image);

        this.getImage().setFont(new Font("Helvetica",size));

        this.getImage().drawString(String.valueOf(score), 0, size);
    }

    public void refresh(int score, int size)
    {
        this.getImage().clear();         
        this.getImage().drawString(String.valueOf(score),0,size);
    }
}
