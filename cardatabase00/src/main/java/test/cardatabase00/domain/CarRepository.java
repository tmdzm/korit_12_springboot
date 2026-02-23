package test.cardatabase00.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {//Long은 Car의 id의 자료형

    // CrudRepository에 없는 메서드라면 여기에 정의를 해줘야 합니다.
    // 쿼리는 접두사(findBy)로 시작해야하고, 쿼리에 이용할 엔티티 클래스 필드를 정의해야합니다.

    List<Car> findByBrand(String brand);

    // 색상으로 검색, 그리고 연도로 검색하는 메서드를 직접 정의하시오.
    List<Car> findByColor(String color);

    List<Car> findByModelYear(int modelYear);

    // SQL 상에서의 AND 및 OR 연산자도 적용이 됩니다.(camel case를 잘 적용할 것)
    // 브랜드와 모델로 자동차 검색
    List<Car> findByBrandAndModel(String brand, String model);

    // 브랜드 또는 색상 별로 자동차 가져오기를 작성하시오.
    List<Car> findByBrandOrColor(String brand, String color);

    // 정렬도 집어넣을 수 있습니다. - 브랜드로 자동차 검색하고, 연도로 정렬
    List<Car> findByBrandOrderByModelYearAsc(String brand);

    // @Query 애너테이션을 이용해서 SQL문 자체를 집어넣을 수도 있습니다.
    // 모델명으로 검색하기
    @Query("select c from Car c where c.model = ?1")
    List<Car> findByModel(String model);
    // 그런데 이상의 경우에는 일치하는 것만 구할 수가 있는데, @Query를 쓰는 이유는 LIKE
    // 연산자가 사용 가능하기 때문입니다.

    @Query("select c from Car c where c.brand like %?1")
    List<Car> findByBrandEndWith(String brand);

    // 다만 @Query를 쓰게 되면 다른 데이터베이스로의 이식성이 좀 떨어지긴 합니다.
}