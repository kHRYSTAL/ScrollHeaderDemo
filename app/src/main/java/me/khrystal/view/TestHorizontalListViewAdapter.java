package me.khrystal.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import me.khrystal.hotheaderdemo.R;
import me.khrystal.model.TestModel;
import me.khrystal.model.User;

/**
 * Created by Yao on 2015/12/15.
 */
public class TestHorizontalListViewAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater mInflater;
    private List<TestModel> mList;

    public TestHorizontalListViewAdapter(Context context,List<TestModel> list){
        mContext = context;
        mList = list;
        //LayoutInflater.from(mContext);
        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.test_header_item,parent,false);
            holder.textView = (TextView)convertView.findViewById(R.id.test_tv);
            holder.button = (Button)convertView.findViewById(R.id.test_btn);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        final TestModel tm = mList.get(position);
        holder.textView.setText(tm.text);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,tm.text+"id="+tm.id,Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    private class ViewHolder {
        TextView textView;
        Button button;
    }
}
