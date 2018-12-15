package com.codetouch.filtrodeprodutos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.codetouch.filtrodeprodutos.adapters.FilterAdapter;
import com.codetouch.filtrodeprodutos.adapters.ProductAdapter;
import com.codetouch.filtrodeprodutos.models.Item;
import com.codetouch.filtrodeprodutos.models.Product;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Product> productList;
    private List<Product> cloneProductList;
    private List<Item> marketList;
    private List<Item> techList;

    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String content = Utilities.readProducts(this);
        Log.i("Content", content);

        Gson gson = new Gson();
        Product[] products = gson.fromJson(content, Product[].class);
        productList = new ArrayList<>(Arrays.asList(products));
        cloneProductList = new ArrayList<>(productList);

        RecyclerView rcvProducts = findViewById(R.id.rcvProducts);
        rcvProducts.setLayoutManager(new LinearLayoutManager(this));
        productAdapter = new ProductAdapter(productList);
        rcvProducts.setAdapter(productAdapter);

        marketList = new ArrayList<>();
        techList = new ArrayList<>();

        for (Product product : products) {
            for (String m : product.getTargetMarket()) {
                Item item = new Item(m);
                if (!marketList.contains(item))
                    marketList.add(item);
            }

            for (String tech : product.getStack()) {
                Item item = new Item(tech);
                if (!techList.contains(item))
                    techList.add(item);
            }
        }

        FilterAdapter marketAdapter = new FilterAdapter(marketList, new FilterAdapter.FilterInteraction() {
            @Override
            public void filter(List<Item> filterList) {
                marketList = filterList;
                filterProducts();
            }
        });

        RecyclerView rcvMarket = findViewById(R.id.rcvMarket);
        rcvMarket.setAdapter(marketAdapter);

        FilterAdapter techAdapter = new FilterAdapter(techList, new FilterAdapter.FilterInteraction() {
            @Override
            public void filter(List<Item> filterList) {
                techList = filterList;
                filterProducts();
            }
        });

        RecyclerView rcvTech = findViewById(R.id.rcvTech);
        rcvTech.setAdapter(techAdapter);
    }

    private void filterProducts() {
        productList.clear();
        if (marketList.size() == 0 && techList.size() == 0) {
            productList.addAll(cloneProductList);
        } else {
            for (Product product : cloneProductList) {
                boolean found = false;
                for (Item iMarket : marketList) {
                    for (String m : product.getTargetMarket()) {
                        if (iMarket.getTitle().equals(m)) {
                            productList.add(product);
                            found = true;
                            break;
                        }
                    }
                    if (found)
                        break;
                }

                if (!found)
                    for (Item iTech : techList) {
                        for (String t : product.getStack()) {
                            if (iTech.getTitle().equals(t)) {
                                productList.add(product);
                                found = true;
                                break;
                            }
                        }
                        if (found)
                            break;
                    }
            }
        }
        productAdapter.notifyDataSetChanged();
    }
}
