package projectt12g03.model.game.elements;

public class Laser extends Element{
    private int ACTIVE_TIME = 15;
    private int REST_TIME = 45;

    private String direction;
    private int active;
    private int resting;

    public Laser(double x, double y,String direction) {
        super(x, y);
        setDirection(direction);
        setActive(0);
        setResting(REST_TIME);
    }

    public String getDirection() {return direction;}

    public void setDirection(String direction) { this.direction = direction;}

    public int getActive() { return active;}

    public void setActive(int active) { this.active = active;}

    public int getResting() { return resting;}

    public void setResting(int resting) { this.resting = resting;}

    public void reduceResting(){this.resting--;}

    public void reduceActive(){this.active--;}

    public void changeMode(String name) {
        if(name.equals("ACTIVE")){
            setActive(ACTIVE_TIME);
        }
        if(name.equals("Resting")){
            setResting(REST_TIME);
        }
    }
}
