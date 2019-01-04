package id.jmlcode.sample.ui.mainmenu.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import id.jmlcode.sample.R;
import id.jmlcode.sample.base.SimpleActivity;
import id.jmlcode.sample.ui.mainmenu.fragment.CountFragment;
import id.jmlcode.sample.ui.mainmenu.fragment.HomeFragment;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;


public class MenuDrawer extends SimpleActivity {


    @BindView(R.id.drawer_layout) DrawerLayout mDrawer;
    @BindView(R.id.nav_view) NavigationView mNavigationView;
    @BindView(R.id.toolbar) Toolbar toolbar;
    private ActionBar actionBar;


    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;


    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_menu_drawer;
    }

    @Override
    protected void initEventAndData() {

        SupportFragment fragment = findFragment(HomeFragment.class);
        if (fragment == null) {
            loadRootFragment(R.id.fl_first_container, HomeFragment.newInstance());
        }
        initToolbar();
        initNavigationMenu();

    }


    @Override
    public FragmentAnimator onCreateFragmentAnimator() {

        // 设置默认Fragment动画  默认竖向(和安卓5.0以上的动画相同)
//        return super.onCreateFragmentAnimator();
        // 设置横向(和安卓4.x动画相同)
        return new DefaultHorizontalAnimator();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("Home Fragment");
    }

    private void initNavigationMenu() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
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
                Toast.makeText(getApplicationContext(), item.getTitle() + " Selected", Toast.LENGTH_SHORT).show();
                mDrawer.closeDrawers();
                return true;
            }
        });

        // open drawer at start
        mDrawer.openDrawer(GravityCompat.START);
    }


    @Override
    public void onBackPressedSupport() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            ISupportFragment topFragment = getTopFragment();

            // 主页的Fragment
            if (topFragment instanceof HomeFragment) {
                mNavigationView.setCheckedItem(R.id.nav_home);
            }

            if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                pop();
            } else {
                if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
                    finish();
                } else {
                    TOUCH_TIME = System.currentTimeMillis();
                    Toast.makeText(this, R.string.press_again_to_exit, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
