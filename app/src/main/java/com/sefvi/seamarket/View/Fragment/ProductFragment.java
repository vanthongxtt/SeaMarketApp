package com.sefvi.seamarket.View.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;
import com.sefvi.seamarket.R;
import com.sefvi.seamarket.View.Fragment.TabLayoutProduc.ProducClamFragment;
import com.sefvi.seamarket.View.Fragment.TabLayoutProduc.ProducFishFragment;
import com.sefvi.seamarket.View.Fragment.TabLayoutProduc.ProducShrimpFragment;
import com.sefvi.seamarket.View.Fragment.TabLayoutProduc.ProducSnailsFragment;
import com.sefvi.seamarket.View.Fragment.TabLayoutProduc.ProductCrabFragment;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private ProducFishFragment producFishFragment;
    private ProductCrabFragment productCrabFragment;
    private ProducShrimpFragment producShrimpFragment;
    private ProducClamFragment producClamFragment;
    private ProducSnailsFragment producSnailsFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View v   =   inflater.inflate(R.layout.fragment_product,null);


        viewPager = v.findViewById(R.id.product_view_pager);
        tabLayout = v.findViewById(R.id.product_tab_layout);

        producFishFragment = new ProducFishFragment();
        productCrabFragment = new ProductCrabFragment();
        producShrimpFragment = new ProducShrimpFragment();
        producClamFragment = new ProducClamFragment();
        producSnailsFragment = new ProducSnailsFragment();

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);


        tabLayout.getTabAt(0).setIcon(R.drawable.fish);
        tabLayout.getTabAt(1).setIcon(R.drawable.crab);
        tabLayout.getTabAt(2).setIcon(R.drawable.shrimp);
        tabLayout.getTabAt(3).setIcon(R.drawable.clam);
        tabLayout.getTabAt(4).setIcon(R.drawable.snail);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        return  v;
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return  new ProducFishFragment();
                case 1:
                    return new ProductCrabFragment();
                case 2:
                    return new  ProducShrimpFragment();
                case  3:
                    return new ProducClamFragment();
                case  4:
                    return new ProducSnailsFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 5;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getText(R.string.text_product_ca);
                case 1:
                    return getText(R.string.text_product_cua);
                case 2:
                    return getText(R.string.text_product_tom);
                case 3:
                    return getText(R.string.text_product_so);
                case 4:
                    return getText(R.string.text_product_oc);
                default:
                    return null;
            }
        }
    }
//    private class ViewPagerAdapter extends FragmentPagerAdapter {
//
//        private List<Fragment> fragments = new ArrayList<>();
//        private List<String> fragmentTitle = new ArrayList<>();
//
//        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
//            super(fm, behavior);
//        }
//
//        public void addFragment(Fragment fragment, String title) {
//            fragments.add(fragment);
//            fragmentTitle.add(title);
//        }
//
//        @NonNull
//        @Override
//        public Fragment getItem(int position) {
//            return fragments.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return 5;
//        }
//
//        @Nullable
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return fragmentTitle.get(position);
//        }
//    }
}
