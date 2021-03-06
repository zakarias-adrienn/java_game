package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.net.URL;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import model.Model;

public class Menu extends javax.swing.JFrame {

    public static View v;
    public static Model model;
    public static boolean level1ButtonClicked = false;
    public static boolean level2ButtonClicked = false;
    public static boolean level3ButtonClicked = false;

    public Menu() {
        initComponents();
        URL url = Menu.class.getClassLoader().getResource("res/fish.png");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(url));
        this.setTitle("Kezdőképernyő");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        towersButton = new javax.swing.JButton();
        enemiesButton = new javax.swing.JButton();
        creditsButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        level1Button = new javax.swing.JButton();
        level2Button = new javax.swing.JButton();
        level3Button = new javax.swing.JButton();
        hintsButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        towersButton.setBackground(new java.awt.Color(0, 0, 51));
        towersButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 20)); // NOI18N
        towersButton.setForeground(new java.awt.Color(250, 215, 172));
        towersButton.setText("Tornyok");
        towersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                towersButtonActionPerformed(evt);
            }
        });
        getContentPane().add(towersButton);
        towersButton.setBounds(20, 10, 150, 40);

        enemiesButton.setBackground(new java.awt.Color(0, 0, 51));
        enemiesButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 20)); // NOI18N
        enemiesButton.setForeground(new java.awt.Color(250, 215, 172));
        enemiesButton.setText("Ellenségek");
        enemiesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enemiesButtonActionPerformed(evt);
            }
        });
        getContentPane().add(enemiesButton);
        enemiesButton.setBounds(190, 10, 180, 40);

        creditsButton.setBackground(new java.awt.Color(0, 0, 51));
        creditsButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 20)); // NOI18N
        creditsButton.setForeground(new java.awt.Color(250, 215, 172));
        creditsButton.setText("Megvalósítók");
        creditsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creditsButtonActionPerformed(evt);
            }
        });
        getContentPane().add(creditsButton);
        creditsButton.setBounds(390, 10, 180, 40);

        exitButton.setBackground(new java.awt.Color(255, 51, 51));
        exitButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 22)); // NOI18N
        exitButton.setForeground(new java.awt.Color(0, 0, 51));
        exitButton.setText("Kilépés");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        getContentPane().add(exitButton);
        exitButton.setBounds(1220, 10, 150, 40);

        level1Button.setBackground(new java.awt.Color(250, 215, 172));
        level1Button.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 36)); // NOI18N
        level1Button.setForeground(new java.awt.Color(0, 0, 51));
        level1Button.setText("1. pálya");
        level1Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                level1ButtonActionPerformed(evt);
            }
        });
        getContentPane().add(level1Button);
        level1Button.setBounds(580, 390, 220, 80);

        level2Button.setBackground(new java.awt.Color(250, 215, 172));
        level2Button.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 36)); // NOI18N
        level2Button.setForeground(new java.awt.Color(0, 0, 51));
        level2Button.setText("2. pálya");
        level2Button.setEnabled(false);
        level2Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                level2ButtonActionPerformed(evt);
            }
        });
        getContentPane().add(level2Button);
        level2Button.setBounds(580, 510, 220, 80);

        level3Button.setBackground(new java.awt.Color(250, 215, 172));
        level3Button.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 36)); // NOI18N
        level3Button.setForeground(new java.awt.Color(0, 0, 51));
        level3Button.setText("3. pálya");
        level3Button.setEnabled(false);
        level3Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                level3ButtonActionPerformed(evt);
            }
        });
        getContentPane().add(level3Button);
        level3Button.setBounds(580, 620, 220, 80);

        hintsButton1.setBackground(new java.awt.Color(0, 0, 51));
        hintsButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 22)); // NOI18N
        hintsButton1.setForeground(new java.awt.Color(250, 215, 172));
        hintsButton1.setText("A játékról");
        hintsButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hintsButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(hintsButton1);
        hintsButton1.setBounds(590, 10, 150, 40);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/menu.png"))); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(1397, 842));
        jLabel1.setMinimumSize(new java.awt.Dimension(1397, 842));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1490, 845);

        setBounds(0, 0, 1397, 872);
    }// </editor-fold>//GEN-END:initComponents

    private void towersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_towersButtonActionPerformed

        JDialog d = new JDialog(this, "Tornyok");
        JTextArea txtAreaDetail = new JTextArea("- GoldTower: Ezzel a toronnyal a játékban eltelt idővel arányosan  \n"
                + "  lehet pénz gyűjteni. Aktív támadásra nem használható, azonban \n"
                + "  minden - más torony által - kilőtt Goldfish után a pénztermelés \n"
                + "  gyorsasága a kétszeresére nő. Ára: 20.\n\n"
                + "- BubbleTower: Az ElectricTower-rel ellentétben ez a toronytípus\n"
                + "  pontatlanabbul, de sokkal nagyobb hatőkörben képes lőni maga \n"
                + "  körül a fejlesztésektől függő sugarú körben. Enemy, akire\n"
                + "  leginkább hatásos: Fugu. Ára: 80.\n\n"
                + "- IceTower: Ez a torony a hatókörébe érkező gyors Swordfish-eket \n"
                + "  képes lelassítani, fejlesztések után akár megállítani, \n"
                + "  befagyasztani, hogy minél nagyobb arányban ki tudja őket lőni. Ára: 100.\n\n"
                + "- ElectricTower: Ez a torony nagyon gyors ütemben, de egyszerre\n"
                + "  csak egy enemy-t képes lőni nagy pontossággal, messziről. Enemy, \n"
                + "  akire leginkább hatásos: Eel. Ára: 50.");
        txtAreaDetail.setEditable(false);
        txtAreaDetail.setBackground(new Color(250, 215, 172));
        txtAreaDetail.setForeground(new Color(0, 0, 81)/*Color.BLACK*/);
        Font font = new Font("Tw Cen MT", Font.BOLD, 25);
        txtAreaDetail.setFont(font);;
        JScrollPane txtAreaScroll = new JScrollPane();
        txtAreaScroll.setViewportView(txtAreaDetail);
        d.add(txtAreaScroll);
        d.pack();
        d.setVisible(true);
        d.setLocationRelativeTo(null);
    }//GEN-LAST:event_towersButtonActionPerformed

    private void level2ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_level2ButtonActionPerformed

        this.setVisible(false);
        Menu.level2ButtonClicked = true;
        Menu.level1ButtonClicked = false;
        Menu.level3ButtonClicked = false;
        this.model = new Model(this);
        this.v = new View();
        v.setVisible(true);
    }//GEN-LAST:event_level2ButtonActionPerformed

    private void level3ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_level3ButtonActionPerformed

        this.setVisible(false);
        Menu.level2ButtonClicked = false;
        Menu.level1ButtonClicked = false;
        Menu.level3ButtonClicked = true;
        this.model = new Model(this);
        this.v = new View();
        v.setVisible(true);

    }//GEN-LAST:event_level3ButtonActionPerformed

    private void level1ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_level1ButtonActionPerformed
        
        this.setVisible(false);
        Menu.level2ButtonClicked = false;
        Menu.level1ButtonClicked = true;
        Menu.level3ButtonClicked = false;
        this.model = new Model(this);
        this.v = new View();
        v.setVisible(true);
    }//GEN-LAST:event_level1ButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_exitButtonActionPerformed

    private void enemiesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enemiesButtonActionPerformed
        JDialog d = new JDialog(this, "Ellenségek");
        JTextArea txtAreaDetail = new JTextArea("- Goldfish: Lassú, de életerős hal, ami megnehezíti a kilövését. \n"
                + "  Minden kilőtt egyed után megkétszereződik a GoldTower által \n"
                + "  termelt pénz.\n\n"
                + "- Swordfish: Nagyon gyors, könnyen mozgó hal. Az ElectricTower\n"
                + "  képes lelassítani, megállítani.\n\n"
                + "- Fugu: Nagytestű, lomha hal, de sokáig él. Legkönnyebben a\n"
                + "  BubbleTower tudja kilőni.\n\n"
                + "- Eel: Gyors, áramvonalas hal. Nehéz messziről eltalálni, ezt \n"
                + "  legkönnyebben az ElectricTower tudja megtenni.");
        txtAreaDetail.setEditable(false);
        txtAreaDetail.setBackground(new Color(250, 215, 172));
        txtAreaDetail.setForeground(new Color(0, 0, 81));
        Font font = new Font("Tw Cen MT", Font.BOLD, 25);
        txtAreaDetail.setFont(font);
        JScrollPane txtAreaScroll = new JScrollPane();
        txtAreaScroll.setViewportView(txtAreaDetail);
        d.add(txtAreaScroll);
        d.pack();
        d.setVisible(true);
        d.setLocationRelativeTo(null);
    }//GEN-LAST:event_enemiesButtonActionPerformed

    private void creditsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creditsButtonActionPerformed
        JDialog d = new JDialog(this, "Készítők");
        JTextArea txtAreaDetail = new JTextArea("A játékot készítették:\n"
                + "\tZakariás Adrienn\n"
                + "\tWolosz András\n"
                + "\tKálmán Jázmin\n"
                + "\t\t2020. tavasz\n"
                + "\t\tELTE IK, Szoftvertechnológia");
        txtAreaDetail.setEditable(false);
        txtAreaDetail.setBackground(new Color(250, 215, 172));
        txtAreaDetail.setForeground(new Color(0, 0, 81));
        Font font = new Font("Tw Cen MT", Font.BOLD, 25);
        txtAreaDetail.setFont(font);
        JScrollPane txtAreaScroll = new JScrollPane();
        txtAreaScroll.setViewportView(txtAreaDetail);
        d.add(txtAreaScroll);
        d.pack();
        d.setVisible(true);
        d.setLocationRelativeTo(null);
    }//GEN-LAST:event_creditsButtonActionPerformed

    private void hintsButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hintsButton1ActionPerformed
        JDialog d = new JDialog(this, "A játékról");
        JTextArea txtAreaDetail = new JTextArea("  Az Atlantis Defence egy tower defence sítlusú, 3 szinten \n"
                + "  elérhető játék. Minden szinten lehetőség van tornyok \n"
                + "  lehelyzésére a pályán megjelölt helyekre, melyek a hullámokban\n"
                + "  érkező ellenségeket hivatottak feltartóztatni, hogy ne érjék el\n"
                + "  a pálya végén található igazgyöngyöt. Ha ez mégis megtörtnik, a\n"
                + "  göngy veszít életerejéből, végső esetben elpusztul, ezzel a \n"
                + "  felhasználó elveszítette a szintet. Minden szint az előző szint\n"
                + "  sikeres teljesítése után válik elérhetővé.");
        txtAreaDetail.setEditable(false);
        txtAreaDetail.setBackground(new Color(250, 215, 172));
        txtAreaDetail.setForeground(new Color(0, 0, 81));
        Font font = new Font("Tw Cen MT", Font.BOLD, 25);
        txtAreaDetail.setFont(font);
        JScrollPane txtAreaScroll = new JScrollPane();
        txtAreaScroll.setViewportView(txtAreaDetail);
        d.add(txtAreaScroll);
        d.pack();
        d.setVisible(true);
        d.setLocationRelativeTo(null);
    }//GEN-LAST:event_hintsButton1ActionPerformed

    public void setButton(int i) {

        if (i == 2) {
            level2Button.setEnabled(true);
        } else if (i == 3) {
            level3Button.setEnabled(true);
        }
    }

    
    
    
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton creditsButton;
    private javax.swing.JButton enemiesButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton hintsButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton level1Button;
    public javax.swing.JButton level2Button;
    public javax.swing.JButton level3Button;
    private javax.swing.JButton towersButton;
    // End of variables declaration//GEN-END:variables
}
