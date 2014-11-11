package com.aaaliua.adapter;

import com.example.ttt.R;
import com.saulmm.material.slidingtabs.views.SnackBar;
import com.saulmm.material.slidingtabs.views.SnackBar.OnMessageClickListener;

import android.app.Activity;
import android.content.Context;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private final Context mContext;
    private final String[] mDataset;
    public final  Activity ac;

    public RecyclerViewAdapter(Context context, String[] dataset,Activity ac) {
    	mContext = context;
    	mDataset = dataset;
    	this.ac = ac;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView view = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(mContext,view,ac);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        String[] values = mDataset[position].split(",");
        String countryName = values[0];
        int flagResId = mContext.getResources().getIdentifier(values[1], "drawable", mContext.getPackageName());
        viewHolder.mTextView.setText(countryName);
        viewHolder.mTextView.setCompoundDrawablesWithIntrinsicBounds(flagResId, 0, 0, 0);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
    
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        private final Context mContext;
       
        
        private final Activity ac;
        public ViewHolder(Context mCon,TextView v, Activity acd) {
            super(v);
            mTextView = v;
            mContext = mCon;
            this.ac = acd;
          
          
            
            mTextView.setOnClickListener(new OnClickListener() {  
                @Override  
                public void onClick(View v) {  
                    Toast.makeText(mContext, "当前点击的位置："+getPosition(), Toast.LENGTH_SHORT).show();
                	 
                	
                }  
            }); 
            
        }
    }
}