package com.cbowe.dvdlibrary.ui;

import com.cbowe.dvdlibrary.dto.Dvd;
import java.util.List;

/**
 * The {@code DvdLibraryView} class is used by the DvdLibraryController
 * to create and determine the layout of menus, display prompts and
 * receive input for numerous methods, and display banners and messages.
 */

public class DvdLibraryView {
    // Declare UserIO object
    private UserIO io;

    /**
     * Constructor initializes a UserIO object via dependency injection.
     *
     * @param io UserIO object
     */
    public DvdLibraryView(UserIO io) {
        this.io = io; // Initialize io object
    }

    /**
     * Method displays the Main Menu with options, prompts user to
     * choose an option, and returns the user's selection
     *
     * @return menu selection
     */
    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List DVDs");
        io.print("2. Create New DVD");
        io.print("3. View a DVD");
        io.print("4. Edit a DVD");
        io.print("5. Remove a DVD");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.", 1, 6);
    }

    /**
     * Method displays the Edit DVD Menu with options, prompts user to
     * choose and option, and returns the user's selection
     *
     * @return edit menu selection
     */
    public int printEditMenuAndGetSelection() {
        io.print("Edit DVD Menu");
        io.print("1. Edit Release Date");
        io.print("2. Edit MPAA Rating");
        io.print("3. Edit Director's Name");
        io.print("4. Edit Studio Name");
        io.print("5. Edit User Rating");
        io.print("6. Exit Edit Menu");

        return io.readInt("Please select from the above choices.", 1, 6);
    }

    /**
     * Method prompts the user for DVD title, release date, MPAA rating,
     * director name, studio name, and user rating. A new Dvd object is
     * then created, setter methods are used to provide the collected
     * information the Dvd object, and the Dvd object is returned to the caller.
     *
     * @return new Dvd object
     */
    public Dvd getNewDvDInfo() {
        String dvdTitle = io.readString("Please enter DVD Title");
        String releaseDate = io.readString("Please enter Release Date");
        String mpaaRating = io.readString("Please enter MPAA Rating");
        String directorName = io.readString("Please enter Direct Name");
        String studioName = io.readString("Please enter Studio Name");
        String userRating = io.readString("Please enter User Rating");

        Dvd currentDvd = new Dvd(dvdTitle);
        currentDvd.setReleaseDate(releaseDate);
        currentDvd.setMPAA(mpaaRating);
        currentDvd.setDirectorsName(directorName);
        currentDvd.setStudio(studioName);
        currentDvd.setUserRating(userRating);

        return currentDvd;
    }

    /**
     * Method displays a banner to the UI indicating that the next
     * interactions on the screen will be for creating a new Dvd object.
     */
    public void displayCreateDvDBanner() {
        io.print("=== Create DvD ===");
    }

    /**
     * Method displays a message that the new Dvd object was successfully created
     * and waits for the user to hit Enter to continue.
     */
    public void displayCreateSuccessBanner() {
        io.readString("DVD successfully created. Please hit enter to continue.");
    }

    /**
     * Method takes a list of Dvd objects as a parameter and displays the
     * information for each Dvd to the screen.
     *
     * @param dvdList list of Dvd objects
     */
    public void displayDvdList(List<Dvd> dvdList) {
        // Iterate over dvdList
        for (Dvd currentDvd : dvdList) {
            // String will display Dvd title and MPAA rating only to prevent crowding
            // Further information can be seen by viewing individual Dvd object
            String dvdInfo = String.format("%s : %s", currentDvd.getTitle(), currentDvd.getMPAA());
            // Print currentDvd info
            io.print(dvdInfo);
        }
        io.readString("Please hit enter to continue.");
    }

    /**
     * Method displays a banner to the UI indicating that the next
     * interactions on the screen will be for displaying all Dvds.
     */
    public void displayDisplayAllBanner() {
        io.print("=== Display All DVDs ===");
    }

    /**
     * Method displays a banner to the UI indicating that the next
     * interactions on the screen will be for displaying an individual
     * DVD's information.
     */
    public void displayDisplayDvdBanner() {
        io.print("=== Display DVD ===");
    }

    /**
     * Method prompts the user, gets the DVD tile, and returns the title.
     *
     * @return dvd title
     */
    public String getDvdTitleChoice() {
        return io.readString("Please enter the DVD title.");
    }

    /**
     * Method displays information on the Dvd object that is passed to it,
     * or displays that Dvd does not exist, then waits for the user to hit
     * Enter to continue.
     *
     * @param dvd Dvd object
     */
    public void displayDvd(Dvd dvd) {
        if (dvd != null) {
            io.print(dvd.getReleaseDate());
            io.print(dvd.getMPAA());
            io.print(dvd.getDirectorsName());
            io.print(dvd.getStudio());
            io.print(dvd.getUserRating());
            io.print("");
        }
        else {
            io.print("No such DVD.");
        }

        io.readString("Please hit enter to continue.");
    }

    /**
     * Method displays a banner to the UI indicating that the next
     * interactions on the screen will be for removing a Dvd object.
     */
    public void displayRemoveDvdBanner() {
        io.print("=== Remove DVD ===");
    }

    /**
     * Method displays a message that the Dvd object was successfully
     * removed and waits for the user to hit Enter to continue.
     */
    public void displayRemoveResult(Dvd dvdRecord) {
        if (dvdRecord != null) {
            io.print("DVD successfully removed.");
        }
        else {
            io.print("No such DVD.");
        }

        io.readString("Please hit enter to continue.");
    }

    /**
     * Method displays a banner to the UI indicating that the
     * program is exiting.
     */
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    /**
     * Method displays a banner to the UI indicating that the input
     * received is an unknown command.
     */
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    /**
     * Method displays an Error banner and error message to the UI.
     */
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    /**
     * Method displays a banner to the UI indicating that the next
     * interactions on the screen will be for editing a Dvd object.
     */
    public void displayEditDvdBanner() {
        io.print("=== Edit DVD ===");
    }

    /**
     * Method displays a message that the Dvd object was successfully
     * edited and waits for the user to hit Enter to continue.
     */
    public void displayEditDvdSuccess() {
        io.readString("DVD successfully Edited.  Please hit enter to continue");
    }

    /**
     * Method displays a banner to the UI indicating that the next
     * interactions on the screen will be for editing the release
     * date of a Dvd object.
     */
    public void displayEditReleaseDateBanner() {
        io.print("=== Edit DVD Release Date ===");
    }

    /**
     * Method displays a banner to the UI indicating that the next
     * interactions on the screen will be for editing the MPAA rating
     * of a Dvd object.
     */
    public void displayEditMpaaBanner() {
        io.print("=== Edit DVD MPAA rating ===");
    }

    /**
     * Method displays a banner to the UI indicating that the next
     * interactions on the screen will be for editing the director
     * name of a Dvd object.
     */
    public void displayEditDirectorNameBanner() {
        io.print("=== Edit DVD Director's Name ===");
    }

    /**
     * Method displays a banner to the UI indicating that the next
     * interactions on the screen will be for editing the studio
     * name of a Dvd object.
     */
    public void displayEditStudio() {
        io.print("=== Edit DVD Studio ===");
    }

    /**
     * Method displays a banner to the UI indicating that the next
     * interactions on the screen will be for editing the user
     * rating of a Dvd object.
     */
    public void displayEditUserRating() {
        io.print("=== Edit DVD User Rating ===");
    }

    /**
     * Method prompts the user, gets the new release date, and returns
     * the release date.
     *
     * @return DVD release date
     */
    public String getNewReleaseDate() {
        return io.readString("Please enter new release date.");
    }

    /**
     * Method prompts the user, gets the new MPAA rating, and returns
     * the MPAA rating.
     *
     * @return DVD MPAA rating
     */
    public String getNewMpaaRating() {
        return io.readString("Please enter new MPAA rating.");
    }

    /**
     * Method prompts the user, gets the new Director name, and returns
     * the Director name.
     *
     * @return DVD Director name
     */
    public String getNewDirectorName() {
        return io.readString("Please enter new director's name.");
    }

    /**
     * Method prompts the user, gets the new user rating, and returns
     * the user rating.
     *
     * @return DVD user rating
     */
    public String getNewUserRating() {
        return io.readString("Please enter new user rating.");
    }

    /**
     * Method prompts the user, gets the new studio name, and returns
     * the studio name.
     *
     * @return DVD studio name
     */
    public String getNewStudio() {
        return io.readString("Please enter new studio.");
    }

    /**
     * Method displays a message that no such the Dvd object
     * exists and waits for the user to hit Enter to continue.
     */
    public void displayNullDVD() {
        io.print("No such DVD.");
        io.readString("Please hit enter to continue.");
    }
}

