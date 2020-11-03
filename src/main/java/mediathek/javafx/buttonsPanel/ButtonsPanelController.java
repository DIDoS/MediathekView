package mediathek.javafx.buttonsPanel;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import mediathek.config.Daten;
import mediathek.config.Konstanten;
import mediathek.gui.messages.ProgramSetChangedEvent;
import mediathek.tool.ApplicationConfiguration;
import net.engio.mbassy.listener.Handler;
import org.apache.commons.configuration2.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ButtonsPanelController implements Initializable {
    private static final Logger logger = LogManager.getLogger();
    private final Configuration config = ApplicationConfiguration.getConfiguration();
    @FXML
    private Tab buttonsTab;
    @FXML
    private GridPane gridPane;
    @FXML
    private ContextMenu contextMenu;

    public static ButtonsPanelController install(JFXPanel parent) throws IOException {
        logger.trace("install");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ButtonsPanelController.class.getResource("/mediathek/res/programm/fxml/pset_buttons.fxml"));

        TabPane buttonsPane = loader.load();
        final ButtonsPanelController buttonsController = loader.getController();
        parent.setScene(new Scene(buttonsPane));

        return buttonsController;
    }

    public void setOnCloseRequest(EventHandler<Event> e) {
        buttonsTab.setOnCloseRequest(e);
    }

    public void setupButtonLayout() {
        logger.trace("setupButtonLayout called");

        final int maxColumns = ApplicationConfiguration.getConfiguration()
                .getInt(ApplicationConfiguration.APPLICATION_BUTTONS_PANEL_MAX_VISIBLE, Konstanten.DEFAULT_BUTTON_PANEL_COLUMNS);

        gridPane.getChildren().clear();
        gridPane.setPadding(new Insets(5));
        //var listeButton = Daten.listePset.getListeButton();
        final var rows = (int)Math.ceil((double)20 / maxColumns);
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < maxColumns;col++) {
                var l = new Button("col " + col + " row " + row);
                GridPane.setConstraints(l,col,row);
                gridPane.getChildren().add(l);
            }
    }

    @Handler
    private void handleProgramSetChangedEvent(ProgramSetChangedEvent e) {
        Platform.runLater(this::setupButtonLayout);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.trace("initialize");
        Daten.getInstance().getMessageBus().subscribe(this);
        gridPane.setOnContextMenuRequested(e -> contextMenu.show(gridPane, e.getScreenX(), e.getScreenY()));
    }

    public void onColumnCountChange(Event e) {
        final int numCols = Integer.parseInt(((MenuItem) e.getSource()).getText());
        config.setProperty(ApplicationConfiguration.APPLICATION_BUTTONS_PANEL_MAX_VISIBLE, numCols);
        setupButtonLayout();
    }

}
