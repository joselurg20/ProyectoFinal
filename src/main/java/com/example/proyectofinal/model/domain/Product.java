package com.example.proyectofinal.model.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Product {
    private int id;
    private String nombre;
    private String pais_origen;
    private LocalDate fecha_caducidad;
    private Double precio;
    private List<Purchase> purchases = null;

    public Product(int id, String nombre, String pais_origen, LocalDate fecha_caducidad, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.pais_origen = pais_origen;
        this.fecha_caducidad = fecha_caducidad;
        this.precio = precio;
    }
    public Product() {
        this(0,"", "", null,0.0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais_origen() {
        return pais_origen;
    }

    public void setPais_origen(String pais_origen) {
        this.pais_origen = pais_origen;
    }

    public LocalDate getFecha_caducidad() {
        return fecha_caducidad;
    }

    public void setFecha_caducidad(LocalDate fecha_caducidad) {
        this.fecha_caducidad = fecha_caducidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public void addPurchases(Purchase purchase){
        if(this.purchases == null){
            this.purchases = new ArrayList<>();
        }
        this.purchases.add(purchase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fecha_caducidad, id, nombre, pais_origen, precio, purchases);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        return Objects.equals(fecha_caducidad, other.fecha_caducidad) && id == other.id
                && Objects.equals(nombre, other.nombre) && Objects.equals(pais_origen, other.pais_origen)
                && Objects.equals(precio, other.precio) && Objects.equals(purchases, other.purchases);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", pais_origen='" + pais_origen + '\'' +
                ", fecha_caducidad=" + fecha_caducidad +
                ", precio=" + precio +
                ", purchases=" + purchases +
                '}';
    }
}
