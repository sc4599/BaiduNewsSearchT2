package com.linpeng.baidunewssearch;

import java.lang.reflect.Method;
import java.util.ArrayList;

import com.linpeng.fragment.FragmentNews;
import com.linpeng.fragment.FragmentPhoto;
import com.linpeng.fragment.FragmentXHFL;
import com.linpeng.fragment.FragmentXHDS;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import cpm.linpeng.adapter.MyPagerAdapter;


public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener,OnCheckedChangeListener{

	ArrayList<Fragment> fragments=new ArrayList<Fragment>();
	MyPagerAdapter adapter;
	ViewPager viewPager ;
	RadioGroup rgpButtons;//底侧 按钮
	private RadioButton zxxh,xhfl,gxtp,xhds;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		setListener();
		initFragment();
		initPager();
	}

	/**
	 * 初始化所有控件
	 */
	private void initView() {
		viewPager = (ViewPager)findViewById(R.id.main_fragment_viewPager);
		rgpButtons = (RadioGroup)findViewById(R.id.main_fragment_rgp);
		zxxh = (RadioButton)findViewById(R.id.main_rbn_zxxh);
		xhfl = (RadioButton)findViewById(R.id.main_rbn_xhfl);
		xhds = (RadioButton)findViewById(R.id.main_rbn_xhds);
		gxtp = (RadioButton)findViewById(R.id.main_rbn_gxtp);
		zxxh.setChecked(true);
	}

	/**
	 * 为控件设置监听
	 */
	private void setListener() {
		viewPager.setOnPageChangeListener(this);
		rgpButtons.setOnCheckedChangeListener(this);
	}

	private void initFragment(){
		fragments.add(new FragmentNews());
		fragments.add(new FragmentXHFL());
		fragments.add(new FragmentXHDS());
		fragments.add(new FragmentPhoto());
	}
	private void initPager(){
		adapter = new MyPagerAdapter(getSupportFragmentManager(), fragments);
		viewPager.setAdapter(adapter);
		viewPager.setCurrentItem(0);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_share://分享按钮
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("text/plain");
				intent.putExtra(Intent.EXTRA_TEXT, "这是一个可以定制关键词的新闻app,欢迎体验");
				startActivity(Intent.createChooser(intent, getTitle()));
				break;

			default:
				break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
			if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
				try {
					Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
					m.setAccessible(true);
					m.invoke(menu, true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return super.onMenuOpened(featureId, menu);
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

	}

	//底部菜单按钮监听
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId){
			case R.id.main_rbn_zxxh:
				getActionBar().setTitle(R.string.zxxh);
				viewPager.setCurrentItem(0);
				break;
			case R.id.main_rbn_xhfl:
				getActionBar().setTitle(R.string.xhfl);
				viewPager.setCurrentItem(1);
				break;
			case R.id.main_rbn_xhds:
				getActionBar().setTitle(R.string.xhds);
				viewPager.setCurrentItem(2);
				break;
			case R.id.main_rbn_gxtp:
				getActionBar().setTitle(R.string.gxtp);
				viewPager.setCurrentItem(3);
				break;
		}
	}

	/**
	 * 页面被选择后执行
	 */
	@Override
	public void onPageSelected(int position) {
		//滑动后执行
		Log.i("info","当前位置="+position);
		switch (position){
			case 0:
				rgpButtons.check(R.id.main_rbn_zxxh);
				break;
			case 1:
				rgpButtons.check(R.id.main_rbn_xhfl);
				break;
			case 2:
				rgpButtons.check(R.id.main_rbn_xhds);
				break;
			case 3:
				rgpButtons.check(R.id.main_rbn_gxtp);
				break;
		}
	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}


}