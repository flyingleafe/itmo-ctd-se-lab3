package ru.akirakozov.sd.refactoring.helpers;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author flyingleafe
 */
@FunctionalInterface
public interface ThrowingAction<T> {
    void accept(T t) throws Exception;
}
