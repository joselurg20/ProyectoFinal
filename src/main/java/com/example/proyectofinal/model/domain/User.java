package com.example.proyectofinal.model.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private String dni;
    private String nombre;
    private String apellido;
    private List<Purchase> purchases = null;

    public User(String dni, String nombre, String Apellido) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = Apellido;
    }
    public User(){
        this("","","");
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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
        final int prime = 31;
        int result = 1;
        result = prime * result + (( dni == null) ? 0 : dni.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        return Objects.equals(dni, other.dni) && Objects.equals(nombre, other.nombre)
                && Objects.equals(apellido, other.apellido) && Objects.equals(purchases, other.purchases);
    }

    @Override
    public String toString() {
        return "Users{" +
                "DNI='" + dni + '\'' +
                ", first_name='" + nombre + '\'' +
                ", last_name='" + apellido + '\'' +
                ", purchases=" + purchases +
                '}';
    }
}
