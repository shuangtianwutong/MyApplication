package com.example.cuiqiaoyu.myapplication;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.cuiqiaoyu.myapplication.bean.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements BookBaseAdapter.OnItemClickListener {

    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;


    private List<Book> mlsit = new ArrayList<Book>();
    BookBaseAdapter mBookBaseAdapter;

    public MainActivityFragment() {
    }

    @Override
    public void onClick(int position) {
        Toast.makeText(getContext(),"onClick事件       您点击了第："+position+"个Item",0).show();
    }

    @Override
    public void onLongClick(int position) {
        Toast.makeText(getContext(),"onLongClick事件       您点击了第："+position+"个Item",0).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBook();
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        mLayoutManager = new LinearLayoutManager(getContext());
        //mLayoutManager .setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration( new DividerGridItemDecoration(this ));
//设置增加或删除条目的动画
        mRecyclerView.setItemAnimator( new DefaultItemAnimator());
        mBookBaseAdapter= new BookBaseAdapter(mlsit);
        mBookBaseAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mBookBaseAdapter);

    }

    private void initBook(){

            Book book01 = new Book("Book1",R.drawable.dislike_1);
            mlsit.add(book01);
            Book book02 = new Book("Book2",R.drawable.dislike_2);
            mlsit.add(book02);
            Book book03 = new Book("Book3",R.drawable.dislike_3);
            mlsit.add(book03);
    }

}
