
package brainkilla.GUI;

import brainkilla.Engine.QuestionManager;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import util.Resource;

/**
 *
 * @author Jacopo Carletti
 */
public class QuestionsPanel extends JPanel {

    //Configurazione elementi del pannello principale degli indovinelli
    private QuestionManager questionManager;
    private JPanel controlpanel;
    private JLabel controlabel;
    private JLabel labelimage;
    private JLabel attentionimage;
    private JButton controlbutton;
    private JProgressBar ProgressBar;
    private boolean resolve;
    private boolean controlpanelIsActive;
    private JButton answerButton;
    private JButton switchButton;
    private JButton suggestionButton;
    private JButton closepanelButton;
    private JLabel titleLabel;
    private JLabel infoLabel;
    private JScrollPane jScrollPane1;
    private JSeparator upSeparator;
    private JSeparator downSeparator;
    private JTextArea questionArea;
    private JTextField wardenField;
    private JTextField heroField;
    private JTextField switchnumberField;
    private JTextField suggestionField;
    private JTextField answerField;

    
    public QuestionsPanel(QuestionManager questionManager) {
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        setBounds(d.width / 2 - 252, d.height / 2 - 204, 504, 408);

        this.questionManager = questionManager;
        
        initComponents();
        activateEnterKey();
        jScrollPane1.setBorder(null);
 
        //Configurazione elementi pannello di controllo eccezioni
        controlpanel = new JPanel();
        controlpanel.setLayout(null);
        controlpanel.setBounds(52, 54, 400, 300);
        controlpanel.setBackground(new java.awt.Color(204,236,255));
        controlpanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153,153,255), 2));
        ProgressBar = new JProgressBar();
        ProgressBar.setForeground(new java.awt.Color(31,139,28));
        ProgressBar.setMaximum(3);
        ProgressBar.setSize(390,70);
        ProgressBar.setBounds(10,100,380,70);
        ProgressBar.setStringPainted(true);
        labelimage = new JLabel("");
        labelimage.setIcon(new javax.swing.ImageIcon(Resource.GetImage("error128.png")));
        labelimage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelimage.setBounds(45, 70, 310, 128);
        attentionimage = new JLabel("");        
        attentionimage.setIcon(new javax.swing.ImageIcon(Resource.GetImage("attention128.png")));
        attentionimage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        attentionimage.setBounds(45, 70, 310, 128);
        controlabel = new JLabel("");
        controlabel.setFont(new java.awt.Font("Arial", 1, 15));
        controlabel.setBounds(45, 30, 310, 20);
        controlabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        controlbutton = new JButton("");
        controlbutton.setBounds(45, 210, 310, 23);
        controlpanel.add(controlabel);
        controlpanel.add(ProgressBar);
        controlpanel.add(attentionimage);
        controlpanel.add(labelimage);
        controlpanel.add(controlbutton);
        
    }

    //Configurazione pannello principale indovinelli
    private void initComponents() {

        
        wardenField = new JTextField();
        heroField = new JTextField();
        upSeparator = new JSeparator();
        downSeparator = new JSeparator();
        titleLabel = new JLabel();
        jScrollPane1 = new JScrollPane();
        questionArea = new JTextArea();
        answerButton = new JButton();
        switchButton = new JButton();
        infoLabel = new JLabel();
        suggestionButton = new JButton();
        switchnumberField = new JTextField();
        suggestionField = new JTextField();
        closepanelButton = new JButton();
        answerField = new JTextField();

       

        setBackground(new java.awt.Color(0, 0, 0));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 255), 2));
        setAlignmentX(2.0F);
        setAlignmentY(2.0F);
        setFocusable(false);
        setMaximumSize(new java.awt.Dimension(423, 389));
        setRequestFocusEnabled(false);
        setVerifyInputWhenFocusTarget(false);

        wardenField.setBackground(new java.awt.Color(204, 236, 255));
        wardenField.setEditable(false);
        wardenField.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        wardenField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        wardenField.setText("Guardiano:");
        wardenField.setAutoscrolls(false);
        wardenField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 255)));

        heroField.setBackground(new java.awt.Color(204, 236, 255));
        heroField.setEditable(false);
        heroField.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        heroField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        heroField.setText("Eroe:");
        heroField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 255)));

        upSeparator.setForeground(new java.awt.Color(192, 189, 221));

        downSeparator.setForeground(new java.awt.Color(192, 189, 221));

        titleLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("RISOLVI L'INDOVINELLO DEL GUARDIANO");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        questionArea.setBackground(new Color (0,0,0,0));
        questionArea.setEditable(false);
        questionArea.setFont(new java.awt.Font("Arial", 3, 13)); // NOI18N
        questionArea.setLineWrap(true);
        questionArea.setRows(5);
        questionArea.setTabSize(0);
        
              if (questionManager.getPanelHasBeenClosed() == true){
                  
              questionArea.setText(questionManager.getSortedQuestion());
              
              }
              
              else{
                  
                  questionArea.setText(questionManager.sortQuestion());
                  
              }
              
        questionArea.setToolTipText("L'indovinello del Guardiano");
        questionArea.setWrapStyleWord(true);
        questionArea.setAlignmentX(2.0F);
        questionArea.setAlignmentY(2.0F);
        questionArea.setAutoscrolls(false);
        questionArea.setBorder(null);
        questionArea.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        questionArea.setMargin(new java.awt.Insets(10, 20, 2, 2));
        jScrollPane1.setViewportView(questionArea);

        answerButton.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        answerButton.setText("Invia Risposta");
        answerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                answerButtonMouseClicked(evt);
            }
        });

        switchButton.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        switchButton.setText("Cambia Indovinello");
        switchButton.setMaximumSize(new java.awt.Dimension(153, 23));
        switchButton.setMinimumSize(new java.awt.Dimension(153, 23));
        switchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                switchButtonMouseClicked(evt);
            }
        });

        infoLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        infoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoLabel.setText("La risposta consiste in un sostantivo da inserire nel campo sottostante.");

        suggestionButton.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        suggestionButton.setText("Mostra Suggerimento");
        suggestionButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                suggestionButtonMouseClicked(evt);
            }
        });

        switchnumberField.setBackground(new Color (0,0,0,0));
        switchnumberField.setEditable(false);
        switchnumberField.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        switchnumberField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        switchnumberField.setBorder(null);

        suggestionField.setBackground(new Color (0,0,0,0));
        suggestionField.setEditable(false);
        suggestionField.setFont(new java.awt.Font("Arial", 3, 16)); // NOI18N
        suggestionField.setBorder(null);

        closepanelButton.setBackground(new java.awt.Color(255, 0, 0));
        closepanelButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        closepanelButton.setForeground(new java.awt.Color(255, 255, 255));
        closepanelButton.setText("X");
        closepanelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closepanelButtonMouseClicked(evt);
            }
        });

        answerField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 255)));
        answerField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        answerField.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(infoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(upSeparator)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(closepanelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(suggestionButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(107, 107, 107)
                        .addComponent(switchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(downSeparator)
                    .addComponent(switchnumberField)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(wardenField, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                            .addComponent(heroField))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1)
                                    .addComponent(suggestionField)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(answerField)))))
                .addGap(38, 38, 38))
            .addGroup(layout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addComponent(answerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(closepanelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(titleLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(downSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(wardenField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(suggestionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(suggestionButton)
                    .addComponent(switchButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(infoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(heroField, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(answerField))
                .addGap(5, 5, 5)
                .addComponent(switchnumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(upSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(answerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
    }                   

   
    //Attivazione pulsante "Invio"
    private void activateEnterKey(){
                           
        answerField.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                {answerFieldActionPerformed(evt);}
            }
        });
      }
  
    //Pulsante pannello di controllo eccezioni
    public void addcontrolbuttonListener(){
        
         controlbutton.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                    remove(controlpanel);
                     controlpanelIsActive = false;         
            }
        });
        
    }
    
    //Metodo controllo risposte (inizializzato tramite tastiera e mouse)
    public void checkAnswer(){
        
        if (controlpanelIsActive == true){

                if (resolve == false){

                    remove(controlpanel);
                    controlpanelIsActive =false;

                  }

                else{
                    
                    remove(controlpanel);
                    
                    questionManager.closeQuestionPanel();
                    controlpanelIsActive  = false;
                  }

        }

            else{
            add(controlpanel,0);
            controlpanelIsActive = true;

            ProgressBar.setVisible(true);
            labelimage.setVisible(false);
            attentionimage.setVisible(false);

            resolve = questionManager.controlAnswer(answerField.getText());

               if (resolve == true) {

                    controlpanel.setBounds(52, 54, 400, 300);
                    ProgressBar.setString(questionManager.getResolvedQuestions() + " / 3 Diamanti Ottenuti!");
                    ProgressBar.setValue(questionManager.getResolvedQuestions());

                    controlabel.setText("Risposta esatta! Hai ottenuto un diamante!");
                    controlbutton.setText("Continua a giocare");

               } else {

                    ProgressBar.setVisible(false);
                    labelimage.setVisible(true);
                    controlabel.setText("Risposta errata!");
                    controlbutton.setText("Ritenta");

            }

            answerField.setText("");
            controlbutton.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    if (resolve == true) {

                        remove(controlpanel);
                        questionManager.closeQuestionPanel();
                        controlpanelIsActive =false;
                        
                    } 
                    else {

                    remove(controlpanel);
                    controlpanelIsActive =false;

                    }
                }
                });
            }
    }
    
    public void answerFieldActionPerformed(java.awt.event.ActionEvent evt) {
    
        checkAnswer();
      
    }
    
    public void answerButtonMouseClicked(java.awt.event.MouseEvent evt) {                                      
       
        checkAnswer();
        
    }                                     

    //Cambio Indovinello e gestione del relativo pannello
    public void switchButtonMouseClicked(java.awt.event.MouseEvent evt) {                                      
         
         ProgressBar.setVisible(false);
         labelimage.setVisible(false);
         attentionimage.setVisible(true);

         questionArea.setText(questionManager.getNextQuestion());
         switchnumberField.setText("Hai ancora " + questionManager.getCountdown() + " possibilità di cambiare indovinello");
         suggestionField.setText(questionManager.getNextSuggestion());
        
        
        if (questionManager.getcheckNext() == true){
            
            add(controlpanel,0);
            controlpanelIsActive = true;
            
            controlabel.setText("Non puoi cambiare indovinello!");
            controlbutton.setText("Ok");
            addcontrolbuttonListener();
                     
        }
    }                                     

    //Mostra Suggerimento e getione del relativo pannello
    public void suggestionButtonMouseClicked(java.awt.event.MouseEvent evt) {                                      

        ProgressBar.setVisible(false);
        labelimage.setVisible(true);
        attentionimage.setVisible(false);
        
        questionManager.controlSuggestion();
        suggestionField.setText(questionManager.getNextSuggestion());
        
        if (questionManager.getCheckSuggestion() == true){
           
            add(controlpanel,0);
            controlpanelIsActive = true;
            
            controlabel.setText("Non posso fornirti ulteriore aiuto...");
            controlbutton.setText("Ok");
            addcontrolbuttonListener();
        }
      
    }                                     

    public void closepanelButtonMouseClicked(java.awt.event.MouseEvent evt) {                                      
        
        
        questionManager.suspendQuestionPanel();
    } 
    
    Image img = Toolkit.getDefaultToolkit().createImage(Resource.GetImage("questionprovvisorium.jpg"));
    

    @Override
    public void paintComponent(Graphics g) {
        if (img != null) {
            g.drawImage(img, 0, 0, this);
        }
    }
}
