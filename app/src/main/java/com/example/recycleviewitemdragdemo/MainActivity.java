package com.example.recycleviewitemdragdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.os.Bundle;

public class MainActivity extends BaseActivity implements RecyclerDragHelper.DragListener {

    private RecyclerView mRecyclerView;
    private DragAdapter mDragAdapter;
    private RecyclerDragHelper mRecyclerDragHelper;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initDate() {

    }

    @Override
    protected void initView() {
        mRecyclerView=findViewById(R.id.recyclerview);
        mDragAdapter=new DragAdapter(ItemBean.getItemBeans(),this);
        mRecyclerDragHelper=new RecyclerDragHelper(mRecyclerView,this);
        mRecyclerView.setAdapter(mDragAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        mDragAdapter.setDragClickListener(new DragClickListener() {

            @Override
            public void onClick(Action action, ItemBean item, int position) {
                int itemPosition = mDragAdapter.getItemBeans().indexOf(item);
                switch (action){
                    case COMMON_ITEM_LONG_CLICK:
                        if(itemPosition > -1 && itemPosition < mDragAdapter.getItemCount()){
                            ItemBean itemBean=mDragAdapter.getItemBeans().get(itemPosition);
                            itemBean.setVisible(false);
                            mDragAdapter.notifyItemChanged(itemPosition);
                            mRecyclerDragHelper.startDrag(itemPosition);
                        }
                }
            }
        });
    }

    @Override
    public void onDrag(int startPosition, int targetPosition) {
//        if (targetPosition == -1 || startPosition == targetPosition) {
            ItemBean itemBean=mDragAdapter.getItemBeans().get(startPosition);
            itemBean.setVisible(true);
            mDragAdapter.notifyItemChanged(startPosition);
//        }
    }
}