package com.bobomee.android.myapplication.base;

import com.bobomee.android.common.mvp.MvpView;
import com.bobomee.android.myapplication.model.ReposModel;
import java.util.List;

/**
 * Created by Abner on 16/6/16.
 * Email nimengbo@gmail.com
 * github https://github.com/nimengbo
 */
public interface ReposListView extends MvpView {

    void userList(List<ReposModel> userModels);

}
