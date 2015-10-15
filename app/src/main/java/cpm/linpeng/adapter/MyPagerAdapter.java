package cpm.linpeng.adapter;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyl on 15/10/13.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {


    private List<Fragment> fragments = new ArrayList<Fragment>();

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    public MyPagerAdapter(FragmentManager fragmentManager,
                          ArrayList<Fragment> fragments){
        super(fragmentManager);this.fragments = fragments;
    }



    @Override
    public Fragment getItem(int index) {
        return fragments.get(index);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
