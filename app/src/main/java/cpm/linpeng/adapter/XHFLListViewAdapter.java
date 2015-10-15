package cpm.linpeng.adapter;

import java.lang.ref.SoftReference;
import java.util.List;

import com.linpeng.baidunewssearch.R;
import com.linpeng.netutil.GetBitmapUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class XHFLListViewAdapter extends BaseAdapter{

	private String[] xhflTile;
	private Context context;


	public XHFLListViewAdapter(Context context, String[] xhflTile) {
		super();
		this.xhflTile = xhflTile;
		this.context = context;
	}


	public String[] getPostbars() {
		return xhflTile;
	}

	@Override
	public int getCount() {
		return xhflTile.length;
	}

	@Override
	public Object getItem(int position) {
		return xhflTile[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		String title = xhflTile[position];
		if(convertView==null){
			convertView = View.inflate(context, R.layout.listview_fragment_xhfl, null);
		}
		getTextViewByViewAndId(convertView,R.id.fragment_xhfl_listview_title).setText(title);

		return convertView;
	}

	public TextView getTextViewByViewAndId(View view,int id){
		return (TextView)view.findViewById(id);
	}

}
