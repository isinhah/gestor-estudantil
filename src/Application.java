import menu.Menu;
import model.dao.DaoFactory;
import model.dao.MajorDao;
import model.dao.StudentDao;
import model.entities.Major;
import model.entities.Student;


import java.util.List;
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
                    Menu.gerenciarEstudantes(sc);
                    break;
                case 2:
                    Menu.gerenciarCursos(sc);
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
