package sk.microservise.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import sk.microservise.client.client.ViewManager;

@SpringBootApplication
public class ClientApplication extends Application {

    private ConfigurableApplicationContext context;


    public static void main(String[] args) {
        launch(ClientApplication.class);
    }

    @Override
    public void init() throws Exception {
        context = SpringApplication.run(ClientApplication.class);

    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Admin log in");
        ViewManager manager = context.getBean(ViewManager.class);
        stage.setScene(manager.createLogInScene());
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        context.close();
        Platform.exit();
    }
}
