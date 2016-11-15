package br.com.livroandroid.carrosfinal.Fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.livroandroid.carrosfinal.Adapter.TabAdapter;
import br.com.livroandroid.carrosfinal.R;
import livroandroid.lib.utils.Prefs;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarrosTabFragment extends Fragment {


    public CarrosTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_carros_tab, container, false);
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(new TabAdapter(getContext(),getChildFragmentManager()));
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        int cor = ContextCompat.getColor(getContext(),R.color.white);
        tabLayout.setTabTextColors(cor,cor);
        int tabIdx = Prefs.getInteger(getContext(),"tabIdx");
        viewPager.setCurrentItem(tabIdx);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            @Override
            public void onPageSelected(int position) {
                    Prefs.setInteger(getContext(),"tabIdx", viewPager.getCurrentItem());
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
        return view;
    }

}
