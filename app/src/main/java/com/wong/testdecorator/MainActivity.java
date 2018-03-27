package com.wong.testdecorator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.OnItemClickListener{

    private RecyclerView recyclerView;
    private ArrayList<ItemEnity> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);

        for(int i = 0;i<10;i++){
            list.add(new ItemEnity("name"+i));
        }

        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(MainActivity.this,list);
        adapter.setOnItemClickListener(this);

//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);

//        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
                if (getChildCount() > 0) {
                    View firstChildView = recycler.getViewForPosition(0);
                    measureChild(firstChildView, widthSpec, heightSpec);
                    setMeasuredDimension(View.MeasureSpec.getSize(widthSpec), firstChildView.getMeasuredHeight()*3);
                } else {
                    super.onMeasure(recycler, state, widthSpec, heightSpec);
                }
            }
        });

        MyRecyclerViewDecorator myDecorator = new MyRecyclerViewDecorator(adapter);
        View headerView = LayoutInflater.from(this).inflate(R.layout.layout_header,this.recyclerView,false);
       myDecorator.addHeaderView(headerView);
        recyclerView.setAdapter(myDecorator);


    }

    @Override
    public void setOnClick(View view, int position) {
        Toast.makeText(MainActivity.this,list.get(position).getName(),Toast.LENGTH_SHORT).show();
    }
}
