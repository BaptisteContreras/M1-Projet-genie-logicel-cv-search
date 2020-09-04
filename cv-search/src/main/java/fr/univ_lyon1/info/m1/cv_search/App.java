package fr.univ_lyon1.info.m1.cv_search;


import fr.univ_lyon1.info.m1.cv_search.controller.CvSearchController;
import fr.univ_lyon1.info.m1.cv_search.model.CvSearchModel;
import fr.univ_lyon1.info.m1.cv_search.view.CvSearchView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class for the application (structure imposed by JavaFX).
 */
public class App extends Application {

    /**
     * A main method in case the user launches the application using
     * App as the main class.
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * With javafx, start() is called when the application is launched.
     */
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Cv-search : 2.0");

        // Create Model
        CvSearchModel defaultModel = new CvSearchModel();

        // Create Controller
        CvSearchController defaultController = new CvSearchController(defaultModel);

        // Create View
        CvSearchView defaultView = new CvSearchView(defaultController, defaultModel);

        // Create Scene
        Scene defaultScene = new Scene(defaultView.asParent(), 950, 600);
        Stage s = new Stage();

        // Display Scene
        stage.setResizable(false);
        stage.setScene(defaultScene);
        stage.show();


        /*
        Pour tester la creation d'une seconde vue liee avec le meme controller, model
        CvSearchView view2 = new CvSearchView(defaultController, defaultModel);
        Scene secondScene = new Scene(view2.asParent(), 600, 600);
        Stage second = new Stage();
        second.setScene(secondScene);
        second.show();
         */

    }
}
