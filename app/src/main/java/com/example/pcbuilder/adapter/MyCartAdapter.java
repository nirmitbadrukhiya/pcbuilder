package com.example.pcbuilder.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.pcbuilder.R;
import com.example.pcbuilder.activities.DetailedActivity;
import com.example.pcbuilder.model.MyCartModel;
import com.example.pcbuilder.model.NewProductModel;

import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {

    private Context context;
    private List<MyCartModel> list;
    int totalAmount=0;

    public MyCartAdapter(Context context, List<MyCartModel> list){
        this.context = context;
        this.list =list;
    }

    @NonNull
    @Override
    public MyCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return new MyCartAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_item,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyCartAdapter.ViewHolder holder, int position) {
        holder.Name.setText(list.get(position).getProductName());
        holder.totalPrice.setText(String.valueOf(list.get(position).getTotalPrice())+"$");
        holder.Price.setText(String.valueOf(list.get(position).getProductPrice()+"$"));
        holder.Time.setText(list.get(position).getCurrentTime());
        holder.Date.setText(list.get(position).getCurrentDate());
        holder.totalQuantity.setText(String.valueOf(list.get(position).getTotalQuantity()));

        //TotalAmount
        totalAmount = totalAmount+list.get(position).getTotalPrice();
        Intent intent = new Intent("MyTotalAmount");
        intent.putExtra("totalAmount",totalAmount);

        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Name,Price,Time,Date,totalQuantity,totalPrice;
        public ViewHolder(@NonNull View itemview){
            super(itemview);
            Name=itemview.findViewById(R.id.product_name);
            Price=itemview.findViewById(R.id.product_price);
            Time=itemview.findViewById(R.id.current_time);
            Date=itemview.findViewById(R.id.current_date);
            totalQuantity=itemview.findViewById(R.id.total_quantity);
            totalPrice=itemview.findViewById(R.id.total_price);
        }
    }
}
