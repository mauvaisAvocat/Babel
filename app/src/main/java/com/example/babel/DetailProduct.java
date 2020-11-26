package com.example.babel;

public class DetailProduct {
    private double id;
    private String nameProduct;
    private String description_prod;
    private double costo_prod;
    private double precio_prod;
    private double descuento;
    private String material_prod;
    private String image;
    private double existence_s;
    private double existence_m;
    private double existence_l;
    private String status;
    private String category;
    private String provider;

    public double getId() {
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

    public double getExistence_s() {
        return existence_s;
    }

    public void setExistence_s(int existence_s) {
        this.existence_s = existence_s;
    }

    public double getExistence_m() {
        return existence_m;
    }

    public void setExistence_m(int existence_m) {
        this.existence_m = existence_m;
    }

    public double getExistence_l() {
        return existence_l;
    }

    public void setExistence_l(int existence_l) {
        this.existence_l = existence_l;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

}
