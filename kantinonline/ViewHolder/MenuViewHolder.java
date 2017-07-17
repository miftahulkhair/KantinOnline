package com.example.mmift.kantinonline.ViewHolder;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mmift.kantinonline.R;

/**
 * Created by mmift on 4/14/2017.
 */

public class MenuViewHolder extends RecyclerView.ViewHolder {

    public ImageView mIvFotoMakanan;
    public TextView mTvNamaMakanan, mTvHargaMakanan;

    public MenuViewHolder(View itemView) {
        super(itemView);

        mIvFotoMakanan = (ImageView) itemView.findViewById(R.id.image);
        mTvNamaMakanan = (TextView) itemView.findViewById(R.id.item_menu);
        mTvHargaMakanan = (TextView) itemView.findViewById(R.id.item_harga);
    }
}
