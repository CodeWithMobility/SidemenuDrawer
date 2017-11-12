package com.mobiledev.pushmenu.fragments;

/**
 * Created by Ravi on 29/07/15.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobiledev.pushmenu.MainActivity;
import com.mobiledev.pushmenu.R;


public class FragmentHome extends Fragment {
    MainActivity mActivity;
   @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity)getActivity();
     }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mActivity.updateTitle(getString(R.string.title_home));
    }
}
