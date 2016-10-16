package br.com.livroandroid.carrosfinal.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import br.com.livroandroid.carrosfinal.Fragments.CarrosFragment;
import br.com.livroandroid.carrosfinal.R;

/**
 * Created by Rrafael on 14/10/2016.
 */

public class TabAdapter extends FragmentPagerAdapter {
    private Context context;

    public TabAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return 3;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return context.getString(R.string.classicos);
        } else if (position == 1) {
            return context.getString(R.string.esportivos);
        }
        return context.getString(R.string.luxo);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = null;
        if (position == 0) {
            f = CarrosFragment.newInstance(R.string.classicos);
        } else if (position == 1) {
            f = CarrosFragment.newInstance(R.string.esportivos);
        } else {
            f = CarrosFragment.newInstance(R.string.luxo);
        }
        return f;
    }
}
