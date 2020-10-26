package brainkilla.GUI;

import brainkilla.Engine.GameLevel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import util.Resource;

/**
 *
 * @author Leonardo Montecchiari
 */
/*
 * questa classe eredita da JPanel, definisce la schermata di ogni fine livello
 * che puo essere creata a piacere definendo i parametri relativi al colore
 * dello sfondo, nome dell'immagine da mettere a video con relativa altezza e
 * larghezza, colore del pulsante con relativa altezza e larghezza
 */
public class EndLevelPanel extends JPanel {

    private JLabel loadicon = null;
    private JButton returmainframe = null;
    private String buttonName = null;

    public EndLevelPanel(final GameLevel gameLevel, final GameFrame gameFrame, Color backgroundColor, String iconName, int widhtIcon, int heightIcon, Color buttonColor, String buttonName, int widhtButton, int heightButton, ActionListener buttonActionListener) {

        this.buttonName = buttonName;
        setLayout(null);

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        setBounds(0, 0, d.width, d.height);
        setBackground(backgroundColor);
        loadicon = new JLabel();
        loadicon.setIcon(new ImageIcon(Resource.GetImage(iconName)));
        loadicon.setBounds(d.width / 2 - (int) (widhtIcon / 2), d.height / 2 - (int) (heightIcon / 2), widhtIcon, heightIcon);
        add(loadicon);

        if (this.buttonName != null) {

            returmainframe = new JButton();
            returmainframe.setIcon(new ImageIcon(Resource.GetImage(buttonName)));
            returmainframe.setBounds(d.width / 2 - 70, d.height / 2 + (int) (heightIcon / 2) + 50, widhtButton, heightButton);
            returmainframe.setBackground(buttonColor);
            returmainframe.addActionListener(buttonActionListener);
            add(returmainframe);
        }

    }
}
