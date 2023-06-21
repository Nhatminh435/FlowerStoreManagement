package business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import model.Flower;
import model.Order;
import model.OrderDetail;
import tool.Adder;
import tool.Validation;

public class FlowerSet extends HashSet<Flower> implements Serializable {

    /**
     * Return true if Flower ID already exist in FlowerSet, and vice versa.
     * @param flowerID
     * @param flowerSet
     * @return true if Flower ID already exist in FlowerSet, and vice versa.
     */
    public boolean isExistID(String flowerID, FlowerSet flowerSet){
        int count = 0;
        for (Flower flower : flowerSet) {
            if (flower.getId().equalsIgnoreCase(flowerID)) {
                count++;
            }
        }
        return (count > 0);
    }
    
    /**
     * Return true if Flower name already exist in FlowerSet, and vice versa.
     * @param flowerDescription
     * @param flowerSet
     * @return Return true if Flower ID already exist in FlowerSet, and vice versa.
     */
    public boolean isExistName(String flowerDescription, FlowerSet flowerSet){
        int count = 0;
        for (Flower flower : flowerSet) {
            if (flower.getDescription().equalsIgnoreCase(flowerDescription)) {
                count++;
            }
        }
        return (count > 0);
    }
    
    /**<ul>
     * <li>Return true if adding new flower to FlowerSet
     * successfully with conditions.
     * <li>To see the conditions, click below:
     * <li> {@linkplain Validation#checkFlowerID(java.lang.String, business.FlowerSet) }
     * <Li> {@linkplain Validation#checkValidPositiveDoubleNumber(java.lang.String) }
     * <Li> {@linkplain Validation#checkDateFormat(java.lang.String) }
     * <Li> {@linkplain Validation#checkValidStringWithBlank(java.lang.String) }
     * </ul>
     * @param flowerSet
     * @return return true if adding new flower to FlowerSet successfully with conditions.
     * @see Adder#addFlowerID(business.FlowerSet) 
     * @see Adder#addStringWithBlank() 
     * @see Adder#addDate() 
     * @see Adder#addPrice() 
     */
    //Add a flower
    public boolean addFlower(FlowerSet flowerSet) {
        System.out.print("➜ Flower ID: ");
        String flowerID = Adder.addFlowerID(flowerSet);
        System.out.print("➜ Flower's description: ");
        String flowerDescription = Adder.addStringWithBlank();
        System.out.print("➜ Flower's import date: ");
        String flowerImportDate = Adder.addDate();
        System.out.print("➜ Flower's unit price: ");
        Double flowerUnitPrice = Adder.addPrice();
        System.out.print("➜ Flower's category: ");
        String flowerCategory = Adder.addStringWithBlank();

        Flower flower = new Flower(flowerID, flowerDescription, flowerImportDate, flowerUnitPrice, flowerCategory);
        return (flowerSet.add(flower));
    }

    /**
     * Find flower by inputting flower id/ flower name
     * and display that flower
     * @param flowerSet 
     * @see FlowerSet#findFlowerByID(java.lang.String, business.FlowerSet) 
     * @see FlowerSet#findFlowerByName(java.lang.String, business.FlowerSet) 
     * @see Adder
     */
    public void findFlower(FlowerSet flowerSet) {
        System.out.print("➜ Find by (flower id/flower name): ");
        String inputField = Adder.addStringWithBlank();
        System.out.print(inputField.toUpperCase() + " : ");
        String flowerToFind = Adder.addStringWithBlank();
        if (inputField.equalsIgnoreCase("id") || inputField.equalsIgnoreCase("flower id")) {
            findFlowerByID(flowerToFind, flowerSet);
        }
        if (inputField.equalsIgnoreCase("name") || inputField.equalsIgnoreCase("flower name")) {
            findFlowerByName(flowerToFind, flowerSet);
        }
        if (!(inputField.equalsIgnoreCase("id") || inputField.equalsIgnoreCase("name"))) {
            System.out.println("Unvalid request.");
        }
    }

    /**<ul>
     * <li>Find flower through id (flowerToFind parameter) and display that flower.
     * <li>If flower id does not exist in FlowerSet, this method will 
     * display "The flower does not exist.".
     * </ul>
     * @param flowerToFind
     * @param flowerSet 
     */
    public void findFlowerByID(String flowerToFind, FlowerSet flowerSet) {
        int count = 0;    
        System.out.println(" ------------------------------------------------------------------------------------- ");
        System.out.printf("| %9s | %20s | %12s | %10s | %20s |\n", "Flower ID", "Flower name", "Import date", "Unit price", "Category");
        System.out.println("|-------------------------------------------------------------------------------------|");
        for (Flower flower : flowerSet) {
            if (flower.getId().equals(flowerToFind)) {
                System.out.printf("| %9s | %20s | %12s | %10s | %20s |\n", flower.getId(), flower.getDescription(), flower.getImportDate(), flower.getUnitPrice(), flower.getCategory());
                System.out.println(" ------------------------------------------------------------------------------------- ");
                count++;
            }
        }
        if (count <= 0) {
            System.out.println("The flower does not exist.");
        }
    }

    /**
     * <ul>
     * <li>Find flower through flower name (flowerToFind parameter) and display that flower.
     * <li>If flower id does not exist in FlowerSet, this method will 
     * display "The flower does not exist.".
     * </ul>
     * @param flowerToFind
     * @param flowerSet 
     */
    public void findFlowerByName(String flowerToFind, FlowerSet flowerSet) {
        int count = 0;    
        System.out.println(" ------------------------------------------------------------------------------------- ");
        System.out.printf("| %9s | %20s | %12s | %10s | %20s |\n", "Flower ID", "Flower name", "Import date", "Unit price", "Category");
        System.out.println("|-------------------------------------------------------------------------------------|");
        for (Flower flower : flowerSet) {
            if (flower.getDescription().equalsIgnoreCase(flowerToFind)) {
                System.out.printf("| %9s | %20s | %12s | %10s | %20s |\n", flower.getId(), flower.getDescription(), flower.getImportDate(), flower.getUnitPrice(), flower.getCategory());
                System.out.println(" ------------------------------------------------------------------------------------- ");
                count++;
            }
        }
        if (count <= 0) {
            System.out.println("The flower does not exist.");
        }
    }

    /**
     * This method will update flower's information 
     * (import date, unit price, category), by inputting flower name
     * (if it appears in flowerSet)
     * , and return true if it updates flower's information successfully.
     * and vice versa.
     * @param flowerSet
     * @return true if this method updates flower's information successfully, and vice versa.
     * @see FlowerSet#isExistName(java.lang.String, business.FlowerSet).
     * @see Adder
     */
    public boolean updateFlower(FlowerSet flowerSet) {
        System.out.print("➜ Enter the flower name you want to update: ");
        String flowerDescription = Adder.addStringWithBlank();
        if (isExistName(flowerDescription, flowerSet)) {
            for (Flower flower : flowerSet) {
                if (flower.getDescription().equalsIgnoreCase(flowerDescription)) {
                    System.out.println("Which field of " + flower.getDescription()+ " you want to update?");
                    System.out.println(" -------------------------------------");
                    System.out.println("| 1. Import date   |   2. Unit price  |");
                    System.out.println("| 3. Category      |   Other. Back    |");
                    System.out.println(" ------------------------------------- ");
                    System.out.print("➜ Your choice: ");
                    String getChoiceStr = Adder.addStringWithBlank();
                    int getChoice = Integer.parseInt(getChoiceStr);
                    switch (getChoice) {
                        case 1:
                            System.out.print("➜ Enter new import date: ");
                            String newImportDate = Adder.addDate();
                            flower.setImportDate(newImportDate);
                            break;
                        case 2:
                            System.out.print("➜ Enter new unit price: ");
                            double newUnitPrice = Adder.addPrice();
                            flower.setUnitPrice(newUnitPrice);
                            break;
                        case 3:
                            System.out.print("➜ Enter new category: ");
                            String newCategory = Adder.addStringWithBlank();
                            flower.setCategory(newCategory);
                            break;
                        default:
                            break;
                    }
                }
            }
            return true;
        }else{
            return false;
        }
        
    }

    /**
     * Return true if flower is in order, which is checked by flowerID.
     * @param flowerID
     * @param orderSet
     * @return Return true if flower is in order
     */
    public boolean isFlowerInOrder(String flowerID, OrderSet orderSet){
        int count = 0;
        for (Order order : orderSet) {
            for (OrderDetail orderDetail : order.getOrderDetails().values()) {
                if (orderDetail.getFlower().getId().equalsIgnoreCase(flowerID)) {
                    count++;
                }
            }
        }
        return (count > 0);
    }

    /**<ul>
     * <li> This method will remove a flower out of flowerSet by inputing flower ID.
     * <li> If flower does not exist, this method will display “The flower does not exist”
     * <li> If flower is in order, this method will display "Flower is in order."
     * <ui> This method will only returns true if it removes the flower you want out successfully.
     * </ul>
     * @param flowerSet
     * @param orderSet
     * @return returns true if it removes the flower you want out successfully.
     * @see FlowerSet#isExistID(java.lang.String, business.FlowerSet) 
     * @see FlowerSet#isFlowerInOrder(java.lang.String, business.OrderSet) 
     * @see Adder
     */
    public boolean deleteFlower(FlowerSet flowerSet, OrderSet orderSet) {
        boolean check = false;
        System.out.print("➜ Enter flower ID you want to delete: ");
        String flowerID = Adder.addStringWithBlank();
        if (isExistID(flowerID, flowerSet)) {
            if(!isFlowerInOrder(flowerID, orderSet)){
            for (Flower flower : flowerSet) {
                if (flower.getId().equalsIgnoreCase(flowerID)) {
                    System.out.println("Do you want to delete " + flower.getDescription()+ ": ");
                    System.out.println(" -------------------------------------------------");
                    System.out.println("|        y: Yes          |       Other: No        |");
                    System.out.println(" -------------------------------------------------");
                    System.out.print("Your choice: ");
                    String getChoice = Adder.addStringWithBlank();
                    if (getChoice.equalsIgnoreCase("y")) {
                        flowerSet.remove(flower);
                        check = true;
                        break;
                    }else{ 
                        check = false;
                        break;
                    }
                }
            }
            return check;
        }else{
            System.out.println("Flower is in order.");
            return false;
        }
        }else{
            System.out.println("Flower does not exist.");
            return false;
        }
    }

    /**
     * Return name of file through file's path by pathFile
     * @param pathFile
     * @return Return name of file through file's path by pathFile
     */
    private String getFileName(String pathFile){
        String[] subPathFile = pathFile.split("/");
        return subPathFile[subPathFile.length - 1];
    }
    
    /**This method will:
     * <ul>
     * <li>Saves the collection of flowers one by one to the binary file flowers.dat.
     * <li>Return true if it saves successfully.
     * <li>Return false with message "Fail to save flower's information to flowers.dat." if it saves failed,
     * </ul>
     * @param pathFlower
     * @param flowerSet
     * @return Return true if it saves the collection of flowers one by one to the binary file flowers.dat successfully.
     */
    public boolean saveFlowerToFile(String pathFlower, FlowerSet flowerSet) {
        try {
            FileOutputStream fileOutput = new FileOutputStream(new File(pathFlower));
            ObjectOutputStream objOutput = new ObjectOutputStream(fileOutput);
            for (Flower flower : flowerSet) {
                objOutput.writeObject(flower);
            }
            objOutput.flush();
            objOutput.close();
            fileOutput.close();
            return true;
        } catch (FileNotFoundException f) {
            System.out.println("Fail to save flower's information to " + getFileName(pathFlower) + ".");
            return false;
        } catch (IOException e) {
            System.out.println("Fail to save flower's information to " + getFileName(pathFlower) + ".");
            return false;
        }
    }
    
    /**
     * Ask to change or remove the flower id in file if it is already
     * exists before loading.
     * @param flower
     * @param flowerSet 
     */
    public void changeFlowerIDIfLoadDuplicate(Flower flower, FlowerSet flowerSet){
        if (isExistID(flower.getId(), flowerSet)) {
                                System.out.println("There is a flower id [" + flower.getId() + "] already existing in flowerSet.");
                                System.out.println("Do you want to change or remove this flower id [" + flower.getId() +"]? : ");
                                System.out.println(" -------------------------------------------------");
                                System.out.println("|        c: Change       |       r: Remove        |");
                                System.out.println(" -------------------------------------------------");
                                System.out.print("➜ Your choice: ");
                                String getChoice = Adder.addStringWithBlank();
                                if (!(getChoice.equalsIgnoreCase("r") || getChoice.equalsIgnoreCase("c"))) {
                                    do {                                    
                                        System.out.println("Unvalid request.");
                                        System.out.println("➜ Please enter again: ");
                                    } while (!(getChoice.equalsIgnoreCase("r") || getChoice.equalsIgnoreCase("c")));
                                }
                                if (getChoice.equalsIgnoreCase("c")) {
                                    System.out.print("New flower id: ");
                                    String newFlowerID = Adder.addFlowerID(flowerSet);
                                    flower.setId(newFlowerID);
                                    flowerSet.add(flower);
                                }
                            }else{
                                flowerSet.add(flower);
                            }
    }
    
    /**This method will:
     * <ul>
     * <li>Get flowers' information from the binary file flowers.dat through
     * file path [pathFlower] and add them one by one to a flowerSet.
     * <li>Ask to change or remove the flower id in file if it is already
     * exists before loading.
     * <li>Return flowerSet has flowers' information with message "Load
     * information from flowers.dat successfully!" if it successes.
     * <li>Return flowerSet has no flowers' information with message 
     * "Fail to load flowers.dat"
     * </ul>
     * @param pathFlower
     * @return Return flowerSet has flowers' information with message "Load information from flowers.dat successfully!" if it successes.
     */
    public FlowerSet getFlowerFromFile(String pathFlower, FlowerSet flowerSet) {
        try {
            FileInputStream fileInput = new FileInputStream(new File(pathFlower));
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            while (true) {
                try {
                    Object object = objectInput.readObject();
                    if (object != null) {
                        if (object instanceof Flower) {
                            Flower flower = (Flower) object;
                            changeFlowerIDIfLoadDuplicate(flower, flowerSet);
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    break;
                }
            }
            objectInput.close();
            fileInput.close();
            System.out.println("Load information from " + getFileName(pathFlower) +" successfully!");
        } catch (FileNotFoundException f) {
            System.out.println("Fail to load " + getFileName(pathFlower) + ".");
        } catch (IOException e) {
            System.out.println("Fail to load " + getFileName(pathFlower) + ".");
        }
        return flowerSet;
    }
}
