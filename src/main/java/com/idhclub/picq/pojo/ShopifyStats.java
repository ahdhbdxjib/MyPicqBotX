package com.idhclub.picq.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopifyStats {

    public String region;

    public String user;

    public String orders;

    public String sold;

    public List<String> products;

    public String getpProducts() {
        StringBuilder detail = new StringBuilder();
        for (String s : products) {
            detail.append(s + "\n\n");
        }
        return detail.toString();
    }

    @Override
    public String toString() {

        return "region:            " + region + "\n" +
                "user:              " + user + "\n" +
                "orders:            " + orders + "\n" +
                "solds:             " + sold + "\n"
                ;
    }

}
