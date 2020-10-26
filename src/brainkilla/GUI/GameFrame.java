package brainkilla.GUI;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 *
 * @author Markus Aurich 
 */
public class GameFrame extends JFrame {

    private JLayeredPane layeredPane;
    private int orderIndex = 0;

    public GameFrame() {

        super();

        layeredPane = new JLayeredPane();
        
        getContentPane().add(layeredPane);
        
        GraphicsEnvironment localGraphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreenDevice = localGraphicsEnvironment.getDefaultScreenDevice();

        if (defaultScreenDevice.isFullScreenSupported()) {

            this.setUndecorated(true);
            this.setResizable(false);
            defaultScreenDevice.setFullScreenWindow(this);
        }
    }

    public void addSubPanel(JPanel subPanel) {

        layeredPane.add(subPanel, new Integer(orderIndex));
        orderIndex++;
    }

    public void removeAllSubPanel() {

        layeredPane.removeAll();
    }

    public void removeSubPanel(JPanel component) {

        layeredPane.remove(component);        
    }
}
