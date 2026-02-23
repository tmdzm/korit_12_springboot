package test.cardatabase00.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Owner {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long ownerId;

    private String firstName;
    private String lastName; //getter setter가 없어도 실행이 된다.
    //Noarg~가 디폴트로 생성되어 있어서?

    //자바는 기본생성자를 자동 생성한다
    //JPA는 객체생성시 기본생성자를 사용한다.

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Car> cars;

    public Owner(){}

    public Owner(Long ownerId, String firstName, String lastName, List<Car> cars) {
        this.ownerId = ownerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cars = cars;
    }

    public Owner(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
