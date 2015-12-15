package me.khrystal.hotheaderdemo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import java.util.ArrayList;
import java.util.List;

import me.khrystal.model.TestModel;
import me.khrystal.view.BaseViewHolder;
import me.khrystal.view.HorizontalListView;
import me.khrystal.view.TestHorizontalListViewAdapter;

public class MainActivity extends AppCompatActivity {
    UltimateRecyclerView recyclerView;
    List dataList;
    List<TestModel> headerList;
    SimpleAdapter adapter;
    TestHorizontalListViewAdapter testAdapter;

    View headerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (UltimateRecyclerView)findViewById(R.id.recycler_view);
        dataList = new ArrayList();

        adapter = new SimpleAdapter(this,dataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        initHeaderView();
        for (int i=0;i<100;i++){
            String str = "第"+(i+1)+"行";
            dataList.add(str);
        }
        adapter.notifyDataSetChanged();
        for (int i = 0; i < 100; i++) {
            TestModel tm = new TestModel();
            tm.id=i;
            tm.text = "第"+(i+1)+"个";
            headerList.add(tm);
        }
        testAdapter.notifyDataSetChanged();

    }

    private void initHeaderView() {
        headerView = getLayoutInflater().inflate(R.layout.hot_header,recyclerView.mRecyclerView,false);
        HorizontalListView listView = (HorizontalListView)headerView.findViewById(R.id.header_list_view);
        headerList = new ArrayList();
        testAdapter = new TestHorizontalListViewAdapter(this,headerList);
        listView.setAdapter(testAdapter);
        recyclerView.setNormalHeader(headerView);


    }


    public class SimpleAdapter extends UltimateViewAdapter<SimpleViewHolder>{
        List<String> dataList;
        Object mParent;

        public SimpleAdapter(Object parent ,List<String> dataList){
            this.dataList = dataList;
            mParent = parent;
        }
        @Override
        public SimpleViewHolder getViewHolder(View view) {
            return new SimpleViewHolder(view,MainActivity.this,true);
        }

        @Override
        public SimpleViewHolder onCreateViewHolder(ViewGroup parent) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_item,parent,false);
            SimpleViewHolder vh  = new SimpleViewHolder(v,MainActivity.this,false);
            return vh;
        }

        @Override
        public int getAdapterItemCount() {
            return dataList.size();
        }

        @Override
        public long generateHeaderId(int i) {
            return 0;
        }

        @Override
        public void onBindViewHolder(SimpleViewHolder holder, int position) {
            if (position < getItemCount() && (customHeaderView != null ? position <= dataList.size() : position < dataList.size()) && (customHeaderView != null ? position > 0 : true)) {
                String str = dataList.get(customHeaderView != null ? position - 1 : position);
                holder.bindData(str);
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup) {
            return null;
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

        }

        public void append(String item) {
            insert(dataList, item, dataList.size());
        }

        public void append(List<String> items) {
            int pos = dataList.size();
            for (String item : items) {
                    dataList.add(item);
            }
            notifyItemRangeInserted(pos, items.size());
        }

        public void remove(int position) {
            remove(dataList, position);
        }

        public void clear() {
            clear(dataList);
        }
    }


    public class SimpleViewHolder extends BaseViewHolder implements View.OnClickListener {
        TextView itemText;
        Activity activity;
        public SimpleViewHolder(View itemView,Activity activity,boolean skip) {
            super(itemView);
            if (skip)
                return;
            this.activity = activity;
            itemText = (TextView)(itemView.findViewById(R.id.recycler_text));
            itemText.setOnClickListener(this);

        }
        String mStr;
        public void bindData(String str){
            mStr = str;
            if (!TextUtils.isEmpty(str))
                itemText.setText(str);
        }

        @Override
        public void onClick(View v) {
            if (!TextUtils.isEmpty(mStr))
                Toast.makeText(activity,mStr,Toast.LENGTH_SHORT).show();
        }
    }
}
