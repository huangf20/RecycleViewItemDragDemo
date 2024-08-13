package com.example.recycleviewitemdragdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.os.Bundle;

import java.util.Collections;

public class MainActivity extends BaseActivity{

    private RecyclerView mRecyclerView;
    private DragAdapter mDragAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initDate() {

    }

    @Override
    protected void initView() {
        mRecyclerView = findViewById(R.id.recyclerview);
        mDragAdapter = new DragAdapter(ItemBean.getItemBeans(), this);
        mRecyclerView.setAdapter(mDragAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT; // 支持拖动
                int swipeFlags = 1; // 不支持滑动
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder source, @NonNull RecyclerView.ViewHolder target) {
                int fromPosition = source.getAdapterPosition();
                int toPosition = target.getAdapterPosition();
                // 更新数据集中的位置
                Collections.swap(mDragAdapter.getItemBeans(), fromPosition, toPosition);
                // 更新RecyclerView的显示
                recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

            }
        });
        helper.attachToRecyclerView(mRecyclerView);
    }


}