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

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdapterCatalog extends RecyclerView.Adapter<AdapterCatalog.ViewHolderCatalog> {

    ArrayList<ProductsItem> catalog;
    Context context;

    //public  AdapterCatalog(ArrayList<ProductsItem> catalog, Context context){
    public  AdapterCatalog(Context context){
        //this.catalog = catalog;
        this.context = context;
        catalog = new ArrayList<>();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addItem(ArrayList<ProductsItem> mCatalog) {
        catalog.addAll(mCatalog);
        notifyDataSetChanged();
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

        if(item.getStock() > 0){
            //holder.imgViewProduct.setImageResource(item.getSourceImg());
            Glide.with(context)
                    .load(item.getThumbnail())
                    .into(holder.imgViewProduct);
            holder.txtViewName.setText(item.getTitle());
            holder.txtViewDescription.setText(item.getDescription());
            if (item.getDiscountPercentage() > 0){
                DecimalFormat df = new DecimalFormat("#.00");
                String valorFinalFormateado = df.format(newPrice(item.getPrice(), item.getDiscountPercentage()));
                holder.txtViewCost.setText("Before $" + Double.toString(item.getPrice()) + " now $" + valorFinalFormateado);
            } else {
                holder.txtViewCost.setText("$" + Double.toString(item.getPrice()));
            }
            holder.txtViewBrand.setText(item.getBrand());
        }

    }

    @Override
    public int getItemCount() {
        return catalog.size();
    }

    private float newPrice(float coast, float percentage){
        float realPercentage = percentage / 100;
        return coast - realPercentage;
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
