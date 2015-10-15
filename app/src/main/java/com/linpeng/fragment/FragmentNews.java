package com.linpeng.fragment;

import java.util.List;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.linpeng.baidunewssearch.R;
import com.linpeng.baidunewssearch.NewsDetailsActivity;
import com.linpeng.domain.News;
import com.linpeng.netservice.GetNewsService;

import cpm.linpeng.adapter.NewsListViewAdapter;

public class FragmentNews extends Fragment implements OnItemClickListener,OnScrollListener{

	private NewsListViewAdapter adapter;
	private ListView listView;
	private boolean isLoading = true;;
	private Activity activity;
	private int page = 1;//初始第一页


	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_news, container, false);
		this.activity = getActivity();
		return view;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		listView = (ListView)activity.findViewById(R.id.fragment_news_listview);
		adapter = new NewsListViewAdapter(activity, null);
		listView.addFooterView(View.inflate(activity, R.layout.foot, null));
		listView.setAdapter(adapter);
		listView.setOnScrollListener(this);
		listView.setOnItemClickListener(this);
		new MyAsyncTaskGetNews().execute(1);
		super.onActivityCreated(savedInstanceState);
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
							long id) {
		if(view.getId()!=R.id.foot_view){
			Intent intent = new Intent(activity, NewsDetailsActivity.class);
			intent.putExtra("aid", adapter.getNewss().get(position).getUrl());
			intent.putExtra("name", "zxxh");
			intent.putExtra("title", adapter.getNewss().get(position).getTitle());
			intent.putExtra("summary", adapter.getNewss().get(position).getSummary());
			intent.putExtra("username", adapter.getNewss().get(position).getSource());
			intent.putExtra("dateline", adapter.getNewss().get(position).getDate());
			startActivity(intent);
		}
	}


	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
	}

	/**
	 * 滑动到底时自动加载更多
	 */
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
						 int visibleItemCount, int totalItemCount) {
		if(totalItemCount <= firstVisibleItem+visibleItemCount+1&&isLoading==false){
			isLoading = true;
			page++;
			new MyAsyncTaskGetNews().execute(page);
		}
	}

	/**
	 * 异步获取新闻列表集合
	 * @author linpeng123l
	 *
	 */
	public class MyAsyncTaskGetNews extends AsyncTask<Integer, String, List<News>>{
		@Override
		protected List<News> doInBackground(Integer... pages) {
			List<News> newss = GetNewsService.getNewsByPage(pages[0]);
			Log.i("info","MyAsyncTaskGetNews中新闻个数"+newss.size()+"");
			return newss;
		}
		@Override
		protected void onPostExecute(List<News> newss) {
			if(newss.size()>0){
				adapter.addNews(newss);
				adapter.notifyDataSetChanged();
			}
			isLoading = false;
		}

	}

}
