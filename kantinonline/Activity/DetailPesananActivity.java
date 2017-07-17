package com.example.mmift.kantinonline.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mmift.kantinonline.Model.ModelOrder;
import com.example.mmift.kantinonline.Model.ModelPesanan;
import com.example.mmift.kantinonline.R;
import com.example.mmift.kantinonline.ViewHolder.DetailPesananViewHolder;
import com.example.mmift.kantinonline.ViewHolder.OrderViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

import static com.example.mmift.kantinonline.Activity.MainActivity.Uid;

public class DetailPesananActivity extends AppCompatActivity {
    int total = 0;
    private TextView mTvTotalHarga;
    private FirebaseRecyclerAdapter<ModelOrder, DetailPesananViewHolder> mAdapter;
    private RecyclerView mRv;
    private DatabaseReference rootRef, orderRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_activity_detailpesanan);

        rootRef = FirebaseDatabase.getInstance().getReference();

        //get intent user id
        Intent intent = getIntent();
        String userId = intent.getExtras().getString("userId");
        orderRef = rootRef.child("order").child(userId);

        mRv = (RecyclerView) findViewById(R.id.rv_detailpesanan);
        mTvTotalHarga = (TextView) findViewById(R.id.textTotalDetailPesanan);

        //set layout
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DetailPesananActivity.this, LinearLayoutManager.VERTICAL, false);
        mRv.setLayoutManager(linearLayoutManager);
        mRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        //total harga pesanan
        orderRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    final ModelOrder modelOrder = dataSnapshot1.getValue(ModelOrder.class);
                    total += modelOrder.getmHargaOrder();
                }
                mTvTotalHarga.setText("TOTAL : Rp. " + total);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //isi setiap pesanan
        mAdapter = new FirebaseRecyclerAdapter<ModelOrder, DetailPesananViewHolder>
                (ModelOrder.class, R.layout.recycler_item_detailpesanan,
                        DetailPesananViewHolder.class, orderRef) {

            @Override
            protected void populateViewHolder(final DetailPesananViewHolder viewHolder,
                                              final ModelOrder model, final int position) {
                viewHolder.mTvNamaMakanan.setText(model.getmNamaMenuOrder());
                viewHolder.mTvJumlahMakanan.setText(String.valueOf(model.getmJumlahOrder()));
                viewHolder.mTvHargaMakanan.setText(String.valueOf("Rp. " + model.getmHargaOrder()));
            }
        };
        mRv.setAdapter(mAdapter);
    }
}


//total harga pesanan


//                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
//                    final ModelOrder modelOrder = dataSnapshot1.getValue(ModelOrder.class);
//                    total += modelOrder.getmHargaOrder();
//                }
//                mTvTotalHarga.setText("TOTAL : Rp. "+ total);


//kodingan di bawah pake pesananRef
//                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
//                    final ModelPesanan modelPesanan = dataSnapshot1.getValue(ModelPesanan.class);
//                    total = modelPesanan.getmHargaPesanan();
//                }
//                mTvTotalHarga.setText("TOTAL : Rp. "+ total);


//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DetailPesananActivity.this, LinearLayoutManager.VERTICAL, false);
//        mRv.setLayoutManager(linearLayoutManager);
//        mRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
