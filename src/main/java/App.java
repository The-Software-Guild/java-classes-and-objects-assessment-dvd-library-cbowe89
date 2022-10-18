import controller.DvdLibraryController;
import dao.DvdLibraryDaoFileImpl;
import dao.DvdLibraryDao;
import ui.DvdLibraryView;
import ui.UserIO;
import ui.UserIOConsoleImpl;

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
