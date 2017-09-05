package com.lzy.headerviewpager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.headerviewpager.fragment.GridViewFragment;
import com.lzy.headerviewpager.fragment.ListViewFragment;
import com.lzy.headerviewpager.fragment.RecyclerViewFragment;
import com.lzy.headerviewpager.fragment.ScrollViewFragment;
import com.lzy.headerviewpager.fragment.WebViewFragment;
import com.lzy.headerviewpager.fragment.base.HeaderViewPagerFragment;
import com.lzy.headerviewpager.helper.HeaderScrollHelper;
import com.lzy.headerviewpager.helper.HeaderViewPager;

//import com.lzy.widget.tab.CircleIndicator;
//import com.lzy.widget.tab.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements HeaderScrollHelper.ScrollableContainer {

    //public List<HeaderViewPagerFragment> fragments;
    private HeaderViewPager scrollableLayout;
    //private ViewPager pagerHeader;
    //private View titleBar_Bg;
    //private TextView titleBar_title;
    //private View status_bar_fix;
    //private View titleBar;

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //内容的fragment
//        fragments = new ArrayList<>();
//        fragments.add(ScrollViewFragment.newInstance());
//        fragments.add(ListViewFragment.newInstance());
//        fragments.add(GridViewFragment.newInstance());
//        fragments.add(RecyclerViewFragment.newInstance());
//        fragments.add(WebViewFragment.newInstance());

        scrollableLayout = (HeaderViewPager) findViewById(R.id.scrollableLayout);
        webView = (WebView) findViewById(R.id.main_fragment);
       /* titleBar = findViewById(R.id.titleBar);
        titleBar_Bg = titleBar.findViewById(R.id.bg);
        //当状态栏透明后，内容布局会上移，这里使用一个和状态栏高度相同的view来修正内容区域
        status_bar_fix = titleBar.findViewById(R.id.status_bar_fix);
        status_bar_fix.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.getStatusHeight(this)));
        titleBar_title = (TextView) titleBar.findViewById(R.id.title);
        titleBar_Bg.setAlpha(0);
        status_bar_fix.setAlpha(0);
        titleBar_title.setText("标题栏透明度(0%)");*/
       /* pagerHeader = (ViewPager) findViewById(R.id.pagerHeader);
        pagerHeader.setAdapter(new HeaderAdapter());*/
        //CircleIndicator ci = (CircleIndicator) findViewById(R.id.ci);
       // ci.setViewPager(pagerHeader);

        //tab标签和内容viewpager
        //PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
//        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
//        viewPager.setAdapter(new ContentAdapter(getSupportFragmentManager()));
        //tabs.setViewPager(viewPager);
        scrollableLayout.setCurrentScrollableContainer(this);
        /*viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                scrollableLayout.setCurrentScrollableContainer(fragments.get(position));
            }
        });*/
        scrollableLayout.setOnScrollListener(new HeaderViewPager.OnScrollListener() {
            @Override
            public void onScroll(int currentY, int maxY) {

                //让头部具有差速动画,如果不需要,可以不用设置
                //pagerHeader.setTranslationY(currentY / 2);
                //动态改变标题栏的透明度,注意转化为浮点型
                /*float alpha = 1.0f * currentY / maxY;
                titleBar_Bg.setAlpha(alpha);
                //注意头部局的颜色也需要改变
                status_bar_fix.setAlpha(alpha);
                titleBar_title.setText("标题栏透明度(" + (int) (alpha * 100) + "%)");*/
            }
        });
//        viewPager.setCurrentItem(0);

        initView();
        webView.requestDisallowInterceptTouchEvent(true);


    }

    private void initView() {
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setDisplayZoomControls(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        if (isNetworkAvailable(this)) {
            webView.loadUrl("https://cpu.baidu.com/1021/d7ea1627");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    webView.setVisibility(View.VISIBLE);
                }
            }, 800);
        } else {
            webView.setVisibility(View.GONE);
        }
        //webView.loadUrl("https://cpu.baidu.com/1021/d7ea1627");

        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {  //表示按返回键
                        webView.goBack();   //后退
                        //webview.goForward();//前进
                        return true;    //已处理
                    }
                }
                return false;
            }
        });

        webView.requestDisallowInterceptTouchEvent(true);

    }

    /**
     * 描述：判断网络是否有效.
     *
     * @param context
     *            the context
     * @return true, if is network available
     */
    public static boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //当前窗口获取焦点时，才能正真拿到titlebar的高度，此时将需要固定的偏移量设置给scrollableLayout即可
        //scrollableLayout.setTopOffset(titleBar.getHeight());
    }

    @Override
    public View getScrollableView() {
        return webView;
    }

    /**
     * 内容页的适配器
     */
    /*private class ContentAdapter extends FragmentPagerAdapter {

        public ContentAdapter(FragmentManager fm) {
            super(fm);
        }

        public String[] titles = new String[]{*//*"ScrollView", "ListView", "GridView", "RecyclerView", "*//* "WebView"};

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
*/
    /**
     * 头布局的适配器
     */
//    private class HeaderAdapter extends PagerAdapter {
//
//        public int[] images = new int[]{//
//                                        R.mipmap.image1/*, R.mipmap.image2, R.mipmap.image3,//
//                                        R.mipmap.image4, R.mipmap.image5*/};
//
//        @Override
//        public Object instantiateItem(ViewGroup container, final int position) {
//            ImageView imageView = new ImageView(getApplicationContext());
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setImageResource(images[position]);
//            container.addView(imageView);
//            imageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(getApplicationContext(), "第" + position + "页", Toast.LENGTH_SHORT).show();
//                }
//            });
//            return imageView;
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView((View) object);
//        }
//
//        @Override
//        public int getCount() {
//            return images.length;
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view == object;
//        }
//    }
}
