package com.wong.testdecorator;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wong on 18-3-27.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<ItemEnity> list;

    public MyRecyclerViewAdapter(Context context,ArrayList<ItemEnity> list){
        this.mContext = context;
        this.list = list;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder mholder, final int position) {

        //        for(int i = 0;i<10;i++){
        String nickName = "name"+position;
        Log.i(nickName,list.get(position).getName());
//        }
        ItemViewHolder holder = (ItemViewHolder)mholder;
        TextView textView = holder.getView(R.id.textView);
        textView.setText(list.get(position).getName());

        Button button = holder.getView(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   if(mOnItemClickListener != null){
                       mOnItemClickListener.setOnClick(v,position);
                   }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;

    }
    public interface OnItemClickListener{
        public void setOnClick(View view,int position);
    }
}
