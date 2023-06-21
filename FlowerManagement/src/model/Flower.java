package model;

import java.io.Serializable;
/**
 * This is a class representing a Flower.
 * It contains properties such as id, age, gender, address, phone.
 * It implements the Serializable interface to enable object serialization.
 * The class provides methods to get and set these properties.
 */
public class Flower implements Serializable{
    
    private String id;
    private String description;
    private String importDate;
    private double unitPrice;
    private String category;
    
    /**
     * Default constructor of Flower class.
     */
    public Flower() {
    }

    /**
     * Constructor of Flower class.
     * @param id
     * @param description
     * @param importDate
     * @param unitPrice
     * @param category 
     */
    public Flower(String id, String description, String importDate, double unitPrice, String category) {
        this.id = id;
        this.description = description;
        this.importDate = importDate;
        this.unitPrice = unitPrice;
        this.category = category;
    }

    /**
     * Set the id of the Flower.
     * 
     * If id parameter contains blank, it will return "Null".
     *
     * @param id The ID of the Flower.
     */
    public void setId(String id) {
        if (id.contains(" ")) {
            this.id = id;
        }else{
        this.id = "Null";
        }
    }
    
    /**
     * Set the description of the Flower
     * 
     * If description parameter does not has 3-50 characters, it will return "Null".
     * 
     * @param description The description of Flower
     */
    public void setDescription(String description) {
        if ((description.length() >= 3) && (description.length() <= 50)) {
            this.description  = description;
        } else {
            this.description = "Null";
        }
    }

    /**
     * Set the import date of Flower
     * 
     * If import date parameter is not a valid format, it will return "Null"
     * 
     * @param importDate The import date of Flower
     */
    public void setImportDate(String importDate) {
        if (importDate.matches("[0-3][0-9]/[0-1][0-9]/[0-9]{4}") && !importDate.matches("00/00/0000")) {
            this.importDate = importDate;
        } else {
            this.importDate = "Null";
        }
        
    }

    /**
     * Set the unit price of Flower
     * 
     * If unit price parameter is negative, it will return 0.0
     * 
     * @param unitPrice The unit price of Flower 
     */
    public void setUnitPrice(double unitPrice) {
        if (unitPrice > 0) {
            this.unitPrice = unitPrice;
        }else{
            this.unitPrice = 0.0;
        }  
    }

    /**
     * Set the category of Flower
     * 
     * @param category The category of flower
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Returns the id of the Flower.
     *
     * @return The id of the Flower.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Returns the description of the Flower.
     *
     * @return The description of the Flower.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the import date of the Flower.
     *
     * @return The import date of the Flower.
     */
    public String getImportDate() {
        return this.importDate;
    }

    /**
     * Returns the unit price of the Flower.
     *
     * @return The unit price of the Flower.
     */
    public double getUnitPrice() {
        return this.unitPrice;
    }

    /**
     * Returns the category of the Flower.
     *
     * @return The category of the Flower.
     */
    public String getCategory() {
        return this.category;
    }

    @Override
    public String toString() {
        return getId() + ", " + getDescription() + ", " + getImportDate() 
                + ", " + getUnitPrice() + ", " + getCategory();
    }
}
