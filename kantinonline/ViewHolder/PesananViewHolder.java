package com.example.mmift.kantinonline.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mmift.kantinonline.R;

/**
 * Created by mmift on 5/1/2017.
 */

public class PesananViewHolder extends RecyclerView.ViewHolder {

    public TextView mTvNamaPelanggan, mTvAlamatPelanggan, mTvTglPesanan, mTvWaktuPesanan, mTvTotalPesanan;
//    public Button mBtnTerkirim;

    public PesananViewHolder(View itemView) {
        super(itemView);

        mTvNamaPelanggan = (TextView) itemView.findViewById(R.id.nama_pelanggan);
        mTvAlamatPelanggan = (TextView) itemView.findViewById(R.id.alamat_pelanggan);
        mTvTglPesanan = (TextView) itemView.findViewById(R.id.tgl_pesanan);
        mTvWaktuPesanan = (TextView) itemView.findViewById(R.id.waktu_pesanan);
        mTvTotalPesanan = (TextView) itemView.findViewById(R.id.total_pesanan);
//        mBtnTerkirim = (Button) itemView.findViewById(R.id.btn_terkirim);
    }

}
