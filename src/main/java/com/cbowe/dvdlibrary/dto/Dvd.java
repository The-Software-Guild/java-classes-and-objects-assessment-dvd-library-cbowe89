package com.cbowe.dvdlibrary.dto;

/**
 * The {@code Dvd} class is part of the DTO (data transfer object)
 * package. It is used to move data between tiers in the application.
 * It contains private variables, the constructor to create a new
 * Dvd object, and the getter and setter methods for the private
 * variables.
 */

public class Dvd {
    // Declare private variables
    private String dvdTitle;
    private String releaseDate;
    private String mpaa;
    private String directorsName;
    private String studio;
    private String userRating;

    /**
     * Constructor for new Dvd object.
     * @param title Dvd title
     */
    public Dvd(String title) {
        this.dvdTitle = title;
    }

    /**
     * Method to get title from Dvd object
     * @return dvdTitle
     */
    public String getTitle() {
        return dvdTitle;
    }

    /**
     * Method to set release date of Dvd object.
     * @param newReleaseDate release date
     */
    public void setReleaseDate(String newReleaseDate) {
        this.releaseDate = newReleaseDate;
    }

    /**
     * Method to get release date from Dvd object
     * @return releaseDate
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * Method to set MPAA rating of Dvd object.
     * @param newMpaaRating MPAA rating
     */
    public void setMPAA(String newMpaaRating) {
        this.mpaa = newMpaaRating;
    }

    /**
     * Method to get MPAA rating from Dvd object
     * @return mpaa
     */
    public String getMPAA() {
        return mpaa;
    }

    /**
     * Method to set director name of Dvd object.
     * @param newDirectorName Director name
     */
    public void setDirectorsName(String newDirectorName) {
        this.directorsName = newDirectorName;
    }

    /**
     * Method to get director name from Dvd object
     * @return directorsName
     */
    public String getDirectorsName() {
        return directorsName;
    }

    /**
     * Method to set studio name of Dvd object.
     * @param newStudioName studio name
     */
    public void setStudio(String newStudioName) {
        this.studio = newStudioName;
    }

    /**
     * Method to get studio name from Dvd object
     * @return studio
     */
    public String getStudio() {
        return studio;
    }

    /**
     * Method to set user rating of Dvd object.
     * @param newUserRating user rating
     */
    public void setUserRating(String newUserRating) {
        this.userRating = newUserRating;
    }

    /**
     * Method to get user rating from Dvd object
     * @return userRating
     */
    public String getUserRating() {
        return userRating;
    }
}
