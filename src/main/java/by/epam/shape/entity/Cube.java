package by.epam.shape.entity;


import by.epam.shape.observer.CubeEvent;
import by.epam.shape.observer.Observable;
import by.epam.shape.observer.Observer;
import by.epam.shape.observer.impl.CubeObserver;
import by.epam.shape.util.IdGenerator;

public class Cube implements Observable {
   private long cubeId;
   private Point center;
   private double edge;
   private Observer observer = new  CubeObserver();

   public Cube(Point center, double edge){
        cubeId = IdGenerator.generateId();
        this.center = center;
        this.edge = edge;

        this.notifyObservers();
   }

    public long getCubeId() { return cubeId; }

    public void setCubeId(long cubeId) { this.cubeId = cubeId; }

    public Point getCenter() { return center; }

    public void setCenter(Point center) {
       this.center = center;
       this.notifyObservers();
   }

    public double getEdge() { return edge; }

    public void setEdge(double edge) {
       this.edge = edge;
       this.notifyObservers();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Cube cube = (Cube) o;
        if (cubeId != cube.cubeId) return false;
        if (Double.compare(cube.edge, edge) != 0) return false;
        return center != null ? center.equals(cube.center) : cube.center == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (cubeId ^ (cubeId >>> 32));
        result = 31 * result + (center != null ? center.hashCode() : 0);
        temp = Double.doubleToLongBits(edge);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
       StringBuilder stringBuilder = new StringBuilder("Cube{");
       stringBuilder.append("cubeId=").append(cubeId);
       stringBuilder.append(", center=").append(center);
       stringBuilder.append(", edge=").append(edge).append('}');
       return stringBuilder.toString();

    }

    @Override
    public void attach(Observer observer) {
        this.observer = observer;
    }

    @Override
    public void detach() {
        this.observer = null;
    }

    @Override
    public void notifyObservers() {
        if (observer != null){
            CubeEvent event = new CubeEvent(this);
            observer.parameterChanged(event);
        }
    }
}
