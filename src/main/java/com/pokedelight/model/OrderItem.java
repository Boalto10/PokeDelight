package com.pokedelight.model;
public class OrderItem {
    private int id;
    private int orderId;
    private PokemonItem pokemon;
    private int quantity;
    private double price;
    public OrderItem(){}
    // getters and setters
    public int getId(){return id;} public void setId(int id){this.id=id;}
    public int getOrderId(){return orderId;} public void setOrderId(int o){this.orderId=o;}
    public PokemonItem getPokemon(){return pokemon;} public void setPokemon(PokemonItem p){this.pokemon=p;}
    public int getQuantity(){return quantity;} public void setQuantity(int q){this.quantity=q;}
    public double getPrice(){return price;} public void setPrice(double p){this.price=p;}
}
