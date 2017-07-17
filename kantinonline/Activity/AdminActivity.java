package com.example.mmift.kantinonline.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.mmift.kantinonline.Login.LoginForm;
import com.example.mmift.kantinonline.Model.Menu;
import com.example.mmift.kantinonline.Model.ModelPesanan;
import com.example.mmift.kantinonline.R;
import com.example.mmift.kantinonline.ViewHolder.PesananViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AdminActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    FirebaseAuth mAuth;
    GoogleApiClient mGoogleApiClient;
    private FirebaseRecyclerAdapter<ModelPesanan, PesananViewHolder> mAdapter;
    private RecyclerView mRv;
    private DatabaseReference rootRef, nasiRef, ayamRef, ikanRef, sayuranRef, pesananRef;
    private String m_TextMenu = "";
    private int m_TextHarga;
    private String m_TextGambar = "";
    private boolean bayar;

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemClickedId = item.getItemId();
        if (itemClickedId == R.id.mLogoutAdmin) {
            mAuth.signOut();
            Auth.GoogleSignInApi.signOut(mGoogleApiClient);
            Intent intent = new Intent(AdminActivity.this, LoginForm.class);
            startActivity(intent);
            finish();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rootRef = FirebaseDatabase.getInstance().getReference();
        pesananRef = rootRef.child("pesanan");
        nasiRef = rootRef.child("menu").child("nasi");
        ayamRef = rootRef.child("menu").child("ayam");
        ikanRef = rootRef.child("menu").child("ikan");
        sayuranRef = rootRef.child("menu").child("sayuran");

        mRv = (RecyclerView) findViewById(R.id.recyclerView);

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() == null) {
            startActivity(new Intent(AdminActivity.this, LoginForm.class));
            finish();
        }

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<ModelPesanan, PesananViewHolder>(ModelPesanan.class,
                R.layout.recycler_item_admin, PesananViewHolder.class, pesananRef) {
            @Override
            protected void populateViewHolder(final PesananViewHolder viewHolder,
                                              final ModelPesanan model, final int position) {

                viewHolder.mTvNamaPelanggan.setText(model.getmNamaPelanggan());
                viewHolder.mTvAlamatPelanggan.setText(model.getmAlamatPelanggan());
                viewHolder.mTvTotalPesanan.setText(String.valueOf("Rp. " + model.getmHargaPesanan()));

                pesananRef.child(mAdapter.getRef(position).getKey()).getRef()
                        .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        long date = (long) dataSnapshot.child("mTgl_Pesanan").getValue();
                        Date dateObject = new Date(date);
                        String formattedDate = formatDate(dateObject);
                        String formattedTime = formatTime(dateObject);
                        viewHolder.mTvTglPesanan.setText(formattedDate);
                        viewHolder.mTvWaktuPesanan.setText(formattedTime);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String key = getRef(position).getKey();
                        Intent intent = new Intent(AdminActivity.this, DetailPesananActivity.class);
                        intent.putExtra("userId", key);
                        startActivity(intent);
                    }
                });
            }

            //helper method tanggal
            private String formatDate(Date dateObject) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
                return dateFormat.format(dateObject);
            }

            //helper method waktu
            private String formatTime(Date dateObject) {
                SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
                return timeFormat.format(dateObject);
            }
        };

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AdminActivity.this, LinearLayoutManager.VERTICAL, false);
        mRv.setLayoutManager(linearLayoutManager);
        mRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRv.setItemAnimator(new DefaultItemAnimator());
        mRv.setAdapter(mAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewGroup viewGroup = (ViewGroup) findViewById(R.id.container);
                AlertDialog.Builder builder = new AlertDialog.Builder(AdminActivity.this);
                builder.setTitle("Insert Menu");

                View viewInflated = LayoutInflater.from(AdminActivity.this)
                        .inflate(R.layout.text_input_dialog, viewGroup, false);
                // Set up the input
                final EditText inputMenu = (EditText) viewInflated.findViewById(R.id.input_menu);
                final EditText inputHarga = (EditText) viewInflated.findViewById(R.id.input_harga);
                final EditText inputGambar = (EditText) viewInflated.findViewById(R.id.input_gambar);

                final RadioGroup rbGroup = (RadioGroup) viewInflated.findViewById(R.id.radio_group);
                final RadioButton rbNasi = (RadioButton) viewInflated.findViewById(R.id.radio_nasi);
                final RadioButton rbAyam = (RadioButton) viewInflated.findViewById(R.id.radio_ayam);
                final RadioButton rbIkan = (RadioButton) viewInflated.findViewById(R.id.radio_ikan);
                final RadioButton rbSayur = (RadioButton) viewInflated.findViewById(R.id.radio_sayur);

                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                builder.setView(viewInflated);

                // Set up the buttons
                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        m_TextMenu = inputMenu.getText().toString();
                        m_TextHarga = Integer.parseInt(inputHarga.getText().toString());
                        m_TextGambar = inputGambar.getText().toString();

                        Menu menu = new Menu(m_TextMenu, m_TextHarga, m_TextGambar);

                        int id = rbGroup.getCheckedRadioButtonId();
                        switch (id) {
                            case R.id.radio_nasi:
                                if (rbNasi.isChecked()) {
                                    nasiRef.push().setValue(menu);
                                }
                                break;
                            case R.id.radio_ayam:
                                if (rbAyam.isChecked()) {
                                    ayamRef.push().setValue(menu);
                                }
                                break;
                            case R.id.radio_ikan:
                                if (rbIkan.isChecked()) {
                                    ikanRef.push().setValue(menu);
                                }
                                break;
                            case R.id.radio_sayur:
                                if (rbSayur.isChecked()) {
                                    sayuranRef.push().setValue(menu);
                                }
                                break;
                        }
                        Toast.makeText(AdminActivity.this, "Insert Berhasil", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
