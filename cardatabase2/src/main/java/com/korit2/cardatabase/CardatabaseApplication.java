package com.korit2.cardatabase;

import com.korit2.cardatabase.domain.Car;
import com.korit2.cardatabase.domain.CarRepository;
import com.korit2.cardatabase.domain.Owner;
import com.korit2.cardatabase.domain.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
public class CardatabaseApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CardatabaseApplication.class, args);
	}
	// field 선언
	private final CarRepository carRepository;
	private final OwnerRepository ownerRepository;


	@Override
	public void run(String... args) throws Exception {
		// Owner 인스턴스 생성
		Owner owner1 = new Owner("김", "일");
		Owner owner2 = new Owner("Jone", "Doe");
		// 근데 이건 Owner 인스턴스를 생성한거지 DB에 저장한게 아닙니다.
		// owner 테이블에 저장하는 코드
		ownerRepository.saveAll(Arrays.asList(owner1, owner2));

		carRepository.save(new Car("현대", "소나타", "검정", "123가4567", 2026, 30000000, owner1));
		carRepository.save(new Car("기아", "K9", "흰색", "987나5432", 2025, 20000000, owner2));
		carRepository.save(new Car("람보르기니", "쿤타치", "빨강", "159다7532", 2010, 130000000, owner2));
	}
}
