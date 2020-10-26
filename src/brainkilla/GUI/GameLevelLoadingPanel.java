package brainkilla.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import util.Resource;

/**
 *
 * @author Leonardo Montecchiari
 */
/*
 * questa classe eredita da JPanel, definisce il pannello di caricamento del gioco
 */
public class GameLevelLoadingPanel extends JPanel {

    private JLabel loadicon;

    public GameLevelLoadingPanel(Color backgroundColor, String iconName, int widhtIcon, int heightIcon) {

        setLayout(null);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        setBounds(0, 0, d.width, d.height);
        setBackground(backgroundColor);


        loadicon = new JLabel();
        loadicon.setIcon(new ImageIcon(Resource.GetImage(iconName)));
        loadicon.setBounds(d.width / 2 - (int) (widhtIcon / 2), d.height / 2 - (int) (heightIcon / 2), widhtIcon, heightIcon);
        add(loadicon);
    }
}
