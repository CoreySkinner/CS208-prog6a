/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog6a;

import javax.swing.JFrame;

/**
 *
 * @author Hang
 */
public class Tester extends JFrame {
    private GUI gui;
    
    public Tester(){
        gui = new GUI();
        gui.setFocusable(true);
        gui.requestFocusInWindow();
        add(gui);
        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Tester tester = new Tester();
    }
    
}
