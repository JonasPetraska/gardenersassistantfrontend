package com.psi.gardenerasistance;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SettingsFragment extends Fragment {
    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savingInstance) {
        myView = inflater.inflate(R.layout.fragment_settings, container, false);
        this.getActivity().setTitle(R.string.settings);

        return myView;
    }
}