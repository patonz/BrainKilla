package brainkilla.GUI;


import brainkilla.Engine.HighscoreManager;
import brainkilla.Skeleton.FrameInterface;
import brainkilla.Skeleton.PanelInterface;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import util.AudioManager;
import util.Resource;

/**
 *
 * @author Jacopo Carletti
 */

public class HighScorePanel extends DefaultPanel implements PanelInterface
{
    
    private JTable scoresTable;
    private DefaultTableModel scoresModel;
    private MyTableCellRenderer renderer;
    
    //Gestione sfondo alternato e caratteri scoresTable
    public class MyTableCellRenderer extends DefaultTableCellRenderer{
      
     @Override
     public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus, int row, int column){
        
     super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
      if(row == 0){
          
      setHorizontalAlignment( CENTER );
      setFont(new java.awt.Font("Arial", 3, 14));
        
      }
         else {setHorizontalAlignment( CENTER );
         setFont(new java.awt.Font("Arial", 1, 11));
         
                }
      
      if(column == 1 || column ==3){
        
      setBackground(new java.awt.Color(204,204,204,70));
      
      }
      
      else{
          
          setBackground(new java.awt.Color(204,236,255,70));
          
      }
     
        return this;
    }
}

 
    FrameInterface frame = null;
    @Override
    public void setFrame(FrameInterface frame) {
        this.frame = frame;
    }

    public HighScorePanel()
    {
        setLayout(null);
        
        //Caricamento dati arraylist
        HighscoreManager.load();
        
        //Configurazione tabella scoresTable       
        scoresModel = new DefaultTableModel();
        scoresTable = new JTable(scoresModel);
        renderer = new MyTableCellRenderer();
        scoresTable.setDefaultRenderer(Object.class, renderer);
        scoresTable.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoresTable.setEnabled(false);
        scoresTable.setFocusable(false);
        scoresTable.setIntercellSpacing(new java.awt.Dimension(0, 0));
        scoresTable.setBounds(130, 210, 700, 300);
        scoresTable.setGridColor(new java.awt.Color(204,204,204,60));
        scoresTable.setBackground(new java.awt.Color(204,236,255,80));
        scoresTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153,153,255,70), 2));
        scoresTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        scoresTable.setRowHeight(30);
        
        scoresModel.addColumn("Player Name");
        scoresModel.addColumn("Score");
        scoresModel.addColumn("Minutes Elapsed");
        scoresModel.addColumn("Questions Solved");
        
        //Aggiunge i valori alla tabella (riga per riga)
        scoresModel.addRow(new Object[] {"Player Name", "Score", "Minutes Elapsed", "Questions Solved"}); 
        
        for (int i =0; i<10; i++){
            
        HighscoreManager.getTotalsecondsList(i);    
        scoresModel.addRow(new Object[] {HighscoreManager.getPlayerList(i), HighscoreManager.getScoreList(i), HighscoreManager.getMinutesList(i), HighscoreManager.getWardendList(i)});
        
        }
       
        
        add(scoresTable);
        
        
        final AudioManager aud = new AudioManager("tsrh.mp3");
        aud.play();   
        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                aud.stop();
                
                AudioManager mg = frame.getAudioMf(); 
                mg.loop();
                
                frame.nextPanel(new MainPanel());
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }
   
    
    //sfondo MainPanel ***
    Image img = Toolkit.getDefaultToolkit().createImage(Resource.GetImage("backgroundmp2.jpg"));
        
    @Override
    public void paintComponent(Graphics g) 
    {
        if (img!=null) 
            g.drawImage(img,0,0,this);
    }
    //***
}


      