package me.stefan923.collections.tree;

import me.stefan923.collections.Collection;

public interface Tree<E> extends Collection<E> {

    Object[] computeTraversal(TraversalType traversalType);

}
