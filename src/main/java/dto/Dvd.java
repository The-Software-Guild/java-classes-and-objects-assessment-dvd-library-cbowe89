package dto;

import java.util.Date;

public class Dvd {
    private String dvdTitle;
    private String releaseDate;
    private String mpaa;
    private String directorsName;
    private String studio;
    private String userRating;

    public Dvd(String title) {
        this.dvdTitle = title;
    }

    public String getTitle() {
        return dvdTitle;
    }

    public void setReleaseDate(String newReleaseDate) {
        this.releaseDate = newReleaseDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setMPAA(String newMpaaRating) {
        this.mpaa = newMpaaRating;
    }

    public String getMPAA() {
        return mpaa;
    }

    public void setDirectorsName(String newDirectorName) {
        this.directorsName = newDirectorName;
    }

    public String getDirectorsName() {
        return directorsName;
    }

    public void setStudio(String newStudioName) {
        this.studio = newStudioName;
    }

    public String getStudio() {
        return studio;
    }

    public void setUserRating(String newUserRating) {
        this.userRating = newUserRating;
    }

    public String getUserRating() {
        return userRating;
    }
}
