package by.epam.shape.util;

public class IdGenerator {
    private static long id;

    private IdGenerator(){}
    public static long generateId(){
        return ++id;
    }
}
