package service.common;

import models.Product;

import java.util.Comparator;

public class ProductComparator implements Comparator<Product> {


    @Override
    public int compare(Product o1, Product o2) {
        return o1.getCreatedAt().compareTo(o2.getCreatedAt());
    }

    @Override
    public Comparator<Product> reversed() {
        return Comparator.super.reversed();
    }
}
