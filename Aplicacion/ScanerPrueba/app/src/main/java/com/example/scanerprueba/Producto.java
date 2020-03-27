package com.example.scanerprueba;

public class Producto {

    private String codigo, nombre, marca, caracteristicas, urlimg;
    private double precio;
    private static int cantidad = 0;

    public Producto(double precio, String codigo, String nombre, String marca, String caracteristicas, String urlimg) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.marca = marca;
        this.caracteristicas = caracteristicas;
        this.urlimg = urlimg;
        this.precio = precio;
    }
}
