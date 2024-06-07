package com.unibg.UnibgProject.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coppia<T,U> {
    private T object1;
    private U object2;

    public Coppia(T object1, U object2) {
        this.object1 = object1;
        this.object2 = object2;
    }

}
