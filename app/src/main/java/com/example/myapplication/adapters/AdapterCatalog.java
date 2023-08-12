package com.example.myapplication.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.models.ProductsItem;

import java.util.ArrayList;

public class AdapterCatalog extends RecyclerView.Adapter<AdapterCatalog.ViewHolderCatalog> {

    ArrayList<ProductsItem> catalog;
    Context context;

    public  AdapterCatalog(ArrayList<ProductsItem> catalog, Context context){
        this.catalog = catalog;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterCatalog.ViewHolderCatalog onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolderCatalog(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AdapterCatalog.ViewHolderCatalog holder, int position) {
        ProductsItem item = catalog.get(position);

        //holder.imgViewProduct.setImageResource(item.getSourceImg());
        Glide.with(context)
                .load(item.getThumbnail())
                .into(holder.imgViewProduct);
        holder.txtViewName.setText(item.getTitle());
        holder.txtViewDescription.setText(item.getDescription());
        holder.txtViewCost.setText(Double.toString(item.getPrice()));
        holder.txtViewBrand.setText(item.getBrand());

    }

    @Override
    public int getItemCount() {
        return catalog.size();
    }

    public class ViewHolderCatalog extends RecyclerView.ViewHolder {
        private ImageView imgViewProduct;
        private TextView txtViewName;
        private TextView txtViewDescription;
        private TextView txtViewCost;
        private TextView txtViewBrand;

        public ViewHolderCatalog(View itemView) {
            super(itemView);

            imgViewProduct = itemView.findViewById(R.id.imgViewProduct);
            txtViewName = itemView.findViewById(R.id.txtViewName);
            txtViewDescription = itemView.findViewById(R.id.txtViewDescription);
            txtViewCost = itemView.findViewById(R.id.txtViewCost);
            txtViewBrand = itemView.findViewById(R.id.txtViewBrand);
        }
    }
}
