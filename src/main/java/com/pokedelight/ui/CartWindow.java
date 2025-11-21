package com.pokedelight.ui;

import javax.swing.*;
import java.awt.*;
import com.pokedelight.model.OrderItem;

public class CartWindow extends javax.swing.JFrame {

    public CartWindow() {
        initComponents();
        refresh();
    }

    private void initComponents() {
        setTitle("Carrinho - PokeDelight");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(500,400);
        setLocationRelativeTo(null);
        listModel = new DefaultListModel<>();
        lst = new JList<>(listModel);
        JScrollPane sp = new JScrollPane(lst);
        JButton btnCheckout = new JButton("Finalizar compra");
        btnCheckout.addActionListener(e -> new PaymentWindow().setVisible(true));
        JButton btnClear = new JButton("Limpar carrinho");
        btnClear.addActionListener(e -> { Cart.clear(); refresh(); });
        JPanel south = new JPanel();
        south.add(btnClear); south.add(btnCheckout);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(sp, BorderLayout.CENTER);
        getContentPane().add(south, BorderLayout.SOUTH);
    }

    private void refresh() {
        listModel.clear();
        for (OrderItem it : Cart.getItems()) {
            listModel.addElement(it.getPokemon().getName() + " x" + it.getQuantity() + " — R$ " + String.format("%.2f", it.getPrice()*it.getQuantity()));
        }
    }

    private DefaultListModel<String> listModel;
    private JList<String> lst;
}
