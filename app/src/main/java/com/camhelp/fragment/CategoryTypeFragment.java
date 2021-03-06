package com.camhelp.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.camhelp.R;
import com.camhelp.activity.CategoryEachActivity;
import com.camhelp.common.CommonGlobal;
import com.camhelp.utils.MyProcessDialog;

/**
 * 选择类型fragment
 */
public class CategoryTypeFragment extends Fragment implements View.OnClickListener{

    private String TAG = "CategoryTypeFragment";
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private String colorPrimary, colorPrimaryBlew, colorPrimaryDark, colorAccent;

    private FrameLayout fl_category_top;
    private LinearLayout ll_base_categoryType;
    private LinearLayout ll_categoryType_focus,ll_categoryType_new,ll_categoryType_hot,
            ll_categoryType_fresh,ll_categoryType_party,ll_categoryType_lose,
            ll_categoryType_pickup,ll_categoryType_problem,ll_categoryType_unburden;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    public CategoryTypeFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoryTypeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoryTypeFragment newInstance(String param1, String param2) {
        CategoryTypeFragment fragment = new CategoryTypeFragment();
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
        return inflater.inflate(R.layout.fragment_category_type02, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        initcolor();
        initview();
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

    public void initview(){
        fl_category_top = (FrameLayout) getActivity().findViewById(R.id.fl_category_top);
        ll_base_categoryType = (LinearLayout) getActivity().findViewById(R.id.ll_base_categoryType);
        fl_category_top.setBackgroundColor(Color.parseColor(colorPrimary));
        ll_base_categoryType.setBackgroundColor(Color.parseColor(colorPrimary));

        ll_categoryType_focus = (LinearLayout) getActivity().findViewById(R.id.ll_categoryType_focus);
        ll_categoryType_new = (LinearLayout) getActivity().findViewById(R.id.ll_categoryType_new);
        ll_categoryType_hot = (LinearLayout) getActivity().findViewById(R.id.ll_categoryType_hot);
        ll_categoryType_fresh = (LinearLayout) getActivity().findViewById(R.id.ll_categoryType_fresh);
        ll_categoryType_party = (LinearLayout) getActivity().findViewById(R.id.ll_categoryType_party);
        ll_categoryType_lose = (LinearLayout) getActivity().findViewById(R.id.ll_categoryType_lose);
        ll_categoryType_pickup = (LinearLayout) getActivity().findViewById(R.id.ll_categoryType_pickup);
        ll_categoryType_problem = (LinearLayout) getActivity().findViewById(R.id.ll_categoryType_problem);
        ll_categoryType_unburden = (LinearLayout) getActivity().findViewById(R.id.ll_categoryType_unburden);

        ll_categoryType_focus.setOnClickListener(this);
        ll_categoryType_new.setOnClickListener(this);
        ll_categoryType_hot.setOnClickListener(this);
        ll_categoryType_fresh.setOnClickListener(this);
        ll_categoryType_party.setOnClickListener(this);
        ll_categoryType_lose.setOnClickListener(this);
        ll_categoryType_pickup.setOnClickListener(this);
        ll_categoryType_problem.setOnClickListener(this);
        ll_categoryType_unburden.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_categoryType_focus:
                Intent intentFocus = new Intent(getActivity(), CategoryEachActivity.class);
                intentFocus.putExtra(CommonGlobal.categoryType,CommonGlobal.sCategoryType_focus);
                startActivity(intentFocus);
                break;
            case R.id.ll_categoryType_new:
                Intent intentNew = new Intent(getActivity(), CategoryEachActivity.class);
                intentNew.putExtra(CommonGlobal.categoryType,CommonGlobal.sCategoryType_new);
                startActivity(intentNew);
                break;
            case R.id.ll_categoryType_hot:
                Intent intentHot = new Intent(getActivity(), CategoryEachActivity.class);
                intentHot.putExtra(CommonGlobal.categoryType,CommonGlobal.sCategoryType_hot);
                startActivity(intentHot);
                break;
            case R.id.ll_categoryType_fresh:
                Intent intentFresh = new Intent(getActivity(), CategoryEachActivity.class);
                intentFresh.putExtra(CommonGlobal.categoryType,CommonGlobal.sCategoryType_fresh);
                startActivity(intentFresh);
                break;
            case R.id.ll_categoryType_party:
                Intent intentParty = new Intent(getActivity(), CategoryEachActivity.class);
                intentParty.putExtra(CommonGlobal.categoryType,CommonGlobal.sCategoryType_experience);
                startActivity(intentParty);
                break;
            case R.id.ll_categoryType_lose:
                Intent intentLose = new Intent(getActivity(), CategoryEachActivity.class);
                intentLose.putExtra(CommonGlobal.categoryType,CommonGlobal.sCategoryType_lose);
                startActivity(intentLose);
                break;
            case R.id.ll_categoryType_pickup:
                Intent intentPickup = new Intent(getActivity(), CategoryEachActivity.class);
                intentPickup.putExtra(CommonGlobal.categoryType,CommonGlobal.sCategoryType_pickup);
                startActivity(intentPickup);
                break;
            case R.id.ll_categoryType_problem:
                Intent intentProblem = new Intent(getActivity(), CategoryEachActivity.class);
                intentProblem.putExtra(CommonGlobal.categoryType,CommonGlobal.sCategoryType_problem);
                startActivity(intentProblem);
                break;
            case R.id.ll_categoryType_unburden:
                Intent intentUnburden = new Intent(getActivity(), CategoryEachActivity.class);
                intentUnburden.putExtra(CommonGlobal.categoryType,CommonGlobal.sCategoryType_unburden);
                startActivity(intentUnburden);
                break;
        }
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
