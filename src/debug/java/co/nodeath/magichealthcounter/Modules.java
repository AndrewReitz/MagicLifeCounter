package co.nodeath.magichealthcounter;

import org.jetbrains.annotations.NotNull;

final class Modules {
    static Object[] list(@NotNull MagicLifeCounterApp app) {
        return new Object[]{
                new MagicLifeCounterModule(app),
                new DebugMagicLifeCounterModule()
        };
    }

    private Modules() {
        // No instances.
    }
}
