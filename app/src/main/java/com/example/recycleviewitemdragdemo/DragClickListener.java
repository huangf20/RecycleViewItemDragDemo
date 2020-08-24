package com.example.recycleviewitemdragdemo;

public interface DragClickListener {

    void onClick(Action action, ItemBean item, int position);

    enum Action {
        ACTION_ITEM_CLICK,
        COMMON_ITEM_CLICK,
        COMMON_ITEM_LONG_CLICK,
        ;
    }
}
