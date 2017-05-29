package com.ju5tEgoist;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
/**
 * Created by yulia on 25.05.17.
 */

@XmlRootElement(name = "products")
@XmlType(propOrder = {"name","brand","price", "initialPrice", "description", "articleID", "shippingCosts",  "url", "color"})
public class Product {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(String initialPrice) {
        this.initialPrice = initialPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArticleID() {
        return articleID;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }

    public String getShippingCosts() {
        return shippingCosts;
    }

    public void setShippingCosts(String shippingCosts) {
        this.shippingCosts = shippingCosts;
    }

    public Product() {
    }

    public Product(String name, String brand, String price, String initialPrice, String description, String articleID, String shippingCosts, String url, String color) {
        this.name = name;
        this.brand = brand;
        this.color = color;
        //, List<String> color
        this.price = price;
        this.initialPrice = initialPrice;
        this.description = description;
        this.articleID = articleID;
        this.shippingCosts = shippingCosts;
        this.url = url;

    }

    private String name;
    private String brand;
    private String color;
    private String price;
    private String initialPrice;
    private String description;
    private String articleID;
    private String shippingCosts;
    private String url;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    @Override
    public String toString(){
        return "Product{ " + "url = " + url + "}";
    }
}
