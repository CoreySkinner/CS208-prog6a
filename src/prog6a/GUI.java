/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog6a;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 *
 * @author Hang
 */
public class GUI extends JPanel {

    private CastleMap castle;
    private Rectangle2D[] rooms;
    private Ellipse2D me;

    public GUI(){
        this.setBackground(Color.CYAN);
        //this.setPreferredSize(new Dimension(700, 700));
        castle = new CastleMap();
        
        rooms = new Rectangle2D[CastleMap.castleSide*CastleMap.castleSide];
        buildCastle();
        
        int firstLocation = castle.getActivatedPlayer().hashCode();
        me = new Ellipse2D.Double(rooms[firstLocation].getX(), rooms[firstLocation].getY(), rooms[firstLocation].getWidth(), rooms[firstLocation].getHeight());
        
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int direction = -1;
                if(e.getKeyCode() == KeyEvent.VK_LEFT)
                    direction = 0;
                else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
                    direction = 1;
                else if (e.getKeyCode() == KeyEvent.VK_UP)
                    direction = 2;
                else if(e.getKeyCode() == KeyEvent.VK_DOWN)
                    direction = 3;
                if( direction != -1){
                    if(castle.movePlayer(castle.getActivatedPlayer(), direction)){
                        for(int i = 0; i < castle.getPlayerArr().length; ++i){
                            if(castle.getPlayerArr()[i] == castle.getActivatedPlayer()){
                                if(i < castle.getPlayerArr().length - 1){
                                    castle.setActivatedPlayer(castle.getPlayerArr()[i+1]);
                                }
                                else{   
                                    castle.setActivatedPlayer(castle.getPlayerArr()[0]);
                                }
                                break;
                            }      
                        }
                        me.setFrame(rooms[castle.getActivatedPlayer().hashCode()]);
                        repaint();
                    }
                }
        }});
    }


    private void buildCastle() {
        for (int i = 0; i < CastleMap.castleSide; ++i) {
            int temp = 0;
            for (int j = i; j <= i + CastleMap.castleSide * (CastleMap.castleSide - 1); j += CastleMap.castleSide) {
                rooms[j] = new Rectangle2D.Double(200 + 50 * i, 550 - 50 * temp, 50, 50);
                temp++;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);
        g2d.drawString("Lives: "+castle.getActivatedPlayer().numLives(), 25, 25);
        g2d.draw(me);
        g2d.drawString(castle.getActivatedPlayer().getName(), (float)me.getX() + 15, (float)me.getY() + 28);
        for (int i = 0; i < rooms.length; ++i) {
            g2d.draw(rooms[i]);
        }
    }
}
