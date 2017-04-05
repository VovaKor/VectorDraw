package vectordraw;

import com.google.inject.Guice;
import com.google.inject.Injector;
import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import vectordraw.controllers.MainWindowController;
import vectordraw.util.LangTool;
import vectordraw.view.Canvas;

/**
 *The main class of the project.
 * 
 * @author Volodymyr Korobko
 */
public class VectorDraw extends Application {

    private static Injector injector;

    /**
     * The entry point of the program.
     *
     * @param args
     */
    public static void main(String[] args) {

        launch(args);
    }

    /**
     * @return The dependencies injector of the application.
     */
    public static Injector getInjector() {
        return injector;
    }

    private Pane splashPane;
    private Stage mainStage;
    private ProgressBar loadProgress;

    @Override
    public void init() {
        Image img = new Image("/res/VectorDraw.png");
        ImageView splash = new ImageView(img);
        loadProgress = new ProgressBar();
        loadProgress.setPrefWidth(img.getWidth());
        splashPane = new VBox();
        splashPane.getChildren().addAll(splash, loadProgress);

    }

    @Override
    public void start(final Stage stage) throws IOException {
        final Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws InterruptedException {

                updateProgress(0.1, 1.0);
                try {
                    injector = Guice.createInjector(new VDBindingModule());
                    final Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainWindow.fxml"), LangTool.INSTANCE.getBundle(),
                            new VDBuilderFactory(injector), injector::getInstance);
                    updateProgress(0.6, 1.0);
                    final Scene scene = new Scene(root);
                    updateProgress(0.7, 1.0);
                    scene.getStylesheets().add("/css/style.css");
                    updateProgress(0.8, 1.0);

                    Platform.runLater(() -> {
                        mainStage = new Stage(StageStyle.DECORATED);
                        mainStage.setIconified(true);
                        mainStage.setTitle("VectorDraw");
                        mainStage.setScene(scene);
                        updateProgress(0.9, 1.0);
                        injector.getInstance(MainWindowController.class).centreViewport();
                        injector.getInstance(Canvas.class).requestFocus();
                        updateProgress(1.0, 1.0);
                        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), splashPane);
                        fadeTransition.setFromValue(1.0);
                        fadeTransition.setToValue(0.0);
                        fadeTransition.setOnFinished(evt -> {
                            stage.hide();
                            loadProgress.progressProperty().unbind();
                            mainStage.setMaximized(true);
                            mainStage.show();
                        });
                        fadeTransition.play();
                        
                    });

                } catch (final IOException ex) {
                    ex.printStackTrace();
                }
                return null;
            }
        };
        loadProgress.progressProperty().bind(task.progressProperty());
        final Scene splashScene = new Scene(splashPane);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(splashScene);
        stage.centerOnScreen();
        stage.show();
        new Thread(task).start();
    }
}
