// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.oba.wificrack.view;

import android.content.Context;
import android.view.*;
import android.widget.*;
import java.util.List;

// Referenced classes of package com.oba.wificrack.view:
//            ViewHolder, ListVo

public class ListAdapter extends BaseAdapter
{

    public ListAdapter(Context context, List list)
    {
        mLayoutInflater = LayoutInflater.from(context);
        infos = list;
    }

    public int getCount()
    {
        return infos.size();
    }

    public Object getItem(int i)
    {
        return Integer.valueOf(i);
    }

    public long getItemId(int i)
    {
        return (long)i;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        ViewHolder viewholder;
        ListVo listvo;
        if(view == null)
        {
            view = mLayoutInflater.inflate(0x7f030005, null);
            viewholder = new ViewHolder();
            viewholder.name = (TextView)view.findViewById(0x7f06000b);
            viewholder.level = (ImageView)view.findViewById(0x7f06000a);
            viewholder.focusing = (ImageView)view.findViewById(0x7f06000c);
            view.setTag(viewholder);
        } else
        {
            viewholder = (ViewHolder)view.getTag();
        }
        listvo = (ListVo)infos.get(i);
        viewholder.name.setText(listvo.getName());
        viewholder.level.setImageResource(listvo.getLevelImgId());
        if(listvo.isFocusing())
            viewholder.focusing.setVisibility(0);
        else
            viewholder.focusing.setVisibility(8);
        return view;
    }

    private List infos;
    private LayoutInflater mLayoutInflater;
}
