package com.unava.dia.daggerdotaopenapi.di;

import com.unava.dia.daggerdotaopenapi.MainActivity;
import dagger.Component;

@Component(modules = {NetModule.class})
public interface NetComponent {
    void inject(MainActivity mainActivity);
}
