import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Arrays;
/**
 * Class asks for the input file and the outfile names also is the class where prints the car information such as make, year, and price.
 *
 * CSC 1351 Programming Project No 1
 7
 * Section 2
 *
 * @author Joshua Saldana
 * @since 03/17/24
 *
 */
public class Prog01_aOrderedList {
    public static void main(String[] args) {
        aOrderedList aorderedList = new aOrderedList();
        try {
            Scanner scanner = GetInputFile("Enter input filename: ");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] carData = line.split(",");
                if (carData.length == 4 && carData[0].equals("A")) {
                    String make = carData[1];
                    int year = Integer.parseInt(carData[2]);
                    int price = Integer.parseInt(carData[3]);
                    Car car = new Car(make, year, price);
                    aorderedList.add(car);
                    System.out.println("Added Car: " + car);
                } else {
                     if (carData[0].equals("D")) {
                         String make = carData[1];
                         int year = Integer.parseInt(carData[2]);
                         boolean found = false;
                         for (int i = 0; i < aorderedList.size(); i++) {
                             if (((Car) aorderedList.get(i)).getYear() == year  && ((Car) aorderedList.get(i)).getMake().equals(make)) {
                                 aorderedList.remove(i);
                                 found = true;
                                 System.out.println("Deleted Car: " + make + " " + year);
                                 break;
                             }
                         }
                         if (!found) {
                             System.out.println("Car not found for deletion: " + make + " " + year);
                         }
                     } else {
                         System.out.println("Invalid input line: " + line);
                     }
                }
            }
            scanner.close();
            PrintWriter outputFile = getOutputFile("Enter the filename for the output file: ");
            for(int i = 0; i < aorderedList.size(); i++){
            outputFile.println(aorderedList.get(i).toString());
            }
            outputFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("Program execution canceled.");
        }
    }
    /**
     * Method to get input file.
     *
     * CSC 1351 Programming Project No 1
     * Section 2
     *
     * @author Joshua Saldana
     * @since 03/17/24
     *
     */
    public static Scanner GetInputFile(String userPrompt) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        String fileName;
        do {
            System.out.print(userPrompt);
            fileName = scanner.nextLine();
            try {
                Scanner fileScanner = new Scanner(new File(fileName));
                return fileScanner;
            } catch (FileNotFoundException ex) {
                System.out.println("File specified <" + fileName + "> does not exist. Would you like to continue? <Y/N>");
                String response = scanner.nextLine().toUpperCase();
                if (response.equals("N")) {
                    throw new FileNotFoundException();
                }
            }
        } while (true);
    }
    /**
     * Method to get output file.
     *
     * CSC 1351 Programming Project No 1
     * Section 2
     *
     * @author Joshua Saldana
     * @since 03/17/24
     *
     */
        public static PrintWriter getOutputFile(String userPrompt) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        PrintWriter writer = null;
        boolean validFilename = false;
        while (!validFilename) {
            System.out.print(userPrompt);
            String filename = scanner.nextLine();
            try {
                writer = new PrintWriter(filename);
                validFilename = true;
            } catch (FileNotFoundException e) {
                System.out.println("Invalid filename or folder is write-protected. Please try again.");
                System.out.println("Enter 'cancel' to cancel program execution.");
                String userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("cancel")) {
                    throw new FileNotFoundException("User canceled program execution.");
                }
            }
        }
        return writer;
    }
}
/**
 * Class representing an ordered list of Car objects.
 *
 * CSC 1351 Programming Project No 1
 7
 * Section 2
 *
 * @author Joshua Saldana
 * @since 03/17/24
 *
 */
class aOrderedList {
    final int SIZEINCREMENTS = 20;
    //private Car[] oList;
    private Comparable[] oList;
    private int listSize;
    private int numObjects;
    private int curr;
    /**
     * Constructor for aOrderedList class.
     *
     * CSC 1351 Programming Project No 1
     * Section 2
     *
     * @author Joshua Saldana
     * @since 03/17/24
     *
     */
    public aOrderedList(){
        numObjects = 0;
        listSize = SIZEINCREMENTS;
        oList = new Car[listSize];
    }
    /**
     * Adds a new Comparable object to the ordered list.
     *
     * CSC 1351 Programming Project No 1
     * Section 2
     *
     * @author Joshua Saldana
     * @since 03/17/24
     *
     */
    public void add(Comparable newObject) {
        if (numObjects == listSize) {
            // Increase array size
            oList = Arrays.copyOf(oList, listSize + SIZEINCREMENTS);
            listSize += SIZEINCREMENTS;
        }
        int index = numObjects;
        while (index > 0 && oList[index - 1].compareTo(newObject) > 0) {
            oList[index] = oList[index - 1];
            index--;
        }
        oList[index] = newObject;
        numObjects++;
    }
    /**
     * Returns a string representation of the ordered list.
     *
     * CSC 1351 Programming Project No 1
     * Section 2
     *
     * @author Joshua Saldana
     * @since 03/17/24
     *
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < numObjects; i++) {
            sb.append(oList[i].toString());
        }
        sb.append("]");
        return sb.toString();
    }
    /**
     * Returns the number of elements in the list.
     *
     * CSC 1351 Programming Project No 1
     * Section 2
     *
     * @author Joshua Saldana
     * @since 03/17/24
     *
     */
    public int size() {
        return numObjects;
    }
    /**
     * Returns the element at the specified index in the list.
     *
     * CSC 1351 Programming Project No 1
     * Section 2
     *
     * @author Joshua Saldana
     * @since 03/17/24
     *
     */
    public Comparable get(int index) {
        if (index < 0 || index >= numObjects) {
            throw new IndexOutOfBoundsException("Index out of bounds");
       }
        return oList[index];
    }
    /**
     * Checks if the list is empty.
     *
     * CSC 1351 Programming Project No 1
     * Section 2
     *
     * @author Joshua Saldana
     * @since 03/17/24
     *
     */
    public boolean isEmpty() {
        return numObjects == 0;
    }
    /**
     * Removes the element at the specified index from the list.
     *
     * CSC 1351 Programming Project No 1
     * Section 2
     *
     * @author Joshua Saldana
     * @since 03/17/24
     *
     */
    public void remove(int index) {
        if (index < 0 || index >= numObjects) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        for (int i = index; i < numObjects - 1; i++) {
            oList[i] = oList[i + 1];
        }
        numObjects--;
    }
    /**
     * Resets the iterator to the beginning of the list.
     *
     * CSC 1351 Programming Project No 1
     * Section 2
     *
     * @author Joshua Saldana
     * @since 03/17/24
     *
     */
    public void reset() {
        curr = 0;
    }
    /**
     * Returns the next element in the iteration and increments the iterator.
     *
     * CSC 1351 Programming Project No 1
     * Section 2
     *
     * @author Joshua Saldana
     * @since 03/17/24
     *
     */
    public Comparable next() {
        if (!hasNext()) {
            throw new IllegalStateException("No more elements to iterate through.");
        }
        return oList[curr++];
    }
    /**
     * Checks if there are more elements to iterate through.
     *
     * CSC 1351 Programming Project No 1
     * Section 2
     *
     * @author Joshua Saldana
     * @since 03/17/24
     *
     */
    public boolean hasNext() {
        return curr < numObjects;
    }
    /**
     * Removes the last element returned by the next() method.
     *
     * CSC 1351 Programming Project No 1
     * Section 2
     *
     * @author Joshua Saldana
     * @since 03/17/24
     *
     */
    public void remove() {
        if (curr == 0 || curr > numObjects) {
            throw new IllegalStateException("No element to remove.");
        }
        for (int i = curr; i < numObjects - 1; i++) {
            oList[i] = oList[i + 1];
        }
        numObjects--;
        curr--;
    }
}