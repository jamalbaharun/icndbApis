package id.jmlcode.sample.ui.icndb;

import android.content.DialogInterface;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import id.jmlcode.sample.R;
import id.jmlcode.sample.base.BaseActivity;
import id.jmlcode.sample.contract.IcndbContract;
import id.jmlcode.sample.model.bean.IcndbBean;
import id.jmlcode.sample.model.bean.Joke;
import id.jmlcode.sample.presenter.IcndbPresenter;

public class IcndbActivity extends BaseActivity<IcndbPresenter> implements IcndbContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_main)
    RecyclerView rvMain;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    int count;

    private List<Joke> mList = new ArrayList<>();
    private JokesAdapter mAdapter;


    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_icndb;
    }

    @Override
    protected void initEventAndData() {
        count = 3;

        loadData();
        swipeRefresh.setOnRefreshListener(new SwipeError());

        mAdapter = new JokesAdapter(mContext, mList);
        rvMain.setLayoutManager(new LinearLayoutManager(mContext));
        rvMain.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new JokesAdapter.OnItemClickListener() {

            @Override
            public void onClick(Joke data, boolean isOntop) {
                if (isOntop){
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(mContext);

                    builder.setMessage("Hello World!");
                    String positiveText = "Hello too!";
                    builder.setPositiveButton(positiveText,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });

                    builder.setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });

                    android.app.AlertDialog dialog1 = builder.create();
                    dialog1.show();

                }else {
                    mList.remove(data);
                    mList.add(0, data);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public void showResult(IcndbBean response) {
        if (response.getType().equals("success")){
            mList.clear();
            mList.addAll(response.getValue());
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showLoading() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefresh.setRefreshing(false);
    }

    @OnClick(R.id.tv_more)
    void onMoreClick(){
        count = 2 * count;
        mPresenter.getJokes(count);
    }


    private class SwipeError implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            loadData();
        }
    }

    private void loadData() {
        mPresenter.getJokes(3);
    }
}
