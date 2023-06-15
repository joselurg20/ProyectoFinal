package com.example.proyectofinal.model.domain;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
public class Purchase {
    private User u;
    private Product p;
    private int cantidad;
    private LocalDate fecha_compra;

    public Purchase(User u, Product p, int cantidad, LocalDate fecha_compra) {
        this.u = u;
        this.p = p;
        this.cantidad = cantidad;
        this.fecha_compra = fecha_compra;
    }
    public Purchase(){
        this(null, null, 0, null);
    }
    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    public Product getP() {
        return p;
    }

    public void setP(Product p) {
        this.p = p;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(LocalDate fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cantidad, fecha_compra,p, u);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Purchase other = (Purchase) obj;
        return cantidad == other.cantidad && Objects.equals(fecha_compra, other.fecha_compra)
                && Objects.equals(p, other.p) && Objects.equals(u, other.u);
    }


    @Override
    public String toString() {
        return "Purchase{" +
                "u=" + u +
                ", p=" + p +
                ", cantidad=" + cantidad +
                ", fecha_compra=" + fecha_compra +
                '}';
    }
}
