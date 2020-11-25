package com.example.babel;

public class ProductList {

    private int id;
    private String nameProduct;
    private String description_prod;
    private double costo_prod;
    private double precio_prod;
    private double descuento;
    private String material_prod;
    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getDescription_prod() {
        return description_prod;
    }

    public void setDescription_prod(String description_prod) {
        this.description_prod = description_prod;
    }

    public double getCosto_prod() {
        return costo_prod;
    }

    public void setCosto_prod(double costo_prod) {
        this.costo_prod = costo_prod;
    }

    public double getPrecio_prod() {
        return precio_prod;
    }

    public void setPrecio_prod(double precio_prod) {
        this.precio_prod = precio_prod;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public String getMaterial_prod() {
        return material_prod;
    }

    public void setMaterial_prod(String material_prod) {
        this.material_prod = material_prod;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
