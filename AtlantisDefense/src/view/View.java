package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.border.Border;
import model.Eel;
import model.Enemy;
import model.Fugu;
import model.GoldFish;
import model.Model;
import model.SwordFish;

public class View extends javax.swing.JFrame {

    private long startTime;
    public static Timer timer;
    public static Timer timerForEnemies;
     public static Timer timerForElapsed;
    public static boolean paused = false;
    private long elapsed = 0;
    private int i = 0;

    private static boolean selected1 = false;
    private static boolean selected2 = false;
    private static boolean selected3 = false;
    private static boolean selected4 = false;

    public View() {
        initComponents();
        this.setVisible(true);
        URL url = View.class.getClassLoader().getResource("res/fish.png");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(url));
        this.setTitle("Főképernyő");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.moneyView.setText("" + Model.money);
        this.startTime = System.currentTimeMillis();
        this.timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!paused) {
                    timeView.setText("" + i++);
                    
                }

            }
        });
        this.timerForEnemies = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!paused) {
                    elapsed += 10;
                    for (int i = 0; i < Model.readedEnemies.size(); ++i) {
                        if (Model.readedEnemies.get(i).startTime == elapsed) {
                            try {
                                // el kell induljon az ellenség, mert eljött az ideje
                                Enemy enemy;
                                if("electric".equals(Model.readedEnemies.get(i).type)){
                                    enemy = new Eel();
                                } 
                                else if("ice".equals(Model.readedEnemies.get(i).type)){
                                    enemy = new SwordFish();
                                }
                                else if("bubble".equals(Model.readedEnemies.get(i).type)) {
                                    enemy = new Fugu();
                                }
                                else {
                                    enemy = new GoldFish();
                                }
                                Model.enemies.add(enemy);
                                Board.enemyComes(Model.readedEnemies.get(i).getImage(), Model.readedEnemies.get(i).speed, enemy);
                            } catch (IOException ex) {
                                System.out.println("Ellenség képét nem sikerült elérni.\n");
                            }
                        }
                    }
                }

            }
        });
        timer.start();
        timerForEnemies.start();
    }

//    public long elapsedTime() {
//            return Math.round((this.elapsed - this.startTime) / 100);
//    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        try
        {
			board = new Board(this);
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
        helpButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        timeView = new javax.swing.JLabel();
        moneyView = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        timerContinuePanel = new javax.swing.JPanel();
        timerStopPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        board.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                boardMouseClicked(evt);
            }
        });
        getContentPane().add(board);
        board.setBounds(120, 100, 870, 638);

        helpButton.setBackground(new java.awt.Color(250, 215, 172));
        helpButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        helpButton.setForeground(new java.awt.Color(0, 0, 51));
        helpButton.setText("Súgó");
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });
        getContentPane().add(helpButton);
        helpButton.setBounds(1050, 20, 100, 40);

        exitButton.setBackground(new java.awt.Color(250, 215, 172));
        exitButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        exitButton.setForeground(new java.awt.Color(0, 0, 51));
        exitButton.setText("Kilépés");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        getContentPane().add(exitButton);
        exitButton.setBounds(1160, 20, 110, 40);

        timeView.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 48)); // NOI18N
        timeView.setForeground(new java.awt.Color(255, 206, 159));
        timeView.setText("0");
        getContentPane().add(timeView);
        timeView.setBounds(1120, 430, 130, 50);

        moneyView.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 48)); // NOI18N
        moneyView.setForeground(new java.awt.Color(255, 206, 159));
        moneyView.setText("0");
        getContentPane().add(moneyView);
        moneyView.setBounds(1160, 490, 170, 50);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/sand-castle_medium.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel5);
        jLabel5.setBounds(1070, 160, 70, 80);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/castle_medium.png"))); // NOI18N
        jLabel8.setName(""); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel8);
        jLabel8.setBounds(1170, 160, 70, 80);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/sand-castle_brown_medium.png"))); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel7);
        jLabel7.setBounds(1070, 280, 80, 80);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/toy_medium.png"))); // NOI18N
        jLabel9.setText("jLabel9");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel9);
        jLabel9.setBounds(1170, 280, 70, 80);

        jLabel6.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 51));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/bg.png"))); // NOI18N
        getContentPane().add(jLabel6);
        jLabel6.setBounds(0, -10, 1520, 860);

        timerContinuePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                timerContinuePanelMouseClicked(evt);
            }
        });
        getContentPane().add(timerContinuePanel);
        timerContinuePanel.setBounds(1050, 690, 60, 50);

        timerStopPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                timerStopPanelMouseClicked(evt);
            }
        });
        getContentPane().add(timerStopPanel);
        timerStopPanel.setBounds(1140, 690, 50, 50);

        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(1190, 210, 41, 16);

        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(1180, 210, 41, 16);

        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(1160, 190, 41, 16);

        jLabel4.setText("jLabel4");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(1170, 190, 41, 16);
        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(210, 190, 2, 2);

        setBounds(0, 0, 1397, 872);
        setVisible(true);
    }// </editor-fold>//GEN-END:initComponents

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
        JDialog d = new JDialog(this, "Súgó");
        JTextArea txtAreaDetail = new JTextArea("TORNYOK\n\n- GoldTower: Ezzel a toronnyal a játékban eltelt idővel arányosan  \n"
                + "  lehet pénz gyűjteni. Aktív támadásra nem használható, azonban \n"
                + "  minden - más torony által - kilőtt Goldfish után a pénztermelés \n"
                + "  gyorsasága a kétszeresére nő.\n\n"
                + "- BubbleTower: Az ElectricTower-rel ellentétben ez a toronytípus\n"
                + "  pontatlanabbul, de sokkal nagyobb hatőkörben képes lőni maga \n"
                + "  körül a fejlesztésektől függő sugarú körben. Enemy, akire\n"
                + "  leginkább hatásos: Fugu.\n\n"
                + "- IceTower: Ez a torony a hatókörébe érkező gyors Swordfish-eket \n"
                + "  képes lelassítani, fejlesztések után akár megállítani, \n"
                + "  befagyasztani, hogy minél nagyobb arányban ki tudja őket lőni.\n\n"
                + "- ElectricTower: Ez a torony nagyon gyors ütemben, de egyszerre\n"
                + "  csak egy enemy-t képes lőni nagy pontossággal, messziről. Enemy, \n"
                + "  akire leginkább hatásos: Eel.\n\n"
                + "ELLENSÉGEK\n\n"
                + "- Goldfish: Lassú, de életerős hal, ami megnehezíti a kilövését. \n"
                + "  Minden kilőtt egyed után megkétszereződik a GoldTower által \n"
                + "  termelt pénz.\n\n"
                + "- Swordfish: Nagyon gyors, könnyen mozgó hal. Az ElectricTower\n"
                + "  képes lelassítani, megállítani.\n\n"
                + "- Fugu: Nagytestű, lomha hal, de sokáig él. Legkönnyebben a\n"
                + "  BubbleTower tudja kilőni.\n\n"
                + "- Eel: Gyors, áramvonalas hal. Nehéz messziről eltalálni, ezt \n"
                + "  legkönnyebben az ElectricTower tudja megtenni.");
        txtAreaDetail.setEditable(false);
        txtAreaDetail.setBackground(new Color(240, 248, 255));
        txtAreaDetail.setForeground(new Color(0, 0, 81));
        /*Font f = txtAreaDetail.getFont();
        Font f2 = new Font(f.getFontName(), f.getStyle(), f.getSize() + 3);
        txtAreaDetail.setFont(f2);*/
        Font font = new Font("Tw Cen MT", Font.BOLD, 20);
        txtAreaDetail.setFont(font);
        JScrollPane txtAreaScroll = new JScrollPane();
        txtAreaScroll.setViewportView(txtAreaDetail);
        d.add(txtAreaScroll);
        d.pack();
        d.setVisible(true);
        d.setLocationRelativeTo(null);
    }//GEN-LAST:event_helpButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        this.setVisible(false);
        this.dispose();
        Menu menu = new Menu();
        menu.setPreferredSize(new Dimension(1397, 842));
        menu.pack();
        menu.setVisible(true);
                    Model.money = 200;
            View.moneyView.setText("" + Model.money);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void timerContinuePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timerContinuePanelMouseClicked
        this.paused = false;
        timer.start();
        timerForEnemies.start();
        for(int i = 0; i<Model.towers.size(); ++i){
            Model.towers.get(i).getTimer().start();
        }
    }//GEN-LAST:event_timerContinuePanelMouseClicked

    private void timerStopPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timerStopPanelMouseClicked
        // TODO add your handling code here:
        timer.stop();
        this.paused = true;
        timerForEnemies.stop();
        for(int i = 0; i<Model.towers.size(); ++i){
            Model.towers.get(i).getTimer().stop();
        }
    }//GEN-LAST:event_timerStopPanelMouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked

        if (!selected1) {
            Border border = BorderFactory.createLineBorder(new Color(0, 255, 255), 4, true);
            jLabel5.setBorder(border);
            selected1 = true;
            selected2 = false;
            selected3 = false;
            selected4 = false;
            Border border2 = BorderFactory.createLineBorder(new Color(0, 255, 255), 0, true);
            jLabel7.setBorder(border2);
            Border border3 = BorderFactory.createLineBorder(new Color(0, 255, 255), 0, true);
            jLabel8.setBorder(border3);
            Border border4 = BorderFactory.createLineBorder(new Color(0, 255, 255), 0, true);
            jLabel9.setBorder(border4);
            return;
        } else {
            Border border = BorderFactory.createLineBorder(new Color(0, 255, 255), 0, true);
            jLabel5.setBorder(border);
            selected1 = false;
        }
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        if (!selected3) {
            Border border = BorderFactory.createLineBorder(new Color(0, 255, 255), 4, true);
            jLabel8.setBorder(border);
            selected3 = true;
            selected1 = false;
            selected2 = false;
            selected4 = false;
            Border border2 = BorderFactory.createLineBorder(new Color(0, 255, 255), 0, true);
            jLabel7.setBorder(border2);
            Border border3 = BorderFactory.createLineBorder(new Color(0, 255, 255), 0, true);
            jLabel5.setBorder(border3);
            Border border4 = BorderFactory.createLineBorder(new Color(0, 255, 255), 0, true);
            jLabel9.setBorder(border4);
            return;
        } else {
            Border border = BorderFactory.createLineBorder(new Color(0, 255, 255), 0, true);
            jLabel8.setBorder(border);
            selected3 = false;
        }
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked

        if (!selected2) {
            Border border = BorderFactory.createLineBorder(new Color(0, 255, 255), 4, true);
            jLabel7.setBorder(border);
            selected2 = true;
            selected1 = false;
            selected3 = false;
            selected4 = false;
            Border border2 = BorderFactory.createLineBorder(new Color(0, 255, 255), 0, true);
            jLabel5.setBorder(border2);
            Border border3 = BorderFactory.createLineBorder(new Color(0, 255, 255), 0, true);
            jLabel8.setBorder(border3);
            Border border4 = BorderFactory.createLineBorder(new Color(0, 255, 255), 0, true);
            jLabel9.setBorder(border4);
            return;
        } else {
            Border border = BorderFactory.createLineBorder(new Color(0, 255, 255), 0, true);
            jLabel7.setBorder(border);
            selected2 = false;
        }
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked

        if (!selected4) {
            Border border = BorderFactory.createLineBorder(new Color(0, 255, 255), 4, true);
            jLabel9.setBorder(border);
            selected4 = true;
            selected2 = false;
            selected3 = false;
            selected1 = false;
            Border border2 = BorderFactory.createLineBorder(new Color(0, 255, 255), 0, true);
            jLabel7.setBorder(border2);
            Border border3 = BorderFactory.createLineBorder(new Color(0, 255, 255), 0, true);
            jLabel8.setBorder(border3);
            Border border4 = BorderFactory.createLineBorder(new Color(0, 255, 255), 0, true);
            jLabel5.setBorder(border4);
            return;
        } else {
            Border border = BorderFactory.createLineBorder(new Color(0, 255, 255), 0, true);
            jLabel9.setBorder(border);
            selected4 = false;
        }
    }//GEN-LAST:event_jLabel9MouseClicked

    private void boardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boardMouseClicked
        System.out.println(evt);
    }//GEN-LAST:event_boardMouseClicked

    public void resetBorder() {
        Border nullborder = BorderFactory.createLineBorder(new Color(0, 255, 255), 0, true);
        jLabel7.setBorder(nullborder);
        jLabel5.setBorder(nullborder);
        jLabel8.setBorder(nullborder);
        jLabel9.setBorder(nullborder);
        selected4 = false;
        selected2 = false;
        selected3 = false;
        selected1 = false;
    }

    public static int checkClick() {
        if (selected1) {
            return 1;
        } else if (selected2) {
            return 2;
        } else if (selected3) {
            return 3;
        } else if (selected4) {
            return 4;
        } else {
            return 0;
        }
    } 
    
    public static void createGameOverDialog(){
        int result = JOptionPane.showConfirmDialog(null,
                "A játék sajnos végetért.",
                "FIGYELEM!",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE);
            if(result==JOptionPane.OK_OPTION || result==JOptionPane.CLOSED_OPTION){
                Menu.v.setVisible(false);
                Menu.v.dispose();
                Menu menu = new Menu();
                menu.setPreferredSize(new Dimension(1397, 842));
                menu.pack();
                menu.setVisible(true);
            }
    }
    
    public void createMoneyNotEnoughDialog(){
        int result = JOptionPane.showConfirmDialog(null,
                "Erre a toronyra nincs elég pénz!",
                "FIGYELEM!",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel board;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton helpButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JLabel moneyView;
    private javax.swing.JLabel timeView;
    private javax.swing.JPanel timerContinuePanel;
    private javax.swing.JPanel timerStopPanel;
    // End of variables declaration//GEN-END:variables
}
