package models;

/**
 * Created by Guest on 7/31/17.
 */
public class Parcel {
    public Double width;
    public Double length;
    public Double height;
    public Double weight;
    public boolean isWrapped;

    public Parcel(Double width, Double length, Double height, Double weight){
        this.width = width;
        this.length = length;
        this.height = height;
        this.weight = weight;
        this.isWrapped = false;
    }

    public Double volume(){
        return (width * length * height);
    }

    public Double costToShip(String speed, Double distance){
        if (speed.equals("Standard")) {
            return ((distance*.02) + (this.volume()/500) + this.weight);
        } else if(speed.equals("Express")) {
            return ((distance*.02) + (this.volume()/100) + this.weight*1.5);
        } else {
            System.out.println("Invalid Shipping Parameter");
            return 0.0;
        }
    }

    public Double giftWrapCost(){
        return ((width*height*2) + (length*width*2) + (height*length*2))/100;
    }

}
