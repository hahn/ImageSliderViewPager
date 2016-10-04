package id.web.hn.imagesliderviewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by hahn on 02/10/16.
 */

public class ImageFragmentAdapter extends FragmentStatePagerAdapter {

    private int mSize;
    private int[] mImgList;
//    private String BASE_URL = "http://10.0.3.2/tesgambar/";

    public ImageFragmentAdapter(FragmentManager fm, int size, int[] imgList) {
        super(fm);
        mSize = size;
        mImgList = imgList;
    }

    @Override
    public Fragment getItem(int position) {
        int img = mImgList[position];
        return ImageFragment.newInstance(img);
    }

    @Override
    public int getCount() {
        return mSize;
    }


}
