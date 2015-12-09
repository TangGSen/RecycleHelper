package com.mto100.recycle.internal;

/**
 * Created by CHM on 2015/12/5.
 */
public interface IDataSource<DATA> {

    /**
     * 刷新
     * @return
     */
    public DATA refresh();

    /**
     * 加载更多
     * @return
     */
    public DATA loadMore();


    public boolean hasMore();

}
