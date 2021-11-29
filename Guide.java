import greenfoot.*;  
public class Guide extends World
{

    public Guide(String language)
    {    
        super(1500, 700, 1); 
        
        setBackground("guide"+language+".png");
        BackButton BackButton = new BackButton();
        addObject(BackButton,153,630);
    }

}
