package com.company.cssjmixtutorial.screen.main;

import com.company.cssjmixtutorial.screen.home.HomeScreen;
import com.company.cssjmixtutorial.screen.product.ProductBrowse;
import com.company.cssjmixtutorial.screen.product.ProductScreen;
import io.jmix.ui.ScreenTools;
import io.jmix.ui.Screens;
import io.jmix.ui.component.*;
import io.jmix.ui.navigation.Route;
import io.jmix.ui.navigation.UrlRouting;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("MainScreen")
@UiDescriptor("main-screen.xml")
@Route(path = "main", root = true)
public class MainScreen extends Screen implements Window.HasWorkArea {

    @Autowired
    private ScreenTools screenTools;

    @Autowired
    private AppWorkArea workArea;

    private boolean isActive = false;
    @Autowired
    private CssLayout sidebar;

    @Subscribe
    public void onInit(InitEvent event) {


//        1.
        // set data


        // display

//        2.
        // loop -> sidebarNav
        //        cssLayout =
    }



//    @Autowired
//    private Drawer drawer;
//    @Autowired
//    private Button collapseDrawerButton;

    @Override
    public AppWorkArea getWorkArea() {
        return workArea;
    }

//    @Subscribe("collapseDrawerButton")
//    private void onCollapseDrawerButtonClick(Button.ClickEvent event) {
//        drawer.toggle();
//        if (drawer.isCollapsed()) {
//            collapseDrawerButton.setIconFromSet(JmixIcon.CHEVRON_RIGHT);
//        } else {
//            collapseDrawerButton.setIconFromSet(JmixIcon.CHEVRON_LEFT);
//        }
//    }

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        screenTools.openDefaultScreen(
                UiControllerUtils.getScreenContext(this).getScreens());

        screenTools.handleRedirect();
    }

    @Subscribe("hambergerMenu")
    public void onHambergerMenuLayoutClick(LayoutClickNotifier.LayoutClickEvent event) {
        _sidebarHandler();
    }

    @Autowired
    private Screens screens;

    @Autowired
    private UrlRouting urlRouting;

    @Subscribe("navHomepage")
    public void onNavHomepageLayoutClick(LayoutClickNotifier.LayoutClickEvent event) {
        HomeScreen screen = screens.create(HomeScreen.class, OpenMode.THIS_TAB);
        urlRouting.replaceState(screen);
        screen.show();

        isActive = false;
        sidebar.removeStyleName("is-active");
    }

    @Subscribe("navProduct")
    public void onNavProductLayoutClick(LayoutClickNotifier.LayoutClickEvent event) {
        ProductScreen screen = screens.create(ProductScreen.class, OpenMode.THIS_TAB);
        urlRouting.replaceState(screen);
        screen.show();

        isActive = false;
        sidebar.removeStyleName("is-active");
    }

    @Subscribe("navDiscount")
    public void onNavDiscountLayoutClick(LayoutClickNotifier.LayoutClickEvent event) {
        ProductBrowse screen = screens.create(ProductBrowse.class, OpenMode.THIS_TAB);
        urlRouting.replaceState(screen);
        screen.show();

        isActive = false;
        sidebar.removeStyleName("is-active");
    }

    @Subscribe("backdropHandler")
    public void onBackdropHandlerLayoutClick(LayoutClickNotifier.LayoutClickEvent event) {
        _sidebarHandler();
    }

    void _sidebarHandler(){
        isActive = !isActive;

        if (isActive) {
            sidebar.addStyleName("is-active");
        } else {
            sidebar.removeStyleName("is-active");
        }
    }


}
