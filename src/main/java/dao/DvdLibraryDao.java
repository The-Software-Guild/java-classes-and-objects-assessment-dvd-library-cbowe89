package dao;

import dto.Dvd;
import java.util.List;

public interface DvdLibraryDao {
    /**
     * Adds the given Dvd to the library and associates it with the given
     * Title. If there is already a dvd associated with the given
     * title it will return that dvd object, otherwise it will
     * return null.
     *
     * @param title with which dvd is to be associated
     * @param dvd dvd to be added to the library
     * @return the dvd object previously associated with the given
     * title if it exists, null otherwise
     * @throws DvdLibraryDaoException prints msg
     */
    Dvd addDvd(String title, Dvd dvd) throws DvdLibraryDaoException;

    /**
     * Returns a List of all dvds in the library.
     *
     * @return List containing all dvds in the library.
     * @throws DvdLibraryDaoException prints msg
     */
    List<Dvd> getAllDvds() throws DvdLibraryDaoException;

    /**
     * Returns the dvd object associated with the given title.
     * Returns null if no such dvd exists
     *
     * @param title title of the dvd to retrieve
     * @return the dvd object associated with the given title,
     * null if no such dvd exists
     * @throws DvdLibraryDaoException prints msg
     */
    Dvd getDvd(String title) throws DvdLibraryDaoException;

    /**
     * Removes from the library the dvd associated with the title.
     * Returns the dvd object that is being removed or null if
     * there is no dvd associated with the title
     *
     * @param title title of dvd to be removed
     * @return dvd object that was removed or null if no dvd
     * was associated with the given title
     * @throws DvdLibraryDaoException prints msg
     */
    Dvd removeDvd(String title) throws DvdLibraryDaoException;
    /**
     * Edits the release date of dvd associated with the title.
     * Returns the dvd object that is being edited or null if
     * there is no dvd associated with the title
     *
     * @param title title of dvd to be edited
     * @return dvd object that was edited or null if no dvd
     * was associated with the given title
     * @throws DvdLibraryDaoException prints msg
     */
    Dvd editReleaseDate(String title, String newReleaseDate) throws DvdLibraryDaoException;

    /**
     * Edits the MPAA rating of the dvd associated with the title.
     * Returns the dvd object that is being edited or null if
     * there is no dvd associated with the title.
     *
     * @param title title of dvd to be edited
     * @param newMpaaRating rating of dvd
     * @return dvd object that was edited or null if no dvd
     * was associated with the given title
     * @throws DvdLibraryDaoException prints msg
     */
    Dvd editMPAA(String title, String newMpaaRating) throws DvdLibraryDaoException;

    /**
     * Edits the director name of the dvd associated with the title.
     * Returns the dvd object that is being edited or null if
     * there is no dvd associated with the title.
     *
     * @param title title of dvd to be edited
     * @param newDirectorName name of film director
     * @return dvd object that was edited or null if no dvd
     * was associated with the given title
     * @throws DvdLibraryDaoException prints msg
     */
    Dvd editDirectorName(String title, String newDirectorName) throws DvdLibraryDaoException;

    /**
     * Edits the user rating of the dvd associated with the title.
     * Returns the dvd object that is being edited or null if
     * there is no dvd associated with the title.
     *
     * @param title of dvd to be edited
     * @param newUserRating user rating of dvd
     * @return dvd object that was edited or null if no dvd
     * was associated with the given title
     * @throws DvdLibraryDaoException prints msg
     */
    Dvd editUserRating(String title, String newUserRating) throws DvdLibraryDaoException;

    /**
     * Edits the studio of the dvd associated with the title.
     * Returns the dvd object that is being edited or null if
     * there is no dvd associated with the title.
     *
     * @param title of dvd to be edited
     * @param newStudioName studio that released film
     * @return dvd object that was edited or null if no dvd
     * was associated with the give title
     * @throws DvdLibraryDaoException prints msg
     */
    Dvd editStudio(String title, String newStudioName) throws DvdLibraryDaoException;
}
