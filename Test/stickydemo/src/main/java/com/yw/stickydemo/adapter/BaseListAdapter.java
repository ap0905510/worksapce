package com.yw.stickydemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseListAdapter<Q> extends BaseAdapter {

    protected Context mContext;
    private List<Q> mList = new ArrayList<Q>();
    protected LayoutInflater mInflater;

    public BaseListAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public BaseListAdapter(Context context, List<Q> list) {
        this(context);
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    public void clearAll() {
        mList.clear();
    }

    public List<Q> getData() {
        return mList;
    }

    public void addALL(List<Q> list){
        if(list==null||list.size()==0) return;
        mList.addAll(list);
    }
    public void add(Q item){
        mList.add(item);
    }

    @Override
    public Q getItem(int position) {
        return (Q) mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void removeEntity(Q e){
        mList.remove(e);
    }

}
