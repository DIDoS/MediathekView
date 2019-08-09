package mediathek.gui.dialog;

import mediathek.config.Daten;
import mediathek.gui.dialogEinstellungen.PanelFilmlisteLaden;
import mediathek.tool.GuiFunktionen;

import javax.swing.*;
import java.awt.*;

public class LoadFilmListDialog extends StandardCloseDialog {
    public LoadFilmListDialog(Frame owner) {
        super(owner,"Filmliste laden",true);
        setResizable(false);
        pack();
        GuiFunktionen.centerOnScreen(this,false);
    }

    @Override
    public JComponent createContentPanel() {
        return new PanelFilmlisteLaden(Daten.getInstance());
    }
}
