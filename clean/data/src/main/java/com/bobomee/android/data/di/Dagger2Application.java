package com.bobomee.android.data.di;

import android.content.Context;
import android.support.v7.app.AppCompatDelegate;
import com.bobomee.android.common.app.BaseApplication;
import com.bobomee.android.data.di.internal.HasComponent;
import com.bobomee.android.data.di.internal.components.ApplicationComponent;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created on 16/10/2.下午6:48.
 *
 * @author bobomee.
 * @description
 */

public class Dagger2Application extends BaseApplication
    implements HasComponent<ApplicationComponent> {


  static {
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
  }


  private ApplicationComponent mApplicationComponent;

  @Override public void onCreate() {
    super.onCreate();
    if (LeakCanary.isInAnalyzerProcess(this)) {
      // This process is dedicated to LeakCanary for heap analysis.
      // You should not init your app in this process.
      return;
    }
    LeakCanary.install(this);
    // Normal app init code...
    mApplicationComponent = ApplicationComponent.Init.initialize(this);
    mApplicationComponent.inject(this);

    Stetho.initializeWithDefaults(this);

  }

  public static Dagger2Application get(final Context _context) {
    return (Dagger2Application) _context.getApplicationContext();
  }

  @Override public ApplicationComponent getComponent() {
    return mApplicationComponent;
  }
}
