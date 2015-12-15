package me.khrystal.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;

import me.khrystal.hotheaderdemo.R;
import me.khrystal.model.User;


/**
 * Created by Yao on 2015/12/12.
 */
public class HorizontalListViewAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<User> mList;

    public HorizontalListViewAdapter(Context context,List<User> list){
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
            convertView = mInflater.inflate(R.layout.hot_header_item,parent,false);
            holder.imageView = (CircleImageView)convertView.findViewById(R.id.avatar);
            holder.fansCount = (TextView)convertView.findViewById(R.id.fans_count);
            holder.followButton = (TextView)convertView.findViewById(R.id.follow);
            holder.nickName = (TextView)convertView.findViewById(R.id.nick_name);
            holder.devider = convertView.findViewById(R.id.hot_devider);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        User user = mList.get(position);
        if (!TextUtils.isEmpty(user.pic)){
            Picasso.with(mContext).load(user.pic).into(holder.imageView);
        }
        holder.nickName.setText(user.nick);
        holder.fansCount.setText(user.is_fans ? "已关注" : "加关注" );
        holder.fansCount.setSelected(user.is_fans);
        if (position == mList.size()-1){
            holder.devider.setVisibility(View.INVISIBLE);
        }else {
            holder.devider.setVisibility(View.VISIBLE);
        }
        holder.nickName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到个人中心
            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 跳转到个人中心
            }
        });
        holder.followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发起网络请求 同时发广播同步数据 同时粉丝数增加或减少
            }
        });
        return convertView;
    }

    private class ViewHolder{
        CircleImageView imageView;
        TextView nickName;
        TextView fansCount;
        TextView followButton;
        View devider;
    }
}
