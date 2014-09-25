package co.nodeath.magichealthcounter;

import android.support.annotation.NonNull;

final class Modules {
    static Object[] list(@NonNull MagicLifeCounterApp app) {
        return new Object[]{
                new MagicLifeCounterModule(app),
                new DebugMagicLifeCounterModule()
        };
    }

    private Modules() {
        // No instances.
    }
}
