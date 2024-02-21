package com.example.pcbuilder.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.pcbuilder.R;
import com.example.pcbuilder.activities.DetailedActivity;
import com.example.pcbuilder.model.NewProductModel;
import com.google.protobuf.StringValue;

import java.util.List;

public class NewProductAdapter extends RecyclerView.Adapter<NewProductAdapter.ViewHolder> {

    private Context context;
    private List<NewProductModel> list;

    public NewProductAdapter(Context context, List<NewProductModel> list){
        this.context = context;
        this.list =list;
    }

    @NonNull
    @Override
    public NewProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return new NewProductAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.newpopular_list,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull NewProductAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.catImg);
        holder.catName.setText(list.get(position).getName());
        holder.catPrice.setText(String.valueOf(list.get(position).getPrice()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailedActivity.class);
                intent.putExtra("detailed",list.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView catImg;
        TextView catName;
        TextView catPrice;
        public ViewHolder(@NonNull View itemview){
            super(itemview);
            catImg=itemview.findViewById(R.id.all_img);
            catName=itemview.findViewById(R.id.all_product_name);
            catPrice=itemview.findViewById(R.id.all_price);
        }
    }
}
