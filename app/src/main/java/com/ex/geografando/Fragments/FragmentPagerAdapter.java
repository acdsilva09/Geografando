package com.ex.geografando.Fragments;



import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


public class FragmentPagerAdapter extends  androidx.fragment.app.FragmentPagerAdapter {


    private static final int count=3;

    public FragmentPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return com.ex.geografando.Fragments.EstatisticaGeral.newInstance();
            case 1:
                return com.ex.geografando.Fragments.EstatisticaCategorias.newInstance();
            default:
                return EstatisticaRecords.newInstance();

        }

    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public CharSequence getPageTitle(int position){
        if(position==0){
            return "GERAL";
        }
        if(position==1){
            return "CATEGORIAS";
        }
        return "PARTIDAS";
    }
}
