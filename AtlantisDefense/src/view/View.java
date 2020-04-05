package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.border.Border;
import model.AtlantisDefense;
import model.BubbleTower;
import model.Eel;
import model.ElectricTower;
import model.Enemy;
import model.Fugu;
import model.GoldFish;
import model.IceTower;
import model.Model;
import model.SwordFish;
import model.Tower;
import res.ResourceLoader;

public class View extends javax.swing.JFrame {

    private long startTime;
    public static Timer timer;
    public static Timer timerForEnemies;
    public static Timer timerForElapsed;
    public static Timer timerForAnimation;
    public static Timer timerForCollosion;
    public static JDialog j;
    public static boolean paused = false;
    private long elapsed = 0;
    private int i = 0;
    private boolean plus = true;
    public static JButton button1;
    public static JButton button2;
    public static JButton button3;
    public static JButton button4;
    public static JButton button5;
    public static JButton button6;

    public static JLabel debugTarget;

    private static boolean selected1 = false;
    private static boolean selected2 = false;
    private static boolean selected3 = false;
    private static boolean selected4 = false;

    public View() {
        initComponents();

        View.j = new JDialog();
        View.j.setLocationRelativeTo(Menu.v);
        View.j.setTitle("Torony módosítása");
        View.j.setSize(new Dimension(400, 200));
        JPanel outer = new JPanel(new BorderLayout());
        JPanel pan = new JPanel();
        GridLayout layout = new GridLayout(5, 1);
        pan.setLayout(layout);
        button1 = new JButton("Torony feljavítása - életerő feljavítása (50 tallér)");
        button1.setBackground(new java.awt.Color(250, 215, 172));
        button1.setForeground(new java.awt.Color(0, 0, 51));
        button1.setFont(new java.awt.Font("Tw Cen MT", Font.BOLD, 17));
        button2 = new JButton("Torony feljavítása - több irányba tudjon lőni");
        button2.setBackground(new java.awt.Color(0, 0, 51));
        button2.setForeground(new java.awt.Color(250, 215, 172));
        button2.setFont(new java.awt.Font("Tw Cen MT", Font.BOLD, 17));
        button3 = new JButton("Csak a saját ellenségeit lőjje");
        button3.setBackground(new java.awt.Color(250, 215, 172));
        button3.setForeground(new java.awt.Color(0, 0, 51));
        button3.setFont(new java.awt.Font("Tw Cen MT", Font.BOLD, 17));
        button4 = new JButton("Minden ellenséget lőjjön");
        button4.setBackground(new java.awt.Color(0, 0, 51));
        button4.setForeground(new java.awt.Color(250, 215, 172));
        button4.setFont(new java.awt.Font("Tw Cen MT", Font.BOLD, 17));
        button5 = new JButton("Torony eladása");
        button5.setBackground(new java.awt.Color(250, 215, 172));
        button5.setForeground(new java.awt.Color(0, 0, 51));
        button5.setFont(new java.awt.Font("Tw Cen MT", Font.BOLD, 17));
        pan.add(button1);
        pan.add(button2);
        pan.add(button3);
        pan.add(button4);
        pan.add(button5);
        outer.add(pan);
        View.j.add(outer, BorderLayout.CENTER);

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
                //System.out.println("a");
                /*Point tmp = timeView.getLocation();
                 int x = tmp.x + 10;
                 timeView.setLocation(x, tmp.y);*/
                if (!paused) {
                    timeView.setText("" + i++);

                }

            }
        });

        this.timerForAnimation = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ///System.out.println("wasJustPlaced");
                if (!paused) {
                    Point tmp = fish.getLocation();
                    int x = 0;
                    if (tmp.x > 1000) {
                        plus = false;
                    } else if (tmp.x < 0) {
                        plus = true;
                    }
                    if (plus) {
                        x = tmp.x + 10;
                    } else {
                        x = tmp.x - 10;
                    }

                    fish.setLocation(x, tmp.y);
                }

            }
        });

        /*debugTarget = new javax.swing.JLabel();
         debugTarget.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/debug_target.png")));
         getContentPane().add(debugTarget);
         debugTarget.setBounds(98, 54, 30, 30);
         debugTarget.setForeground(Color.WHITE);
         getContentPane().setComponentZOrder(debugTarget, 0);*/
        this.timerForCollosion = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println("-------");
                //csak nekem segít egyelőre
                for (int i = 0; i < Model.enemies.size(); ++i) {

//                    System.out.println("" + Model.enemies.get(i).collosion().y + ", " + Model.enemies.get(i).collosion().x);
                    Model.enemies.get(i).collosion();
//                    System.out.println(Model.enemies.get(i).life);

                    /*debugTarget.setLocation(Model.enemies.get(0).collosion().x, Model.enemies.get(0).collosion().y);
                     if (Model.enemies.size() >1) 
                     debugTarget.setLocation(Model.enemies.get(1).collosion().x, Model.enemies.get(1).collosion().y);
                     if (Model.enemies.size() >2)
                     debugTarget.setLocation(Model.enemies.get(1).collosion().x, Model.enemies.get(1).collosion().y);*/
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
                                if ("electric".equals(Model.readedEnemies.get(i).type)) {
                                    enemy = new Eel();
                                } else if ("ice".equals(Model.readedEnemies.get(i).type)) {
                                    enemy = new SwordFish();
                                } else if ("bubble".equals(Model.readedEnemies.get(i).type)) {
                                    enemy = new Fugu();
                                } else {
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
        timerForAnimation.start();
        timerForCollosion.start();
    }

//    public long elapsedTime() {
//            return Math.round((this.elapsed - this.startTime) / 100);
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

                                try{
			board = new Board(this);
            getContentPane().add(board);
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		};
        fish = new javax.swing.JLabel();
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

        fish.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/fishanim.png"))); // NOI18N
        fish.setText("jLabel10");
        getContentPane().add(fish);
        fish.setBounds(20, 90, 120, 110);

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
        jLabel1.setBounds(1190, 210, 53, 23);

        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(1180, 210, 53, 23);

        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(1160, 190, 53, 23);

        jLabel4.setText("jLabel4");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(1170, 190, 53, 23);
        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(210, 190, 2, 2);

        setBounds(0, 0, 1397, 872);
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
        Model.money = Model.moneyDefaultValue;
        View.moneyView.setText("" + Model.money);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void timerContinuePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timerContinuePanelMouseClicked
        this.paused = false;
        timer.start();
        timerForEnemies.start();
        for (int i = 0; i < Model.towers.size(); ++i) {
            Model.towers.get(i).getTimer().start();
        }
        for (int i = 0; i < Model.bulletTimers.size(); ++i) {
            Model.bulletTimers.get(i).start();
        }
    }//GEN-LAST:event_timerContinuePanelMouseClicked

    private void timerStopPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timerStopPanelMouseClicked
        // TODO add your handling code here:
        timer.stop();
        this.paused = true;
        timerForEnemies.stop();
        for (int i = 0; i < Model.towers.size(); ++i) {
            Model.towers.get(i).getTimer().stop();
        }
        for (int i = 0; i < Model.bulletTimers.size(); ++i) {
            Model.bulletTimers.get(i).stop();
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

    public static void createGameOverDialog() {
        int result = JOptionPane.showConfirmDialog(null,
                "A játék sajnos végetért. Veszítettél!",
                "FIGYELEM!",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION || result == JOptionPane.CLOSED_OPTION) {
            Menu.v.setVisible(false);
            Menu.v.dispose();
            Menu menu = new Menu();
            menu.setPreferredSize(new Dimension(1397, 842));
            menu.pack();
            menu.setVisible(true);
        }
    }
    
    public static void createWinDialog() {
        String message = "Gratulálok, győztél!\n Elérhető a következő szint.";
        if (AtlantisDefense.level3Opened)
        {
            message = "Gratulálok, győztél!\n Sikeresen megvédted Atlantist és annak igazgyöngyét!\n Ha gondolod játszd újra a pályákat!";
        }
        int result = JOptionPane.showConfirmDialog(null,
                message,
                "GRATULÁLOK!",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION || result == JOptionPane.CLOSED_OPTION) {
            Menu.v.setVisible(false);
            Menu.v.dispose();
            Menu menu = new Menu();
            menu.setPreferredSize(new Dimension(1397, 842));
            menu.pack();
            menu.setVisible(true);
            System.out.println("HAHÓ");
            System.out.println(menu.level3Button.isEnabled() + ", " + AtlantisDefense.level2Opened);
            if (!AtlantisDefense.level2Opened)
            {
                menu.level2Button.setEnabled(true);
                AtlantisDefense.level2Opened = true;
            }
            else if (!menu.level3Button.isEnabled() && AtlantisDefense.level2Opened)
            {
                System.out.println("ITTT MEGVAGYOK MÉG?");
                menu.level2Button.setEnabled(true);
                menu.level3Button.setEnabled(true);
                AtlantisDefense.level3Opened = true;
            }
            System.out.println("HAHÓ2");
        }
    }

    public static void createDialogForTower(int x, int y, Cell thumb) {
        View.j = new JDialog();
        URL url = View.class.getClassLoader().getResource("res/fish.png");
        View.j.setIconImage(Toolkit.getDefaultToolkit().getImage(url));
        View.j.setTitle("Torony módosítása");
        View.j.setSize(new Dimension(400, 200));
        View.j.setLocationRelativeTo(Menu.v);
        JPanel outer = new JPanel(new BorderLayout());
        JPanel pan = new JPanel();
        GridLayout layout = new GridLayout(6, 1);
        pan.setLayout(layout);
        button1 = new JButton("Torony feljavítása - életerő feljavítása (50 tallér)");
        button1.setBackground(new java.awt.Color(250, 215, 172));
        button1.setForeground(new java.awt.Color(0, 0, 51));
        button1.setFont(new java.awt.Font("Tw Cen MT", Font.BOLD, 17));
        button2 = new JButton("Torony feljavítása - arany szint (100 tallér)");
        button2.setBackground(new java.awt.Color(0, 0, 51));
        button2.setForeground(new java.awt.Color(250, 215, 172));
        button2.setFont(new java.awt.Font("Tw Cen MT", Font.BOLD, 17));
        button6 = new JButton("Torony feljavítása - vörös szint (200 tallér)");
        button6.setBackground(new java.awt.Color(0, 0, 51));
        button6.setForeground(new java.awt.Color(250, 215, 172));
        button6.setFont(new java.awt.Font("Tw Cen MT", Font.BOLD, 17));
        button3 = new JButton("Csak a saját ellenségeit lőjje");
        button3.setBackground(new java.awt.Color(250, 215, 172));
        button3.setForeground(new java.awt.Color(0, 0, 51));
        button3.setFont(new java.awt.Font("Tw Cen MT", Font.BOLD, 17));
        button4 = new JButton("Minden ellenséget lőjjön");
        button4.setBackground(new java.awt.Color(0, 0, 51));
        button4.setForeground(new java.awt.Color(250, 215, 172));
        button4.setFont(new java.awt.Font("Tw Cen MT", Font.BOLD, 17));
        button5 = new JButton("Torony eladása");
        button5.setBackground(new java.awt.Color(250, 215, 172));
        button5.setForeground(new java.awt.Color(0, 0, 51));
        button5.setFont(new java.awt.Font("Tw Cen MT", Font.BOLD, 17));
        pan.add(button1);
        pan.add(button2);
        pan.add(button6);
        pan.add(button3);
        pan.add(button4);
        pan.add(button5);
        outer.add(pan);
        View.j.add(outer, BorderLayout.CENTER);
        View.j.setVisible(true);
        Tower t = findTower(x, y);
        if (t.getType().equals("gold")) {
            button3.setEnabled(false);
            button4.setEnabled(false);
        } else {
            button2.setEnabled(true);
            button3.setEnabled(true);
            button4.setEnabled(true);
            button6.setEnabled(true);
        }

        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Model.money >= 50) {
                    Model.money -= 50;
                    moneyView.setText("" + Model.money);
                    // meg kell keresni az ezen a helyen levő tornyot s növelni az életerejét
                    Tower t = findTower(x, y);
                    t.increaseLife();
                    try {
                        thumb.setLife(100);
                        thumb.repaint();
                    } catch (IOException ex) {
                        System.out.println("Nem sikerült feljavítani az életerőt a healthbaron.");
                    }
                }
                View.j.setVisible(false);
//                System.out.println(View.j.isVisible());
                View.j.dispose();
            }
        });

        // csak a saját ellenségeit lőjje
        button3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Tower t = findTower(x, y);
                if (button3.isEnabled()) {
                    t.onlyMyEnemiesShooting = true;
                    View.j.setVisible(false);
                    View.j.dispose();
                }
            }
        });

        // minden ellenséget lőjjön
        button4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Tower t = findTower(x, y);
                if (button4.isEnabled()) {
                    t.onlyMyEnemiesShooting = false;
                    View.j.setVisible(false);
                    View.j.dispose();
                }
            }
        });

        // torony eladása
        button5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Tower t = findTower(x, y);
                if (t != null) {
                    thumb.wasJustPlaced = true;
                    t.towerTimer.stop();
                    Model.towers.remove(t);
                    int moneyForTower = Math.round(t.getLife() / 10);
                    Model.money += moneyForTower;
                    moneyView.setText("" + Model.money);
                }
                try {
                    thumb.unsetIsTower();
                    thumb.isMouseListenerActive = false;
                    Board.cells[thumb.getXPos() - 1][thumb.getYPos() - 1].unsetIsTower();
                    Board.cells[thumb.getXPos() - 1][thumb.getYPos() - 1].setLife(0);
                    Image img = ResourceLoader.loadImage("res/toronyhely.png");
                    ImageIcon icon = new ImageIcon(img);
                    Board.cells[thumb.getXPos() - 1][thumb.getYPos() - 1].setIcon(icon);
                    Board.cells[thumb.getXPos() - 1][thumb.getYPos() - 1].setLife(0);
                    Board.cells[thumb.getXPos() - 1][thumb.getYPos() - 1].repaint();
                } catch (IOException ex) {
                    System.out.println("Nem sikerült toronyhelyre cserélni az eladni kívánt tornyot");
                }
                View.j.setVisible(false);
                View.j.dispose();
            }
        });

        // torony feljavítása arany szint, speedet még nem változtatja
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // ide esetleg lehetne hogyha isEnabled csak akkor történjen valami
                if (button2.isEnabled()) {
                    Tower t = findTower(x, y);
                    if (Model.money - 100 >= 0) {
                        Model.money = Model.money - 100;
                        moneyView.setText("" + Model.money);
                        Image img = null;
                        ImageIcon icon;
                        if (t instanceof BubbleTower) {
                            t.setDistance(35);
                            try {
                                img = ResourceLoader.loadImage("res/bubble_bg_2.png");
                            } catch (IOException ex) {
                                System.out.println("Nem sikerült az upgrade kép betöltése.");
                            }
                        } else if (t instanceof ElectricTower) {
                            t.setDistance(7);
                            try {
                                img = ResourceLoader.loadImage("res/electric_bg_2.png");
                            } catch (IOException ex) {
                                System.out.println("Nem sikerült az upgrade kép betöltése.");
                            }
                        } else if(t instanceof IceTower) {
                            t.setDistance(8);
                            try {
                                img = ResourceLoader.loadImage("res/ice_bg_2.png");
                            } catch (IOException ex) {
                                System.out.println("Nem sikerült az upgrade kép betöltése.");
                            }
                        } else {
                            t.raiseMoneyValue = 10;
                            try {
                                img = ResourceLoader.loadImage("res/gold_bg_2.png");
                            } catch (IOException ex) {
                                System.out.println("Nem sikerült az upgrade kép betöltése.");
                            }
                        }
                        icon = new ImageIcon(img);
                        Board.cells[t.getPos().x][t.getPos().y].setIcon(icon);
                        t.increaseLife();
                        try {
                            Board.cells[t.getPos().x][t.getPos().y].setLife(100);
                            Board.cells[t.getPos().x][t.getPos().y].repaint();
                        } catch (IOException ex) {
                            System.out.println("Nem sikerült az életerőt feljavítani.");
                        }
                        View.j.setVisible(false);
                        View.j.dispose();
                    } else {
                        // ha nincs elég pénze
                        View.createMoneyNotEnoughDialog();
                    }
                }
            }
        });
        
        
        // torony feljavítása vörös szint, még a speedet nem változtatja
        // goldTowert is lehessen? -> ne legyenek potyába a képek?
        button6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // ide esetleg lehetne hogyha isEnabled csak akkor történjen valami
                if (button6.isEnabled()) {
                    Tower t = findTower(x, y);
                    if (Model.money - 200 >= 0) {
                        Model.money = Model.money - 200;
                        moneyView.setText("" + Model.money);
                        Image img = null;
                        ImageIcon icon;
                        if (t instanceof BubbleTower) {
                            t.setDistance(40);
                            try {
                                img = ResourceLoader.loadImage("res/bubble_bg_3.png");
                            } catch (IOException ex) {
                                System.out.println("Nem sikerült az upgrade kép betöltése.");
                            }
                        } else if (t instanceof ElectricTower) {
                            t.setDistance(7);
                            try {
                                img = ResourceLoader.loadImage("res/electric_bg_3.png");
                            } catch (IOException ex) {
                                System.out.println("Nem sikerült az upgrade kép betöltése.");
                            }
                        } else if(t instanceof IceTower) {
                            t.setDistance(9);
                            try {
                                img = ResourceLoader.loadImage("res/ice_bg_3.png");
                            } catch (IOException ex) {
                                System.out.println("Nem sikerült az upgrade kép betöltése.");
                            }
                        } else {
                            t.raiseMoneyValue = 15;
                            try {
                                img = ResourceLoader.loadImage("res/gold_bg_3.png");
                            } catch (IOException ex) {
                                System.out.println("Nem sikerült az upgrade kép betöltése.");
                            }
                        }
                        icon = new ImageIcon(img);
                        Board.cells[t.getPos().x][t.getPos().y].setIcon(icon);
                        t.increaseLife();
                        try {
                            Board.cells[t.getPos().x][t.getPos().y].setLife(100);
                            Board.cells[t.getPos().x][t.getPos().y].repaint();
                        } catch (IOException ex) {
                            System.out.println("Nem sikerült az életerőt feljavítani.");
                        }
                        View.j.setVisible(false);
                        View.j.dispose();
                    }else {
                        // ha nincs elég pénze
                        View.createMoneyNotEnoughDialog();
                    }
                }
            }
        });
    }

    public static Tower findTower(int x, int y) {
        for (int i = 0; i < Model.towers.size(); ++i) {
            if (Model.towers.get(i).getPos().x == x && Model.towers.get(i).getPos().y == y) {
                return Model.towers.get(i);
            }
        }
        return null;
    }

    public static void createMoneyNotEnoughDialog() {
        int result = JOptionPane.showConfirmDialog(null,
                "Ehhez sajnos nincs elég pénzed!",
                "FIGYELEM!",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel board;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel fish;
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
