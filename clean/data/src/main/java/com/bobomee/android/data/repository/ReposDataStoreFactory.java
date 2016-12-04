package com.bobomee.android.data.repository;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.bobomee.android.data.di.core.ApplicationContext;
import com.bobomee.android.data.serializer.UserCache;
import com.bobomee.android.data.serializer.Wrapper;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created on 2016/12/3.下午8:19.
 *
 * @author bobomee.
 * @description
 */

@Singleton public class ReposDataStoreFactory {

  private final UserCache userCache;
  private final Context context;

  @Inject ReposCloudDataRepository mReposCloudDataRepository;
  @Inject ReposDiskDataRepository mReposDiskDataRepository;

  @Inject public ReposDataStoreFactory(@ApplicationContext Context _context, UserCache userCache) {
    this.context = _context;
    this.userCache = userCache;
  }

  public <T> ReposRepository create(Wrapper<T> _wrapper) {
    ReposRepository reposRepository;

    if (!isThereInternetConnection()) {
      if (cacheExpired(_wrapper)) {
        reposRepository = mReposDiskDataRepository;
      } else {
        reposRepository = mReposCloudDataRepository;
      }
    } else {
      if (!_wrapper.isRefresh() && cacheExpired(_wrapper)) {
        reposRepository = mReposDiskDataRepository;
      } else {
        reposRepository = mReposCloudDataRepository;
      }
    }

    return reposRepository;
  }

  private <T> boolean cacheExpired(Wrapper<T> _wrapper) {
    return !this.userCache.isExpired() && this.userCache.isCached(_wrapper.getKey());
  }

  /**
   * Checks if the device has any active internet connection.
   *
   * @return true device with internet connection, otherwise false.
   */
  private boolean isThereInternetConnection() {
    boolean isConnected;

    ConnectivityManager connectivityManager =
        (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
    isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

    return isConnected;
  }
}
