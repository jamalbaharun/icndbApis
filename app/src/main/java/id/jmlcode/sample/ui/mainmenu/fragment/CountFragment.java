package id.jmlcode.sample.ui.mainmenu.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import id.jmlcode.sample.R;
import id.jmlcode.sample.base.SimpleFragment;

public class CountFragment extends SimpleFragment {

    @BindView(R.id.tv_count)
    TextView tvCount;

    @BindView(R.id.btn_add)
    Button btnAdd;


    @BindView(R.id.common_toolbar)
    Toolbar mtoolbar;

    int count = 1;

    public static CountFragment newInstance(int count){
        CountFragment fragment = new CountFragment();
        Bundle args = new Bundle();
        args.putInt("key", count);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_count;
    }

    @Override
    protected void initEventAndData() {
        initToolbar();

        if (getArguments() != null) {
            count = getArguments().getInt("key");
            count++;
        }

        tvCount.setText("this your " + count);


    }


    private void initToolbar() {
        mtoolbar.setTitle("Count on " + count);
    }

    @OnClick(R.id.btn_add)
    public void onAddFragment(){
        start(CountFragment.newInstance(count));
//        Crashlytics.getInstance().crash(); // Force a crash

    }
}
