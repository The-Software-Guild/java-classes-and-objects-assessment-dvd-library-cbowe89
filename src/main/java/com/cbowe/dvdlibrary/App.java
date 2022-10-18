package com.cbowe.dvdlibrary;

// Import DvdLibraryController from controller package
import com.cbowe.dvdlibrary.controller.DvdLibraryController;
// Import DvdLibraryDao and DvdLibraryDaoFileImpl from dao package
import com.cbowe.dvdlibrary.dao.DvdLibraryDao;
import com.cbowe.dvdlibrary.dao.DvdLibraryDaoFileImpl;
// Import DvdLibraryView, UserIO, and UserIOConsoleImpl from ui package
import com.cbowe.dvdlibrary.ui.DvdLibraryView;
import com.cbowe.dvdlibrary.ui.UserIO;
import com.cbowe.dvdlibrary.ui.UserIOConsoleImpl;

public class App {

    public static void main(String[] args) {
        // Instantiate UserIOConsoleImpl, DvdLibraryView,
        // DvdLibraryDaoFileImpl, and DvdLibraryController objects
        UserIO myIo = new UserIOConsoleImpl();
        DvdLibraryView myView = new DvdLibraryView(myIo);
        DvdLibraryDao myDao = new DvdLibraryDaoFileImpl();
        DvdLibraryController controller = new DvdLibraryController(myDao, myView);

        // Call run method
        controller.run();
    }
}
