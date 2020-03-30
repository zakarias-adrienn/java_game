package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import view.View;

public class GoldTower extends Tower {
    
    public GoldTower()
    {
        super(true);
        this.life = 100;
        this.spot = null;
        this.price = 20;
        this.distance = 0;
        this.type = "gold";
        
    }
    
    @Override
    public String getType(){
        return "gold";
    }
    
    @Override
    public void shoot() {
        this.towerTimer = new Timer(4000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!View.paused) {
                    Model.money += 5;
                    View.moneyView.setText(Integer.toString(Model.money));
                }
            }
        });
        // valamikor le kell állítani!!!
        this.towerTimer.start();
    }
}
