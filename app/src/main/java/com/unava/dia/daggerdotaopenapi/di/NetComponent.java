package com.unava.dia.daggerdotaopenapi.di;

import com.unava.dia.daggerdotaopenapi.MainActivity;
import dagger.Component;

@Component(modules = {ApplicationModule.class, NetModule.class})
public interface NetComponent {
    void inject(MainActivity mainActivity);
}
