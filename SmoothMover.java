import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)
 
/**
 * A variation of an actor that maintains a precise location and rotation (using doubles for the co-ordinates
 * instead of ints).  This allows small precise movements (e.g. movements of 1 pixel or less)
 * that do not lose precision.
 * 
 * @author Poul Henriksen
 * @author Michael Kolling
 * @author Neil Brown
 * 
 * @author Exact rotation: TheGoldenProof
 * 
 * @version 3.0
 */
public abstract class SmoothMover extends Actor
{
    private double exactX;
    private double exactY;
     
    private double exactRot;
 
    /**
     * Move forward by the specified distance.
     * (Overrides the method in Actor).
     */
    @Override
    public void move(int distance)
    {
        move((double)distance);
    }
     
    /**
     * Move forward by the specified exact distance.
     */
    public void move(double distance)
    {
        double radians = Math.toRadians(getExactRotation());
        double dx = Math.cos(radians) * distance;
        double dy = Math.sin(radians) * distance;
        setLocation(exactX + dx, exactY + dy);
    }
     
    /**
     * Set the location using exact coordinates.
     */
    public void setLocation(double x, double y) 
    {
        exactX = x;
        exactY = y;
        super.setLocation((int) (x + 0.5), (int) (y + 0.5));
    }
     
    /**
     * Set the location using integer coordinates.
     * (Overrides the method in Actor.)
     */
    @Override
    public void setLocation(int x, int y) 
    {
        setLocation((double)x, (double)y);
    }
     
    /**
     * Set the rotation using exact degrees.
     * 
     * Added by TheGoldenProof
     */
    public void setRotation(double degrees) {
        exactRot = degrees;
        super.setRotation((int)(Math.round(degrees)%360));
    }
     
    /**
     * Set the rotation using integer degrees.
     * (Overrides the method in Actor.)
     * 
     * Added by TheGoldenProof
     */
    @Override
    public void setRotation(int degrees) {
        setRotation((double)degrees);
    }
 
    /**
     * Return the exact x-coordinate (as a double).
     */
    public double getExactX() 
    {
        return exactX;
    }
 
    /**
     * Return the exact y-coordinate (as a double).
     */
    public double getExactY() 
    {
        return exactY;
    }
     
    /**
     * Return the exact rotation (as a double).
     * 
     * Added by TheGoldenProof
     */
    public double getExactRotation() {
        return exactRot;
    }
}