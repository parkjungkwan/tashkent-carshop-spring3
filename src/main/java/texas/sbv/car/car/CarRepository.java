package texas.sbv.car.car;


import org.springframework.context.annotation.Lazy;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Lazy
public interface CarRepository extends CrudRepository<Car, Long> {

    public List<Car> findByMakenm(String makenm);
    public List<Car> findByModelGrpNm(String modelGrpNm);
    public List<Car> findByCenterCode(String centerCode);
    public List<Car> findByModelnmText(String ModelnmText);
    public Car findByCarcd(String carcd);
    public List<Car> findByModelnmAndCenterCodeOrderByPrice(String mondelnm ,String centercode);
    public List<Car> findByCategorycdAndCenterCode(String categorycd ,String centercode);
    public Car  findFirstByModelnm(String modelnm);

}
