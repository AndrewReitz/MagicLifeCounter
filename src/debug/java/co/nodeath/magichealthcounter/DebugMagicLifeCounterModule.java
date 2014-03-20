package co.nodeath.magichealthcounter;

import co.nodeath.magichealthcounter.data.DebugDataModule;
import co.nodeath.magichealthcounter.ui.DebugUiModule;

import dagger.Module;

@Module(
        addsTo = MagicLifeCounterModule.class,
        includes = {
                DebugUiModule.class,
                DebugDataModule.class
        },
        overrides = true
)
public final class DebugMagicLifeCounterModule {
}
