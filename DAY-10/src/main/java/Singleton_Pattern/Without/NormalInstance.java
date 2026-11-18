package Singleton_Pattern.Without;

public class NormalInstance {
    static NormalInstance instance;

    private NormalInstance() {
    }

    public static NormalInstance getInstance() {
        instance = new NormalInstance();
        return instance;
    }
}
