package com.EmirMuhamadZaidJmartAK.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ProductViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BigDecimal discountBD = new BigDecimal(String.valueOf(ProductFragment.productClicked.discount));
        BigDecimal roundedDiscount = discountBD.setScale(1, RoundingMode.FLOOR);

        BigDecimal priceBD = new BigDecimal(String.valueOf(ProductFragment.productClicked.price));
        BigDecimal priceRounded = priceBD.setScale(3, RoundingMode.FLOOR);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);

        TextView name = (TextView) findViewById(R.id.productName);
        TextView weight = (TextView) findViewById(R.id.weightProduct);
        TextView conditionProduct = (TextView) findViewById(R.id.conditionProduct);
        TextView price = (TextView) findViewById(R.id.priceProduct);
        TextView discount = (TextView) findViewById(R.id.discountProduct);
        TextView category = (TextView) findViewById(R.id.categoryProduct);
        TextView shipmentPlans = (TextView) findViewById(R.id.shipmentPlans);


        Button buyButton = findViewById(R.id.button_buy);

        name.setText(ProductFragment.productClicked.name);
        weight.setText(String.valueOf(ProductFragment.productClicked.weight));
        conditionProduct.setText(convertConditionUsed(ProductFragment.productClicked.conditionUsed));
        price.setText(String.valueOf(priceRounded));
        discount.setText(String.valueOf(roundedDiscount));
        category.setText(String.valueOf(ProductFragment.productClicked.category));
        shipmentPlans.setText(convertShipmentPlans(ProductFragment.productClicked.shipmentPlans));

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductViewActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });
    }

    private String convertShipmentPlans(byte shipment){
        switch (shipment) {
            case 0:
                return "INSTANT";
            case 1:
                return "SAME DAY";
            case 2:
                return "NEXT DAY";
            case 3:
                return "REGULER";
            default:
                return "CARGO";
        }
    }



    private String convertConditionUsed(boolean conditionUsed){
        if (conditionUsed) {
            return "Used";
        }else{
            return "New";
        }
    }
}