package dto;

import java.util.Date;

public class Dvd {
    private String title;
    private String ReleaseDate;
    private String MPAA;
    private String DirectorsName;
    private String Studio;
    private String UserRating;

    public Dvd(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setReleaseDate(String newReleaseDate) {
    }

    public String getReleaseDate() {
        return ReleaseDate;
    }

    public void setMPAA(String newMpaaRating) {
    }

    public String getMPAA() {
        return MPAA;
    }

    public void setDirectorsName(String newDirectorName) {
    }

    public String getDirectorsName() {
        return DirectorsName;
    }

    public void setUserRating(String newUserRating) {
    }

    public String getStudio() {
        return Studio;
    }

    public void setStudio(String newStudioName) {
    }

    public String getUserRating() {
        return UserRating;
    }
}
