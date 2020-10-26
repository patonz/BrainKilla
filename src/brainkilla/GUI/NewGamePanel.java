package brainkilla.GUI;

import brainkilla.BrainKilla;
import brainkilla.Skeleton.FrameInterface;
import brainkilla.Skeleton.PanelInterface;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import util.Resource;

/**
 *
 * @author Lorenzo Gigli*
 */
public class NewGamePanel extends DefaultPanel implements PanelInterface {

    private DefaultButton startGameButton;
    private DefaultButton backButton;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    public GameFrame gameFrame;
    private final String defaultValuePlayerName = "Insert name here...";
    private FrameInterface mainFrame = null;
    private Image backgroundImage = Toolkit.getDefaultToolkit().createImage(Resource.GetImage("backgroundng.jpg"));

    public NewGamePanel() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        
        nameTextField = new javax.swing.JTextField();
        nameTextField.setFont(new java.awt.Font("Georgia", 0, 36));
        nameTextField.setText(defaultValuePlayerName);
        nameLabel = new javax.swing.JLabel();
        nameLabel.setFont(new java.awt.Font("Georgia", 1, 36));
        nameLabel.setText("Character's name:");
        backButton = new DefaultButton("buttback.jpg");
        backButton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        mainFrame.nextPanel(new MainPanel());
                    }
                });
        
        startGameButton = new DefaultButton("buttstartgame.jpg");
        startGameButton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evento) {

                        mainFrame.getAudioMf().stop();
                        String playerName = nameTextField.getText();

                        if (playerName.equals(defaultValuePlayerName)) {
                            playerName = "Unknown";
                        }

                        BrainKilla.startNewGame(playerName);
                    }
                });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(backButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(startGameButton))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addComponent(nameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 135, Short.MAX_VALUE)))
                        .addContainerGap()));
        layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(246, 246, 246)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(startGameButton)
                            .addComponent(backButton))
                        .addContainerGap()));
    }

    @Override
    public void paintComponent(Graphics g) {
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this);
        }
    }

    @Override
    public void setFrame(FrameInterface frame) {
        this.mainFrame = frame;
    }
}