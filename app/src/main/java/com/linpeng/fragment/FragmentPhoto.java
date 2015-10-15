package com.linpeng.fragment;

import java.util.List;

import com.linpeng.baidunewssearch.NewsDetailsActivity;
import com.linpeng.baidunewssearch.R;
import com.linpeng.domain.Photo;
import com.linpeng.netservice.GetPhotoService;
import cpm.linpeng.adapter.PhotoListViewAdapter;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class FragmentPhoto extends Fragment implements OnScrollListener,OnItemClickListener{

	private PhotoListViewAdapter adapter;
	private boolean isLoading = true;
	private int page = 1;
	private Activity activity;

	@Override
	public View onCreateView(LayoutInflater inflater,
							 @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_photo, container, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		activity = getActivity();
		ListView listview = (ListView) activity.findViewById(R.id.fragment_photo_listview);
		adapter = new PhotoListViewAdapter(activity,null);
		listview.addFooterView(View.inflate(activity, R.layout.foot, null));
		listview.setAdapter(adapter);
		listview.setOnScrollListener(this);
		new MyAsyncTashGetPhoto().execute(0);
		listview.setOnItemClickListener(this);
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Toast.makeText(activity, "你点击了item", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(activity, NewsDetailsActivity.class);
		intent.putExtra("aid", adapter.getPhotos().get(position).getAid());
		intent.putExtra("name", "gxtp");
		intent.putExtra("title", adapter.getPhotos().get(position).getTitle());
		intent.putExtra("username", adapter.getPhotos().get(position).getUserName());
		intent.putExtra("dateline", adapter.getPhotos().get(position).getDateline());
		startActivity(intent);
	}

	/**
	 * 异步获取图片集合类
	 * @author linpeng123l
	 *
	 */
	public class MyAsyncTashGetPhoto extends AsyncTask<Integer, String, List<Photo>>{
		@Override
		protected List<Photo> doInBackground(Integer... params) {
			List<Photo> photos = GetPhotoService.getPhotosByPage(page);
			Log.i("info", "FragmentPhoto中照片的个数=" + photos.size() + "");
			return photos;
		}
		@Override
		protected void onPostExecute(List<Photo> photos) {
			isLoading = false;
			if(photos!=null && photos.size()>0){
				adapter.addPhotos(photos);
				adapter.notifyDataSetChanged();
				page ++ ;
			}
			super.onPostExecute(photos);
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
	}
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
						 int visibleItemCount, int totalItemCount) {
		if(totalItemCount <= firstVisibleItem+visibleItemCount+1&&!isLoading){
			isLoading = true;
			new MyAsyncTashGetPhoto().execute(page);
		}
	}

}
