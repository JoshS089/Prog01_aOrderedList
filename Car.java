/**
 * Class representing a Car object.
 *
 * CSC 1351 Programming Project No 1
 7
 * Section 2
 *
 * @author Joshua Saldana
 * @since 03/17/24
 *
 */
public class Car implements Comparable<Car> {
    private String make;
    private int year;
    private int price;
    /**
     * Constructor for Car class.
     *
     * CSC 1351 Programming Project No 1
     7
     * Section 2
     *
     * @author Joshua Saldana
     * @since 03/17/24
     *
     */
    public Car(String make, int year, int price) {
        this.make = make;
        this.year = year;
        this.price = price;
    }
    /**
     * Getters
     *
     * CSC 1351 Programming Project No 1
     * Section 2
     *
     * @author Joshua Saldana
     * @since 03/17/24
     *
     */
    public String getMake() {
        return make;
    }
    public int getYear() {
        return year;
    }
    public int getPrice() {
        return price;
    }
    /**
     * Compares this Car object with another Car object based on make and year.
     *
     * CSC 1351 Programming Project No 1
     * Section 2
     *
     * @author Joshua Saldana
     * @since 03/17/24
     *
     */
    @Override
    public int compareTo(Car other) {
        int makeComparison = this.make.compareTo(other.make);
        if (makeComparison != 0) {
            return makeComparison;
        }
        return Integer.compare(this.year, other.year);
    }
    /**
     * Returns a string representation of the Car object.
     *
     * CSC 1351 Programming Project No 1
     * Section 2
     *
     * @author Joshua Saldana
     * @since 03/17/24
     *
     */
    @Override
    public String toString() {
        return "Make: " + make + ", Year: " + year + ", Price: " + price + ";";
    }
}