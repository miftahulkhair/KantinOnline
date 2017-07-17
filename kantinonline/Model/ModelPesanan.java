package com.example.mmift.kantinonline.Model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.ServerValue;

/**
 * Created by mmift on 5/1/2017.
 */

public class ModelPesanan {
    private String mNamaPesanan, mNamaPelanggan, mUidPesanan, mAlamatPelanggan;
    private int mHargaPesanan;
    private long mTgl_Pesanan;
    private boolean isBayar;
    Object timeStamp;


    //getter
    @Exclude
    public long getTimeStampLong() {
        return (long) timeStamp;
    }

    public String getmUidPesanan() {
        return mUidPesanan;
    }

    public String getmAlamatPelanggan() {
        return mAlamatPelanggan;
    }

    public String getmNamaPesanan() {
        return mNamaPesanan;
    }

    public int getmHargaPesanan() {
        return mHargaPesanan;
    }

    public String getmNamaPelanggan() {
        return mNamaPelanggan;
    }

    public long getmTgl_Pesanan() {
        return mTgl_Pesanan;
    }

    public boolean isBayar() {

        return isBayar;
    }

    //setter
    public void setBayar(boolean bayar) {
        isBayar = bayar;
    }

    public void setmUidPesanan(String mUidPesanan) {
        this.mUidPesanan = mUidPesanan;
    }

    public void setmAlamatPelanggan(String mAlamatPelanggan) {
        this.mAlamatPelanggan = mAlamatPelanggan;
    }

    public void setmNamaPesanan(String mNamaPesanan) {
        this.mNamaPesanan = mNamaPesanan;
    }

    public void setmHargaPesanan(int mHargaPesanan) {

        this.mHargaPesanan = mHargaPesanan;
    }

    public void setmNamaPelanggan(String mNamaPelanggan) {
        this.mNamaPelanggan = mNamaPelanggan;
    }

    public void setmTgl_Pesanan(long mTgl_pesanan) {
        this.mTgl_Pesanan = mTgl_Pesanan;
    }


    public ModelPesanan() {
    }

    public ModelPesanan(String vNamaPelanggan, int vHargaPesanan, long vTgl_Pesanan) {
        mNamaPelanggan = vNamaPelanggan;
        mHargaPesanan = vHargaPesanan;
        mTgl_Pesanan = vTgl_Pesanan;
    }

    public ModelPesanan(String vNamaPelanggan, int vHargaPesanan) {
        mNamaPelanggan = vNamaPelanggan;
        mHargaPesanan = vHargaPesanan;
        timeStamp = ServerValue.TIMESTAMP;
    }

    public ModelPesanan(String vNamaPesanan, String vNamaPelanggan, int vHargaPesanan) {
        mNamaPesanan = vNamaPesanan;
        mNamaPelanggan = vNamaPelanggan;
        mHargaPesanan = vHargaPesanan;
    }

    public ModelPesanan(String vNamaPesanan, String vNamaPelanggan, int vHargaPesanan, long vTgl_Pesanan) {
        mNamaPesanan = vNamaPesanan;
        mNamaPelanggan = vNamaPelanggan;
        mHargaPesanan = vHargaPesanan;
        mTgl_Pesanan = vTgl_Pesanan;
    }
}
