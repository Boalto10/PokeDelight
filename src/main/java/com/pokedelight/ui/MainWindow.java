package com.pokedelight.ui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends javax.swing.JFrame {

    public MainWindow() {
        initComponents();
    }

    private void initComponents() {
        setTitle("PokeDelight - Restaurante Pokémon");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900,600);
        setLocationRelativeTo(null);

        JPanel nav = new JPanel(new BorderLayout());
        nav.setBackground(new Color(30,144,255));
        JLabel brand = new JLabel("  PokeDelight");
        brand.setForeground(Color.white);
        brand.setFont(new Font("SansSerif", Font.BOLD, 22));
        nav.add(brand, BorderLayout.WEST);

        JPanel navButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        navButtons.setOpaque(false);
        JButton btnMenu = new JButton("Cardápio");
        btnMenu.addActionListener(e -> new MenuWindow().setVisible(true));
        JButton btnPedidos = new JButton("Pedidos");
        btnPedidos.addActionListener(e -> new CartWindow().setVisible(true));
        JButton btnSobre = new JButton("Sobre");
        btnSobre.addActionListener(e -> new SobreWindow().setVisible(true));
        JButton btnAdmin = new JButton("Admin");
        btnAdmin.addActionListener(e -> new AdminDashboard().setVisible(true));
        navButtons.add(btnMenu); navButtons.add(btnPedidos); navButtons.add(btnSobre); navButtons.add(btnAdmin);
        nav.add(navButtons, BorderLayout.EAST);

        JPanel hero = new JPanel(new BorderLayout());
        hero.setBackground(Color.white);
        JLabel htitle = new JLabel("<html><h2>Bem-vindo ao <span style='color:#1e90ff'>PokeDelight</span></h2><p>O melhor restaurante Pokémon — sabores inspirados nos seus Pokémon favoritos.</p></html>");
        htitle.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        hero.add(htitle, BorderLayout.WEST);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(nav, BorderLayout.NORTH);
        getContentPane().add(hero, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow().setVisible(true));
    }
}
