// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.oba.wificrack.view;


public class ListVo
{

    public ListVo(String s, int i, boolean flag)
    {
        name = s;
        level = i;
        focusing = flag;
    }

    public int getLevel()
    {
        return level;
    }

    public int getLevelImgId()
    {
        int i;
        if(level > -51)
            i = 0x7f020008;
        else
        if(level > -70)
            i = 0x7f020007;
        else
        if(level > -80)
            i = 0x7f020006;
        else
            i = 0x7f020005;
        return i;
    }

    public String getName()
    {
        return name;
    }

    public boolean isFocusing()
    {
        return focusing;
    }

    public void setFocusing(boolean flag)
    {
        focusing = flag;
    }

    public void setLevel(int i)
    {
        level = i;
    }

    public void setName(String s)
    {
        name = s;
    }

    public String getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(String capabilities) {
		this.capabilities = capabilities;
	}

	private boolean focusing;
    private int level;
    private String name;
    private String capabilities;
}
