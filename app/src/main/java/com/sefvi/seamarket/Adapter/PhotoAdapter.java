package com.sefvi.seamarket.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sefvi.seamarket.R;

import java.io.IOException;
import java.util.ArrayList;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

private Context mContext;
private ArrayList<Uri> mListPhotos;

    public PhotoAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void setData (ArrayList<Uri> list){
        this.mListPhotos = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_add_photo_product,parent ,false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
    Uri uri = mListPhotos.get(position);
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(),uri);
        holder.imgPhoto.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (mListPhotos == null) {
            return 0;
        }else {
            return mListPhotos.size();
        }
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgPhoto;
        public PhotoViewHolder(View itemView){
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.addmin_photo_addproduct);

        }

    }
}
