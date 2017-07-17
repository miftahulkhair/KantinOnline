package com.example.mmift.kantinonline.Model;

/**
 * Created by mmift on 5/10/2017.
 */

public class ModelOrder {
    private String mNamaMenuOrder, mUidPesanan;
    private int mHargaOrder, mJumlahOrder, mHargaTotalOrder;
//    public int getmHargaTotalOrder() {
//        return mHargaTotalOrder;
//    }

    public String getmUidPesanan() {
        return mUidPesanan;
    }

    public int getmHargaOrder() {
        return mHargaOrder;
    }

    public int getmJumlahOrder() {
        return mJumlahOrder;
    }

    public String getmNamaMenuOrder() {
        return mNamaMenuOrder;
    }

    public void setmUidPesanan(String mUidPesanan) {
        this.mUidPesanan = mUidPesanan;
    }

    public void setmHargaOrder(int mHargaOrder) {
        this.mHargaOrder = mHargaOrder;
    }

    public void setmJumlahOrder(int mJumlahOrder) {
        this.mJumlahOrder = mJumlahOrder;
    }

    public void setmNamaMenuOrder(String mNamaMenuOrder) {
        this.mNamaMenuOrder = mNamaMenuOrder;
    }
//    public void setmHargaTotalOrder(int mHargaTotalOrder) {
//        this.mHargaTotalOrder = mHargaTotalOrder;
//    }

    public ModelOrder() {

    }

    public ModelOrder(String vNamaMenuOrder, int vJumlahOrder, int vHargaOrder, String vUidPesanan) {
        mNamaMenuOrder = vNamaMenuOrder;
        mJumlahOrder = vJumlahOrder;
        mHargaOrder = vHargaOrder;
        mUidPesanan = vUidPesanan;
    }
}

//    public ModelOrder(String vNamaMenuOrder, int vJumlahOrder, int vHargaOrder, int vHargaTotalOrder){
//        mNamaMenuOrder = vNamaMenuOrder;
//        mJumlahOrder = vJumlahOrder;
//        mHargaOrder = vHargaOrder;
//        mHargaTotalOrder = vHargaTotalOrder;
//    }
//}
