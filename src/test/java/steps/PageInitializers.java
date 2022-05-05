package steps;

import pages.DashboardPage;
import pages.EmAddPage;
import pages.EmSearchPage;
import pages.LoginPage;

public class PageInitializers {
    public static LoginPage loginPage;
    public static EmSearchPage emSearchPage;
    public static EmAddPage emAddPage;
    public static DashboardPage dashboardPage;

    public static void initializePageObjects() {
        loginPage = new LoginPage();
        emSearchPage = new EmSearchPage();
        emAddPage = new EmAddPage();
        dashboardPage = new DashboardPage();
    }
}
