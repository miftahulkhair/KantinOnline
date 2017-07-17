package com.example.mmift.kantinonline.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mmift.kantinonline.Model.Menu;
import com.example.mmift.kantinonline.Model.ModelOrder;
import com.example.mmift.kantinonline.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.mmift.kantinonline.Activity.MainActivity.Uid;

public class DetailMenuActivity extends AppCompatActivity {
    private DatabaseReference rootRef, nasiRef, ayamRef, ikanRef, sayuranRef, menuRef, orderRef;
    int quantity = 1;
    int total;
    int hargaMenu = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu);

        rootRef = FirebaseDatabase.getInstance().getReference();
        menuRef = rootRef.child("menu");
        orderRef = rootRef.child("order");

        final ImageView imageDm = (ImageView) findViewById(R.id.gambar_dm);
        final TextView namaDm = (TextView) findViewById(R.id.nama_dm);
        final TextView hargaDm = (TextView) findViewById(R.id.harga_dm);
        final TextView jumlahDm = (TextView) findViewById(R.id.jumlah_dm);
        final Button incrementDm = (Button) findViewById(R.id.increment_dm);
        final Button decrementDm = (Button) findViewById(R.id.decrement_dm);
        final Button btnDm = (Button) findViewById(R.id.btn_dm);

        //get intent
        Intent intent = getIntent();
        final String Ref = intent.getExtras().getString("Ref");
        if (intent.hasExtra("key")) {
            final String refMenu = intent.getStringExtra("key");
            Uid = intent.getStringExtra("User");

            menuRef.child(Ref).child(refMenu).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    final Menu menu = dataSnapshot.getValue(Menu.class);
                    namaDm.setText(menu.getmListMenu());
                    hargaDm.setText(String.valueOf(menu.getmHargaMenu()));
                    Glide.with(DetailMenuActivity.this)
                            .load(menu.getmURL())
                            .into(imageDm);

                    hargaMenu = menu.getmHargaMenu();
                    final String namaMenu = menu.getmListMenu();

                    //push data ke child order
                    btnDm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final ModelOrder modelOrder = new ModelOrder(namaMenu, quantity, (quantity * menu.getmHargaMenu()), Uid);
                            orderRef.child(Uid).child(refMenu).setValue(modelOrder)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(DetailMenuActivity.this, "Telah di tambah ke keranjang", Toast.LENGTH_SHORT).show();
                                            finish();
                                        }
                                    });
//                            pesananRef.push().setValue(modelOrder);
//                            Toast.makeText(DetailMenuActivity.this, "Telah di tambah ke keranjang", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }


        incrementDm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity++;
                total = hargaMenu * quantity;
                jumlahDm.setText(String.valueOf(quantity));
                hargaDm.setText(String.valueOf(total));


            }
        });

        decrementDm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity == 1) {
                    return;
                }
                quantity--;
                total = hargaMenu * quantity;
                jumlahDm.setText(String.valueOf(quantity));
                hargaDm.setText(String.valueOf(total));
            }
        });
    }
}


//        m_TextMenu = namaDm.getText().toString();
//        m_TextJumlah = Integer.parseInt(jumlahDm.getText().toString());
//        m_TextHarga = Integer.parseInt(hargaDm.getText().toString());
//
//
//
//        final ModelOrder order = new ModelOrder(m_TextMenu, m_TextJumlah, m_TextHarga);
//
//        btnDm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pesananRef.push().setValue(order);
//                Toast.makeText(DetailMenuActivity.this, "Telah ditambah ke keranjang", Toast.LENGTH_SHORT).show();
//            }
//        });

//
//    }


//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.decrement_dm:
//                if(quantity >= 0){
//                    quantity -= 1;
//                }
//                jumlahDm.setText(String.valueOf(quantity));
//                break;
//            case R.id.increment_dm:
//                quantity += 1;
//                jumlahDm.setText(String.valueOf(quantity));
//        }
//
//    }
//}


////mendapatkan user UID
//        orderRef.addListenerForSingleValueEvent(new ValueEventListener() {
//@Override
//public void onDataChange(DataSnapshot dataSnapshot) {
//
//        //iterator untuk mendapatkan userID
//        Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
//        while (iterator.hasNext()) {
//        DataSnapshot ds = iterator.next();
//        String id = ds.getKey();
////                    ModelOrder modelOrder = ds.getValue(ModelOrder.class);
////                    modelOrder.setmUidPesanan(id);
//        Log.d("TEST USER ID", id);
