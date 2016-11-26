package ru.akirakozov.sd.refactoring.helpers;

/**
 * @author flyingleafe
 */
@FunctionalInterface
public interface ThrowingAction<T> {
    void accept(T t) throws Exception;
}
