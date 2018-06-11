package com.example.cuiqiaoyu.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuiqiaoyu.myapplication.bean.Book;

import java.util.List;

/**
 * Created by cuiqiaoyu on 2018/6/10.
 */

public class BookBaseAdapter extends RecyclerView.Adapter<BookBaseAdapter.ViewHolder>{



    public interface OnItemClickListener{
        void onClick( int position);
        void onLongClick( int position);
    }

    OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        this. mOnItemClickListener=onItemClickListener;
    }

    private List<Book> mBookList;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyle_view_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.bookImage.setImageResource(mBookList.get(position).getmIconid());
        holder.bookname.setText(mBookList.get(position).getmName());
        holder.bookImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mBookList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView bookImage;
        TextView bookname;

        public ViewHolder(View view) {
            super(view);
            bookImage = (ImageView) view.findViewById(R.id.book_iamge);
            bookname = (TextView) view.findViewById(R.id.book_name);
        }
    }

    public BookBaseAdapter(List<Book> mBookList) {
        this.mBookList = mBookList;
    }
}
