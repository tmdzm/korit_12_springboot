package test.cardatabase00;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import test.cardatabase00.domain.Car;
import test.cardatabase00.domain.CarRepository;
import test.cardatabase00.domain.Owner;
import test.cardatabase00.domain.OwnerRepository;

@SpringBootApplication
public class Cardatabase00Application implements CommandLineRunner {
	private final CarRepository carRepository;
	private final OwnerRepository ownerRepository;

    public Cardatabase00Application(CarRepository carRepository, OwnerRepository ownerRepository) {
        this.carRepository = carRepository;
        this.ownerRepository = ownerRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(Cardatabase00Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Owner owner0 = new Owner("김","영");
		Owner owner1 = new Owner("김","일");

		ownerRepository.save(owner0);
		ownerRepository.save(owner1);

		Car car0 = new Car("르노","Qm6","회색","987나6543",2026,1234567,owner0);
		Car car1 = new Car("르qod","Qmz","회색","987나6513",2026,1211567,owner1);

		carRepository.save(car0);
		carRepository.save(car1);

	}
}
