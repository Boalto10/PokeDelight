package com.pokedelight.ui;

import com.pokedelight.model.PokemonItem;
import com.pokedelight.model.OrderItem;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static List<OrderItem> items = new ArrayList<>();
    public static void add(PokemonItem p, int qty) {
        for (OrderItem it : items) {
            if (it.getPokemon().getId() == p.getId()) {
                it.setQuantity(it.getQuantity() + qty);
                return;
            }
        }
        OrderItem ni = new OrderItem();
        ni.setPokemon(p);
        ni.setQuantity(qty);
        ni.setPrice(p.getPrice());
        items.add(ni);
    }
    public static List<OrderItem> getItems(){ return items; }
    public static void clear(){ items.clear(); }
    public static double total() {
        double s=0; for (OrderItem it: items) s += it.getPrice()*it.getQuantity(); return s;
    }
}
