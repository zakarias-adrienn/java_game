package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import java.util.ArrayList;
import javax.swing.BorderFactory;
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
import model.GoldTower;
import model.IceTower;
import model.Model;
import model.SwordFish;
import model.Tower;
import res.ResourceLoader;

public class View extends javax.swing.JFrame {

    private long startTime;
    public static Timer timerForMoneyViewAndEnemiesComing;
    public static Timer goldTowerTimer;
    public static Timer otherTowerTimer;
    public static Timer bulletTimer;
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
        View.j.setTitle("Torony m??dos??t??sa");
        View.j.setSize(new Dimension(400, 200));
        JPanel outer = new JPanel(new BorderLayout());
        JPanel pan = new JPanel();
        GridLayout layout = new GridLayout(5, 1);
        pan.setLayout(layout);
        button1 = new JButton("Torony feljav??t??sa - ??leter?? feljav??t??sa (50 tall??r)");
        button1.setBackground(new java.awt.Color(250, 215, 172));
        button1.setForeground(new java.awt.Color(0, 0, 51));
        button1.setFont(new java.awt.Font("Tw Cen MT", Font.BOLD, 17));
        button2 = new JButton("Torony feljav??t??sa - t??bb ir??nyba tudjon l??ni");
        button2.setBackground(new java.awt.Color(0, 0, 51));
        button2.setForeground(new java.awt.Color(250, 215, 172));
        button2.setFont(new java.awt.Font("Tw Cen MT", Font.BOLD, 17));
        button3 = new JButton("Csak a saj??t ellens??geit l??jje");
        button3.setBackground(new java.awt.Color(250, 215, 172));
        button3.setForeground(new java.awt.Color(0, 0, 51));
        button3.setFont(new java.awt.Font("Tw Cen MT", Font.BOLD, 17));
        button4 = new JButton("Minden ellens??get l??jj??n");
        button4.setBackground(new java.awt.Color(0, 0, 51));
        button4.setForeground(new java.awt.Color(250, 215, 172));
        button4.setFont(new java.awt.Font("Tw Cen MT", Font.BOLD, 17));
        button5 = new JButton("Torony elad??sa");
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
        this.setTitle("F??k??perny??");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        View.moneyView.setText("" + Model.money);
        this.startTime = System.currentTimeMillis();
        View.timerForMoneyViewAndEnemiesComing = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!paused) {
                    timeView.setText("" + i++);
                    elapsed += 10;
                    for (int i = 0; i < Model.readedEnemies.size(); ++i) {
                        if (Model.readedEnemies.get(i).startTime == elapsed) {
                            try {
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
                                System.out.println("Ellens??g k??p??t nem siker??lt el??rni.\n");
                            }
                        }
                    }
                }

            }
        });

        View.otherTowerTimer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!paused) {
                    for (int i = 0; i < Model.towers.size(); ++i) {
                        Model.towers.get(i).beforeShoot();
                    }
                }
            }
        });
        View.otherTowerTimer.start();

        View.goldTowerTimer = new Timer(4000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!View.paused) {
                    for (int i = 0; i < Model.towers.size(); ++i) {
                        if (Model.towers.get(i) instanceof GoldTower) {
                            Model.towers.get(i).shoot();
                        }
                    }
                    View.moneyView.setText(Integer.toString(Model.money));
                }
            }
        });
        View.goldTowerTimer.start();

        View.bulletTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View.moneyView.setText(Integer.toString(Model.money));
                for (int i = 0; i < Model.allBullets.size(); ++i) {
                    Model.allBullets.get(i).move();
                }
                Point tmp = fish.getLocation();
                int y = 0;
                if (tmp.y > 750) {
                    plus = false;
                } else if (tmp.y < 0) {
                    plus = true;
                }
                if (plus) {
                    y = tmp.y + 10;
                } else {
                    y = tmp.y - 10;
                }

                fish.setLocation(tmp.x, y);
                for (int i = 0; i < Model.enemies.size(); ++i) {
                    Model.enemies.get(i).collosion();
                }
            }
        });
        View.bulletTimer.start();
        timerForMoneyViewAndEnemiesComing.start();
    }

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
        helpButton.setText("S??g??");
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
        exitButton.setText("Kil??p??s");
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
        JDialog d = new JDialog(this, "S??g??");
        JTextArea txtAreaDetail = new JTextArea("TORNYOK\n\n- GoldTower: Ezzel a toronnyal a j??t??kban eltelt id??vel ar??nyosan  \n"
                + "  lehet p??nz gy??jteni. Akt??v t??mad??sra nem haszn??lhat??, azonban \n"
                + "  minden - m??s torony ??ltal - kil??tt Goldfish ut??n a p??nztermel??s \n"
                + "  gyorsas??ga a k??tszeres??re n??.\n\n"
                + "- BubbleTower: Az ElectricTower-rel ellent??tben ez a toronyt??pus\n"
                + "  pontatlanabbul, de sokkal nagyobb hat??k??rben k??pes l??ni maga \n"
                + "  k??r??l a fejleszt??sekt??l f??gg?? sugar?? k??rben. Enemy, akire\n"
                + "  legink??bb hat??sos: Fugu.\n\n"
                + "- IceTower: Ez a torony a hat??k??r??be ??rkez?? gyors Swordfish-eket \n"
                + "  k??pes lelass??tani, fejleszt??sek ut??n ak??r meg??ll??tani, \n"
                + "  befagyasztani, hogy min??l nagyobb ar??nyban ki tudja ??ket l??ni.\n\n"
                + "- ElectricTower: Ez a torony nagyon gyors ??temben, de egyszerre\n"
                + "  csak egy enemy-t k??pes l??ni nagy pontoss??ggal, messzir??l. Enemy, \n"
                + "  akire legink??bb hat??sos: Eel.\n\n"
                + "ELLENS??GEK\n\n"
                + "- Goldfish: Lass??, de ??leter??s hal, ami megnehez??ti a kil??v??s??t. \n"
                + "  Minden kil??tt egyed ut??n megk??tszerez??dik a GoldTower ??ltal \n"
                + "  termelt p??nz.\n\n"
                + "- Swordfish: Nagyon gyors, k??nnyen mozg?? hal. Az ElectricTower\n"
                + "  k??pes lelass??tani, meg??ll??tani.\n\n"
                + "- Fugu: Nagytest??, lomha hal, de sok??ig ??l. Legk??nnyebben a\n"
                + "  BubbleTower tudja kil??ni.\n\n"
                + "- Eel: Gyors, ??ramvonalas hal. Neh??z messzir??l eltal??lni, ezt \n"
                + "  legk??nnyebben az ElectricTower tudja megtenni.");
        txtAreaDetail.setEditable(false);
        txtAreaDetail.setBackground(new Color(240, 248, 255));
        txtAreaDetail.setForeground(new Color(0, 0, 81));
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
        if (AtlantisDefense.level2Opened) {
            menu.level2Button.setEnabled(true);
        }
        if (AtlantisDefense.level3Opened) {
            menu.level3Button.setEnabled(true);
        }
        menu.setPreferredSize(new Dimension(1397, 842));
        menu.pack();
        menu.setVisible(true);
        Model.money = Model.moneyDefaultValue;
        View.moneyView.setText("" + Model.money);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void timerContinuePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timerContinuePanelMouseClicked
        this.paused = false;
        timerForMoneyViewAndEnemiesComing.start();
        otherTowerTimer.start();
        goldTowerTimer.start();
        bulletTimer.start();
    }//GEN-LAST:event_timerContinuePanelMouseClicked

    private void timerStopPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timerStopPanelMouseClicked
        // TODO add your handling code here:
        timerForMoneyViewAndEnemiesComing.stop();
        this.paused = true;
        otherTowerTimer.stop();
        goldTowerTimer.stop();
        bulletTimer.stop();
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
        if (View.j != null) {
            View.j.setVisible(false);
        }
        if (View.timerForMoneyViewAndEnemiesComing != null) {
            View.timerForMoneyViewAndEnemiesComing.stop();
        }
        if (View.otherTowerTimer != null) {
            View.otherTowerTimer.stop();
        }
        if (View.bulletTimer != null) {
            View.bulletTimer.stop();
        }
        if (Board.timers != null) {
            for (int i = 0; i < Board.timers.size(); ++i) {
                Board.timers.get(i).stop();
            }
        }
        if (Menu.v != null) {
            int result = JOptionPane.showConfirmDialog(null,
                    "A j??t??k sajnos v??get??rt. Vesz??tett??l! \nLegy??z??tt ellens??gek sz??ma: " + Model.deadEnemyNum,
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

    }

    public static void createWinDialog() {
        if (View.j != null && View.timerForMoneyViewAndEnemiesComing != null && View.otherTowerTimer != null && View.bulletTimer != null && Board.timers != null) {
            View.j.setVisible(false);
            View.timerForMoneyViewAndEnemiesComing.stop();
            View.otherTowerTimer.stop();
            View.bulletTimer.stop();
            for (int i = 0; i < Board.timers.size(); ++i) {
                Board.timers.get(i).stop();
            }
        }
        if (Menu.v != null) {
            String message = "Gratul??lok, gy??zt??l!\n El??rhet?? a k??vetkez?? szint.";
            if (AtlantisDefense.level3Opened) {
                message = "Gratul??lok, gy??zt??l!\n Sikeresen megv??dted Atlantist ??s annak igazgy??ngy??t!\n Ha gondolod j??tszd ??jra a p??ly??kat!";
            }
            int result = JOptionPane.showConfirmDialog(null,
                    message,
                    "GRATUL??LOK!",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION || result == JOptionPane.CLOSED_OPTION) {
                Menu.v.setVisible(false);
                Menu.v.dispose();
                Menu menu = new Menu();
                menu.setPreferredSize(new Dimension(1397, 842));
                menu.pack();
                menu.setVisible(true);
                System.out.println("HAH??");
                System.out.println(menu.level3Button.isEnabled() + ", " + AtlantisDefense.level2Opened);
                if (!AtlantisDefense.level2Opened) {
                    menu.level2Button.setEnabled(true);
                    AtlantisDefense.level2Opened = true;
                } else if (!menu.level3Button.isEnabled() && AtlantisDefense.level2Opened) {
                    menu.level2Button.setEnabled(true);
                    menu.level3Button.setEnabled(true);
                    AtlantisDefense.level3Opened = true;
                }
            }
        }

    }

    public static void closeDialogForTower() {
        View.j.setVisible(false);
        View.j.dispose();
    }

    public static void createDialogForTower(int x, int y, Cell thumb) {
        View.j = new JDialog();
        URL url = View.class.getClassLoader().getResource("res/fish.png");
        View.j.setIconImage(Toolkit.getDefaultToolkit().getImage(url));
        View.j.setTitle("Torony m??dos??t??sa");
        View.j.setSize(new Dimension(400, 200));
        View.j.setLocationRelativeTo(Menu.v);
        JPanel outer = new JPanel(new BorderLayout());
        JPanel pan = new JPanel();
        GridLayout layout = new GridLayout(6, 1);
        pan.setLayout(layout);
        button1 = new JButton("Torony feljav??t??sa - ??leter?? feljav??t??sa (50 tall??r)");
        button1.setBackground(new java.awt.Color(250, 215, 172));
        button1.setForeground(new java.awt.Color(0, 0, 51));
        button1.setFont(new java.awt.Font("Tw Cen MT", Font.BOLD, 17));
        button2 = new JButton("Torony feljav??t??sa - arany szint (100 tall??r)");
        button2.setBackground(new java.awt.Color(0, 0, 51));
        button2.setForeground(new java.awt.Color(250, 215, 172));
        button2.setFont(new java.awt.Font("Tw Cen MT", Font.BOLD, 17));
        button6 = new JButton("Torony feljav??t??sa - v??r??s szint (200 tall??r)");
        button6.setBackground(new java.awt.Color(0, 0, 51));
        button6.setForeground(new java.awt.Color(250, 215, 172));
        button6.setFont(new java.awt.Font("Tw Cen MT", Font.BOLD, 17));
        button3 = new JButton("Csak a saj??t ellens??geit l??jje");
        button3.setBackground(new java.awt.Color(250, 215, 172));
        button3.setForeground(new java.awt.Color(0, 0, 51));
        button3.setFont(new java.awt.Font("Tw Cen MT", Font.BOLD, 17));
        button4 = new JButton("Minden ellens??get l??jj??n");
        button4.setBackground(new java.awt.Color(0, 0, 51));
        button4.setForeground(new java.awt.Color(250, 215, 172));
        button4.setFont(new java.awt.Font("Tw Cen MT", Font.BOLD, 17));
        button5 = new JButton("Torony elad??sa");
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
        Tower t = Model.findTower(x, y);
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
                Tower t = Model.findTower(x, y);
                if (Model.money >= 50 && t != null) {
                    Model.money -= 50;
                    moneyView.setText("" + Model.money);
                    t.increaseLife();
                    try {
                        thumb.setLife(100);
                        thumb.repaint();
                    } catch (IOException ex) {
                        System.out.println("Nem siker??lt feljav??tani az ??leter??t a healthbaron.");
                    }
                } else if (t != null) {
                    View.createMoneyNotEnoughDialog();
                }
                View.j.setVisible(false);
                View.j.dispose();
            }
        });

        // csak a saj??t ellens??geit l??jje
        button3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Tower t = Model.findTower(x, y);
                if (button3.isEnabled()) {
                    if (t != null) {
                        t.onlyMyEnemiesShooting = true;
                    }
                    View.j.setVisible(false);
                    View.j.dispose();
                }
            }
        });

        // minden ellens??get l??jj??n
        button4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Tower t = Model.findTower(x, y);
                if (button4.isEnabled()) {
                    if (t != null) {
                        t.onlyMyEnemiesShooting = false;
                    }
                    View.j.setVisible(false);
                    View.j.dispose();
                }
            }
        });

        // torony elad??sa
        button5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Tower t = Model.findTower(x, y);
                if (t != null) {
                    thumb.wasJustPlaced = true;
                    Model.towers.remove(t);
                    int moneyForTower = Math.round(t.getLife() / 10);
                    Model.money += moneyForTower;
                    moneyView.setText("" + Model.money);
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
                        System.out.println("Nem siker??lt toronyhelyre cser??lni az eladni k??v??nt tornyot");
                    }
                }

                View.j.setVisible(false);
                View.j.dispose();
            }
        });

        // torony feljav??t??sa arany szint, speedet m??g nem v??ltoztatja
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // ide esetleg lehetne hogyha isEnabled csak akkor t??rt??njen valami
                if (button2.isEnabled()) {
                    Tower t = Model.findTower(x, y);
                    if (Model.money - 100 >= 0 && t != null) {
                        Model.money = Model.money - 100;
                        moneyView.setText("" + Model.money);
                        Image img = null;
                        ImageIcon icon;
                        if (t instanceof BubbleTower) {
                            t.setDistance(35);
                            try {
                                img = ResourceLoader.loadImage("res/bubble_bg_2.png");
                            } catch (IOException ex) {
                                System.out.println("Nem siker??lt az upgrade k??p bet??lt??se.");
                            }
                        } else if (t instanceof ElectricTower) {
                            t.setDistance(4);
                            try {
                                img = ResourceLoader.loadImage("res/electric_bg_2.png");
                            } catch (IOException ex) {
                                System.out.println("Nem siker??lt az upgrade k??p bet??lt??se.");
                            }
                        } else if (t instanceof IceTower) {
                            t.setDistance(8);
                            try {
                                img = ResourceLoader.loadImage("res/ice_bg_2.png");
                            } catch (IOException ex) {
                                System.out.println("Nem siker??lt az upgrade k??p bet??lt??se.");
                            }
                        } else {
                            t.setRaiseMoneyValue(10);
                            try {
                                img = ResourceLoader.loadImage("res/gold_bg_2.png");
                            } catch (IOException ex) {
                                System.out.println("Nem siker??lt az upgrade k??p bet??lt??se.");
                            }
                        }
                        icon = new ImageIcon(img);
                        Board.cells[t.getPos().x][t.getPos().y].setIcon(icon);
                        t.increaseLife();
                        try {
                            Board.cells[t.getPos().x][t.getPos().y].setLife(100);
                            Board.cells[t.getPos().x][t.getPos().y].repaint();
                        } catch (IOException ex) {
                            System.out.println("Nem siker??lt az ??leter??t feljav??tani.");
                        }
                    } else if (t != null) {
                        // ha nincs el??g p??nze
                        View.createMoneyNotEnoughDialog();
                    }
                    View.j.setVisible(false);
                    View.j.dispose();
                }
            }
        });

        // torony feljav??t??sa v??r??s szint, m??g a speedet nem v??ltoztatja
        button6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (button6.isEnabled()) {
                    Tower t = Model.findTower(x, y);
                    if (Model.money - 200 >= 0 && t != null) {
                        Model.money = Model.money - 200;
                        moneyView.setText("" + Model.money);
                        Image img = null;
                        ImageIcon icon;
                        if (t instanceof BubbleTower) {
                            t.setDistance(40);
                            try {
                                img = ResourceLoader.loadImage("res/bubble_bg_3.png");
                            } catch (IOException ex) {
                                System.out.println("Nem siker??lt az upgrade k??p bet??lt??se.");
                            }
                        } else if (t instanceof ElectricTower) {
                            t.setDistance(5);
                            try {
                                img = ResourceLoader.loadImage("res/electric_bg_3.png");
                            } catch (IOException ex) {
                                System.out.println("Nem siker??lt az upgrade k??p bet??lt??se.");
                            }
                        } else if (t instanceof IceTower) {
                            t.setDistance(9);
                            try {
                                img = ResourceLoader.loadImage("res/ice_bg_3.png");
                            } catch (IOException ex) {
                                System.out.println("Nem siker??lt az upgrade k??p bet??lt??se.");
                            }
                        } else {
                            t.setRaiseMoneyValue(15);
                            try {
                                img = ResourceLoader.loadImage("res/gold_bg_3.png");
                            } catch (IOException ex) {
                                System.out.println("Nem siker??lt az upgrade k??p bet??lt??se.");
                            }
                        }
                        icon = new ImageIcon(img);
                        Board.cells[t.getPos().x][t.getPos().y].setIcon(icon);
                        t.increaseLife();
                        try {
                            Board.cells[t.getPos().x][t.getPos().y].setLife(100);
                            Board.cells[t.getPos().x][t.getPos().y].repaint();
                        } catch (IOException ex) {
                            System.out.println("Nem siker??lt az ??leter??t feljav??tani.");
                        }
                    } else if (t != null) {
                        // ha nincs el??g p??nze
                        View.createMoneyNotEnoughDialog();

                    }
                    View.j.setVisible(false);
                    View.j.dispose();
                }
            }
        });
    }

    public static void createMoneyNotEnoughDialog() {
        int result = JOptionPane.showConfirmDialog(null,
                "Ehhez sajnos nincs el??g p??nzed!",
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
