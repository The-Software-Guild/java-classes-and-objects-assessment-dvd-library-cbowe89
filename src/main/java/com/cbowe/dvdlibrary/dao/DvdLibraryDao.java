package com.cbowe.dvdlibrary.dao;

import com.cbowe.dvdlibrary.dto.Dvd;
import java.util.List;

/**
 * The {@code DvdLibraryDao} interface defines methods that must be
 * implemented by any class that will act as a DAO (data access object)
 * in the application.
 */

public interface DvdLibraryDao {
    /**
     * Adds the given Dvd to the library and associates it with the
     * given DVD Title. If there is already a dvd associated with the
     * given title it will return that DVD object, otherwise it will
     * return null.
     *
     * @param title with which DVD is to be associated
     * @param dvd DVD to be added to the library
     * @return the DVD object previously associated with the given
     * title if it exists, null otherwise
     * @throws DvdLibraryDaoException prints message
     */
    Dvd addDvd(String title, Dvd dvd) throws DvdLibraryDaoException;

    /**
     * Returns a List of all DVDs in the library.
     *
     * @return a List containing all DVDs in the library.
     * @throws DvdLibraryDaoException prints message
     */
    List<Dvd> getAllDvds() throws DvdLibraryDaoException;

    /**
     * Returns the DVD object associated with the given title.
     * Returns null if no such DVD exists
     *
     * @param title title of the DVD to retrieve
     * @return the DVD object associated with the given title,
     * null if no such DVD exists
     * @throws DvdLibraryDaoException prints message
     */
    Dvd getDvd(String title) throws DvdLibraryDaoException;

    /**
     * Removes from the library the DVD associated with the title.
     * Returns the DVD object that is being removed or null if
     * there is no DVD associated with the title
     *
     * @param title title of DVD to be removed
     * @return DVD object that was removed or null if no DVD
     * was associated with the given title
     * @throws DvdLibraryDaoException prints message
     */
    Dvd removeDvd(String title) throws DvdLibraryDaoException;
    /**
     * Edits the release date of DVD associated with the title.
     * Returns the DVD object that is being edited or null if
     * there is no DVD associated with the title
     *
     * @param title title of DVD to be edited
     * @return DVD object that was edited or null if no DVD
     * was associated with the given title
     * @throws DvdLibraryDaoException prints message
     */
    Dvd editReleaseDate(String title, String newReleaseDate) throws DvdLibraryDaoException;

    /**
     * Edits the MPAA rating of the DVD associated with the title.
     * Returns the DVD object that is being edited or null if
     * there is no DVD associated with the title.
     *
     * @param title title of DVD to be edited
     * @param newMpaaRating rating of DVD
     * @return DVD object that was edited or null if no DVD
     * was associated with the given title
     * @throws DvdLibraryDaoException prints message
     */
    Dvd editMPAA(String title, String newMpaaRating) throws DvdLibraryDaoException;

    /**
     * Edits the director name of the DVD associated with the title.
     * Returns the DVD object that is being edited or null if
     * there is no DVD associated with the title.
     *
     * @param title title of DVD to be edited
     * @param newDirectorName name of film director
     * @return DVD object that was edited or null if no DVD
     * was associated with the given title
     * @throws DvdLibraryDaoException prints message
     */
    Dvd editDirectorName(String title, String newDirectorName) throws DvdLibraryDaoException;

    /**
     * Edits the user rating of the DVD associated with the title.
     * Returns the DVD object that is being edited or null if
     * there is no DVD associated with the title.
     *
     * @param title of DVD to be edited
     * @param newUserRating user rating of DVD
     * @return DVD object that was edited or null if no DVD
     * was associated with the given title
     * @throws DvdLibraryDaoException prints message
     */
    Dvd editUserRating(String title, String newUserRating) throws DvdLibraryDaoException;

    /**
     * Edits the studio of the DVD associated with the title.
     * Returns the DVD object that is being edited or null if
     * there is no DVD associated with the title.
     *
     * @param title of DVD to be edited
     * @param newStudioName studio that released film
     * @return DVD object that was edited or null if no DVD
     * was associated with the give title
     * @throws DvdLibraryDaoException prints message
     */
    Dvd editStudio(String title, String newStudioName) throws DvdLibraryDaoException;
}
