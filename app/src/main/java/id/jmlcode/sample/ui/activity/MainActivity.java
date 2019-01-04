package id.jmlcode.sample.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import id.jmlcode.sample.R;
import id.jmlcode.sample.base.SimpleActivity;
import id.jmlcode.sample.model.bean.Header;
import id.jmlcode.sample.ui.dialogfragment.ExampleDialogFragment;
import id.jmlcode.sample.ui.dialogfragment.service.ClickView;

public class MainActivity extends SimpleActivity {
    @BindView(R.id.common_toolbar)Toolbar mToolbar;
    @BindView(R.id.tv_hello)TextView tvHello;
    Header header = new Header();

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setToolBar(mToolbar,"Main", false, false);
        tvHello.setText("Check Connection");
    }


    @OnClick(R.id.tv_hello)
    public void onClickTvHello(){
        Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
        //intent.putExtra("datas", header);
        startActivity(intent);
    }

    @OnClick(R.id.cs_main)
    public void onClickCsMain(){
        ExampleDialogFragment conversationScheduler = new ExampleDialogFragment();
        conversationScheduler.setScheduleOption(tvHello);
        conversationScheduler.setCancelable(false);
        conversationScheduler.show(getSupportFragmentManager(), "conversationScheduler");
        conversationScheduler.setClickView(new ClickView() {
            @Override
            public void onClickView(Header header1) {
                header = header1;
            }
        });
    }



    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch  (item.getItemId()) {
            case  R.id.action_setting:
                break;
            case  R.id.action_setting1:
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

}
