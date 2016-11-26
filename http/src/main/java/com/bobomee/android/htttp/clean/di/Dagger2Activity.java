package com.bobomee.android.htttp.clean.di;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.bobomee.android.htttp.clean.datastore.repo.Repository;
import com.bobomee.android.htttp.clean.di.internal.HasComponent;
import com.bobomee.android.htttp.clean.di.internal.components.ActivityComponent;
import javax.inject.Inject;

/**
 * Created on 16/10/2.下午6:54.
 *
 * @author bobomee.
 * @description
 */

public class Dagger2Activity extends AppCompatActivity implements HasComponent<ActivityComponent> {
  @Inject protected Repository mRepository;
  private ActivityComponent mInitialize;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mInitialize = ActivityComponent.Init.initialize(this);
    ActivityComponent component = getComponent();
    if (null != component) component.inject(this);
  }

  @Override public ActivityComponent getComponent() {
    return mInitialize;
  }
}
