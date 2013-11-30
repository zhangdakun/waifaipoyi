package com.oba.wificrack.view;

import java.util.List;

import com.android.mywifi02.parser.R;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {
    private List infos;
    private LayoutInflater mLayoutInflater;
    
    
	public GridAdapter(Context context, List infos) {
//		super();
		 mLayoutInflater = LayoutInflater.from(context);
		this.infos = infos;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return infos.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int i, View view, ViewGroup viewgroup) {
        ViewHolder viewholder;
        ListVo listvo;
        if(view == null)
        {
            view = mLayoutInflater.inflate(R.layout.wifi_grid, null);
            viewholder = new ViewHolder();
            viewholder.name = (TextView)view.findViewById(R.id.wifi_name_grid);
            viewholder.level = (ImageView)view.findViewById(R.id.wifi_level_grid);
//            viewholder.focusing = (ImageView)view.findViewById(0x7f06000c);
            view.setTag(viewholder);
        } else
        {
            viewholder = (ViewHolder)view.getTag();
        }
        listvo = (ListVo)infos.get(i);
        viewholder.name.setText(listvo.getName());
        viewholder.level.setImageResource(listvo.getLevelImgId());
//        if(listvo.isFocusing())
//            viewholder.focusing.setVisibility(0);
//        else
//            viewholder.focusing.setVisibility(8);
        return view;
	}

}
