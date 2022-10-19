package com.cbowe.dvdlibrary.dao;

import com.cbowe.dvdlibrary.dto.Dvd;
import java.io.*;
import java.util.*;

/**
 * The {@code DvdLibraryDaoFileImpl} class implements the
 * DvdLibraryDao interface. This class is responsible for
 * retrieving data from a .txt file and storing data in
 * a .txt file.
 */

public class DvdLibraryDaoFileImpl implements DvdLibraryDao {

    // Constant to store file name
    public final String DVD_FILE ;
    // Constant to store delimiter for separating String into tokens
    public static final String DELIMITER = "::";
    // Hash Map to store and retrieve the Dvd; Key is the Dvd title
    private Map<String, Dvd> dvds = new HashMap<>();

    /**
     * No arg constructor.
     * Assigns file name to constant DVD_FILE.
     */
    public DvdLibraryDaoFileImpl() {
        DVD_FILE = "dvdTest.txt";
    }

    /**
     * Constructor accepts String parameter
     *
     * @param libraryTextFile name of file with DVD info
     */
    public DvdLibraryDaoFileImpl(String libraryTextFile) {
        DVD_FILE = libraryTextFile;
    }

    /**
     * Adds a new Dvd object to the DVD_FILE
     *
     * @param title with which DVD is to be associated
     * @param dvd DVD to be added to the library
     * @return new Dvd object
     * @throws DvdLibraryDaoException if an error occurs writing to the file
     */
    @Override
    public Dvd addDvd(String title, Dvd dvd) throws DvdLibraryDaoException {
        loadDvdFile();

        // New Dvd object declared, parameters passed to
        // dvds.put() to add new object to HashMap
        Dvd newDvd = dvds.put(title, dvd);

        writeDvdFile(); // Write all Dvd objects to DVD_FILE

        return newDvd; // Return new Dvd object
    }

    /**
     * Gets all Dvd objects out of the dvds map as a Collection
     * by calling the values() method
     *
     * @return ArrayList of values from dvds HashMap
     * @throws DvdLibraryDaoException prints message
     */
    @Override
    public List<Dvd> getAllDvds() throws DvdLibraryDaoException {
        loadDvdFile(); // Load file into memory
        return new ArrayList<Dvd>(dvds.values());
    }

    /**
     * Retrieves Dvd object associated with specified title
     *
     * @param title title of the DVD to retrieve
     * @return Dvd object associated with title
     * @throws DvdLibraryDaoException prints message
     */
    @Override
    public Dvd getDvd(String title) throws DvdLibraryDaoException {
        loadDvdFile(); // Load file into memory
        return dvds.get(title);
    }

    /**
     * Removes Dvd object associated with specified title
     *
     * @param title title of DVD to be removed
     * @return removed Dvd object
     * @throws DvdLibraryDaoException prints message
     */
    @Override
    public Dvd removeDvd(String title) throws DvdLibraryDaoException{
        loadDvdFile(); // Load file into memory
        Dvd removedDvd = dvds.remove(title); // Remove Dvd from HashMap
        writeDvdFile(); // Write to .txt file
        return removedDvd;
    }

    /**
     * Method to unmarshall the object or read a line of
     * string from the file and convert it into an object
     *
     * @param dvdAsText text representation of a Dvd object
     * @return Dvd object created from String representation
     */
    private Dvd unmarshallDvd(String dvdAsText) {
        // Declare and initialize array of tokens which are
        // created from splitting string at the :: delimiter
        String[] dvdTokens = dvdAsText.split(DELIMITER);

        // Index 0 - title of Dvd object
        String title = dvdTokens[0];

        // Create a new Dvd object to satisfy the requirements
        // of the constructor
        Dvd dvdFromFile = new Dvd(title);

        // Index 1 - releaseDate of Dvd object
        dvdFromFile.setReleaseDate(dvdTokens[1]);

        // Index 2 - mpaaRating of Dvd object
        dvdFromFile.setMPAA(dvdTokens[2]);

        // Index 3 - directorName of Dvd object
        dvdFromFile.setDirectorsName(dvdTokens[3]);

        // Index 4 - studioName of Dvd object
        dvdFromFile.setStudio(dvdTokens[4]);

        // Index 5 - userRating of Dvd object
        dvdFromFile.setUserRating(dvdTokens[5]);

        // Return the Dvd object that was created
        return dvdFromFile;
    }

    /**
     * Method to Load file DVD_FILE into memory
     *
     * @throws DvdLibraryDaoException prints message
     */
    private void loadDvdFile() throws DvdLibraryDaoException {
        Scanner scanner;

        try {
            // Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(DVD_FILE)));
        } catch (FileNotFoundException e) {
            throw new DvdLibraryDaoException("*** Could not load DVD data into memory.", e);
        }

        String currentLine; // Holds most recent line read from file
        Dvd currentDvd; // Holds the most recent Dvd object unmarshalled

        // Go through DVD_FILE line by line, decoding each line into a
        // Dvd object by calling the unmarshallDvd method.
        // Process while there are more lines in the file.
        while (scanner.hasNextLine()) {
            // Get the next line in the file
            currentLine = scanner.nextLine();
            // Unmarshall the line into a Dvd object
            currentDvd = unmarshallDvd(currentLine);

            // The title is the map key for a Dvd object.
            // Put currentDvd into the map using title as the key.
            dvds.put(currentDvd.getTitle(), currentDvd);
        }

        // close scanner
        scanner.close();
    }

    /**
     * This method turns a Dvd object into a line of text to store
     * in the DVD_FILE.
     *
     * @param aDvd Dvd object to be marshalled
     * @return Dvd object as String separated by DELIMITER
     */
    private String marshallDvd(Dvd aDvd) {
        // Declare string, initialize with title and DELIMITER
        String dvdAsText = aDvd.getTitle() + DELIMITER;
        // Concatenate release date and DELIMITER
        dvdAsText += aDvd.getReleaseDate() + DELIMITER;
        // Concatenate MPAA rating and DELIMITER
        dvdAsText += aDvd.getMPAA() + DELIMITER;
        // Concatenate director's name and DELIMITER
        dvdAsText += aDvd.getDirectorsName() + DELIMITER;
        // Concatenate studio name and DELIMITER
        dvdAsText += aDvd.getStudio() + DELIMITER;
        // Concatenate user rating
        dvdAsText += aDvd.getUserRating();

        // Return Dvd object as String
        return dvdAsText;
    }

    /**
     * Writes all Dvds in the library out to a DVD_FILE.
     *
     * @throws DvdLibraryDaoException prints message
     */
    private void writeDvdFile() throws DvdLibraryDaoException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(DVD_FILE));
        } catch (IOException e) {
            throw new DvdLibraryDaoException("Could not save dvd data.", e);
        }

        // Write out the Dvd objects to the DVD file.
        String dvdAsText; // Declare variable to store String representation

        List<Dvd> dvdList = this.getAllDvds(); // List of all Dvd objects

        // Iterate through objects in dvdList
        for (Dvd currentDvd : dvdList) {
            // Get String representation of Dvd object, store in variable
            dvdAsText = marshallDvd(currentDvd);
            // Write the String representation of theDvd object to the file
            out.println(dvdAsText);
            // Force PrintWriter to write line to the file
            out.flush();
        }

        // Clean up
        out.close();
    }

    /**
     * Edits the release date of the current Dvd object
     *
     * @param title title of DVD to be edited
     * @param newReleaseDate release date for Dvd
     * @return current Dvd object
     * @throws DvdLibraryDaoException prints message
     */
    @Override
    public Dvd editReleaseDate(String title, String newReleaseDate) throws DvdLibraryDaoException {
        loadDvdFile(); // Load file into memory
        Dvd currentDVD = dvds.get(title); // Get currentDvd info from HashMap
        currentDVD.setReleaseDate(newReleaseDate); // Call setter to set new release date
        writeDvdFile(); // Write to .txt file
        return currentDVD; // Return currentDvd object
    }

    /**
     * Edits the MPAA rating of the current Dvd object
     *
     * @param title title of DVD to be edited
     * @param newMpaaRating rating of DVD
     * @return current Dvd object
     * @throws DvdLibraryDaoException prints message
     */
    @Override
    public Dvd editMPAA(String title, String newMpaaRating) throws DvdLibraryDaoException {
        loadDvdFile(); // Load file into memory
        Dvd currentDVD = dvds.get(title); // Get currentDvd info from HashMap
        currentDVD.setMPAA(newMpaaRating); // Call setter to set new MPAA rating
        writeDvdFile(); // Write to .txt file
        return currentDVD; // Return currentDvd object
    }

    /**
     * Edits the director name of the current Dvd object
     *
     * @param title title of DVD to be edited
     * @param newDirectorName name of film director
     * @return current Dvd object
     * @throws DvdLibraryDaoException prints message
     */
    @Override
    public Dvd editDirectorName(String title, String newDirectorName) throws DvdLibraryDaoException {
        loadDvdFile(); // Load file into memory
        Dvd currentDVD = dvds.get(title); // Call setter to set new release date
        currentDVD.setDirectorsName(newDirectorName); // Call setter to set new director name
        writeDvdFile(); // Write to .txt file
        return currentDVD; // Return currentDvd object
    }

    /**
     * Edits the user rating of the current Dvd object
     *
     * @param title of DVD to be edited
     * @param newUserRating user rating of DVD
     * @return current Dvd object
     * @throws DvdLibraryDaoException prints message
     */
    @Override
    public Dvd editUserRating(String title, String newUserRating) throws DvdLibraryDaoException {
        loadDvdFile(); // Load file into memory
        Dvd currentDVD = dvds.get(title); // Call setter to set new release date
        currentDVD.setUserRating(newUserRating); // Call setter to set new user rating
        writeDvdFile(); // Write to .txt file
        return currentDVD; // Return currentDvd object
    }

    /**
     * Edits the studio name of the current Dvd object
     *
     * @param title of DVD to be edited
     * @param newStudioName studio that released film
     * @return current Dvd object
     * @throws DvdLibraryDaoException prints message
     */
    @Override
    public Dvd editStudio(String title, String newStudioName) throws DvdLibraryDaoException {
        loadDvdFile(); // Load file into memory
        Dvd currentDVD = dvds.get(title); // Call setter to set new release date
        currentDVD.setStudio(newStudioName); // Call setter to set new studio name
        writeDvdFile(); // Write to .txt file
        return currentDVD; // Return currentDvd object
    }

}
