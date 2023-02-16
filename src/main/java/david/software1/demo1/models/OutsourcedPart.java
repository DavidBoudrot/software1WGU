package david.software1.demo1.models;

/**
 * The OutsourcedPart class represents a part that is outsourced.
 * its is a subclass of Part.
 * it adds a companyName field.
 */

public class OutsourcedPart extends Part {
    private String companyName;


    /**
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * @param companyName
     */
    public OutsourcedPart(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     * */
        public void setCompanyName(String companyName) {
        this.companyName = companyName;

        }
    }
