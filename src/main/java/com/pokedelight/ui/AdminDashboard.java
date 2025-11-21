package com.pokedelight.ui;

import com.pokedelight.dao.PokemonDAO;
import com.pokedelight.model.PokemonItem;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AdminDashboard extends javax.swing.JFrame {

    public AdminDashboard() {
        initComponents();
        load();
    }

    private void initComponents() {
        setTitle("Admin - PokeDelight");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(800,500);
        setLocationRelativeTo(null);
        listModel = new DefaultListModel<>();
        lst = new JList<>(listModel);
        JScrollPane sp = new JScrollPane(lst);

        JButton btnAdd = new JButton("Adicionar item");
        btnAdd.addActionListener(e -> addItem());
        JButton btnEdit = new JButton("Editar selecionado");
        btnEdit.addActionListener(e -> editItem());
        JButton btnDel = new JButton("Remover selecionado");
        btnDel.addActionListener(e -> deleteItem());

        JPanel top = new JPanel();
        top.add(btnAdd); top.add(btnEdit); top.add(btnDel);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(top, BorderLayout.NORTH);
        getContentPane().add(sp, BorderLayout.CENTER);
    }

    private void load() {
        try {
            listModel.clear();
            PokemonDAO dao = new PokemonDAO();
            List<PokemonItem> list = dao.listAll();
            for (PokemonItem p : list) {
                listModel.addElement(p.getId() + " | " + p.getName() + " — R$ " + p.getPrice());
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
    }

    private void addItem() {
        try {
            JTextField tfName = new JTextField();
            JTextField tfType = new JTextField();
            JTextField tfPrice = new JTextField();
            JTextField tfImg = new JTextField();
            JTextArea tfDesc = new JTextArea(4,20);
            Object[] form = {"Nome:", tfName, "Tipo:", tfType, "Preço:", tfPrice, "Image path (rel):", tfImg, "Descrição:", tfDesc};
            int res = JOptionPane.showConfirmDialog(this, form, "Adicionar Item", JOptionPane.OK_CANCEL_OPTION);
            if (res==JOptionPane.OK_OPTION) {
                PokemonItem p = new PokemonItem();
                p.setName(tfName.getText());
                p.setPtype(tfType.getText());
                p.setPrice(Double.parseDouble(tfPrice.getText()));
                p.setImagePath(tfImg.getText());
                p.setDescription(tfDesc.getText());
                PokemonDAO dao = new PokemonDAO();
                dao.addMenuItem(p);
                load();
            }
        } catch (Exception e) { e.printStackTrace(); JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage()); }
    }

    private void editItem() {
        try {
            String sel = lst.getSelectedValue();
            if (sel==null) return;
            int id = Integer.parseInt(sel.split("\\|")[0].trim());
            PokemonDAO dao = new PokemonDAO();
            PokemonItem p = dao.findById(id);
            if (p==null) return;
            JTextField tfName = new JTextField(p.getName());
            JTextField tfType = new JTextField(p.getPtype());
            JTextField tfPrice = new JTextField(String.valueOf(p.getPrice()));
            JTextField tfImg = new JTextField(p.getImagePath());
            JTextArea tfDesc = new JTextArea(p.getDescription(),4,20);
            Object[] form = {"Nome:", tfName, "Tipo:", tfType, "Preço:", tfPrice, "Image path (rel):", tfImg, "Descrição:", tfDesc};
            int res = JOptionPane.showConfirmDialog(this, form, "Editar Item", JOptionPane.OK_CANCEL_OPTION);
            if (res==JOptionPane.OK_OPTION) {
                p.setName(tfName.getText());
                p.setPtype(tfType.getText());
                p.setPrice(Double.parseDouble(tfPrice.getText()));
                p.setImagePath(tfImg.getText());
                p.setDescription(tfDesc.getText());
                dao.updateMenuItem(p);
                load();
            }
        } catch (Exception e) { e.printStackTrace(); JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage()); }
    }

    private void deleteItem() {
        try {
            String sel = lst.getSelectedValue();
            if (sel==null) return;
            int id = Integer.parseInt(sel.split("\\|")[0].trim());
            int c = JOptionPane.showConfirmDialog(this, "Remover item ID " + id + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (c==JOptionPane.YES_OPTION) {
                PokemonDAO dao = new PokemonDAO();
                dao.deleteMenuItem(id);
                load();
            }
        } catch (Exception e) { e.printStackTrace(); JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage()); }
    }

    private DefaultListModel<String> listModel;
    private JList<String> lst;
}
