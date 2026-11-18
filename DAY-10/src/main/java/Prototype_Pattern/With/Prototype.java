package Prototype_Pattern.With;

/**
 * Prototype Interface.
 * <p>
 * Declares the method for cloning objects.
 * By using this interface, clients can duplicate any object that implements
 * Prototype without coupling their code to the concrete classes of those objects.
 *
 * @param <T> The type of the object being cloned.
 */
public interface Prototype<T> {

    /**
     * Creates and returns a copy of this object.
     * Guarantees a deep copy of mutable state so that the clone is fully
     * independent from the prototype.
     *
     * @return A deep copy of this instance.
     */
    T clonePrototype();
}
