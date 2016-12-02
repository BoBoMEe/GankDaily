package commonlib.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.bobomee.android.commonlib.R;
import commonlib.adapter.interfaces.AdapterItem;
import commonlib.adapter.interfaces.IAdapter;
import commonlib.adapter.util.DataBindingJudgement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jack Tony
 * @date 2015/5/15
 */
public abstract class CommonAdapter<T> extends BaseAdapter implements IAdapter<T> {

  protected List<T> mDataList;

  private int mViewTypeCount = 1;

  private int mType;

  private LayoutInflater mInflater;

  private int currentPos;

  protected SparseArray<AdapterItem<T>> mItemsCache;

  public CommonAdapter() {
    this(null);
  }

  public CommonAdapter(List<T> _dataList) {
    this(_dataList, 1);
  }

  public CommonAdapter(int _viewTypeCount) {
    this(null, _viewTypeCount);
  }

  public CommonAdapter(@Nullable List<T> data, int viewTypeCount) {
    this.mDataList = data == null ? new ArrayList<>() : new ArrayList<>(data);
    mViewTypeCount = viewTypeCount;
    DataBindingJudgement.ensureDatabinding(mDataList, this);
    mItemsCache = new SparseArray<>();
  }

  @Override public int getCount() {
    return mDataList.size();
  }

  @Override public void setData(@NonNull List<T> data) {
    mDataList = new ArrayList<>(data);
    notifyDataSetChanged();
  }

  public void setViewTypeCount(int _viewTypeCount) {
    this.mViewTypeCount = _viewTypeCount;
    notifyDataSetChanged();
  }

  @Override public List<T> getData() {
    return mDataList;
  }

  @Override public long getItemId(int position) {
    return position;
  }

  /**
   * 通过数据得到obj的类型的type
   *
   * instead by{@link #getItemType(Object)}
   */
  @Override @Deprecated public int getItemViewType(int position) {
    currentPos = position;
    mType = getItemType(mDataList.get(position));
    // 如果不写这个方法，会让listView更换dataList后无法刷新数据
    return (mType);
  }

  @Override public int getItemType(T t) {
    return -1; // default
  }

  @Override public int getViewTypeCount() {
    return mViewTypeCount;
  }

  @Override public View getView(int position, View convertView, ViewGroup parent) {
    if (mInflater == null) {
      mInflater = LayoutInflater.from(parent.getContext());
    }

    final AdapterItem<T> item;
    if (convertView == null) {
      item = createItem(mType);
      convertView = mInflater.inflate(item.getLayoutResId(), parent, false);
      convertView.setTag(R.id.tag_item, item); // get item

      item.bindViews(convertView);
      item.setViews(getItem(position));
      mItemsCache.put(mItemsCache.size(), item);
    } else {
      item = (AdapterItem<T>) convertView.getTag(R.id.tag_item); // save item
    }
    item.handleData(getItem(position), position);
    return convertView;
  }

  @Override public T getItem(int position) {
    return mDataList.get(position);
  }

  @Override public int getCurrentPosition() {
    return currentPos;
  }

  public void addData(@NonNull List<T> _dataList) {
    mDataList.addAll(_dataList);
    notifyDataSetChanged();
  }

  public void add(int i, T t) {
    mDataList.add(i, t);
    notifyDataSetChanged();
  }

  public void clearData() {
    mDataList.clear();
    notifyDataSetChanged();
  }

  public void remove(int index) {
    mDataList.remove(index);
    notifyDataSetChanged();
  }

  public void remove(T t) {
    mDataList.remove(t);
    notifyDataSetChanged();
  }

  public void clear() {
    mDataList.clear();
    notifyDataSetChanged();
  }
}
