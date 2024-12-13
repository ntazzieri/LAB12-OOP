package it.unibo.es3;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class GUI extends JFrame {
    
    private final Map<JButton, Pair<Integer, Integer>> cells = new HashMap<>();
    
    public GUI(int width) {
        Logics logic = new LogicsImpl(width);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(70*width, 70*width);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        this.getContentPane().add(mainPanel);

        JPanel northPanel = new JPanel(new GridLayout(width,width));
        mainPanel.add(BorderLayout.CENTER, northPanel);

        ActionListener al = e -> {
            var jb = (JButton)e.getSource();
        	jb.setText(String.valueOf(cells.get(jb)));
        };
          
        final JButton nextBtn = new JButton(">");
        nextBtn.addActionListener(acl -> {
            logic.hitNext();
            cells.entrySet().stream()
                .filter(f -> logic.getCell(f.getValue()).equals("*"))
                .forEach(e -> {
                    e.getKey().setText("*");
                    e.getKey().setEnabled(true);
                });
            if(logic.quit()) {
                System.exit(0);
            }
        });

        mainPanel.add(BorderLayout.SOUTH, nextBtn);

        for (int i=0; i<width; i++){
            for (int j=0; j<width; j++){
            	var pos = new Pair<>(j,i);
                final JButton jb = new JButton(logic.getCell(pos));
                jb.setEnabled(logic.getCell(pos).equals("*"));
                this.cells.put(jb, pos);
                jb.addActionListener(al);
                northPanel.add(jb);
            }
        }
        this.setVisible(true);
    }
    
}