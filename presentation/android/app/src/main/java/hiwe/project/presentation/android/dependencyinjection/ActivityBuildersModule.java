package ca.project.presentation.android.dependencyinjection;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ca.project.presentation.android.MainActivity;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();

}
