package com.bobomee.android.data.repo;

import com.bobomee.android.data.repository.ReposRepository;
import com.bobomee.android.domain.bean.ReposEntity;
import com.bobomee.android.domain.executor.PostExecutionThread;
import com.bobomee.android.domain.executor.ThreadExecutor;
import com.bobomee.android.domain.interactor.UseCase;
import java.util.List;
import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for login and
 * retrieve a {@link ReposEntity}.
 */
public class GetRepos extends UseCase<List<ReposEntity>> {

  private final ReposRepository mReposRepository;
  private String user;

  public GetRepos(ReposRepository reposRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.mReposRepository = reposRepository;
  }

  public void setParam(String user) {
    this.user = user;
  }

  @Override public Observable<List<ReposEntity>> buildUseCaseObservable() {
    return this.mReposRepository.login(user);
  }
}
