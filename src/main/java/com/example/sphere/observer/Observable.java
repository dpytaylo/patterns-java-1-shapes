package com.example.sphere.observer;

public interface Observable {
    void attach();
    void detach();
    void notifyObservers();
}
