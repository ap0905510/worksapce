package com.yw.stickydemo.view;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yw.stickydemo.R;
import com.yw.stickydemo.adapter.HeaderOperationAdapter;
import com.yw.stickydemo.model.OperationEntity;
import com.yw.stickydemo.util.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunfusheng on 16/4/20.
 */
public class HeaderOperationView extends AbsHeaderView<List<OperationEntity>> {

    @BindView(R.id.gv_operation)
    FixedGridView gvOperation;

    public HeaderOperationView(Activity context) {
        super(context);
    }

    @Override
    protected void getView(List<OperationEntity> list, ListView listView) {
        View view = mInflate.inflate(R.layout.header_operation_layout, listView, false);
        ButterKnife.bind(this, view);

        dealWithTheView(list);
        listView.addHeaderView(view);
    }

    private void dealWithTheView(List<OperationEntity> list) {
        if (list == null || list.size() < 2 || list.size() > 6) return;
        if (list.size()%2 != 0) return;

        final HeaderOperationAdapter adapter = new HeaderOperationAdapter(mActivity, list);
        gvOperation.setAdapter(adapter);

        gvOperation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.show(mActivity, adapter.getItem(position).getTitle());
            }
        });
    }

}
