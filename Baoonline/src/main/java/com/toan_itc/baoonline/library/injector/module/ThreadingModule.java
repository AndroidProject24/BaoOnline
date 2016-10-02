package com.toan_itc.baoonline.library.injector.module;

import com.toan_itc.data.executor.JobExecutor;
import com.toan_itc.data.executor.PostExecutionThread;
import com.toan_itc.data.executor.ThreadExecutor;
import com.toan_itc.data.executor.UIThread;
import com.toan_itc.data.thread.DefaultExecutorSupplier;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Toan.IT
 * Date: 13/08/2016
 * Email: huynhvantoan.itc@gmail.com
 */
@Module
public class ThreadingModule {

	@Provides
	@Singleton
	DefaultExecutorSupplier provideDefaultExecutorSupplier() {
		return new DefaultExecutorSupplier();
	}

	@Provides
	@Singleton
	ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
		return jobExecutor;
	}

	@Provides
	@Singleton
	PostExecutionThread providePostExecutionThread(UIThread uiThread) {
		return uiThread;
	}
}
