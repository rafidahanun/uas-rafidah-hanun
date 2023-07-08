package com.dewairtzadhany_201011400483.uts_aplikasiresepmasakan.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.dewairtzadhany_201011400483.uts_aplikasiresepmasakan.MieAyamActivity;
import com.dewairtzadhany_201011400483.uts_aplikasiresepmasakan.MieGorengActivity;
import com.dewairtzadhany_201011400483.uts_aplikasiresepmasakan.MieRebusActivity;
import com.dewairtzadhany_201011400483.uts_aplikasiresepmasakan.R;


public class ResepFragment extends Fragment {
    private Context context;
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resep, container, false);

        RecyclerView resepRecyclerView = view.findViewById(R.id.resep_recycler_view);
        context = getContext();
        resepRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        List<Resep> resepList = new ArrayList<>();
        ResepAdapter resepAdapter = new ResepAdapter(resepList, context);
        resepRecyclerView.setAdapter(resepAdapter);

        resepList.add(new Resep("Mie Goreng","Mie goreng populer di seluruh Asia dan merupakan salah satu jenis mie yang paling populer di Indonesia", R.drawable.mg));
        resepList.add(new Resep("Mie Rebus","Mie rebus populer di seluruh dunia dan biasanya dianggap sebagai hidangan yang sederhana, mudah dibuat, dan murah meriah.", R.drawable.mr));
        resepList.add(new Resep("Mie Ayam","Mie Ayam adalah hidangan mie dengan tambahan potongan daging ayam yang dimasak dan bumbu-bumbu seperti bawang putih, kecap manis, minyak wijen, serta bumbu-bumbu lainnya.", R.drawable.ma));

        resepAdapter.notifyDataSetChanged();
        return view;
    }

    private static class ResepAdapter extends RecyclerView.Adapter<ResepAdapter.ViewHolder> {
        private final List<Resep> resepList;
        private final Context context;

        public ResepAdapter(List<Resep> resepList, Context context) {
            this.resepList = resepList;
            this.context = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Resep resep = resepList.get(position);
            holder.resepTextView.setText(resep.getNama());
            holder.descTextView.setText(resep.getDesc());
            holder.resepImageView.setImageResource(resep.getGambar());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (position) {
                        case 0:
                            Intent mieGorengIntent = new Intent(context, MieGorengActivity.class);
                            context.startActivity(mieGorengIntent);
                            break;
                        case 1:
                            Intent mieRebusIntent = new Intent(context, MieRebusActivity.class);
                            context.startActivity(mieRebusIntent);
                            break;
                        case 2:
                            Intent mieAyamIntent = new Intent(context, MieAyamActivity.class);
                            context.startActivity(mieAyamIntent);
                            break;
                        default:
                            break;
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return resepList.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            private final ImageView resepImageView;
            private final TextView resepTextView;
            private final TextView descTextView;

            public ViewHolder(View view) {
                super(view);
                resepImageView = view.findViewById(R.id.food_image_view);
                resepTextView = view.findViewById(R.id.food_text_view);
                descTextView = view.findViewById(R.id.desc_text_view);
            }
        }
    }


    private class Resep {
        private String nama;
        private String desc;
        private int gambar;

        public Resep(String nama, String desc, int gambar) {
            this.nama = nama;
            this.desc = desc;
            this.gambar = gambar;
        }

        public String getNama() {
            return nama;
        }
        public String getDesc(){ return desc; }

        public int getGambar() {
            return gambar;
        }
    }
}