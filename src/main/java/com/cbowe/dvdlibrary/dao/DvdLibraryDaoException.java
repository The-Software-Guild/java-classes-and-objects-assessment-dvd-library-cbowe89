package com.cbowe.dvdlibrary.dao;

/**
 * The {@code DvdLibraryDaoException} class extends Exception
 * and creates specific exceptions for the DVDLibrary application.
 */

public class DvdLibraryDaoException extends Exception {
    public DvdLibraryDaoException(String message) {
        super(message);
    }

    public DvdLibraryDaoException(String message, Throwable cause) {
        super(message, cause);
    }

}

