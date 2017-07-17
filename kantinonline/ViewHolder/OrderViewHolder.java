package com.example.mmift.kantinonline.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mmift.kantinonline.R;

/**
 * Created by mmift on 5/10/2017.
 */

public class OrderViewHolder extends RecyclerView.ViewHolder {

    public TextView mTvNamaMakanan, mTvJumlahMakanan, mTvHargaMakanan;
    public Button mBtnHapusItem;

    public OrderViewHolder(View itemView) {
        super(itemView);

        mTvNamaMakanan = (TextView) itemView.findViewById(R.id.order_nama_menu);
        mTvJumlahMakanan = (TextView) itemView.findViewById(R.id.order_jumlah_menu);
        mTvHargaMakanan = (TextView) itemView.findViewById(R.id.order_total_harga_menu);
        mBtnHapusItem = (Button) itemView.findViewById(R.id.btn_hapus_order);
    }
}
