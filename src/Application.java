import menu.MajorMenu;
import menu.Menu;
import menu.StudentMenu;


import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int opcao;

        do {
            Menu.exibirMenuPrincipal();
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    StudentMenu.studentManagement(sc);
                    break;
                case 2:
                    MajorMenu.majorManagement(sc);
                    break;
                case 3:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Escolha a opção 1, 2 ou 3.");
            }
        } while (opcao != 3);

        sc.close();
    }
}
