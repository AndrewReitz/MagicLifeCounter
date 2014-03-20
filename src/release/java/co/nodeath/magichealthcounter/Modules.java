package co.nodeath.magichealthcounter;

final class Modules {
    static Object[] list(MagicLifeCounterApp app) {
        return new Object[]{
                new MagicLifeCounterModule(app)
        };
    }

    private Modules() {
        // No instances.
    }
}
