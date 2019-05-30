package com.psi.gardenerasistance;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class PermissionDetailFragment extends Fragment
{

    View myView;
    private static PermissionDetailListViewAdapter adapter;
    private static PermissionViewModel model;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savingInstance)
    {
        myView = inflater.inflate(R.layout.fragment_permission_details, container, false);

        Bundle args = this.getArguments();
        model = (PermissionViewModel) args.getSerializable("PermissionUser");
        this.getActivity().setTitle(model.firstName + " " + model.lastName);
        ListView listView = myView.findViewById(R.id.permissionDetailsListView);

        // Replace with call to the database
        adapter = new PermissionDetailListViewAdapter(model.getPermissions(), this.getActivity());

        listView.setAdapter(adapter);

        return myView;
    }
}
