package by.epam.shape.observer;

import by.epam.shape.entity.Cube;

import java.util.EventObject;

public class CubeEvent extends EventObject {
    public CubeEvent(Cube source){
        super(source);
    }

    @Override
    public Cube getSource() {
        return (Cube) super.getSource();
    }
}
