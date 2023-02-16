package david.software1.demo1.models;

/**
 * The InHousePart class represents a part that is in-house.
 * its is a subclass of Part.
 * it adds a machineID field.
 */
public class InHousePart extends Part {
    private int machineID;

    public InHousePart(int id, String name, double price, int stock, int min, int max, int machineID) {
        super(id, name, price, stock, min, max);
        this.machineID = machineID;
    }

    /**
     * @return the machineID
     */
    public int getMachineID() {
        return machineID;
    }

    /**
     * @param machineID the machineID to set
     */
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
}
