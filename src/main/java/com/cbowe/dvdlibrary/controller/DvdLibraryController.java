package com.cbowe.dvdlibrary.controller;

import com.cbowe.dvdlibrary.dao.DvdLibraryDao;
import com.cbowe.dvdlibrary.dao.DvdLibraryDaoException;
import com.cbowe.dvdlibrary.dto.Dvd;
import com.cbowe.dvdlibrary.ui.DvdLibraryView;
import java.util.List;

/**
 * The {@code DvdLibraryController} class orchestrates the actions
 * of the other components in the application to accomplish the
 * application's goals.
 * It controls the flow of the application, allows for navigation
 * via menus, and provides methods to interact with the DvdLibrary.
 */

public class DvdLibraryController {

    // Declare DvdLibraryView and DvdLibraryDao objects
    private DvdLibraryView view;
    private DvdLibraryDao dao;

    /**
     * Constructor initializes the view and dao objects via
     * dependency injection.
     *
     * @param dao DvdLibraryDao object
     * @param view DvdLibraryView object
     */
    public DvdLibraryController(DvdLibraryDao dao, DvdLibraryView view) {
        //Initialize Dao and View
        this.dao = dao;
        this.view = view;
    }

    /**
     * Method controls the application when the menu systems is displayed.
     */
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {
                // Update menu selection from user input
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listDvds();
                        break;
                    case 2:
                        createDvd();
                        break;
                    case 3:
                        viewDvd();
                        break;
                    case 4:
                        editDvd();
                        break;
                    case 5:
                        removeDvd();
                        break;
                    case 6: // Option to exit application
                        keepGoing = false;
                        break;
                    default:
                        // menuSelection is not a valid input
                        unknownCommand();
                }

            }
        } catch (DvdLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
        exitMessage();
    }

    /**
     * Private controller method. Called in the run() method
     * to print menu, prompt for and get user selection.
     *
     * @return menu selection
     */
    private int getMenuSelection() {
        // Call to printMenuAndGetSelection() from the
        // DvdLibraryView class
        return view.printMenuAndGetSelection();
    }

    /**
     * Method to orchestrate the creation of a new Dvd object.
     *
     * @throws DvdLibraryDaoException prints message
     */
    private void createDvd() throws DvdLibraryDaoException {
        // Display the Create DVD banner
        view.displayCreateDvDBanner();
        // Prompt for and get all data from user to create a new Dvd object
        Dvd newDvd = view.getNewDvDInfo();
        // Store the new Dvd object
        dao.addDvd(newDvd.getTitle(), newDvd);
        // Display the Create DVD Success banner
        view.displayCreateSuccessBanner();
    }

    /**
     * Method to display a list of all Dvd objects to the user
     *
     * @throws DvdLibraryDaoException prints message
     */
    private void listDvds() throws DvdLibraryDaoException {
        // Display the Display All Banner
        view.displayDisplayAllBanner();
        // Retrieve all Dvd objects in the system from the DAO
        // and store as a List
        List<Dvd> dvdList = dao.getAllDvds();
        // Display the list of Dvds
        view.displayDvdList(dvdList);
    }

    /**
     * Method to view all information for a single Dvd object
     *
     * @throws DvdLibraryDaoException prints message
     */
    private void viewDvd() throws DvdLibraryDaoException {
        // Display the Display DVD Banner
        view.displayDisplayDvdBanner();
        // Get title of Dvd that the user wants to view
        String dvdTitle = view.getDvdTitleChoice();
        // Get Dvd object from the dao
        Dvd dvd = dao.getDvd(dvdTitle);
        // Display the Dvd object
        view.displayDvd(dvd);
    }

    /**
     * Method to remove a Dvd object from the library
     *
     * @throws DvdLibraryDaoException prints message
     */
    private void removeDvd() throws DvdLibraryDaoException {
        // Display the Remove DVD Banner
        view.displayRemoveDvdBanner();
        // Get title of Dvd that the user wants to remove
        String dvdTitle = view.getDvdTitleChoice();
        // Remove the Dvd object from library via the dao
        Dvd removedDvd = dao.removeDvd(dvdTitle);
        // Display result for Dvd object removal
        view.displayRemoveResult(removedDvd);
    }

    /**
     * Method controls the application when the user wishes to edit
     * a DVD in the library. A menu is displayed, similar to the main
     * menu, for the user to select what information to edit.
     *
     * @throws DvdLibraryDaoException prints message
     */
    private void editDvd() throws DvdLibraryDaoException {
        // Display the Edit DVD Banner
        view.displayEditDvdBanner();
        // Get title of Dvd that the user wants to edit
        String title = view.getDvdTitleChoice();
        // Get Dvd object that user wants to edit
        Dvd currentDVD = dao.getDvd(title);

        if (currentDVD == null) {
            view.displayNullDVD();
        }
        else {
            // Display currentDvd object
            view.displayDvd(currentDVD);
            int editMenuSelection = 0;
            boolean keepGoing = true;
            while (keepGoing) {
                // Update selection for the edit menu from user input
                editMenuSelection = getEditMenuSelection();

                switch (editMenuSelection) {
                    case 1:
                        editReleaseDate(title);
                        break;
                    case 2:
                        editMPAA(title);
                        break;
                    case 3:
                        editDirectorName(title);
                        break;
                    case 4:
                        editStudioName(title);
                        break;
                    case 5:
                        editUserRating(title);
                        break;
                    case 6: // Option to exit edit
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
                if (keepGoing == false) {
                    break;
                }
            }
        }
    }

    /**
     * Method asks the view object to display the edit menu,
     * prompts the user to make a selection, gets the user input,
     * and returns the int selection.
     *
     * @return edit menu selection
     */
    private int getEditMenuSelection(){
        return view.printEditMenuAndGetSelection();
    }

    /**
     * Method allows the release date of a Dvd object to be edited.
     *
     * @param title title of DVD to be edited
     * @throws DvdLibraryDaoException prints message
     */
    private void editReleaseDate(String title) throws DvdLibraryDaoException {
        // Display the Edit Release Date Banner
        view.displayEditReleaseDateBanner();
        // Prompt for and get new release date
        String newReleaseDate = view.getNewReleaseDate();
        // Update the release date of the Dvd object
        dao.editReleaseDate(title, newReleaseDate);
        // Display message for successful editing
        view.displayEditDvdSuccess();
    }

    /**
     * Method allows the MPAA rating of a Dvd object to be edited.
     *
     * @param title title of DVD to be edited
     * @throws DvdLibraryDaoException prints message
     */
    private void editMPAA(String title) throws DvdLibraryDaoException {
        // Display the Edit MPAA Rating Banner
        view.displayEditMpaaBanner();
        // Prompt for and get new MPAA rating
        String newMpaaRating = view.getNewMpaaRating();
        // Update the MPAA rating of the Dvd object
        dao.editMPAA(title, newMpaaRating);
        // Display message for successful editing
        view.displayEditDvdSuccess();
    }

    /**
     * Method allows the director name of a Dvd object to be edited.
     *
     * @param title title of DVD to be edited
     * @throws DvdLibraryDaoException prints message
     */
    private void editDirectorName(String title) throws DvdLibraryDaoException {
        // Display the Edit Director Name Banner
        view.displayEditDirectorNameBanner();
        // Prompt for and get new Director name
        String newDirectorName = view.getNewDirectorName();
        // Update the director name of the Dvd object
        dao.editDirectorName(title, newDirectorName);
        // Display message for successful editing
        view.displayEditDvdSuccess();
    }

    /**
     * Method allows the user rating of a Dvd object to be edited.
     *
     * @param title title of DVD to be edited
     * @throws DvdLibraryDaoException prints message
     */
    private void editUserRating(String title) throws DvdLibraryDaoException {
        // Display the Edit User Rating Banner
        view.displayEditUserRating();
        // Prompt for and get new user rating
        String newUserRating = view.getNewUserRating();
        // Update the user rating of the Dvd object
        dao.editUserRating(title, newUserRating);
        // Display message for successful editing
        view.displayEditDvdSuccess();
    }

    /**
     * Method allows the studio name of a Dvd object to be edited.
     *
     * @param title title of DVD to be edited
     * @throws DvdLibraryDaoException prints message
     */
    private void editStudioName(String title) throws DvdLibraryDaoException {
        // Display the Edit Studio Banner
        view.displayEditStudio();
        // Prompt for and get new Studio name
        String newStudioName = view.getNewStudio();
        // Update the studio name of the Dvd object
        dao.editStudio(title, newStudioName);
        // Display message for successful editing
        view.displayEditDvdSuccess();
    }


    /**
     * Method asks the view to display banner when an unknown
     * command is received.
     * Ex: Menu options are 1 - 6, but the user input is 7.
     */
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    /**
     * Method asks the view to display the Exit Banner
     */
    private void exitMessage() {
        view.displayExitBanner();
    }

}
