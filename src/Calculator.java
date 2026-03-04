import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Calculator {
    private static List<Double> history = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== 계산기 메뉴 ===");
            System.out.println("1. 계산하기");
            System.out.println("2. 계산 이력 보기");
            System.out.println("3. 이력 지우기");
            System.out.println("0. 종료");
            System.out.print("선택: ");

            String menuChoice = scanner.next();

            switch (menuChoice) {
                case "1":
                    perform();
                    break;
                case "2":
                    viewHistory();
                    break;
                case "3":
                    history.clear();
                    System.out.println("계산 이력이 성공적으로 지워졌습니다.");
                    break;
                case "0":
                    System.out.println("계산기를 종료합니다.");
                    scanner.close();
                    return; // 프로그램 완전 종료
                default:
                    System.out.println("잘못된 입력입니다. 0~3 사이의 숫자를 선택해주세요.");
            }
        }
    }

    private static void perform(){
        double a = 0;
        boolean usePre = false;

        if (!history.isEmpty()){
            Double r = history.get(history.size() - 1);
            System.out.print("이전 결과("+r+")를 사용하시겠습니까? (y/n): ");
            String c = scanner.next();
            if (c.equalsIgnoreCase("y")) {
                a = r;
                usePre = true;
            }
        }

        while(true){
            try{
                if(!usePre){
                    System.out.print("첫 번째 숫자를 입력하세요: ");
                    a = scanner.nextDouble();
                }
                System.out.print("연산자를 입력하세요 (+, -, *, /): ");
                String x = scanner.next();
                double res = 0;

                if (x.equals("sqrt")) {
                    if (a < 0) {
                        System.out.println("음수의 제곱근은 계산할 수 없습니다.");
                        usePre = false;
                        continue;
                    }
                    res = Math.sqrt(a);
                } else {
                    System.out.print("두 번째 숫자를 입력하세요: ");
                    double b = scanner.nextDouble();

                    switch(x){
                        case "+": res = a+b; break;
                        case "-": res = a-b; break;
                        case "*": res = a*b; break;
                        case "/":
                            if (b == 0) {
                                System.out.println("0으로 나눌 수 없습니다.");
                                continue; // 다시 계산 루프의 처음으로 돌아감
                            }
                            res = a / b;
                            break;
                        case "%":
                            if (b == 0) {
                                System.out.println("0으로 나눌 수 없습니다.");
                                continue;
                            }
                            res = a % b;
                            break;
                        case "^":
                            res = Math.pow(a, b);
                            break;
                        default:
                            System.out.println("지원하지 않는 연산자입니다.");
                            usePre = false; // 연산자가 틀렸으므로 처음부터 다시 입력받도록 초기화
                            continue;
                    }
                }
                history.add(res);
                System.out.println("결과: "+res);


                System.out.println("계속 계산하시겠습니까? (y/n): ");
                String end = scanner.next();
                if (end.equals("y")){
                    a = res;
                    usePre = true;
                }else{break;}


            }catch (InputMismatchException e) {
                System.out.println("숫자를 입력해주세요.");
                scanner.nextLine(); // 잘못된 입력 제거
                usePre = false;
            }


        }
    }

    private static void viewHistory() {
        if (history.isEmpty()) {
            System.out.println("계산 이력이 없습니다.");
        } else {
            System.out.println("\n=== 계산 이력 ===");
            for (int i = 0; i < history.size(); i++) {
                System.out.println((i + 1) + "번째 결과: " + history.get(i));
            }
        }
    }
}