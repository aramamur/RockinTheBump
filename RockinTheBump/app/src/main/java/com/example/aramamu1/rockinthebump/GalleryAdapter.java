package com.example.aramamu1.rockinthebump;
import android.content.Context;
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




public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private ImageView img;

        public ViewHolder(View view) {
            super(view);

            title = (TextView)view.findViewById(R.id.title);
            img = (ImageView) view.findViewById(R.id.img);

        }
    }

    private ArrayList<Picture> galleryList;
    private Context context;

    public GalleryAdapter(Context context, ArrayList<Picture> galleryList) {
        this.galleryList = galleryList;
        this.context = context;

    }

    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.cell_layout, viewGroup, false);
        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(GalleryAdapter.ViewHolder viewHolder, int i) {
        String pic_name = "Picture " + i;
        viewHolder.title.setText(pic_name);

        if(!galleryList.isEmpty()) {
            if (galleryList.get(i).getPicture() != null) {
                String picfilename = galleryList.get(i).getPicture();
                File fileuri = new File(picfilename);
                Uri uri = Uri.fromFile(fileuri);

                Picasso.with(viewHolder.itemView.getContext()).load(uri).centerCrop().resize(240, 120).into(viewHolder.img);
            }
        }


    }

    @Override
    public int getItemCount() {
        return galleryList.size();
    }

}
