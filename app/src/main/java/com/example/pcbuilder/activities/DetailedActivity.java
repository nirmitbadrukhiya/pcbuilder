package com.example.pcbuilder.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.pcbuilder.R;
import com.example.pcbuilder.model.AllProductModel;
import com.example.pcbuilder.model.NewProductModel;
import com.example.pcbuilder.model.PopularModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailedActivity extends AppCompatActivity {

    int totalQuantity =1;
    int totalPrice =0;
    Toolbar toolbar;
    ImageView detailedImg;
    TextView rating, name, description, price,quantity;
    Button addToCart, buyNow;
    ImageView addItems, removeItems;
    NewProductModel newProductModel = null;
    AllProductModel allProductModel = null;
    PopularModel popularModel = null;
    private FirebaseFirestore firestore;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        toolbar = findViewById(R.id.detailed_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Object obj = getIntent().getSerializableExtra("detailed");
        if (obj instanceof NewProductModel) {
            newProductModel = (NewProductModel) obj;
        } else if (obj instanceof PopularModel) {
            popularModel = (PopularModel) obj;
        } else if (obj instanceof AllProductModel) {
            allProductModel = (AllProductModel) obj;
        }

            detailedImg = findViewById(R.id.detailed_img);
            quantity = findViewById(R.id.quantity);
            rating = findViewById(R.id.pd_rating);
            name = findViewById(R.id.detailed_name);
            description = findViewById(R.id.detailed_desc);
            price = findViewById(R.id.detailed_price);
            detailedImg = findViewById(R.id.detailed_img);

            addToCart = findViewById(R.id.add_to_cart);
            buyNow = findViewById(R.id.buy_now);

            addItems = findViewById(R.id.add_item);
            removeItems = findViewById(R.id.remove_item);

            //newPopularModel
            if (newProductModel != null) {
                Glide.with(getApplicationContext()).load(newProductModel.getImg_url()).into(detailedImg);
                name.setText(newProductModel.getName());
                rating.setText(newProductModel.getRating());
                description.setText(newProductModel.getDescription());
                price.setText(String.valueOf(newProductModel.getPrice()));

                totalPrice = newProductModel.getPrice()*totalQuantity;
            }

            //popularModel
            if (popularModel != null) {
                Glide.with(getApplicationContext()).load(popularModel.getImg_url()).into(detailedImg);
                name.setText(popularModel.getName());
                rating.setText(popularModel.getRating());
                description.setText(popularModel.getDescription());
                price.setText(String.valueOf(popularModel.getPrice()));

                totalPrice = popularModel.getPrice()*totalQuantity;
            }

            //all productModel
            if (allProductModel != null) {
                Glide.with(getApplicationContext()).load(allProductModel.getImg_url()).into(detailedImg);
                name.setText(allProductModel.getName());
                rating.setText(allProductModel.getRating());
                description.setText(allProductModel.getDescription());
                price.setText(String.valueOf(allProductModel.getPrice()));

                totalPrice = allProductModel.getPrice()*totalQuantity;
            }

            buyNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(DetailedActivity.this, AddressActivity.class));

                }
            });

            addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addtocart();
                }
            });

            addItems.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (totalQuantity <10){
                        totalQuantity++;
                        quantity.setText(String.valueOf(totalQuantity));

                        if (newProductModel != null){
                            totalPrice = newProductModel.getPrice()*totalQuantity;
                        }
                        if (popularModel != null){
                            totalPrice = popularModel.getPrice()*totalQuantity;
                        }
                        if (allProductModel != null){
                            totalPrice = allProductModel.getPrice()*totalQuantity;
                        }
                    }
                }
            });

            removeItems.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (totalQuantity >1){
                        totalQuantity--;
                        quantity.setText(String.valueOf(totalQuantity));
                    }
                }
            });
    }

    private void addtocart() {

        String saveCurrentTime,saveCurrentDate;

        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String,Object> cartMap = new HashMap<>();

        cartMap.put("productName",name.getText().toString());
        cartMap.put("productPrice",price.getText().toString());
        cartMap.put("currentTime",saveCurrentTime);
        cartMap.put("currentDate",saveCurrentDate);
        cartMap.put("totalQuantity",quantity.getText().toString());
        cartMap.put("totalPrice",totalPrice);

        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("User").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(DetailedActivity.this,"Added To A Cart",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}
