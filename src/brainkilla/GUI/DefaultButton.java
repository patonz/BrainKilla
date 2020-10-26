package brainkilla.GUI;

import java.awt.Color;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import util.Resource;

/**
 *
 * @author Lorenzo Gigli*
 */
public class DefaultButton extends JButton
{
    public DefaultButton(String icona)
    {
        super(new ImageIcon(Resource.GetImage(icona)));
        this.setBackground(new Color(242, 207, 143, 250));
        this.setBorder( new BordoFine(BordoFine.INCASSATO, new Insets(5, 5, 5, 5)));
    }
}
