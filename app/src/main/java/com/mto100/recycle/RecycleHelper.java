package com.mto100.recycle;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;

import com.mto100.recycle.internal.IDataAdapter;
import com.mto100.recycle.internal.IDataSource;

/**
 * Created by CHM on 2015/12/5.
 */
public class RecycleHelper<DATA> {

    private RecycleHelper instance;
    private Context mContext;
    private RecyclerView mRecycleView;

    private int mOrientation = VERTICAL;
    public static final int HORIZONTAL = OrientationHelper.HORIZONTAL;
    public static final int VERTICAL = OrientationHelper.VERTICAL;

    private IDataAdapter<DATA> mDataAdapter;
    private IDataSource<DATA> mDataSource;

    private RecycleHelper(Context context) {
        this.mContext = context;
    }

    /**
     * 单例模式
     *
     * @return
     */
    public RecycleHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (instance) {
                if (instance == null) {
                    instance = new RecycleHelper(context);
                }
            }
        }
        return instance;
    }

    /**
     * 创建网格布局管理器 Grid布局
     *
     * @param columNum 几行数据
     */
    public void createGridView(int columNum) {
        GridLayoutManager manager = new GridLayoutManager(mContext, columNum, mOrientation, false);
        create(manager);
    }

    /**
     * 创建Linear 现行管理器，支持横向、纵向 RecycleView
     */
    public void createLinearView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, mOrientation, false);
        create(linearLayoutManager);
    }

    /**
     * 瀑布流
     *
     * @param columNum 几行数据
     */
    public void createStaggeredGrid(int columNum) {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(columNum, mOrientation);
        create(staggeredGridLayoutManager);
    }

    /**
     * 创建一个RecycleView
     *
     * @param manager
     */
    public void create(RecyclerView.LayoutManager manager) {
        RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(mContext).inflate(R.layout.view_recycle, null, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.mRecycleView = recyclerView;

    }

    /**
     * 获取创建的RecycleView
     *
     * @return
     */
    public RecyclerView getRecyclerView() {
        return mRecycleView;
    }


    /**
     * 设置数据源和RecycleView适配器
     *
     * @param dataSource 数据源
     * @param dataAdapter 适配器
     */
    public void setDataAndAdapter(IDataSource dataSource, IDataAdapter dataAdapter) {
        this.mDataSource = dataSource;
        this.mDataAdapter = dataAdapter;
    }

    /**
     * 设置RecycleView的方向
     *
     * @param orientation VERTICAL代表纵向、HORIZONTAL代表横向，默认为VERTICAL纵向
     */
    public void setOrientation(int orientation) {
        switch (orientation) {
            case VERTICAL:
                this.mOrientation = VERTICAL;
                break;
            case HORIZONTAL:
                this.mOrientation = HORIZONTAL;
                break;
            default:
                this.mOrientation = VERTICAL;
        }
    }


}
