package com.example.mmift.kantinonline.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mmift.kantinonline.Activity.DetailMenuActivity;
import com.example.mmift.kantinonline.Activity.MainActivity;
import com.example.mmift.kantinonline.Model.Menu;
import com.example.mmift.kantinonline.R;
import com.example.mmift.kantinonline.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

/**
 * A simple {@link Fragment} subclass.
 */
public class SayurFragment extends Fragment {
    private FirebaseRecyclerAdapter<Menu,MenuViewHolder> mAdapter;
    private DatabaseReference rootRef,sayuranRef;
    private RecyclerView mRv;

    public SayurFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycler_view, container, false);

        rootRef = FirebaseDatabase.getInstance().getReference();
        sayuranRef = rootRef.child("menu").child("sayuran");

        mRv = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        mAdapter = new FirebaseRecyclerAdapter<Menu, MenuViewHolder>(Menu.class,R.layout.recycler_item,MenuViewHolder.class,sayuranRef) {
            @Override
            protected void populateViewHolder(final MenuViewHolder viewHolder, final Menu model, final int position) {
                Glide.with(getActivity())
                        .load(model.getmURL())
                        .into(viewHolder.mIvFotoMakanan);

                viewHolder.mTvNamaMakanan.setText(model.getmListMenu());
                viewHolder.mTvHargaMakanan.setText(String.valueOf(model.getmHargaMenu()));

                final String key = getRef(position).getKey();
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), DetailMenuActivity.class);
                        intent.putExtra("key",key);
                        intent.putExtra("Ref", "sayuran");
                        intent.putExtra("User", MainActivity.Uid);
                        startActivity(intent);
                    }
                });
            }
        };

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mRv.setLayoutManager(linearLayoutManager);
        mRv.setItemAnimator(new DefaultItemAnimator());
        mRv.setAdapter(mAdapter);

        return rootView;
    }
}
