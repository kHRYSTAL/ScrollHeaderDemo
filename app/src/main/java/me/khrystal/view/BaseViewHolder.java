package me.khrystal.view;

import android.view.View;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;

/**
 * Created by Yao on 2015/12/15.
 */
public class BaseViewHolder extends UltimateRecyclerviewViewHolder{
    public View mView;

    public BaseViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
    }
}
