package com.bobomee.android.common.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bobomee.android.common.R;
import com.bobomee.android.common.adapter.interfaces.AdapterItem;
import com.bobomee.android.common.adapter.interfaces.IAdapter;
import com.bobomee.android.common.adapter.util.DataBindingJudgement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jack Tony
 * @date 2015/11/29
 */
public abstract class CommonPagerAdapter<T> extends BasePagerAdapter<View> implements IAdapter<T> {

  private List<T> mDataList;

  private LayoutInflater mInflater;

  private boolean mIsLazy = false;

  private int currentPos;

  public CommonPagerAdapter() {
    this(null);
  }

  public CommonPagerAdapter(@Nullable List<T> data) {
    this(data, false);
  }

  public CommonPagerAdapter(@Nullable List<T> data, boolean isLazy) {
    this.mDataList = data == null ? new ArrayList<T>() : new ArrayList<T>(data);
    mIsLazy = isLazy;
    DataBindingJudgement.ensureDatabinding(data, this);
  }

  @Override public int getCount() {
    return mDataList.size();
  }

  @NonNull @Override protected View getViewFromItem(View item, int pos) {
    return item;
  }

  @Override public View instantiateItem(ViewGroup container, int position) {
    View view = super.instantiateItem(container, position);
    if (!mIsLazy) {
      initItem(position, view);
    }
    return view;
  }

  @Override public void setPrimaryItem(ViewGroup container, int position, @NonNull Object object) {
    if (mIsLazy && object != currentItem) {
      initItem(position, ((View) object));
    }
    super.setPrimaryItem(container, position, object);
  }

  private void initItem(int position, View view) {
    final AdapterItem item = (AdapterItem) view.getTag(R.id.tag_item);
    item.handleData(mDataList.get(position), position);
  }

  @Override protected View createItem(ViewGroup viewPager, int position) {
    if (mInflater == null) {
      mInflater = LayoutInflater.from(viewPager.getContext());
    }
    AdapterItem item = createItem(getItemType(position));
    View view = mInflater.inflate(item.getLayoutResId(), null);
    view.setTag(R.id.tag_item, item);
    item.bindViews(view);
    item.setViews(mDataList.get(position));
    return view;
  }

  public void setIsLazy(boolean isLazy) {
    mIsLazy = isLazy;
    notifyDataSetChanged();
  }

  /**
   * instead by {@link #getItemType(Object)}
   */
  @Deprecated protected int getItemType(int position) {
    currentPos = position;
    return getItemType(mDataList.get(position));
  }

  /**
   * 强烈建议返回string,int,bool类似的基础对象做type
   */
  @Override public int getItemType(T t) {
    return -1; // default
  }

  @Override public void setData(@NonNull List<T> data) {
    mDataList = new ArrayList<T>(data);
    notifyDataSetChanged();
  }

  @Override public List<T> getData() {
    return mDataList;
  }

  @Override public int getCurrentPosition() {
    return currentPos;
  }

  public void addData(@NonNull List<T> _data) {
    mDataList.addAll(_data);
    notifyDataSetChanged();
  }

  public void clearData() {
    mDataList.clear();
    notifyDataSetChanged();
  }
}
