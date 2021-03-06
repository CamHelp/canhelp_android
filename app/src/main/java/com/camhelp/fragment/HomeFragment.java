package com.camhelp.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.camhelp.R;
import com.camhelp.common.CommonGlobal;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 * <p>
 * 首页fragment
 */
public class HomeFragment extends Fragment implements View.OnClickListener{

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private String colorPrimary, colorPrimaryBlew, colorPrimaryDark, colorAccent;
    LinearLayout ll_base_home;
    HomeNewFragment tab01;
    HomeFocusFragment tab02;

    private LinearLayout ll_home_toptab;
    public ViewPager mViewPager;
    private FragmentPagerAdapter mHomeAdapter;
    private List<Fragment> mDatas;

    private TextView tv_01, tv_02;
    private LinearLayout ll_tab1, ll_tab2;

    private ImageView mTabline,id_iv_tabline_new,id_iv_tabline_focus;
    private int mScreen1_3;

    private int mCurrentPageIndex;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        initcolor();
        initTabLine();
        initView();
    }


    /*获取主题色*/
    public void initcolor() {
        String defaultColorPrimary = "#" + Integer.toHexString(getResources().getColor(R.color.colorPrimary));
        String defaultColorPrimaryBlew = "#" + Integer.toHexString(getResources().getColor(R.color.colorPrimaryBlew));
        String defaultColorPrimaryDark = "#" + Integer.toHexString(getResources().getColor(R.color.colorPrimaryDark));
        String defaultColorAccent = "#" + Integer.toHexString(getResources().getColor(R.color.colorAccent));

        colorPrimary = pref.getString(CommonGlobal.colorPrimary, defaultColorPrimary);
        colorPrimaryBlew = pref.getString(CommonGlobal.colorPrimaryBlew, defaultColorPrimaryBlew);
        colorPrimaryDark = pref.getString(CommonGlobal.colorPrimaryDark, defaultColorPrimaryDark);
        colorAccent = pref.getString(CommonGlobal.colorAccent, defaultColorAccent);
    }

    private void initTabLine() {
        mTabline = (ImageView) getActivity().findViewById(R.id.id_iv_tabline);
        mTabline.setBackgroundColor(Color.parseColor(colorAccent));
        Display display = getActivity().getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        mScreen1_3 = outMetrics.widthPixels / 2;
        ViewGroup.LayoutParams lp = mTabline.getLayoutParams();
        lp.width = mScreen1_3;
        mTabline.setLayoutParams(lp);
        mTabline.setVisibility(View.GONE);
    }

    private void initView() {
        ll_base_home = (LinearLayout) getActivity().findViewById(R.id.ll_base_home);
        ll_base_home.setBackgroundColor(Color.parseColor(colorPrimary));
        id_iv_tabline_new = (ImageView) getActivity().findViewById(R.id.id_iv_tabline_new);
        id_iv_tabline_focus = (ImageView) getActivity().findViewById(R.id.id_iv_tabline_focus);
        id_iv_tabline_focus.setVisibility(View.GONE)
        ;
        ll_home_toptab = (LinearLayout) getActivity().findViewById(R.id.ll_home_toptab);
        ll_home_toptab.setBackgroundColor(Color.parseColor(colorPrimary));

        mViewPager = (ViewPager) getActivity().findViewById(R.id.viewpager_home);
        tv_01 = (TextView) getActivity().findViewById(R.id.id_tv_new);
        tv_02 = (TextView) getActivity().findViewById(R.id.id_tv_focus);
        tv_01.setTextColor(Color.WHITE);
        tv_02.setTextColor(Color.parseColor(colorAccent));

        ll_tab1 = (LinearLayout) getActivity().findViewById(R.id.ll_home_tab1);
        ll_tab2 = (LinearLayout) getActivity().findViewById(R.id.ll_home_tab2);

        ll_tab1.setOnClickListener(this);
        ll_tab2.setOnClickListener(this);

        mDatas = new ArrayList<Fragment>();

        tab01 = new HomeNewFragment();
        tab02 = new HomeFocusFragment();

        mDatas.add(tab01);
        mDatas.add(tab02);

        /**
         * 解决viewpager滑动数据未保存问题
         * blog地址：http://blog.csdn.net/w372426096/article/details/49951317
         * */
        mHomeAdapter = new FragmentPagerAdapter(this.getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                fragment = mDatas.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("id", "" + position);
                fragment.setArguments(bundle);
                return fragment;
            }

            @Override
            public int getCount() {
                return mDatas.size();
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                Fragment fragment = (Fragment) super.instantiateItem(container, position);
                getActivity().getSupportFragmentManager().beginTransaction().show(fragment).commit();
                return fragment;
            }

            @Override
            public int getItemPosition(Object object) {
                return super.getItemPosition(object);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                Fragment fragment = mDatas.get(position);
                getActivity().getSupportFragmentManager().beginTransaction().hide(fragment).commit();
            }

            @Override
            public Parcelable saveState() {
                return super.saveState();
            }
        };

        mViewPager.setAdapter(mHomeAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position) {
                    case 0:
                        tv_01.setTextColor(Color.WHITE);
                        id_iv_tabline_new.setVisibility(View.VISIBLE);
                        id_iv_tabline_focus.setVisibility(View.GONE);
                        break;
                    case 1:
                        tv_02.setTextColor(Color.WHITE);
                        id_iv_tabline_focus.setVisibility(View.VISIBLE);
                        id_iv_tabline_new.setVisibility(View.GONE);
                        break;
                }
                mCurrentPageIndex = position;
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPx) {
                Log.e("TAG", position + " , " + positionOffset + " , "
                        + positionOffsetPx);

                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabline
                        .getLayoutParams();

                if (mCurrentPageIndex == 0 && position == 0)// 0->1
                {
                    lp.leftMargin = (int) (positionOffset * mScreen1_3 + mCurrentPageIndex
                            * mScreen1_3);
                } else if (mCurrentPageIndex == 1 && position == 0)// 1->0
                {
                    lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_3 + (positionOffset - 1)
                            * mScreen1_3);
                } else if (mCurrentPageIndex == 1 && position == 1) // 滑动条位置纠正
                {
                    lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_3 + positionOffset
                            * mScreen1_3);
                }
                mTabline.setLayoutParams(lp);

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

    }

    protected void resetTextView() {
        tv_01.setTextColor(Color.parseColor(colorAccent));
        tv_02.setTextColor(Color.parseColor(colorAccent));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_tab1:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.ll_tab2:
                mViewPager.setCurrentItem(2);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        tab01.onActivityResult(requestCode, resultCode, data);
        tab02.onActivityResult(requestCode, resultCode, data);
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
