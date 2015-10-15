package com.linpeng.baidunewssearch;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.linpeng.baidunewssearch.R;
import com.linpeng.netservice.GetNewsService;
import com.linpeng.netservice.GetPhotoService;
import com.linpeng.util.FileUtil;

public class NewsDetailsActivity extends Activity implements OnTouchListener{

	private float startX;
	private float startY;
	private RelativeLayout relativeLayout;
	private String summary;
	private String aid;
	private WebView testwebView;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_details);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		aid = getIntent().getStringExtra("aid");
		String title = getIntent().getStringExtra("title");
		summary = getIntent().getStringExtra("summary");
		String username = getIntent().getStringExtra("username");
		String dateline = getIntent().getStringExtra("dateline");
		String name = getIntent().getStringExtra("name");
		((TextView)findViewById(R.id.activity_news_details_title)).setText(title);//设置显示标题
		((TextView)findViewById(R.id.activity_news_details_auther)).setText(username+"|"+dateline);//设置显示发布者和时间
		relativeLayout = (RelativeLayout)findViewById(R.id.activity_news_details_relative_is_loading);
		testwebView=(WebView)findViewById(R.id.activity_news_details_webview);
		getDetailsDate(name,aid);
		testwebView.setWebViewClient(new MyWebViewClient());

	}
	@Override
	public boolean onCreatePanelMenu(int featureId, Menu menu) {
		getMenuInflater().inflate(R.menu.menu,menu);
		return true;
	}
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_share://分享按钮
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("text/plain");
				intent.putExtra(Intent.EXTRA_TEXT, summary+"http://www.qiangxie.cn/zz/index.php?class=body&name=zxxh&id="+aid);
				startActivity(Intent.createChooser(intent, getTitle()));
				break;

			default:
				break;
		}
		return super.onMenuItemSelected(featureId, item);
	}


	/**
	 * 获取 单条笑话的详细信息
	 */
	private void getDetailsDate(final String name,String aid) {

		new AsyncTask<String,Void,String>(){
			@Override
			protected String doInBackground(String... params) {
				String resultString=null;
				if("zxxh".endsWith(name)){
					resultString = GetNewsService.getJokyById(params[0]);
				}else if("gxtp".endsWith(name)){
					resultString = GetPhotoService.getphotoById(params[0]);
				}else if("xhds".endsWith(name)){
					resultString = GetNewsService.getJokyById(params[0]);
				}
				return resultString;
			}

			@Override
			protected void onPostExecute(String s) {
				Log.i("onPostExecute",s);
				testwebView.loadData(s, "text/html; charset=UTF-8", null);
			}
		}.execute(aid);


	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId()==android.R.id.home){
			finish();
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * javascript坚挺函数 
	 * @author linpeng123l
	 *
	 */
	final class InJavaScriptLocalObj{
		private boolean isLoaded = false;
		/**
		 * 将取得的html中不需要的内容去掉
		 * @param html
		 */
		@JavascriptInterface
		public void showSource(String html) {
			if(!isLoaded){
				Document document = Jsoup.parse(html);
				Elements elements = document.getElementsByClass("page-view-article");
				elements.remove(elements.select(".img-eye"));
				elements.select(".img-eye").remove();
				Message message = new Message();
				message.obj = document.head()+elements.toString();
				message.what = 1;
//				handler.sendMessage(message);
				isLoaded = true;
			}
		}
	}

	/*
	 * 监听返回按钮
	 */
	public void back(View view){
		finish();
	}


	/**
	 * webview监听函数
	 * @author linpeng123l
	 *
	 */
	final class MyWebViewClient extends WebViewClient{
		public int count;

		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);
		}

		/**
		 * 当webview 加载完毕时
		 * @param view
		 * @param url
		 */
		public void onPageFinished(WebView view, String url) {
//			view.loadUrl("javascript:window.local_obj.showSource('<head>'+" +
//					"document.getElementsByTagName('html')[0].innerHTML+'</head>');");
			//隐藏加载进度条
			RelativeLayout logding=(RelativeLayout)findViewById(R.id.activity_news_details_relative_is_loading);
			logding.setVisibility(View.GONE);
			super.onPageFinished(view, url);
		}
	}

	/**
	 * 左滑动返回监听
	 */
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				startX = event.getX();
				startY = event.getY();
				break;
			case MotionEvent.ACTION_UP:
				float endX = event.getX();
				float endY = event.getY();
				if(Math.abs(endX-startX)>150&&((endY-startY)==0||Math.abs((endX-startX)/(endY-startY))>2)){
					finish();
				}
				break;
			default:
				break;
		}
		return false;
	}
}
