/**
 * DvdLibraryDaoFileImpl class is part of the dao package.
 */

package com.cbowe.dvdlibrary.dao;

// Import Dvd class from the dto package
import com.cbowe.dvdlibrary.dto.Dvd;
import java.io.*;
import java.util.*;

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
     * @throws DvdLibraryDaoException if error occurs
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
     * @throws DvdLibraryDaoException if error occurs
     */
    @Override
    public List<Dvd> getAllDvds() throws DvdLibraryDaoException {
        loadDvdFile();
        return new ArrayList<Dvd>(dvds.values());
    }

    /**
     * Retrieves Dvd object associated with specified title
     *
     * @param title title of the DVD to retrieve
     * @return Dvd object associated with title
     * @throws DvdLibraryDaoException if error occurs
     */
    @Override
    public Dvd getDvd(String title) throws DvdLibraryDaoException {
        loadDvdFile();
        return dvds.get(title);
    }

    /**
     * Removes Dvd object associated with specified title
     *
     * @param title title of DVD to be removed
     * @return removed Dvd object
     * @throws DvdLibraryDaoException if error occurs
     */
    @Override
    public Dvd removeDvd(String title) throws DvdLibraryDaoException{
        loadDvdFile();
        Dvd removedDvd = dvds.remove(title);
        writeDvdFile();
        return removedDvd;
    }

    /*
    Method to unmarshall the object or read a line of
    string from the line and convert it into an object
     */
    private Dvd unmarshallDvd(String dvdAsText) {
        // dvdAsText is expecting a line read in from our file.
        // For example, it might look like this:
        // Move Title::January 1, 2000::PG-13::John Doe::Studio Name::User Rating
        //
        // We then split that line on our DELIMITER - which we are using as ::
        // leaving us with an array of Strings, stored in dvdTokens.
        // Which should look like this:
        //
        // ____________________________________________________________________
        // |           |               |     |        |           |           |
        // |Movie Title|January 1, 2000|PG-13|John Doe|Studio Name|User Rating|
        // |           |               |     |        |           |           |
        // ____________________________________________________________________
        //     [0]          [1]          [2]     [3]       [4]         [5]
        String[] dvdTokens = dvdAsText.split(DELIMITER);

        // Given the pattern above, the DVD title is in index 0 of the array.
        String title = dvdTokens[0];

        // Which we can then use to create a new Dvd object to satisfy
        // the requirements of the constructor.
        Dvd dvdFromFile = new Dvd(title);

        // However, there are 5 remaining tokens that need to be set into the
        // new Dvd object. Do this manually by using the appropriate setters.

        // Index 1 - releaseDate
        dvdFromFile.setReleaseDate(dvdTokens[1]);

        // Index 2 - mpaaRating
        dvdFromFile.setMPAA(dvdTokens[2]);

        // Index 3 - directorName
        dvdFromFile.setDirectorsName(dvdTokens[3]);

        // Index 4 - studioName
        dvdFromFile.setStudio(dvdTokens[4]);

        // Index 5 - userRating
        dvdFromFile.setUserRating(dvdTokens[5]);

        // Return the Dvd object that was created.
        return dvdFromFile;
    }

    // Method to Load file DVD_FILE into memory
    private void loadDvdFile() throws DvdLibraryDaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
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
     * Memory object will be formatted like the following example:
     * Title::January 1, 2000::PG::John Doe::Studio::User Rating
     *
     * @param aDvd Dvd object to be marshalled
     * @return Dvd object as text separated by DELIMITER
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
     * Writes all Dvds in the library out to a DVD_FILE. See loadDvdFile
     * for file format.
     *
     * @throws DvdLibraryDaoException if an error occurs writing to the file
     */
    private void writeDvdFile() throws DvdLibraryDaoException {
        // NOTE FOR APPRENTICES: We are not handling the IOException - but
        // we are translating it to an application specific exception and
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to
        // handle any errors that occur.
        //implement
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(DVD_FILE));
        } catch (IOException e) {
            throw new DvdLibraryDaoException("Could not save dvd data.", e);
        }

        // Write out the Dvd objects to the DVD file.
        // NOTE TO THE APPRENTICES: We could just grab the dvd map,
        // get the Collection of Dvds and iterate over them, but we've
        // already created a method that gets a List of Dvds, so
        // we'll reuse it.
        String dvdAsText;
        List<Dvd> dvdList = this.getAllDvds(); // List of all Dvd objects
        // Iterate through objects in dvdList
        for (Dvd currentDvd : dvdList) {
            // Turn Dvd object into a String
            dvdAsText = marshallDvd(currentDvd);
            // Write the Dvd object to the file
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
     * @throws DvdLibraryDaoException if an error occurs
     */
    @Override
    public Dvd editReleaseDate(String title, String newReleaseDate) throws DvdLibraryDaoException {
        loadDvdFile();
        Dvd currentDVD = dvds.get(title);
        currentDVD.setReleaseDate(newReleaseDate);
        writeDvdFile();
        return currentDVD;
    }

    /**
     * Edits the MPAA rating of the current Dvd object
     *
     * @param title title of DVD to be edited
     * @param newMpaaRating rating of DVD
     * @return current Dvd object
     * @throws DvdLibraryDaoException if an error occurs
     */
    @Override
    public Dvd editMPAA(String title, String newMpaaRating) throws DvdLibraryDaoException {
        loadDvdFile();
        Dvd currentDVD = dvds.get(title);
        currentDVD.setMPAA(newMpaaRating);
        writeDvdFile();
        return currentDVD;
    }

    /**
     * Edits the director name of the current Dvd object
     *
     * @param title title of DVD to be edited
     * @param newDirectorName name of film director
     * @return current Dvd object
     * @throws DvdLibraryDaoException if an error occurs
     */
    @Override
    public Dvd editDirectorName(String title, String newDirectorName) throws DvdLibraryDaoException {
        loadDvdFile();
        Dvd currentDVD = dvds.get(title);
        currentDVD.setDirectorsName(newDirectorName);
        writeDvdFile();
        return currentDVD;
    }

    /**
     * Edits the user rating of the current Dvd object
     *
     * @param title of DVD to be edited
     * @param newUserRating user rating of DVD
     * @return current Dvd object
     * @throws DvdLibraryDaoException if an error occurs
     */
    @Override
    public Dvd editUserRating(String title, String newUserRating) throws DvdLibraryDaoException {
        loadDvdFile();
        Dvd currentDVD = dvds.get(title);
        currentDVD.setUserRating(newUserRating);
        writeDvdFile();
        return currentDVD;
    }

    /**
     * Edits the studio name of the current Dvd object
     *
     * @param title of DVD to be edited
     * @param newStudioName studio that released film
     * @return current Dvd object
     * @throws DvdLibraryDaoException if an error occurs
     */
    @Override
    public Dvd editStudio(String title, String newStudioName) throws DvdLibraryDaoException {
        loadDvdFile();
        Dvd currentDVD = dvds.get(title);
        currentDVD.setStudio(newStudioName);
        writeDvdFile();
        return currentDVD;
    }

}
