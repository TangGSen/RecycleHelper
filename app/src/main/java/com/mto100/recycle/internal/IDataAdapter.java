package com.mto100.recycle.internal;

/**
 * Created by CHM on 2015/12/5.
 */
public interface IDataAdapter<DATA> {

    public void notifyDataChange(DATA data,boolean isRefresh);


}
