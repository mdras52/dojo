package com.mdras52.dojo.dskatas.utils;


import java.util.stream.Stream;

/**
 * Reference for trampoline pattern:
 * https://java-design-patterns.com/patterns/trampoline/
 * https://github.com/bodar/totallylazy/blob/master/src/com/googlecode/totallylazy/Trampoline.java
 * @param <T>
 */
public interface Trampoline<T> {

    T get();

    default Trampoline<T> jump() {
        return this;
    }

    default T result() {
        return get();
    }

    default boolean complete() {
        return true;
    }

    static <T> Trampoline<T> done(final T result) {
        return () -> result;
    }

    static <T> Trampoline<T> more(final Trampoline<Trampoline<T>> trampoline) {
        return new Trampoline<T>() {

            @Override
            public T get() {
                return trampoline(this);
            }

            @Override
            public boolean complete() {
                return false;
            }

            @Override
            public Trampoline<T> jump() {
                return trampoline.result();
            }

            T trampoline(final Trampoline<T> trampoline) {
                return Stream.iterate(trampoline, Trampoline::jump)
                        .filter(Trampoline::complete)
                        .findFirst()
                        .map(Trampoline::result)
                        .orElseThrow();
            }
        };
    }

}
