package commonlib.mvp.core;

/**
 * Created on 16/9/25.上午12:26.
 *
 * @author bobomee.
 * @description
 */

public class BasePresenter<M extends IModel,V extends IView> implements IPresenter {
  @Override public void onStart() {

  }

  @Override public void onDestroy() {

  }

  protected M mModel;
  protected V mRootView;

  public BasePresenter(M model, V rootView) {
    this.mModel = model;
    this.mRootView = rootView;
  }

  public BasePresenter(V rootView) {
    this.mRootView = rootView;
  }
}
