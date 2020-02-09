package texas.sbv.car.company;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import texas.sbv.car.car.Car;
import texas.sbv.car.car.CarRepository;
import texas.sbv.car.recommend.Recommend;

@Component
public class CompanyServiceImpl implements CompanyService{
    @Autowired
    CarRepository carsRepository;
    public List<Car> getBestCarList(Recommend recommend) {
        List<Car> list =new ArrayList<>();
        carsRepository.findByModelnmAndCenterCodeOrderByPrice(recommend.getModelNm(),recommend.getCenterCode()).forEach(el->{

            if ( list.size()<5&&recommend.getMinPrice() <= el.getPrice() && recommend.getMaxPrice() > el.getPrice()) {
                list.add(el);
            }
        });
        if(list.size()<5){
            carsRepository.findByCategorycdAndCenterCode(carsRepository.findFirstByModelnm(recommend.getModelNm()).getCategorycd(),recommend.getCenterCode()).forEach(el->{
                if(list.size()<5){
                    if(recommend.getMinPrice()<el.getPrice()&&recommend.getMaxPrice()>el.getPrice()){
                        list.add(el);
                    }
                }
            });
        }
        if(list.size()<5){
            carsRepository.findByModelnmAndCenterCodeOrderByPrice(recommend.getModelNm(),recommend.getCenterCode()).forEach(el->{

                if ( list.size()<5) {
                    list.add(el);
                }
            });
        }

        return list;
    }
}
