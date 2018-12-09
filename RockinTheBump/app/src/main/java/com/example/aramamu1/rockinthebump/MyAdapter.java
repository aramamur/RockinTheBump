package com.example.aramamu1.rockinthebump;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;




    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private ArrayList<Picture> galleryList;
        private Context context;

        public MyAdapter(Context context, ArrayList<Picture> galleryList) {
            this.galleryList = galleryList;
            this.context = context;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_layout, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder viewHolder, int i) {
            String pic_name = "Picture " + i;
            viewHolder.title.setText(pic_name);

            if(!galleryList.isEmpty()) {
                if (galleryList.get(i).getPicture() != null) {
                    Uri uri = Uri.fromFile(new File(galleryList.get(i).getPicture()));
                    Picasso.with(context).load(uri).centerCrop().resize(240, 120).into(viewHolder.img);
                }
            }

         }

        @Override
        public int getItemCount() {
            return galleryList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            private TextView title;
            private ImageView img;
            public ViewHolder(View view) {
                super(view);

                title = (TextView)view.findViewById(R.id.title);
                img = (ImageView) view.findViewById(R.id.img);
            }
        }

    }

