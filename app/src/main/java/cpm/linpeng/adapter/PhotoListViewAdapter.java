package cpm.linpeng.adapter;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.linpeng.baidunewssearch.R;
import com.linpeng.domain.Photo;
import com.linpeng.netutil.GetBitmapUtil;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

/**
 * 图片列表适配器
 * 注：此处将一张或两张图片分为一组（长宽比大于1.6的为一张一组）
 * 此处的图片获取是异步的
 * map 缓存了以获取图片
 * @author linpeng123l
 *
 */
public class PhotoListViewAdapter extends BaseAdapter{

	private Context context;
	private List<Photo> photos;
	private Map<String, SoftReference<Bitmap>> map = new HashMap<String, SoftReference<Bitmap>>();

	public PhotoListViewAdapter(Context context , List<Photo> photos) {
		super();
		if(photos==null){
			photos = new ArrayList<Photo>();
		}
		this.photos = photos;
		this.context = context;
	}

	public void addPhotos(List<Photo> newNewss){
		this.photos.addAll(newNewss);
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public int getCount() {
		return photos.size();
	}
	@Override
	public Object getItem(int position) {
		return photos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Photo photo = photos.get(position);
		if(convertView==null){
			convertView = View.inflate(context, R.layout.listview_fragment_photo, null);
		}
		((TextView)convertView.findViewById(R.id.fragment_photo_listview_title)).setText(photo.getTitle());//设置标题
		((TextView)convertView.findViewById(R.id.fragment_photo_listview_summary)).setText(photo.getSummary().substring(1));//添加摘要
		((TextView)convertView.findViewById(R.id.fragment_photo_listview_time)).setText(photo.getUserName()+" | "+photo.getDateline());//添加时间戳

		//异步加载图片
		ImageView imageView = (ImageView)convertView.findViewById(R.id.fragment_photo_listview_image1);
		imageView.setImageResource(R.drawable.img_temp);
		imageView.setTag(photo.getPhotoUrl());


		if(map.get(photo.getPhotoUrl())!=null&&map.get(photo.getPhotoUrl()).get()!=null){
			imageView.setImageBitmap(map.get(photo.getPhotoUrl()).get());
		}else{

			MyAsyncTaskGetBitmap myAsyncTaskGetBitmap = new MyAsyncTaskGetBitmap(imageView);
			myAsyncTaskGetBitmap.imageView = imageView;
			myAsyncTaskGetBitmap.targetUrl = photo.getPhotoUrl();
			myAsyncTaskGetBitmap.execute("");
		}
		return convertView;
	}

	/**
	 * 异步获取图片并显示
	 * @author linpeng123l
	 *
	 */
	public class MyAsyncTaskGetBitmap extends AsyncTask<String, String, Bitmap>{
		String targetUrl;
		String s;
		ImageView imageView;
		public MyAsyncTaskGetBitmap(View imageView){
			this.s = (String) imageView.getTag();
			imageView.setTag(s);
		}
		@Override
		protected Bitmap doInBackground(String... params) {
			if(targetUrl==s){
				Bitmap bitmap = GetBitmapUtil.getBitmapByUrl(targetUrl);
				return bitmap;
			}
			return null;
		}
		@Override
		protected void onPostExecute(Bitmap bitmap) {
			if(bitmap!=null){
				map.put(targetUrl, new SoftReference<Bitmap>(bitmap));
				if(targetUrl.equals(imageView.getTag())){
					imageView.setImageBitmap(bitmap);
				}
			}
			super.onPostExecute(bitmap);
		}
	}
}
