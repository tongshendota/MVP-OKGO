package com.example.pp03.peralppay.work.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import com.example.pp03.peralppay.work.fragment.OrderFragment;
import com.example.pp03.peralppay.work.fragment.ProductFragment;

/**
 * Created by meixx on 2017/3/25.
 */

public class HomeFragmentAdapter extends FragmentPagerAdapter {
    private FragmentTransaction mCurTransaction;
    private FragmentManager mFragmentManager;
    protected Fragment mCurrentPrimaryItem;
    public HomeFragmentAdapter(FragmentManager fm) {
        super(fm);
        mFragmentManager = fm;
    }

    @Override
    public Fragment getItem(int position) {
        return initHomeFragment(position);
    }

    private Fragment initHomeFragment(int position) {
        switch (position) {
            case 0:
                return new ProductFragment();
            case 1:
                return new OrderFragment();
            default:
                return null;
        }
        /*switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new ReportFragment();
            case 2:

            case 3:
                return new MineFragment();
            default:
                return null;
        }*/
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (mCurTransaction == null) {
            mCurTransaction = mFragmentManager.beginTransaction();
        }

        String fragmentName = "homefragement" + position;
        Fragment fragment = mFragmentManager.findFragmentByTag(fragmentName);
        if (fragment != null) {
            mCurTransaction.attach(fragment);
        } else {
            fragment = getItem(position);
            mCurTransaction.add(container.getId(), fragment, fragmentName);
        }
        if (fragment != mCurrentPrimaryItem) {
            fragment.setMenuVisibility(false);
            fragment.setUserVisibleHint(false);
        }
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (mCurTransaction == null) {
            mCurTransaction = mFragmentManager.beginTransaction();
        }
        mCurTransaction.detach((Fragment) object);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        Fragment fragment = (Fragment) object;
        if (fragment != mCurrentPrimaryItem) {
            if (mCurrentPrimaryItem != null) {
                if (mCurTransaction != null) {
                    mCurTransaction.hide(mCurrentPrimaryItem);
                }
            }
            if (fragment != null) {
                if (mCurTransaction != null) {
                    mCurTransaction.show(fragment);
                }
            }
            mCurrentPrimaryItem = fragment;
            if (mCurrentPrimaryItem instanceof OrderFragment){
                ((OrderFragment)mCurrentPrimaryItem).refreshData();
            }
            /*mCurrentPrimaryItem.clearUserChoose();
            mCurrentPrimaryItem.clearFilterState();
            refreshInstanceAgainstData(mCurrentPrimaryItem.getPlayMethodAllAgainstInfo());
            mCurrentPrimaryItem.refreshBtmTipMsg();*/
        }
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        if (mCurTransaction != null) {
            mCurTransaction.commitAllowingStateLoss();
            mCurTransaction = null;
            mFragmentManager.executePendingTransactions();
        }
    }

}
