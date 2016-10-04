package id.web.hn.imagesliderviewpager;

import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager mPager;
    private ImageFragmentAdapter mAdapter;
    private int size;
    private LinearLayout pager_indicator;
    ImageView imgDot[];


    Toolbar toolbar;

    //untuk play otomatis
    Timer timer;
    int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //jumlah gambar
        size = 5;

        pager_indicator = (LinearLayout) findViewById(R.id.ll_dot);

        //list image
        int[] imgViewList;

        String[] imageList = new String[size];
        for(int i = 0; i<size;i++){
//            if(i % 2 == 0) {
//                imageList[i] = "slide-bg.jpg";
//            } else {
//                imageList[i] = "slide-2.jpg";
//            }
            imageList[i] = "p"+i;
//            imgViewList[i] = R.drawable.p1;
        }

        imgViewList = new int[]{
                R.drawable.p1,
                R.drawable.p2,
                R.drawable.p3,
                R.drawable.p4,
                R.drawable.p0,
        };

        mAdapter = new ImageFragmentAdapter(getSupportFragmentManager(), size, imgViewList);
        mPager = (ViewPager) findViewById(R.id.viewpager);
        mPager.setAdapter(mAdapter);
        mPager.addOnPageChangeListener(this);
        //pindahkan page tiap x detik
        pageSwitcher(2);

        //ubah posisi dot
        setUiPageWiewController();



    }



    private void setUiPageWiewController() {
        imgDot = new ImageView[size+1];

        for (int i = 0; i < size; i++){
            imgDot[i] = new ImageView(this);
            imgDot[i].setImageResource(R.drawable.dot_not_selected);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);
            pager_indicator.addView(imgDot[i], params);
        }
        imgDot[0].setImageResource(R.drawable.dot_selected);
    }

    private void pageSwitcher(int detik) {
        timer = new Timer();
        timer.scheduleAtFixedRate(new ImageTask(), 0, detik*1000);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
//        timer.cancel();

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //inner class untuk timer gambar

    class ImageTask extends TimerTask {

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(page >= size){
                        page = 0;
                        mPager.setCurrentItem(0, true);
                        imgDot[page].setImageResource(R.drawable.dot_selected);
                        imgDot[size-1].setImageResource(R.drawable.dot_not_selected);
                    } else {
                        int p = page++;
                        mPager.setCurrentItem(p,true);
                        imgDot[p].setImageResource(R.drawable.dot_selected);
                        if(p>0){
                            imgDot[p-1].setImageResource(R.drawable.dot_not_selected);
                        }
                    }
                }
            });
        }
    }
}
