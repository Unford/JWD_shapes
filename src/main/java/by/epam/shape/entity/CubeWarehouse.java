package by.epam.shape.entity;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CubeWarehouse {
    private Map<Long, CubeParameters> cubeParametersMap = new HashMap<>();

    private CubeWarehouse(){}

    public static CubeWarehouse getInstance(){
        return CubeWarehouseHolder.INSTANCE;
    }

    private static class CubeWarehouseHolder{
        private final static CubeWarehouse INSTANCE = new CubeWarehouse();
    }

    public Optional<CubeParameters> getParameters(long key) {
        return Optional.ofNullable(cubeParametersMap.get(key));
    }

    public void putParameters(Long id, CubeParameters parameters) {
         cubeParametersMap.put(id, parameters);
    }

    public Map<Long, CubeParameters> getCubeParametersMap(){
        return Map.copyOf(cubeParametersMap);
    }

    public void clear(){
        cubeParametersMap.clear();
    }
}
