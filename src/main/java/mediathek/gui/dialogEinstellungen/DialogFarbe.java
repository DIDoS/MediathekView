package mediathek.gui.dialogEinstellungen;

import mediathek.tool.EscapeKeyHandler;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class DialogFarbe extends JDialog {
    public Color farbe = null;

    public DialogFarbe(JFrame parent, boolean modal, Color color) {
        super(parent, modal);
        initComponents();
        if (color != null) {
            jColorChooser.setColor(color);
        }
        jColorChooser.getSelectionModel().addChangeListener(new BeobachterFarbe());
        jButtonOk.addActionListener(new BeobachterOk());
        jButtonAbbrechen.addActionListener(new BeobachterAbbrechen());

        EscapeKeyHandler.installHandler(this, this::abbrechen);
    }

    private void ok() {
        this.dispose();
    }

    private void abbrechen() {
        farbe = null;
        this.dispose();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jColorChooser = new javax.swing.JColorChooser();
        jButtonAbbrechen = new javax.swing.JButton();
        jButtonOk = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jButtonAbbrechen.setText("Abbrechen");

        jButtonOk.setText("OK");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jColorChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButtonOk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAbbrechen)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonAbbrechen, jButtonOk});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jColorChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAbbrechen)
                    .addComponent(jButtonOk))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAbbrechen;
    private javax.swing.JButton jButtonOk;
    private javax.swing.JColorChooser jColorChooser;
    // End of variables declaration//GEN-END:variables

    private class BeobachterFarbe implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent arg0) {
            farbe = jColorChooser.getColor();
        }
    }

    private class BeobachterOk implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ok();
        }
    }

    private class BeobachterAbbrechen implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            abbrechen();
        }
    }
}
