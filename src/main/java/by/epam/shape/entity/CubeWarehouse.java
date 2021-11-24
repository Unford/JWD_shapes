package by.epam.shape.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CubeWarehouse {
    private Map<Long, CubeParameters> cubeParametersMap = new HashMap<>();

    private CubeWarehouse(){
        if(CubeWarehouseHolder.INSTANCE != null){
            throw new RuntimeException("attempt");
        }
    }
    public static CubeWarehouse getInstance(){
        return CubeWarehouseHolder.INSTANCE;
    }

    private static class CubeWarehouseHolder{
        private final static CubeWarehouse INSTANCE = new CubeWarehouse();
    }

    @Override
    public final Object clone()throws CloneNotSupportedException{
        throw new CloneNotSupportedException();
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
}
