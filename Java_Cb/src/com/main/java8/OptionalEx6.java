package com.main.java8;

import java.util.Optional;

public class OptionalEx6 {
    public static void main(String[] args) {
        Optional<String> shouldNotBeEmpty = Optional.empty();
        shouldNotBeEmpty.orElseThrow(() -> new IllegalStateException("This should not happen!!!"));
    }
}
