package fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    private Stage primaryStage;

    public Main() {

    }


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Main");

        initRootLayout();
    }

    private void initializeController() {

    }


    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/main.fxml"));
            Scene scene = new Scene(loader.load());

            primaryStage.setScene(scene);
            primaryStage.show();

            addController(loader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addController(FXMLLoader loader) {
        MainController mainController = loader.getController();
        mainController.setMain(this);
    }


    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }


}


