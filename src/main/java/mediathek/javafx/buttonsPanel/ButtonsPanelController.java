package mediathek.javafx.buttonsPanel;

import javafx.embed.swing.JFXPanel;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import javax.swing.*;
import java.io.IOException;

public class ButtonsPanelController {
    @FXML
    private Tab buttonsTab;

    public static ButtonsPanelController install(JFXPanel parent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ButtonsPanelController.class.getResource("/mediathek/res/programm/fxml/pset_buttons.fxml"));

        TabPane buttonsPane = loader.load();
        final ButtonsPanelController buttonsController = loader.getController();
        buttonsController.setOnCloseRequest(e -> {
            SwingUtilities.invokeLater(() -> parent.setVisible(false));
            e.consume();
        });

        parent.setScene(new Scene(buttonsPane));
        return buttonsController;
    }

    public void setOnCloseRequest(EventHandler<Event> e) {
        buttonsTab.setOnCloseRequest(e);
    }
}
