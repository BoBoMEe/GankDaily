package com.bobomee.android.myapplication.base;

import com.bobomee.android.common.mvp.MvpBasePresenter;
import com.bobomee.android.data.repo.GetRepos;
import com.bobomee.android.domain.bean.ReposEntity;
import com.bobomee.android.domain.interactor.DefaultSubscriber;
import com.bobomee.android.myapplication.mapper.ReposDataMapper;
import com.bobomee.android.myapplication.model.ReposModel;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by Abner on 16/6/16.
 * Email nimengbo@gmail.com
 * github https://github.com/nimengbo
 */
public class ReposListPresenter extends MvpBasePresenter<ReposListView> {

    private final GetRepos mGetRepos;
    private final ReposDataMapper mUserModelDataMapper;

    @Inject
    public ReposListPresenter(GetRepos getRepos,
                              ReposDataMapper userModelDataMapper) {
        mGetRepos = getRepos;
        mUserModelDataMapper = userModelDataMapper;
    }

    @Override
    public void detachView(boolean retainInstance) {
        mGetRepos.unsubscribe();
        super.detachView(retainInstance);
    }

    /**
     * Initializes the presenter by start retrieving the user
     */
    @Override
    public void initialize() {
        getUserList();
    }

    private void processUserList(List<ReposEntity> reposEntity) {
        final List<ReposModel> reposModels = mUserModelDataMapper.transform(reposEntity);
        getView().userList(reposModels);
    }

    private void getUserList() {
        mGetRepos.setParam("moduth");
        mGetRepos.execute(new UserSubscriber());
    }

    private class UserSubscriber extends DefaultSubscriber<List<ReposEntity>> {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onNext(List<ReposEntity> reposEntity) {
            processUserList(reposEntity);
        }
    }
}
