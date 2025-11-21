package com.pokedelight.ui;

import javax.swing.*;

public class SobreWindow extends javax.swing.JFrame {

    public SobreWindow() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Sobre - PokeDelight");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(500,300);
        setLocationRelativeTo(null);

        JLabel lbl = new JLabel("<html><h3>PokeDelight</h3><p>Projeto escolar: restaurante tema Pokémon.<br/>Feito com Java Swing, MySQL e Maven.</p></html>");
        lbl.setBorder(javax.swing.BorderFactory.createEmptyBorder(20,20,20,20));
        getContentPane().add(lbl);
    }
}
