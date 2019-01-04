package id.jmlcode.sample.ui.mainmenu.fragment;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import butterknife.BindView;
import id.jmlcode.sample.R;
import id.jmlcode.sample.base.SimpleFragment;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragment;

public class HomeFragment extends SimpleFragment {

    @BindView(R.id.drawer_layout) DrawerLayout mDrawer;
    @BindView(R.id.nav_view) NavigationView mNavigationView;
    @BindView(R.id.toolbar) Toolbar toolbar;


    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initEventAndData() {
        initNavigationMenu();

//        String registrationId = FirebaseInstanceId.getInstance().getToken();
//        Log.v("FIREBASE MESSAGE :", registrationId);


    }



    private void initNavigationMenu() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(mActivity, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(final MenuItem item) {
                int id = item.getItemId();

                final ISupportFragment topFragment = getTopFragment();
                SupportFragment myHome = (SupportFragment) topFragment;

                if (id == R.id.nav_home) {

                    HomeFragment fragment = findFragment(HomeFragment.class);
                    Bundle newBundle = new Bundle();
                    newBundle.putString("from", "From:" + topFragment.getClass().getSimpleName());
                    fragment.putNewBundle(newBundle);

                    myHome.start(fragment, SupportFragment.SINGLETASK);
                }else if (id == R.id.nav_settings) {
                    CountFragment fragment = findFragment(CountFragment.class);
                    if (fragment == null) {
                        myHome.startWithPopTo(CountFragment.newInstance(1), HomeFragment.class, false);
                    } else {
                        // 如果已经在栈内,则以SingleTask模式start
                        myHome.start(fragment, SupportFragment.SINGLETASK);
                    }
                }
                Toast.makeText(mActivity, item.getTitle() + " Selected", Toast.LENGTH_SHORT).show();
                mDrawer.closeDrawers();
                return true;
            }
        });

        // open drawer at start
        mDrawer.openDrawer(GravityCompat.START);

        toolbar.setTitle("Home");
    }
}
