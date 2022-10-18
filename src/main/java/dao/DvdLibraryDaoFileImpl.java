package dao;

import dto.Dvd;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DvdLibraryDaoFileImpl implements DvdLibraryDao {

    public final String DVD_FILE ;
    public static final String DELIMITER = "::";
    /*
    Hash Map to store and retrieve the dvd with title name
     */
    private Map<String, Dvd> dvds = new HashMap<>();

    public DvdLibraryDaoFileImpl(){ //no arg constructor typically used
        DVD_FILE = "dvdTest.txt";
    }
    public DvdLibraryDaoFileImpl(String libraryTextFile){
        DVD_FILE = libraryTextFile;
    }

    @Override
    public Dvd addDvd(String title, Dvd dvd)
    {

        //implement
    }

    /*
    This code gets all of the DvDs objects out of the dvds map as a collection by calling the values() method.
     */
    @Override
    public List<Dvd> getAllDvds() throws DvdLibraryDaoException {

        //implement
    }

    /*
    This method is quite simple — we just ask the dvds map for the dvd object with the given title and return it
     */
    @Override
    public Dvd getDvd(String title)throws DvdLibraryDaoException {

        //implement
    }

    @Override
    public Dvd removeDvd(String title) throws DvdLibraryDaoException{

        //implement
    }



    /*
    Method to unmarshall the object or read a line of string
    from the line and convert it into an object
     */
    private Dvd unmarshallDvd(String dvdAsText) {
        //implement
    }

    /*
    Method to Load file DVD_FILE into memory
     */
    private void loadDvdFile() throws DvdLibraryDaoException {
        //implement
    }

    private String marshallDvd(Dvd aDvd) {
        //implement
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

        // Write out the DvD objects to the DVD file.
        // NOTE TO THE APPRENTICES: We could just grab the dvd map,
        // get the Collection of dvd and iterate over them but we've
        // already created a method that gets a List of dvds so
        // we'll reuse it.
        //implement
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
