package Flyweight_Pattern.Without;

public class Tree {
    // Extrinsic state (unique per tree)
    private final int x;
    private final int y;

    // Intrinsic state (duplicated across every tree instance)
    private final String name;
    private final String color;
    private final String texture;

    public Tree(int x, int y, String name, String color, String texture) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.color = color;
        this.texture = texture;
    }

    public void draw() {
        System.out.println("Drawing [" + name + "] (" + color + ", " + texture + ") at (" + x + ", " + y + ")");
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getTexture() {
        return texture;
    }
}
