package com.pokedelight.ui;

import com.pokedelight.dao.PokemonDAO;
import com.pokedelight.model.PokemonItem;
import com.pokedelight.model.OrderItem;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MenuWindow extends javax.swing.JFrame {

    public MenuWindow() {
        initComponents();
        loadMenuFromDB();
    }

    private void initComponents() {
        setTitle("PokeDelight - Cardápio");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(900,600);
        setLocationRelativeTo(null);

        pnlTop = new JPanel(new BorderLayout());
        JLabel title = new JLabel("<html><h1 style='font-family:sans-serif'>PokeDelight <span style='font-size:16px;color:gray'>— Cardápio Pokémon</span></h1></html>");
        title.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        pnlTop.add(title, BorderLayout.WEST);

        JButton viewCart = new JButton("Ver Carrinho");
        viewCart.addActionListener(e -> new CartWindow().setVisible(true));
        pnlTop.add(viewCart, BorderLayout.EAST);

        JScrollPane scroll = new JScrollPane();
        pnlCards = new JPanel();
        pnlCards.setLayout(new WrapLayout(FlowLayout.LEFT, 20, 20));
        pnlCards.setBackground(Color.WHITE);
        scroll.setViewportView(pnlCards);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(pnlTop, BorderLayout.NORTH);
        getContentPane().add(scroll, BorderLayout.CENTER);
    }

    private void loadMenuFromDB() {
        try {
            PokemonDAO dao = new PokemonDAO();
            List<PokemonItem> items = dao.listAll();
            pnlCards.removeAll();
            for (PokemonItem p : items) {
                JPanel card = createCard(p);
                pnlCards.add(card);
            }
            pnlCards.revalidate();
            pnlCards.repaint();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar cardápio: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel createCard(PokemonItem p) {
        JPanel card = new JPanel(new BorderLayout());
        card.setPreferredSize(new Dimension(260, 260));
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
        card.setBackground(Color.WHITE);

        // Image placeholder
        JLabel img = new JLabel();
        img.setHorizontalAlignment(SwingConstants.CENTER);
        img.setPreferredSize(new Dimension(260,120));
        if (p.getImagePath() != null && !p.getImagePath().isEmpty()) {
            try {
                ImageIcon icon = new ImageIcon(p.getImagePath());
                Image scaled = icon.getImage().getScaledInstance(240,110,Image.SCALE_SMOOTH);
                img.setIcon(new ImageIcon(scaled));
            } catch (Exception ex) {
                img.setText("[imagem]");
            }
        } else {
            img.setText("<html><div style='text-align:center;color:gray'>[sem imagem]</div></html>");
        }
        card.add(img, BorderLayout.NORTH);

        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.setBackground(Color.WHITE);
        JLabel name = new JLabel("<html><b>" + p.getName() + "</b></html>");
        JLabel type = new JLabel(p.getPtype() == null ? "" : p.getPtype());
        JLabel price = new JLabel(String.format("R$ %.2f", p.getPrice()));
        JLabel desc = new JLabel("<html><small style='color:gray'>" + (p.getDescription()==null?"":p.getDescription()) + "</small></html>");
        name.setAlignmentX(Component.LEFT_ALIGNMENT);
        type.setAlignmentX(Component.LEFT_ALIGNMENT);
        price.setAlignmentX(Component.LEFT_ALIGNMENT);
        desc.setAlignmentX(Component.LEFT_ALIGNMENT);
        info.add(name);
        info.add(type);
        info.add(price);
        info.add(Box.createVerticalStrut(5));
        info.add(desc);

        JButton btnAdd = new JButton("Adicionar");
        btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAdd.addActionListener(evt -> {
            String q = JOptionPane.showInputDialog(this, "Quantidade:", "1");
            try {
                int qty = Integer.parseInt(q);
                Cart.add(p, qty);
                JOptionPane.showMessageDialog(this, p.getName() + " x" + qty + " adicionado ao carrinho.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Quantidade inválida.");
            }
        });

        JPanel south = new JPanel();
        south.setBackground(Color.WHITE);
        south.setLayout(new BorderLayout());
        south.add(info, BorderLayout.CENTER);
        south.add(btnAdd, BorderLayout.SOUTH);

        card.add(south, BorderLayout.CENTER);
        return card;
    }

    // components
    private JPanel pnlTop;
    private JPanel pnlCards;

    // WrapLayout implementation (same as before)
    static class WrapLayout extends FlowLayout {
        public WrapLayout(int align, int hgap, int vgap) {
            super(align, hgap, vgap);
        }
        @Override
        public Dimension preferredLayoutSize(Container target) {
            return layoutSize(target, true);
        }
        private Dimension layoutSize(Container target, boolean preferred) {
            synchronized (target.getTreeLock()) {
                int targetWidth = target.getWidth();
                if (targetWidth == 0) targetWidth = 900;
                int hgap = getHgap(), vgap = getVgap();
                int maxWidth = targetWidth - (getInsets(target).left + getInsets(target).right + hgap*2);
                int x = 0, y = getInsets(target).top;
                int rowHeight = 0;
                for (Component c : target.getComponents()) {
                    if (!c.isVisible()) continue;
                    Dimension d = preferred ? c.getPreferredSize() : c.getMinimumSize();
                    if (x == 0 || x + d.width <= maxWidth) {
                        x += d.width + hgap;
                        rowHeight = Math.max(rowHeight, d.height);
                    } else {
                        x = d.width + hgap;
                        y += rowHeight + vgap;
                        rowHeight = d.height;
                    }
                }
                y += rowHeight + getInsets(target).bottom;
                return new Dimension(targetWidth, y);
            }
        }
        private Insets getInsets(Container t) {
            return (t instanceof JComponent) ? ((JComponent)t).getInsets() : t.getInsets();
        }
    }
}
