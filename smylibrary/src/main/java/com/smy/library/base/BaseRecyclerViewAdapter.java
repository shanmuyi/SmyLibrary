package com.smy.library.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseRecyclerViewAdapter<T, VH extends
        BaseRecyclerViewAdapter.BaseViewHolder> extends RecyclerView.Adapter<VH> {

    protected LayoutInflater mLayoutInflater;
    protected Context mContext;
    protected List<T> list;
    protected ItemClickListener itemClickListener;
    protected ItemLongClickListener itemLongClickListener;
    protected ItemDeleteClickListener itemDeleteClickListener;

    public interface ItemLongClickListener<T> {
        void onItemLongClick(T t, int position);
    }

    public interface ItemClickListener<T> {
        void onItemClick(T t, int position);
    }

    public interface ItemDeleteClickListener<T> {
        void onItemDeleteClick(T t, int position);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setItemLongClickListener(ItemLongClickListener itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
    }

    public void setItemDeleteClickListener(ItemDeleteClickListener itemDeleteListener) {
        this.itemDeleteClickListener = itemDeleteListener;
    }

    public BaseRecyclerViewAdapter(Context context, List<T> list) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
        this.list = list;
    }

    public void setList(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<T> getList() {
        return list;
    }


    public abstract VH onCreateCustomViewHolder(View view);

    public abstract void onBindCustomViewHolder(VH vh, T t, int position);

    public abstract int getLayoutId();

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(getLayoutId(), parent, false);
        return onCreateCustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(list.get(position), position);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (itemLongClickListener != null) {
                    itemLongClickListener.onItemLongClick(list.get(position), position);
                    return true;
                }
                return false;
            }
        });
        onBindCustomViewHolder(holder, list.get(position), position);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

        public BaseViewHolder(View itemView) {
            super(itemView);
        }

    }

}
