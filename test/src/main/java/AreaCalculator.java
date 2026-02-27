
// TODO: calculateArea() 추상 메서드를 가진 Shape 추상 클래스를 작성하시오.
abstract class Shape{
    double calculateArea(){return 0;}
}

// TODO: Shape을 상속받고, 반지름(radius)을 필드로 가지는 Circle 클래스를 작성하시오.
// calculateArea() 메서드를 오버라이딩하여 원의 넓이를 계산하시오. (원주율 = 3.14)
class Circle extends Shape {
    public int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    double calculateArea(){
        return 3.14*radius*radius;
    }
}

// TODO: Shape을 상속받고, 가로(width)와 세로(height)를 필드로 가지는 Rectangle 클래스를 작성하시오.
// calculateArea() 메서드를 오버라이딩하여 사각형의 넓이를 계산하시오.
class Rectangle extends Shape {
    private int raw;
    private int colunm;

    public Rectangle(int raw, int colunm) {
        this.raw = raw;
        this.colunm = colunm;
    }

    @Override
    double calculateArea(){
        return raw*colunm;
    }
}

public class AreaCalculator {
    public static void main(String[] args) {
        // TODO: 반지름이 10인 원(Circle) 객체를 생성하고 넓이를 출력하시오.
        // TODO: 가로가 5, 세로가 8인 사각형(Rectangle) 객체를 생성하고 넓이를 출력하시오.
        Circle circle = new Circle(10);
        System.out.printf("원의 넓이: %.1f\n",circle.calculateArea());

        Rectangle rectangle = new Rectangle(5,8);
        System.out.printf("사각형의 넓이: %.1f\n",rectangle.calculateArea());
    }
}