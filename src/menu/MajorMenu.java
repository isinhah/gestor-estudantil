package menu;

import model.dao.DaoFactory;
import model.dao.MajorDao;
import model.entities.Major;

import java.util.List;
import java.util.Scanner;

public class MajorMenu {

    public MajorMenu(Scanner scanner) {
    }

    public static void majorManagement(Scanner scanner) {
        MajorDao majorDao = DaoFactory.createMajorDao();

        int opcao;
        do {
            Menu.exibirMenuCursos();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("\n---- CADASTRAR CURSO ----");

                    System.out.print("Nome do curso: ");
                    String name = scanner.nextLine();
                    Major newMajor = new Major(null, name);
                    majorDao.insert(newMajor);
                    System.out.println("Adicionado! Id do curso: " + newMajor.getId());
                    break;
                case 2:
                    System.out.println("\n---- ATUALIZAR DADOS DO CURSO ----");

                    System.out.print("Id do curso que será atualizado: ");
                    int majorId = scanner.nextInt();
                    scanner.nextLine();
                    Major existingMajor = majorDao.findById(majorId);

                    System.out.println(existingMajor);

                    if (existingMajor != null) {
                        System.out.print("Novo nome do curso: ");
                        String newName = scanner.nextLine();

                        Major updatedMajor = new Major(existingMajor.getId(), newName);
                        majorDao.update(updatedMajor);
                        System.out.println("Atualizado! Id do curso: " + updatedMajor.getId());
                    } else {
                        System.out.println("Curso não encontrado!");
                    }
                    break;
                case 3:
                    System.out.println("\n---- DELETAR CURSO ----");
                    System.out.print("Insira o id do curso: ");
                    int id = scanner.nextInt();
                    majorDao.deleteById(id);
                    System.out.println("Curso excluído.");
                    break;
                case 4:
                    System.out.println("\n---- LISTAR TODOS OS CURSOS ----");
                    List<Major> majorList = majorDao.findAll();
                    for (Major obj : majorList) {
                        System.out.println(obj);
                    }
                    break;
                case 5:
                    System.out.println("Retornando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        } while (opcao != 5);
    }
}