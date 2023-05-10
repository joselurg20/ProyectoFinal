package com.example.proyectofinal.model.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Users {
    private String DNI;
    private String first_name;
    private String last_name;
    private List<Purchase> purchases = null;

    public Users(String DNI, String first_name, String last_name) {
        this.DNI = DNI;
        this.first_name = first_name;
        this.last_name = last_name;
    }
    public Users(){
        this("","","");
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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
        result = prime * result + (( DNI == null) ? 0 : DNI.hashCode());
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
        Users other = (Users) obj;
        return Objects.equals(DNI, other.DNI) && Objects.equals(first_name, other.first_name)
                && Objects.equals(last_name, other.last_name) && Objects.equals(purchases, other.purchases);
    }

    @Override
    public String toString() {
        return "Users{" +
                "DNI='" + DNI + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", purchases=" + purchases +
                '}';
    }
}
