package ca.project.presentation.android;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import ca.project.presentation.android.dependencyinjection.DaggerAppComponent;

public class BaseApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }

}
