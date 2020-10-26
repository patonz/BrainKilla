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
 * Questa classe eredita da JButton, definisce il pulsante presente nelle
 * sezioni dell'helppanel, e serve per ritornare nel menu
 */
public class InnerHelpButtonBack extends JButton {

    public InnerHelpButtonBack(String name, final JPanel mainpanel, final JPanel removepanel, final JPanel visiblepanel) {

        setText(name);


        setBounds(200, 170, 50, 20);
        setBorder(null);
        addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                mainpanel.remove(removepanel);

                visiblepanel.setVisible(true);
            }
        });
    }
}
