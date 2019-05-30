package com.psi.gardenerasistance;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

public class PermissionsFragment extends Fragment {
    View myView;
    private static PermissionsListViewAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savingInstance)
    {
        myView = inflater.inflate(R.layout.fragment_permissions, container, false);
        this.getActivity().setTitle(R.string.permissions);

        ListView listView = myView.findViewById(R.id.permissionsListView);

        // Replace with call to the database
        adapter = new PermissionsListViewAdapter(GetPermissionUsers(), this.getActivity());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fragmentManager = getFragmentManager();
                PermissionViewModel item = (PermissionViewModel) parent.getItemAtPosition(position);

                PermissionDetailFragment fragment = new PermissionDetailFragment();

                Bundle args = new Bundle();
                args.putSerializable("PermissionUser", item);

                fragment.setArguments(args);

                fragmentManager.beginTransaction()
                        .replace(R.id.drawer_content, fragment)
                        .commit();
            }
        });

        return myView;
    }

    private ArrayList<PermissionViewModel> GetPermissionUsers()
    {
        ArrayList<PermissionViewModel> list = new ArrayList<>();
        ArrayList<String> firstNameDummyArray = new ArrayList<String>();
        ArrayList<String> lastNameDummyArray = new ArrayList<String>();
        ArrayList<ArrayList<String>> permissionsDummyArray = new ArrayList<>();


        //First name dummy array filling
        firstNameDummyArray.add("Jonas");
        firstNameDummyArray.add("Petras");
        firstNameDummyArray.add("Edvinas");
        firstNameDummyArray.add("Kristijonas");

        //Last name dummy array filling
        lastNameDummyArray.add("Petrauskas");
        lastNameDummyArray.add("Jonynas");
        lastNameDummyArray.add("Kairys");
        lastNameDummyArray.add("Dainys");

        //Permissions dummy array filling
        ArrayList<String> dummy1 = new ArrayList<String>();
        dummy1.add("Peržiūrėti sodą");
        dummy1.add("Redaguoti sodą");
        dummy1.add("Ištrinti sodą");


        ArrayList<String> dummy2 = new ArrayList<String>();
        dummy2.add("Peržiūrėti darbuotojus");
        dummy2.add("Pridėti darbuotojus");
        dummy2.add("Redaguoti darbuotojus");
        dummy2.add("Ištrinti darbuotojus");

        ArrayList<String> dummy3 = new ArrayList<String>();
        dummy3.add("Peržiūrėti techniką");
        dummy3.add("Pridėti techniką");
        dummy3.add("Redaguoti techniką");
        dummy3.add("Ištrinti techniką");

        ArrayList<String> dummy4 = new ArrayList<String>();
        dummy4.add("Peržiūrėti jutiklius");
        dummy4.add("Pridėti jutiklius");
        dummy4.add("Redaguoti jutiklius");
        dummy4.add("Ištrinti jutiklius");

        ArrayList<String> dummy5 = new ArrayList<String>();
        dummy5.add("Peržiūrėti žurnalą");
        dummy5.add("Redaguoti žurnalą");

        ArrayList<String> dummyCombined = new ArrayList<String>();
        dummyCombined.addAll(dummy1);
        dummyCombined.addAll(dummy2);
        dummyCombined.addAll(dummy3);
        dummyCombined.addAll(dummy4);
        dummyCombined.addAll(dummy5);

        for(int i = 1; i <= 10; i++)
            list.add(new PermissionViewModel(firstNameDummyArray.get(new Random().nextInt(firstNameDummyArray.size())), lastNameDummyArray.get(new Random().nextInt(firstNameDummyArray.size())), dummyCombined));

        return list;
    }
}
