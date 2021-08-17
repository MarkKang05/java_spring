package com.example.restaurant.db;

import java.util.Optional;

public class MemoryDbRepositoryIfs<T> {

    Optional<T> findById(int index);

}
