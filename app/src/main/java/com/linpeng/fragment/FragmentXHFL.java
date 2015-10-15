package com.linpeng.fragment;


import com.linpeng.baidunewssearch.R;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import cpm.linpeng.adapter.XHFLListViewAdapter;

public class FragmentXHFL extends Fragment implements AdapterView.OnItemClickListener{

	private ListView listView;
	private Activity activity;
	private String[] xhflArray=new String[]{"爆笑男女","冷笑话","职场笑话","短信笑话","交通笑话","校园笑话","家庭笑话","综合笑话"};
	@Override
	public View onCreateView(LayoutInflater inflater,
							 @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_xhfl, container, false);
		this.activity = getActivity();
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		initView();
		setListener();
		getDate();
		setAdapter();
		super.onActivityCreated(savedInstanceState);
	}

	private void initView() {
		listView = (ListView)activity.findViewById(R.id.fragment_xhfl_listview);
	}

	private void setListener() {
		listView.setOnItemClickListener(this);
	}

	private void setAdapter() {
		XHFLListViewAdapter adapter = new XHFLListViewAdapter(activity,xhflArray);
		listView.setAdapter(adapter);
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

	}

	public void getDate() {

	}
}
