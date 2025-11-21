package com.pokedelight.dao;

import com.pokedelight.model.PokemonItem;
import com.pokedelight.model.Order;
import com.pokedelight.model.OrderItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PokemonDAO {

    public List<PokemonItem> listAll() throws Exception {
        List<PokemonItem> list = new ArrayList<>();
        String sql = "SELECT id, name, ptype, price, description, image_path FROM pokemon_menu ORDER BY name";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PokemonItem p = new PokemonItem(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("ptype"),
                    rs.getDouble("price"),
                    rs.getString("description"),
                    rs.getString("image_path")
                );
                list.add(p);
            }
        }
        return list;
    }

    public PokemonItem findById(int id) throws Exception {
        String sql = "SELECT id, name, ptype, price, description, image_path FROM pokemon_menu WHERE id=?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new PokemonItem(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("ptype"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getString("image_path")
                    );
                }
            }
        }
        return null;
    }

    public void addMenuItem(PokemonItem p) throws Exception {
        String sql = "INSERT INTO pokemon_menu (name, ptype, price, description, image_path) VALUES (?,?,?,?,?)";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getName());
            ps.setString(2, p.getPtype());
            ps.setDouble(3, p.getPrice());
            ps.setString(4, p.getDescription());
            ps.setString(5, p.getImagePath());
            ps.executeUpdate();
        }
    }

    public void updateMenuItem(PokemonItem p) throws Exception {
        String sql = "UPDATE pokemon_menu SET name=?, ptype=?, price=?, description=?, image_path=? WHERE id=?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getName());
            ps.setString(2, p.getPtype());
            ps.setDouble(3, p.getPrice());
            ps.setString(4, p.getDescription());
            ps.setString(5, p.getImagePath());
            ps.setInt(6, p.getId());
            ps.executeUpdate();
        }
    }

    public void deleteMenuItem(int id) throws Exception {
        String sql = "DELETE FROM pokemon_menu WHERE id=?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public int createOrder(Order order) throws Exception {
        String insertOrder = "INSERT INTO orders (customer_name, address, total) VALUES (?,?,?)";
        String insertItem = "INSERT INTO order_items (order_id, pokemon_id, quantity, price) VALUES (?,?,?,?)";
        try (Connection c = DBUtil.getConnection()) {
            c.setAutoCommit(false);
            try (PreparedStatement ps = c.prepareStatement(insertOrder, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, order.getCustomerName());
                ps.setString(2, order.getAddress());
                ps.setDouble(3, order.getTotal());
                ps.executeUpdate();
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        int orderId = keys.getInt(1);
                        for (OrderItem it : order.getItems()) {
                            try (PreparedStatement psi = c.prepareStatement(insertItem)) {
                                psi.setInt(1, orderId);
                                psi.setInt(2, it.getPokemon().getId());
                                psi.setInt(3, it.getQuantity());
                                psi.setDouble(4, it.getPrice());
                                psi.executeUpdate();
                            }
                        }
                        c.commit();
                        return orderId;
                    }
                }
            } catch (Exception e) {
                c.rollback();
                throw e;
            } finally {
                c.setAutoCommit(true);
            }
        }
        return -1;
    }
}
