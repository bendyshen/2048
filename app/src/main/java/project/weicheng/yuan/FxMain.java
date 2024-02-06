package project.weicheng.yuan;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main entry class for the JavaFX application. This class provides the primary methods for starting
 * the JavaFX application, setting the root scene, and loading FXML files.
 */
@Generated
public class FxMain extends Application {

    /** The primary scene for the JavaFX application. */
    private static Scene scene;

    /**
     * Starts the JavaFX application by setting the main stage and its associated scene.
     *
     * @param stage The primary stage for the application.
     * @throws IOException if there is an issue loading the FXML file.
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("welcome"), 600, 500);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sets the root for the main scene to the given FXML file.
     *
     * @param fxml The name of the FXML file without the extension.
     * @throws IOException if there is an issue loading the FXML file.
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Loads and returns the root node for the specified FXML file.
     *
     * @param fxml The name of the FXML file without the extension.
     * @return A Parent node containing the hierarchy of nodes defined in the FXML file.
     * @throws IOException if there is an issue loading the FXML file.
     */
    private static Parent loadFXML(String fxml) throws IOException {
        URL path = FxMain.class.getResource("/" + fxml + ".fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(path);
        return fxmlLoader.load();
    }
    
}
