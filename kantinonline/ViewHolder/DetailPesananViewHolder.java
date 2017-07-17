package com.example.mmift.kantinonline.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mmift.kantinonline.R;

/**
 * Created by mmift on 6/21/2017.
 */

public class DetailPesananViewHolder extends RecyclerView.ViewHolder{
    public TextView mTvNamaMakanan, mTvJumlahMakanan, mTvHargaMakanan;

    public DetailPesananViewHolder(View itemView) {
        super(itemView);

        mTvNamaMakanan = (TextView) itemView.findViewById(R.id.detailpesanan_nama_menu);
        mTvJumlahMakanan = (TextView) itemView.findViewById(R.id.detailpesanan_jumlah_menu);
        mTvHargaMakanan = (TextView) itemView.findViewById(R.id.detailpesanan_total_harga_menu);

    }
}
