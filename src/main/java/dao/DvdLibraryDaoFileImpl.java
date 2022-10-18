package dao;

import dto.Dvd;
import java.io.*;
import java.util.*;

public class DvdLibraryDaoFileImpl implements DvdLibraryDao {

    public final String DVD_FILE ;
    public static final String DELIMITER = "::";
    // Hash Map to store and retrieve the dvd with title name
    private Map<String, Dvd> dvds = new HashMap<>();

    public DvdLibraryDaoFileImpl() { //no arg constructor typically used
        DVD_FILE = "dvdTest.txt";
    }

    public DvdLibraryDaoFileImpl(String libraryTextFile){
        DVD_FILE = libraryTextFile;
    }

    @Override
    public Dvd addDvd(String dvdTitle, Dvd dvd) throws DvdLibraryDaoException {
        loadDvdFile();
        Dvd newDvd = dvds.put(dvdTitle, dvd);
        writeDvdFile();
        return newDvd;
    }

    /*
    This code gets all DvDs objects out of the dvds map
    as a collection by calling the values() method.
     */
    @Override
    public List<Dvd> getAllDvds() throws DvdLibraryDaoException {
        loadDvdFile();
        return new ArrayList<Dvd>(dvds.values());
    }

    /*
    This method is quite simple — we just ask the dvds map
    for the dvd object with the given title and return it
     */
    @Override
    public Dvd getDvd(String dvdTitle) throws DvdLibraryDaoException {
        loadDvdFile();
        return dvds.get(dvdTitle);
    }

    @Override
    public Dvd removeDvd(String dvdTitle) throws DvdLibraryDaoException{
        loadDvdFile();
        Dvd removedDvd = dvds.remove(dvdTitle);
        writeDvdFile();
        return removedDvd;
    }

    /*
    Method to unmarshall the object or read a line of
    string from the line and convert it into an object
     */
    private Dvd unmarshallDvd(String dvdAsText) {
        String[] dvdTokens = dvdAsText.split(DELIMITER);
        String dvdTitle = dvdTokens[0];
        Dvd dvdFromFile = new Dvd(dvdTitle);
        dvdFromFile.setReleaseDate(dvdTokens[1]);
        dvdFromFile.setMPAA(dvdTokens[2]);
        dvdFromFile.setDirectorsName(dvdTokens[3]);
        dvdFromFile.setStudio(dvdTokens[4]);
        dvdFromFile.setUserRating(dvdTokens[5]);
        return dvdFromFile;
    }

    /*
    Method to Load file DVD_FILE into memory
     */
    private void loadDvdFile() throws DvdLibraryDaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(DVD_FILE)));
        } catch (FileNotFoundException e) {
            throw new DvdLibraryDaoException("*** Could not load DVD data into memory.", e);
        }

        String currentLine;
        Dvd currentDvd;

        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            currentDvd = unmarshallDvd(currentLine);

            dvds.put(currentDvd.getTitle(), currentDvd);
        }

        // close scanner
        scanner.close();
    }

    private String marshallDvd(Dvd aDvd) {
        String dvdAsText = aDvd.getTitle() + DELIMITER;
        dvdAsText += aDvd.getReleaseDate() + DELIMITER;
        dvdAsText += aDvd.getMPAA() + DELIMITER;
        dvdAsText += aDvd.getDirectorsName() + DELIMITER;
        dvdAsText += aDvd.getStudio() + DELIMITER;
        dvdAsText += aDvd.getUserRating();

        return dvdAsText;
    }

    /**
     * Writes all Dvds in the library out to a DVD_FILE. See loadDvdFile
     * for file format.
     *
     * @throws Exception if an error occurs writing to the file
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

        // Write out the DvD objects to the DVD file.
        // NOTE TO THE APPRENTICES: We could just grab the dvd map,
        // get the Collection of dvd and iterate over them but we've
        // already created a method that gets a List of dvds so
        // we'll reuse it.
        //implement
        String dvdAsText;
        List<Dvd> dvdList = this.getAllDvds();
        for (Dvd currentDvd : dvdList) {
            dvdAsText = marshallDvd(currentDvd);
            out.println(dvdAsText);
            out.flush();
        }

        // Clean up
        out.close();
    }

    @Override
    public Dvd editReleaseDate(String title, String newReleaseDate) throws DvdLibraryDaoException {
        loadDvdFile();
        Dvd currentDVD = dvds.get(title);
        currentDVD.setReleaseDate(newReleaseDate);
        writeDvdFile();
        return currentDVD;
    }

    @Override
    public Dvd editMPAA(String title, String newMpaaRating) throws DvdLibraryDaoException {
        loadDvdFile();
        Dvd currentDVD = dvds.get(title);
        currentDVD.setMPAA(newMpaaRating);
        writeDvdFile();
        return currentDVD;
    }

    @Override
    public Dvd editDirectorName(String title, String newDirectorName) throws DvdLibraryDaoException {
        loadDvdFile();
        Dvd currentDVD = dvds.get(title);
        currentDVD.setDirectorsName(newDirectorName);
        writeDvdFile();
        return currentDVD;
    }

    @Override
    public Dvd editUserRating(String title, String newUserRating) throws DvdLibraryDaoException {
        loadDvdFile();
        Dvd currentDVD = dvds.get(title);
        currentDVD.setUserRating(newUserRating);
        writeDvdFile();
        return currentDVD;
    }

    @Override
    public Dvd editStudio(String title, String newStudioName) throws DvdLibraryDaoException {
        loadDvdFile();
        Dvd currentDVD = dvds.get(title);
        currentDVD.setStudio(newStudioName);
        writeDvdFile();
        return currentDVD;
    }

}
