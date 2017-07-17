package com.example.mmift.kantinonline.Activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mmift.kantinonline.Model.Menu;
import com.example.mmift.kantinonline.Model.ModelOrder;
import com.example.mmift.kantinonline.Model.ModelPesanan;
import com.example.mmift.kantinonline.R;
import com.example.mmift.kantinonline.ViewHolder.MenuViewHolder;
import com.example.mmift.kantinonline.ViewHolder.OrderViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.sql.Array;
import java.util.HashMap;
import java.util.Map;

import static com.example.mmift.kantinonline.Activity.MainActivity.Uid;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener {
    int total = 0;
    private TextView mTvTotalHarga;
    private Button mBtnOrder;
    private EditText alamatPelanggan;
    private FirebaseRecyclerAdapter<ModelOrder, OrderViewHolder> mAdapter;
    private RecyclerView mRv;
    private DatabaseReference rootRef, orderRef, pesananRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_activity_order);

        rootRef = FirebaseDatabase.getInstance().getReference();
        pesananRef = rootRef.child("pesanan");
        orderRef = rootRef.child("order").child(Uid);

        alamatPelanggan = (EditText) findViewById(R.id.editTextOrder);
        mRv = (RecyclerView) findViewById(R.id.rv_order);
        mTvTotalHarga = (TextView) findViewById(R.id.textTotal);
        mBtnOrder = (Button) findViewById(R.id.btn_order);
        mBtnOrder.setOnClickListener(this);

        //set layout
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(OrderActivity.this, LinearLayoutManager.VERTICAL, false);
        mRv.setLayoutManager(linearLayoutManager);
        mRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
//        mRv.setItemAnimator(new DefaultItemAnimator());

        //set total harga
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

        //isi data with adapter
        mAdapter = new FirebaseRecyclerAdapter<ModelOrder, OrderViewHolder>(ModelOrder.class, R.layout.recycler_item_order, OrderViewHolder.class, orderRef) {
            @Override
            protected void populateViewHolder(final OrderViewHolder viewHolder, final ModelOrder model, final int position) {
                final String makananKey = getRef(position).getKey();
                orderRef.child(makananKey).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ModelOrder order = dataSnapshot.getValue(ModelOrder.class);
                        viewHolder.mTvNamaMakanan.setText(order.getmNamaMenuOrder());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

//                viewHolder.mTvNamaMakanan.setText(model.getmListMenu());
                viewHolder.mTvJumlahMakanan.setText(String.valueOf(model.getmJumlahOrder()));
                viewHolder.mTvHargaMakanan.setText(String.valueOf("Rp. " + model.getmHargaOrder()));
                viewHolder.mBtnHapusItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getRef(position).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                total -= model.getmHargaOrder();
                                mTvTotalHarga.setText("TOTAL : Rp." + total);
                            }
                        });
                    }
                });
            }
        };
        mRv.setAdapter(mAdapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_order:
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                String username = mAuth.getCurrentUser().getDisplayName();
                String alamat = alamatPelanggan.getText().toString();
//                ModelPesanan pesanan = new ModelPesanan();

                Map<String, Object> value = new HashMap<>();
                value.put("mNamaPelanggan", username);
                value.put("mHargaPesanan", total);
                value.put("mTgl_Pesanan", ServerValue.TIMESTAMP);
                value.put("mAlamatPelanggan", alamat);
                value.put("mUidPesanan", Uid);

//                ModelPesanan pesanan = new ModelPesanan(username,total);
                pesananRef.child(Uid).setValue(value).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(OrderActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                Toast.makeText(this, "Berhasil di pesan", Toast.LENGTH_SHORT).show();
        }
    }
}


//                pesananRef.child(pesananKey).addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        Long timestamp = (Long) dataSnapshot.getValue();
//
////                        pesananRef.child(pesananKey).child("timestamp").setValue(ServerValue.TIMESTAMP);
//
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });
//
//                pesananRef.child(pesananKey).child("mTgl_Pesanan").setValue(ServerValue.TIMESTAMP);