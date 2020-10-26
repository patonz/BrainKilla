/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brainkilla.GUI;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Leonardo Montecchiari
 */
/*
 * Questa classe eredita da JButton, definisce il pulsante presente nel menu
 * dell'helppanel, e serve per accedere alle sezioni
 */
public class InnerHelpButtonMenu extends JButton {

    public InnerHelpButtonMenu(String name, final JPanel mainpanel, final JPanel addpanel, final JPanel visiblepanel, int numberposbutton) {
        setText(name);
        setBounds(140, 10 + (numberposbutton * 20), 169, 15);
        setBorder(null);
        addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                visiblepanel.setVisible(false);

                mainpanel.add(addpanel);

            }
        });
    }
}
