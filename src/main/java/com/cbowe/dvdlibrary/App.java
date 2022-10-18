package com.cbowe.dvdlibrary;

import com.cbowe.dvdlibrary.controller.DvdLibraryController;
import com.cbowe.dvdlibrary.dao.DvdLibraryDaoFileImpl;
import com.cbowe.dvdlibrary.dao.DvdLibraryDao;
import com.cbowe.dvdlibrary.ui.DvdLibraryView;
import com.cbowe.dvdlibrary.ui.UserIO;
import com.cbowe.dvdlibrary.ui.UserIOConsoleImpl;

public class App {

    public static void main(String[] args) {
        // Instantiate the UserIO, DvdLibraryView, DvdLibraryDao, and DvdLibraryController
        UserIO myIo = new UserIOConsoleImpl();
        DvdLibraryView myView = new DvdLibraryView(myIo);
        DvdLibraryDao myDao = new DvdLibraryDaoFileImpl();
        DvdLibraryController controller = new DvdLibraryController(myDao, myView);

        // Call run method
        controller.run();
    }
}
