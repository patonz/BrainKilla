/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brainkilla.GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
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
 * Questa classe racchiude una serie di pannelli che si appoggiano in un
 * pannello di sfondo (lo stesso HelpPanel) che vanno a formare il tutorial di
 * BrainKilla. Ha un menu con tutte le sezioni che compongono il gioco,
 * cliccando su di esse si accede al pannello relativo; ogni pannello espone nel
 * modo piu esauriente possibile ogni sezione del gioco, anche grazie all'aiuto
 * di piccole immagini dimostrative.
 */
public class HelpPanel extends JPanel {

    private InnerHelpButtonMenu completebutton;
    private InnerHelpPanel completepanel;
    private InnerHelpButtonMenu encounterbutton;
    private InnerHelpPanel encounterpanel;
    private JButton exithelp;
    private InnerHelpPanel mainpanel;
    private InnerHelpPanel missionpanel;
    private InnerHelpButtonMenu missionbutton;
    private InnerHelpButtonMenu movebutton;
    private InnerHelpPanel movepanel;
    private InnerHelpButtonMenu resolvebutton;
    private InnerHelpPanel resolvepanel;
    private InnerHelpButtonMenu statusbutton;
    private InnerHelpPanel statuspanel;
    private InnerHelpPanel scorepanel;
    private InnerHelpButtonMenu scorebutton;
    private GameStatusPanel gameStatusPanel;
    private JLabel priesticon;

    public HelpPanel(GameStatusPanel gameStatusPanel) {
        this.gameStatusPanel = gameStatusPanel;
        initComponents();
    }

    private void initComponents() {

        setLayout(null);
        setSize(472, 222);


        /**
         * ********************MAINPANEL SET ***********************
         */
        //menu
        priesticon = new JLabel(new ImageIcon(Resource.GetImage("principessa_tutorial.png")));
        priesticon.setBounds(313, 7, 137, 186);
        mainpanel = new InnerHelpPanel();
        mainpanel.setBounds(11, 11, 450, 200);
        mainpanel.setOpaque(false);
        mainpanel.setLayout(null);
        mainpanel.add(priesticon);
        add(mainpanel);
        /**
         * ************************MISSION SET ********************************
         */
        //sezione "Scopo del gioco"
        missionpanel = new InnerHelpPanel();
        missionbutton = new InnerHelpButtonMenu("Scopo del gioco", this, missionpanel, mainpanel, 0);
        mainpanel.add(missionbutton);
        InnerHelpButtonBack missionback = new InnerHelpButtonBack("Back", this, missionpanel, mainpanel);

        JLabel missiontext = new JLabel("<HTML>Scopo del gioco: <BR> Trova sulla mappa i 3 guardiani e <BR>rispondi ai loro indovinelli per completare il livello.<BR><BR>ATTENZIONE:<BR>ci saranno mostri ovunque, quindi difenditi o scappa... se ci riesci....<HTML>");
        missiontext.setBounds(100, 10, 300, 130);
        missionpanel.add(missiontext);
        missionpanel.add(missionback);


        // * **************************Move SET**************
        //sezione "muoversi sulla mappa"
        movepanel = new InnerHelpPanel();
        movebutton = new InnerHelpButtonMenu("Muoversi sulla mappa", this, movepanel, mainpanel, 1);
        mainpanel.add(movebutton);
        InnerHelpButtonBack moveback = new InnerHelpButtonBack("Back", this, movepanel, mainpanel);

        JLabel moveicon = new JLabel(new ImageIcon(Resource.GetImage("hero_tutorial.png")));
        moveicon.setBounds(10, 10, 150, 150);
        JLabel arrowicon = new JLabel(new ImageIcon(Resource.GetImage("frecce_tutorial.png")));
        arrowicon.setBounds(160, 50, 150, 112);
        JLabel movetext = new JLabel("<HTML>Usa le FRECCE<BR>DIREZIONALI per<BR>muoverti sulla mappa<BR>nelle 4 direzioni:<BR>NORD, SUD, OVEST, EST.");
        movetext.setBounds(290, 10, 140, 80);
        movepanel.add(movetext);
        movepanel.add(moveicon);
        movepanel.add(arrowicon);
        movepanel.add(moveback);

        //**********************STATUS SET***********************
        //sezione "stato della partita"
        statuspanel = new InnerHelpPanel();
        statusbutton = new InnerHelpButtonMenu("Stato della partita in corso", this, statuspanel, mainpanel, 2);
        mainpanel.add(statusbutton);
        InnerHelpButtonBack statusback = new InnerHelpButtonBack("Back", this, statuspanel, mainpanel);

        JLabel score = new JLabel("<HTML>   Indica il punteggio<BR>     attuale");
        score.setIcon(new ImageIcon(Resource.GetImage("score_tutorial.png")));
        score.setBounds(20, 55, 150, 31);
        JLabel time = new JLabel("<HTML>Indica il tempo<BR>trascorso");
        time.setIcon(new ImageIcon(Resource.GetImage("timer_tutorial.png")));
        time.setBounds(20, 115, 150, 45);
        JLabel warden = new JLabel("<HTML>Indica i guardiani<BR>affrontati");
        warden.setIcon(new ImageIcon(Resource.GetImage("warden_tutorial.png")));
        warden.setBounds(220, 40, 200, 63);
        JLabel lifehero = new JLabel("<HTML>Indica le vite<BR>rimaste");
        lifehero.setIcon(new ImageIcon(Resource.GetImage("lifehero_tutorial.png")));
        lifehero.setBounds(220, 100, 200, 61);
        JLabel statustext = new JLabel("<HTML>Nella schermata di gioco viene mostrato lo stato della partita in corso<BR>Di seguito sono elencate le 4 icone di gioco.");
        statustext.setBounds(30, 0, 450, 40);
        statuspanel.add(score);
        statuspanel.add(time);
        statuspanel.add(warden);
        statuspanel.add(lifehero);
        statuspanel.add(statusback);
        statuspanel.add(statustext);

        //************ECOUNTER SET ************************
        //sezione "affrontare i mostri"
        encounterpanel = new InnerHelpPanel();
        encounterbutton = new InnerHelpButtonMenu("Affrontare i mostri", this, encounterpanel, mainpanel, 3);
        mainpanel.add(encounterbutton);
        InnerHelpButtonBack encounterback = new InnerHelpButtonBack("Back", this, encounterpanel, mainpanel);

        JLabel monster = new JLabel(new ImageIcon(Resource.GetImage("rougelec_tutorial.png")));
        monster.setBounds(340, 10, 34, 50);
        JLabel space = new JLabel(new ImageIcon(Resource.GetImage("space_tutorial.png")));
        space.setBounds(125, 100, 200, 31);
        JLabel shoot = new JLabel(new ImageIcon(Resource.GetImage("shoot_tutorial.png")));
        shoot.setBounds(340, 70, 100, 100);
        JLabel encountertext = new JLabel("<HTML>Un mostro (riportato a destra) può essere ucciso rilasciando un campo d'energia, anche piu volte consecutive.<BR>Questo potere è attivabile con la BARRA SPAZIATRICE.<BR><BR><BR>ATTENZIONE:<BR>Ci sono mostri con molta vita rispetto al normale!");
        encountertext.setBounds(30, 10, 340, 170);
        encounterpanel.add(monster);
        encounterpanel.add(space);
        encounterpanel.add(shoot);
        encounterpanel.add(encountertext);
        encounterpanel.add(encounterback);

        //*********************RESOLVE SET *******************
        //sezione "risolvere gli indovinelli"
        resolvepanel = new InnerHelpPanel();
        resolvebutton = new InnerHelpButtonMenu("Risolvere gli indovinelli", this, resolvepanel, mainpanel, 4);
        mainpanel.add(resolvebutton);
        InnerHelpButtonBack resolveback = new InnerHelpButtonBack("Back", this, resolvepanel, mainpanel);

        JLabel resolve = new JLabel(new ImageIcon(Resource.GetImage("resolve_tutorial.png")));
        resolve.setBounds(276, 10, 184, 150);
        JLabel warden01 = new JLabel(new ImageIcon(Resource.GetImage("warden01_tutorial.png")));
        warden01.setBounds(0, 0, 40, 60);
        JLabel resolvetext = new JLabel("<HTML>        un guardiano è un'entità mistica incapace di muoversi o di attaccare,<BR>quindi entra in contatto con lui per  rispondere al suo indovinello. Questa finestra apparirà in alto a destra e ti permetterà di digitare da tastiera la soluzione.<BR>NOTARE: Riquadro blu = indovinello  Riquadro rosso = digita qui la risposta");
        resolvetext.setBounds(50, 0, 230, 150);
        resolvepanel.add(resolve);
        resolvepanel.add(warden01);
        resolvepanel.add(resolvetext);
        resolvepanel.add(resolveback);

        //***************COMPLETE SET **********************
        //sezione "conludere il livello"
        completepanel = new InnerHelpPanel();
        completebutton = new InnerHelpButtonMenu("Concludere il livello", this, completepanel, mainpanel, 5);
        mainpanel.add(completebutton);
        InnerHelpButtonBack completeback = new InnerHelpButtonBack("Back", this, completepanel, mainpanel);

        JLabel completetext = new JLabel("<HTML>Se perdi tutte le vite a tua disposizione dovrai ricominciare una nuova partita,<BR>ma se invece riesci a risolvere tutti gli indovinelli avrai completato il livello!<BR><BR>NB: NON DISTOGLIERE LO SGUARDO DALLO SCHERMO PER PENSARE ALLA SOLUZIONE DEGLI INDOVINELLI O BRAINKILLA TI SCONFIGGERà!");
        completetext.setBounds(25, 10, 400, 130);
        completepanel.add(completetext);
        completepanel.add(completeback);


        //********************score set *************************
        //sezione "il punteggio"
        scorepanel = new InnerHelpPanel();
        scorebutton = new InnerHelpButtonMenu("Il punteggio", this, scorepanel, mainpanel, 6);
        mainpanel.add(scorebutton);
        InnerHelpButtonBack scoreback = new InnerHelpButtonBack("Back", this, scorepanel, mainpanel);

        JLabel scoretext = new JLabel("<HTML>Il punteggio varia perdendo punti in base al tempo trascorso e guadagnandoli uccidendo mostri e risolvendo indovinelli.<BR><BR>Torna al menù di partenza per vedere i tuoi HighScore con i relativi tempi e con il numero di guardiani affrontati.");
        scoretext.setBounds(35, 10, 400, 130);
        scorepanel.add(scoretext);
        scorepanel.add(scoreback);

        //****************EXIT SET ************************
        //pulsante esci
        exithelp = new JButton();
        exithelp.setText("Exit");
        exithelp.setBounds(200, 170, 50, 20);
        exithelp.setBorder(null);
        exithelp.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gameStatusPanel.removeHelp();
            }
        });
        mainpanel.add(exithelp);

    }
    //************** background set *********************
    //Sfondo dell'helppanel
    Image img = Toolkit.getDefaultToolkit().createImage(Resource.GetImage("tutorial_vuoto.png"));

    @Override
    public void paintComponent(Graphics g) {
        if (img != null) {
            g.drawImage(img, 0, 0, this);
        }
    }
}
