package com.pokedelight.ui;

import javax.swing.*;
import java.awt.*;
import com.pokedelight.dao.PokemonDAO;
import com.pokedelight.model.Order;
import com.pokedelight.model.OrderItem;
import java.util.List;
import java.util.ArrayList;

public class PaymentWindow extends javax.swing.JFrame {

    public PaymentWindow() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Pagamento - PokeDelight");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(400,350);
        setLocationRelativeTo(null);

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        JLabel lblName = new JLabel("Nome:");
        txtName = new JTextField();
        JLabel lblAddress = new JLabel("Endereço:");
        txtAddress = new JTextArea();
        txtAddress.setRows(4);
        form.add(lblName); form.add(txtName);
        form.add(lblAddress); form.add(txtAddress);

        JLabel lblTotal = new JLabel("Total: R$ " + String.format("%.2f", Cart.total()));
        JButton btnPay = new JButton("Pagar");
        btnPay.addActionListener(e -> doPayment());

        form.add(Box.createVerticalStrut(10));
        form.add(lblTotal);
        form.add(Box.createVerticalStrut(10));
        form.add(btnPay);
        getContentPane().add(form);
    }

    private void doPayment() {
        try {
            String name = txtName.getText().trim();
            String addr = txtAddress.getText().trim();
            if (name.isEmpty() || addr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha nome e endereço.");
                return;
            }
            Order order = new Order();
            order.setCustomerName(name);
            order.setAddress(addr);
            order.setTotal(Cart.total());
            List<OrderItem> items = new ArrayList<>();
            for (com.pokedelight.model.OrderItem it : Cart.getItems()) items.add(it);
            order.setItems(items);
            PokemonDAO dao = new PokemonDAO();
            int orderId = dao.createOrder(order);
            JOptionPane.showMessageDialog(this, "Pedido criado! ID: " + orderId);
            Cart.clear();
            this.dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao criar pedido: " + ex.getMessage());
        }
    }

    private javax.swing.JTextField txtName;
    private javax.swing.JTextArea txtAddress;
}
