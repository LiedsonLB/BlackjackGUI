import javafx.application.Application;
import javafx.stage.Stage;
import view.Menu;

public class Main extends Application {

    @Override
    public void start(Stage menu) {
        new Menu(menu);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
