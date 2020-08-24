package com.example.recycleviewitemdragdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DragAdapter extends RecyclerView.Adapter<DragAdapter.ViewHolder>{
    private List<ItemBean> mItemBeans=new ArrayList<>();
    private Context mContext;

    public List<ItemBean> getItemBeans() {
        return mItemBeans;
    }

    private DragClickListener mDragClickListener;
    public DragAdapter(List<ItemBean> itemBeans, Context context) {
        mItemBeans = itemBeans;
        mContext = context;
    }

    public void setDragClickListener(DragClickListener dragClickListener){
        mDragClickListener=dragClickListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mItemBeans.get(position),position);
    }

    @Override
    public int getItemCount() {
        return mItemBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mButton;
        DragClickListener mListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mListener = mDragClickListener;
        }

        public void bind(final ItemBean itemBean, final int position) {
            mButton = itemView.findViewById(R.id.recycler_item);
            mButton.setText(itemBean.getName());
            if(itemBean.isVisible){
                itemView.setVisibility(View.VISIBLE);
            }
            else {
                itemView.setVisibility(View.INVISIBLE);
            }
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onClick(DragClickListener.Action.COMMON_ITEM_LONG_CLICK,itemBean,position);
                    return false;
                }
            });
        }

        public final void onClick(DragClickListener.Action action, ItemBean item, int position) {
            if (mListener != null) {
                mListener.onClick(action, item, position);
            }
        }
    }
}
