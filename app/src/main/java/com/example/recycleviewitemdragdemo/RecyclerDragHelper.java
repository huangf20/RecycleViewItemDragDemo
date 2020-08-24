package com.example.recycleviewitemdragdemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.DragEvent;
import android.view.View;

import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerDragHelper {
    private static final String TAG = "RecyclerDragHelper";
    private RecyclerView mRecyclerView;
    private float mDy;
    private float mSelectedStartY;
    private DragListener mDragListener;

    interface DragListener {
        void onDrag(int startPosition, int targetPosition);
    }

    void startDrag(int position) {
        View startView = mRecyclerView.getLayoutManager().findViewByPosition(position);
        if (startView != null) {
            View.DragShadowBuilder myShadow = new View.DragShadowBuilder(startView);
            ViewCompat.startDragAndDrop(startView, null, myShadow, startView, 0);
        }
    }
    RecyclerDragHelper(RecyclerView recyclerView, DragListener dragListener) {
        mRecyclerView = recyclerView;
        mDragListener = dragListener;
        mRecyclerView.setOnDragListener(new DragEventListener());
    }
    protected class DragEventListener implements View.OnDragListener {
        int startPosition = -1;
        int targetPosition = -1;
        View selectView = null;
        @Override
        public boolean onDrag(View v, DragEvent event) {
            final int action = event.getAction();
            switch(action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    selectView = (View) event.getLocalState();
                    startPosition = mRecyclerView.getChildAdapterPosition(selectView);
                    targetPosition = -1;
                    mSelectedStartY = event.getY();
                    break;
                case DragEvent.ACTION_DRAG_LOCATION:
                    mDy = event.getY() - mSelectedStartY;
                    scrollIfNecessary(selectView);
                    View dragTargetView = mRecyclerView.findChildViewUnder(event.getX(), event.getY());
                    if (dragTargetView != null) {
                        int newTargetPosition = mRecyclerView.getChildAdapterPosition(dragTargetView);
                        if (newTargetPosition != targetPosition) {
                            onEnterView(newTargetPosition);
                            onExitView(targetPosition);
                            targetPosition = newTargetPosition;

                        }
                    } else {
                        onExitView(targetPosition);
                        targetPosition = -1;

                    }
                    break;
                case DragEvent.ACTION_DROP:

                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    onExitView(targetPosition);
                    targetPosition = -1;

                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    onExitView(targetPosition);
                    handleDrag(startPosition, targetPosition);
                    break;
            }

            return true;
        }
    }

    private void handleDrag(int startPosition, int targetPosition) {
        mDragListener.onDrag(startPosition, targetPosition);
    }

    private boolean scrollIfNecessary(View selectView) {
        RecyclerView.LayoutManager lm = mRecyclerView.getLayoutManager();
        int scrollY = 0;
        if (lm.canScrollVertically()) {
            int curY = (int) (mSelectedStartY + mDy);
            int topDiff = curY  - mRecyclerView.getPaddingTop() - selectView.getHeight();
            if (mDy < 0 && topDiff < 0) {
                scrollY = topDiff;
            } else if (mDy > 0) {
                final int bottomDiff =  curY + 2 * selectView.getHeight() -
                        (mRecyclerView.getHeight() - mRecyclerView.getPaddingBottom());
                if (bottomDiff > 0) {
                    scrollY = bottomDiff;
                }
            }
        }
        if ( scrollY != 0) {
            mRecyclerView.scrollBy(0, scrollY / 10);
            return true;
        }
        return false;
    }
    private void onEnterView(int position) {
        View view = getView(position);
        if (view != null) {
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1, 1.1f);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1, 1.1f);
            AnimatorSet animSet =new AnimatorSet();
            animSet.play(scaleX).with(scaleY);
            animSet.setDuration(100);
            animSet.start();
        }
    }

    private View getView(int position) {
        return mRecyclerView.getLayoutManager().findViewByPosition(position);
    }

    private void onExitView(int position) {
        View view = getView(position);
        if (view != null) {
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1.1f, 1f);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1.1f, 1f);
            AnimatorSet animSet =new AnimatorSet();
            animSet.play(scaleX).with(scaleY);
            animSet.setDuration(100);
            animSet.start();
        }
    }
}
