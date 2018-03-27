package com.wong.testdecorator;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by wong on 18-3-27.
 */

public class ItemViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private View itemView;
    public ItemViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        mViews = new SparseArray<View>();
    }
    public <T extends View> T getView(int id){
        View view = mViews.get(id);
        if(view == null){
            view = itemView.findViewById(id);
            mViews.put(id,view);
        }
        return (T)view;
    }
}
