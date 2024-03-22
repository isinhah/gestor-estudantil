package menu;

import model.dao.DaoFactory;
import model.dao.StudentDao;
import model.entities.Major;
import model.entities.Student;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;

    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    public static void exibirMenuPrincipal() {
        System.out.println("\n---- MENU PRINCIPAL ----");
        System.out.println("1. Gerenciar estudantes");
        System.out.println("2. Gerenciar cursos");
        System.out.println("3. Encerrar o programa");
        System.out.print("Escolha uma opção: ");
    }

    public static void exibirMenuEstudantes() {
        System.out.println("\n---- GERENCIAMENTO DE ESTUDANTES ----");
        System.out.println("1. Cadastrar estudante");
        System.out.println("2. Atualizar dados do estudante");
        System.out.println("3. Excluir estudante");
        System.out.println("4. Listar estudantes por curso");
        System.out.println("5. Listar todos os estudantes");
        System.out.println("6. Voltar ao menu principal");
        System.out.print("Escolha uma opção: ");
    }

    public static void exibirMenuCursos() {
        System.out.println("\n---- GERENCIAMENTO DE CURSOS ----");
        System.out.println("1. Cadastrar curso");
        System.out.println("2. Atualizar dados do curso");
        System.out.println("3. Excluir curso");
        System.out.println("4. Listar todos os cursos");
        System.out.println("5. Voltar ao menu principal");
        System.out.print("Escolha uma opção: ");
    }

    public static void gerenciarEstudantes(Scanner scanner) {
        StudentDao studentDao = DaoFactory.createStudentDao();

        int opcao;
        do {
            exibirMenuEstudantes();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("\n---- CADASTRAR ESTUDANTE ----");

                    System.out.print("Nome do estudante: ");
                    String name = scanner.nextLine();
                    System.out.print("Email do estudante: ");
                    String email = scanner.nextLine();
                    System.out.print("Id do curso: ");
                    int majorId = scanner.nextInt();
                    Student newStudent = new Student(null, name, email, new Major(majorId, null));
                    studentDao.insert(newStudent);
                    System.out.println("Adicionado! Id do estudante: = " + newStudent.getId());
                    break;
                case 2:
                    System.out.println("\n---- ATUALIZAR DADOS DO ESTUDANTE ----");

                    System.out.println("Id do estudante que será atualizado: ");
                    int studentId = scanner.nextInt();
                    scanner.nextLine();
                    Student existingStudent = studentDao.findById(studentId);

                    System.out.println("Estudante existente: " + existingStudent);

                    // Verificar se o estudante foi encontrado
                    if (existingStudent != null) {
                        System.out.print("Novo nome do estudante: ");
                        String newName = scanner.nextLine();
                        System.out.print("Novo email do estudante: ");
                        String newEmail = scanner.nextLine();
                        System.out.print("Novo id do curso do estudante: ");
                        int newMajorId = scanner.nextInt();
                        scanner.nextLine();

                        Student updatedStudent = new Student(existingStudent.getId(), newName, newEmail, new Major(newMajorId, null));
                        studentDao.update(updatedStudent);
                        System.out.println("Atualizado! Id do estudante: " + updatedStudent.getId());
                    } else {
                        System.out.println("Estudante não encontrado!");
                    }
                    break;
                case 3:
                    System.out.println("\n---- DELETAR ESTUDANTE ----");
                    break;
                case 4:
                    System.out.println("\n---- LISTAR ESTUDANTES POR CURSO ----");
                    break;
                case 5:
                    System.out.println("\n---- LISTAR TODOS OS ESTUDANTES ----");
                    List<Student> list = studentDao.findAll();
                    for (Student obj : list) {
                        System.out.println(obj);
                    }
                    break;
                case 6:
                    System.out.println("Retornando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        } while (opcao != 6);
    }
    public static void gerenciarCursos(Scanner scanner) {
        int opcao;
        do {
            exibirMenuCursos();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Cadastrar curso selecionado.");
                    break;
                case 2:
                    System.out.println("Atualizar dados do curso selecionado.");
                    break;
                case 3:
                    System.out.println("Excluir curso selecionado.");
                    break;
                case 4:
                    System.out.println("Listar todos os cursos selecionado.");
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

