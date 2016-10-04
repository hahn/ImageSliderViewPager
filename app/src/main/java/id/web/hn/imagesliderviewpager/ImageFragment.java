package id.web.hn.imagesliderviewpager;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends Fragment {


    private ImageView img;
    String urlImage;
    int urlImg;
    private static final String URL_IMG = "urlImg";

    public ImageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        urlImage = getArguments().getString(URL_IMG);
        urlImg = getArguments().getInt(URL_IMG);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_image, container, false);
        img = (ImageView) rootView.findViewById(R.id.img_vp);

        Picasso.with(getContext())
                .load(urlImg)
                .error(R.mipmap.ic_launcher)
                .into(img);

        return rootView;
    }

    public static Fragment newInstance(int url) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putInt(URL_IMG, url);
        fragment.setArguments(args);

        return fragment;
    }

}
