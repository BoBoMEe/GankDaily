package commonlib.adapter.interfaces;

import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import java.util.List;

/**
 * @author Jack Tony
 * @date 2015/11/29
 * 通用的adapter必须实现的接口，作为方法名统一的一个规范
 */
public interface IAdapter<T> {

  /**
   * @param data 设置数据源
   */
  void setData(@NonNull List<T> data);

  List<T> getData();

  /**
   * @param t list中的一条数据
   */
  int getItemType(T t);

  /**
   * 当缓存中无法得到所需item时才会调用
   *
   * @param type 通过{@link #getItemType(T)}得到的type
   * @return 任意类型的 AdapterItem
   */
  @Keep @NonNull AdapterItem<T> createItem(int type);

  /**
   * 通知adapter更新当前页面的所有数据
   */
  void notifyDataSetChanged();

  /**
   * 得到当前要渲染的最后一个item的position
   */
  int getCurrentPosition();
}
