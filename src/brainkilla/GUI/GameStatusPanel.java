/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brainkilla.GUI;

import brainkilla.BrainKilla;
import brainkilla.Engine.GameLevel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.*;
import util.Resource;

/**
 *
 * @author Leonardo Montecchiari
 */
/*
 * Questa classe permette la visualizzazione dello stato di una sessione di gioco
 */
public class GameStatusPanel extends JPanel {

    private JLabel lorebook;
    private JButton helpbutton;
    private JButton exitbutton;
    private JLabel lifeicon;
    private JLabel lifeone;
    private JLabel lifethree;
    private JLabel lifetwo;
    private JLabel scoreicon;
    private JLabel scoretext;
    private JToggleButton soundbutton;
    private JLabel timeicon;
    private JLabel timetext;
    private JLabel wardenicon;
    private JLabel wardentext;
    private HelpPanel help;
    private Toolkit toolkit;
    private Dimension dimensionscreen;
    private GameLevel gameLevel;
    private boolean press;

    public GameStatusPanel(GameLevel gameLevel) {

        this.gameLevel = gameLevel;

        toolkit = Toolkit.getDefaultToolkit();
        dimensionscreen = toolkit.getScreenSize();
        setBounds(0, 0, dimensionscreen.width, dimensionscreen.height);//dimensioni del pannello pari alla grandezza fullscreen
        setOpaque(false);
        initComponents();
        press = true;
    }

    private void initComponents() {

        exitbutton = new JButton();
        soundbutton = new JToggleButton();
        wardenicon = new JLabel();
        wardentext = new JLabel();
        lifeicon = new JLabel();
        lifeone = new JLabel();
        lifetwo = new JLabel();
        lifethree = new JLabel();
        timeicon = new JLabel();
        scoreicon = new JLabel();
        timetext = new JLabel();
        scoretext = new JLabel();


//******************lore set*******************************
        //Creazione e posizionamento del libro di gioco, con relativo pulsante di chiusura
        if (gameLevel.getGame().getLevelIndex() == 0) {

            lorebook = new JLabel();
            lorebook.setIcon(new ImageIcon(Resource.GetImage("lore_book.png")));
            JButton bookmark = new JButton();
            bookmark.setBorder(null);
            bookmark.setOpaque(false);
            bookmark.setContentAreaFilled(false);
            bookmark.setBorderPainted(false);


            bookmark.setBackground(new Color(0, 0, 0, 0));

            bookmark.setBounds(735, 550, 58, 72);

            bookmark.addActionListener(
                    new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent evento) {
                            remove(lorebook);
                        }
                    });
            lorebook.add(bookmark);
            lorebook.setLayout(null);
            lorebook.setOpaque(false);
            lorebook.setBounds(dimensionscreen.width / 2 - 433, dimensionscreen.height / 2 - 327, 866, 654);
            bookmark.setToolTipText("Chiudi il Libro");
            add(lorebook);
        }

//**********************help set*************************
        //Istanziato "helpPanel", e posizionato nel gamestatuspanel
        help = new HelpPanel(this);
        help.setBounds(dimensionscreen.width - 480, dimensionscreen.height - 270, 472, 222);


        //*** *******************************SET OF BUTTON********************************


        //Creazione del pulsante "help" che aggiunge o rimuove HelpPanel
        helpbutton = new DefaultButton("button_help.jpg");
        helpbutton.setToolTipText("Help");
        helpbutton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        if (press == true) {
                            add(help);
                            press = false;
                        } else {
                            removeHelp();
                        }
                    }
                });

        //Crezione del pulsante "exit" che chiude la sessione di gioco
        exitbutton = new DefaultButton("button_close.jpg");
        exitbutton.setToolTipText("Chiudi la sessione di gioco");
        exitbutton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evento) {

                        gameLevel.endLevel();
                        gameLevel.getGame().getGameFrame().dispose();
                        BrainKilla.endGame();
                    }
                });
        //Creazione del pulsante "sound" che attiva o disattiva la musica durante la sessione di gioco
        soundbutton = new JToggleButton();
        soundbutton.setIcon(
                new ImageIcon(Resource.GetImage("button_audion.jpg")));
        soundbutton.setToolTipText("Audio ON/OFF");
        soundbutton.addActionListener(
                new ActionListener() {

                    boolean pressed = true;

                    @Override
                    public void actionPerformed(ActionEvent evento) {

                        if (pressed == true) {
                            soundbutton.setBorder(new BordoFine(BordoFine.INCASSATO, new Insets(5, 5, 5, 5)));
                            gameLevel.pauseSound();
                            soundbutton.setIcon(new ImageIcon(Resource.GetImage("button_mute.jpg")));
                            pressed = false;
                        } else {
                            soundbutton.setBorder(null);
                            gameLevel.playSound();
                            soundbutton.setIcon(new ImageIcon(Resource.GetImage("button_audion.jpg")));
                            pressed = true;
                        }

                    }
                });



        //********* SET OF WARDEN ****************
        //icona + testo dei guardiani
            
        wardenicon.setIcon(
                new ImageIcon(Resource.GetImage("warden_icon.png")));
        wardentext.setFont(
                new java.awt.Font("Snap ITC", 0, 32));
        wardentext.setForeground(
                new java.awt.Color(73, 205, 241));
        wardentext.setText(
                "0/" + gameLevel.getMapScanner().getWardenStartpointsCount());
    
        //*********SET OF TIME**********************
        //icona + testo del tempo
        timeicon.setIcon(
                new ImageIcon(Resource.GetImage("timer_icon.png")));
        timetext.setFont(
                new java.awt.Font("Snap ITC", 0, 32));
        timetext.setForeground(
                new java.awt.Color(73, 205, 241));
        timetext.setText(
                "0:00:00.0");

        //*****************SET OF SCORE*****************
        //icona + testo del punteggio
        scoreicon.setIcon(
                new ImageIcon(Resource.GetImage("score_icon.png")));
        scoretext.setFont(
                new java.awt.Font("Snap ITC", 0, 32));
        scoretext.setForeground(
                new java.awt.Color(73, 205, 241));
        scoretext.setText(
                "p");


        // ********************SET OF LIFE *******************************
        //icone delle vite
        lifeicon.setIcon(new ImageIcon(Resource.GetImage("lifehero_icon.png")));


        //******************SET OF LAYOUT ***********************
        //Layout del pannello
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);

        this.setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(timeicon, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(timetext)).addGroup(layout.createSequentialGroup().addComponent(scoreicon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(scoretext))).addGap(6, 1401, Short.MAX_VALUE)).addGroup(layout.createSequentialGroup().addComponent(wardenicon, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(wardentext, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(lifeicon, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(lifeone, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(lifetwo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(lifethree, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(soundbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(helpbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(exitbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(scoreicon, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(scoretext)).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(25, 25, 25).addComponent(timetext)).addGroup(layout.createSequentialGroup().addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(timeicon, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(wardenicon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(lifeicon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(415, Short.MAX_VALUE).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(wardentext).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(exitbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(soundbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(helpbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)).addComponent(lifeone, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(lifetwo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(lifethree, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap()));
    }
    //metodo "set" del numero dei punti ottenuti

    public void setScore(int score) {

        scoretext.setText(score + "p");
    }
    //metodo "set" del numero dei guardiani affrontati

    public void setWarden(int warden) {
        wardentext.setText(warden + "/" + gameLevel.getMapScanner().getWardenStartpointsCount());
    }
    //metodo "set" del numero di vite rimaste

    public void setLifes(int life) {

        URL urlLifeIcon = Resource.GetImage("life_icon.png");

        if (life == 3) {
            lifeone.setIcon(new ImageIcon(urlLifeIcon));
            lifetwo.setIcon(new ImageIcon(urlLifeIcon));
            lifethree.setIcon(new ImageIcon(urlLifeIcon));
        }

        if (life == 2) {
            lifeone.setIcon(new ImageIcon(urlLifeIcon));
            lifetwo.setIcon(new ImageIcon(urlLifeIcon));
            lifethree.setIcon(new ImageIcon(""));
        }

        if (life == 1) {
            lifeone.setIcon(new ImageIcon(urlLifeIcon));
            lifetwo.setIcon(new ImageIcon(""));
            lifethree.setIcon(new ImageIcon(""));
        }
        if (life == 0) {
            lifeone.setIcon(new ImageIcon(""));
            lifetwo.setIcon(new ImageIcon(""));
            lifethree.setIcon(new ImageIcon(""));
        }

    }
    //metodo "set" del tempo trascorso

    public void setTime(String time) {

        timetext.setText(time);
    }
    //metodo che rimuove il componente help dal gamestatuspanel

    public void removeHelp() {
        remove(help);
        press = true;
    }
}