package sk.microservise.client.client;

import javafx.fxml.FXMLLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;

@Component
public class ContecstFXMLLoader {
    @Autowired
    private ConfigurableApplicationContext context;

    public FXMLLoader getLoader(URL url){
        FXMLLoader loader = new FXMLLoader(url);
        loader.setControllerFactory(context::getBean);
        return loader;
    }

    public FXMLLoader getLoader(String path){
        return getLoader(getClass().getResource(path));
    }
}
