package Exp6;

abstract class Vehicle{
    int vehicleNumber;
    String ownerName;

    public Vehicle(int vehicleNumber,String ownerName) {
        this.vehicleNumber = vehicleNumber;
        this.ownerName = ownerName;
    }
    abstract int calculateServiceCost();
}
class Car extends Vehicle{
    int engine;
    int hours;
    int sparepartcost;
  public Car(int vehicleNumber,String ownerName,int engine,int hours,int sparepartcost){
      super(vehicleNumber, ownerName);
      this.engine =engine;
      this.hours = hours;
      this.sparepartcost = sparepartcost;
  }
  @Override
  int calculateServiceCost(){
      return engine*hours + sparepartcost;
  }

}
class Bike extends Vehicle{
    int engine;
    int hours;
    int sparepartcost;
  public Bike(int vehicleNumber,String ownerName,int engine,int hours,int sparepartcost){
      super(vehicleNumber, ownerName);
      this.engine =engine;
      this.hours = hours;
      this.sparepartcost = sparepartcost;
  }
  @Override
  int calculateServiceCost(){
      return engine*hours + sparepartcost;
  }

}

class Truck extends Vehicle{
    int engine;
    int hours;
    int sparepartcost;
  public Truck(int vehicleNumber,String ownerName,int engine,int hours,int sparepartcost){
      super(vehicleNumber, ownerName);
      this.engine =engine;
      this.hours = hours;
      this.sparepartcost = sparepartcost;
  }
  @Override
  int calculateServiceCost(){
      return engine*hours + sparepartcost;
  }

}


public class Garage {
    public static void main(String[] args) {
        
    }
}
