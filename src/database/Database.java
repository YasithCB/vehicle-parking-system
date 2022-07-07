package database;

import entity.*;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class Database {

    // Below Anchorpane
    public static AnchorPane belowAp;

    // vehicle
    public static ArrayList<Vehicle> vehicles = new ArrayList();

    static {
        vehicles.add(new Bus("NA-3434","Bus",3500,60));
        vehicles.add(new Van("KA-4563","Van",1000,7));
        vehicles.add(new Van("58-3567","Van",1500,4));
        vehicles.add(new Van("GF-4358","Van",800,4));
        vehicles.add(new Van("CCB-3568","Van",1800,8));
        vehicles.add(new Van("LM-6679","Van",1500,4));
        vehicles.add(new Van("QA-3369","Van",1800,6));
        vehicles.add(new CargoLorry("KB-3668","Cargo Lorry",2500,2));
        vehicles.add(new CargoLorry("JJ-9878","Cargo Lorry",3000,2));
        vehicles.add(new CargoLorry("GH-5772","Cargo Lorry",4000,3));
        vehicles.add(new CargoLorry("XY-4456","Cargo Lorry",3500,2));
        vehicles.add(new CargoLorry("YQ-3536","Cargo Lorry",2000,2));
        vehicles.add(new CargoLorry("CBB-3566","Cargo Lorry",2500,2));
        vehicles.add(new CargoLorry("QH-3444","Cargo Lorry",5000,4));

    }

    // drivers
    public static ArrayList<Driver> drivers = new ArrayList<>();

    static {
        drivers.add(new Driver("Sumith Kumara","783534834V","B6474845","Panadura","+94725637456"));
        drivers.add(new Driver("Amila Pathirana","882625373V","B3354674","Galle","+94717573583"));
        drivers.add(new Driver("Jithmal Perera","928328927V","B3674589","Horana","+94772452457"));
        drivers.add(new Driver("Sumith Dissanayaka","942524537V","B8366399","Kaluthara","+94782686390"));
        drivers.add(new Driver("Sumanasiri Herath","897654437V","B3537538","Beruwala","+94772534436"));
        drivers.add(new Driver("Awantha Fernando","917353783V","B3554789","Colombo 5","+94714534356"));
        drivers.add(new Driver("Charith Sudara","957353683V","B6835836","Baththaramulla","+94771536662"));
        drivers.add(new Driver("Prashan Dineth","936242673V","B2683536","Wadduwa","+94715353434"));
        drivers.add(new Driver("Chethiya Dilan","916235343V","B6836836","Panadura","+94772436737"));
        drivers.add(new Driver("Dushantha Perera","925555634V","B3334435","Matara","+94777245343"));
        drivers.add(new Driver("Sumith Udayanga","873535435V","B3573783","Galle","+94703635442"));
        drivers.add(new Driver("Dineth Udara","902634437V","B5343783","Hettimulla","+94713456878"));
        drivers.add(new Driver("Udana Chathuranga","969265333V","B7888632","Kottawa","+94772442444"));
        drivers.add(new Driver("Mohommad Riaz","912453773V","B3638537","Kaluthara","+94777544222"));
        drivers.add(new Driver("Sandun Kumara","956352426V","B2263333","Panadura","+94772325544"));
        drivers.add(new Driver("Priyanga Perera","913534353V","B3853753","Matara","+94723344562"));
    }


    // On park
    public static ArrayList<Vehicle> vehiclesInPark = new ArrayList<>();
    public static ArrayList<Vehicle> vehicleOnDelivery = new ArrayList<>();


    // slots
    public static ArrayList<Slot> slots = new ArrayList<>();

    static {
        slots.add(new Slot());
        slots.add(new Slot(1,true));
        slots.add(new Slot(2,true));
        slots.add(new Slot(3,true));
        slots.add(new Slot(4,true));
        slots.add(new Slot(5,true));
        slots.add(new Slot(6,true));
        slots.add(new Slot(7,true));
        slots.add(new Slot(8,true));
        slots.add(new Slot(9,true));
        slots.add(new Slot(10,true));
        slots.add(new Slot(11,true));
        slots.add(new Slot(12,true));
        slots.add(new Slot(13,true));
        slots.add(new Slot(14,true));
    }


    // Admins
    public static ArrayList<Admin> admins = new ArrayList<>();

}
