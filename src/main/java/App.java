import models.Parcel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Guest on 7/31/17.
 */
public class App {

    private static DecimalFormat df = new DecimalFormat("#0.00");

    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to the Shipping Store!");
        System.out.println(df.format(Double.valueOf(7.625)));
        ArrayList<Parcel> allParcels = new ArrayList<Parcel>();
        boolean programRunning = true;

        while(programRunning){
            System.out.println("Please select from the following options: Add Parcel, Ship Order, or Exit");

            try{

                String navChoice = bufferedReader.readLine();

                if(navChoice.equals("Add Parcel")){
                    System.out.println("Please enter the width of your parcel: ");
                    Double width = Double.parseDouble(bufferedReader.readLine());
                    System.out.println("Please enter the length of your parcel: ");
                    Double length = Double.parseDouble(bufferedReader.readLine());
                    System.out.println("Please enter the height of your parcel: ");
                    Double height = Double.parseDouble(bufferedReader.readLine());
                    System.out.println("Please enter the weight of your parcel: ");
                    Double weight = Double.parseDouble(bufferedReader.readLine());

                    Parcel someParcel = new Parcel(width, length, height, weight);

                    System.out.println("Would you like to gift wrap this parcel for $" + df.format(Double.valueOf(someParcel.giftWrapCost())) + "? - Y or N");
                    String yOrN = bufferedReader.readLine();
                    if(yOrN.equals("Y") || yOrN.equals("y")){
                        someParcel.isWrapped = true;
                    }

                    allParcels.add(someParcel);
                }else if(navChoice.equals("Ship Order")){
                    System.out.println("Please Choose a Shipping Speed: Standard or Express");
                    String speed = bufferedReader.readLine();
                    System.out.println("Please enter a distance in miles");
                    Double distance = Double.parseDouble(bufferedReader.readLine());
                    double totalCost = 0.00;
                    for(Parcel oneParcel : allParcels){
                        Double thisCost = oneParcel.costToShip(speed, distance);
                        totalCost += thisCost;
                        String isWrapped = "is not wrapped";
                        if(oneParcel.isWrapped){
                            thisCost += oneParcel.giftWrapCost();
                            totalCost += oneParcel.giftWrapCost();
                            isWrapped = "is gift-wrapped";
                        }
                        System.out.println("Your package that is " + oneParcel.width + "x" + oneParcel.length + "x" + oneParcel.height + " , weighs " + oneParcel.weight + "lbs, and " + isWrapped + " will cost $" + df.format(Double.valueOf(thisCost)));
                        System.out.println("------------------");
                    }
                    System.out.println("Your total shipping cost is: $" + df.format(Double.valueOf(totalCost)));
                }else if(navChoice.equals("Exit")){
                    System.out.println("Farewell Friend!");
                    programRunning = false;
                }else{
                    System.out.println("Invalid input, try again");
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
