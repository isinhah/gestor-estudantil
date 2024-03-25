package menu;

import model.dao.DaoFactory;
import model.dao.MajorDao;
import model.dao.StudentDao;
import model.entities.Major;
import model.entities.Student;

import java.util.List;
import java.util.Scanner;

public class StudentMenu {

    public StudentMenu(Scanner scanner) {
    }

    public static void studentManagement(Scanner scanner) {
        StudentDao studentDao = DaoFactory.createStudentDao();
        MajorDao majorDao = DaoFactory.createMajorDao();

        int opcao;
        do {
            Menu.exibirMenuEstudantes();
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

                    System.out.print("Id do estudante que será atualizado: ");
                    int studentId = scanner.nextInt();
                    scanner.nextLine();
                    Student existingStudent = studentDao.findById(studentId);

                    System.out.println(existingStudent);

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
                    System.out.print("Insira o id do estudante: ");
                    int id = scanner.nextInt();
                    studentDao.deleteById(id);
                    System.out.println("Estudante excluído.");
                    break;
                case 4:
                    System.out.println("\n---- LISTAR ESTUDANTES POR CURSO ----");
                    System.out.print("Insira o id do curso: ");
                    int idMajor = scanner.nextInt();
                    scanner.nextLine();
                    Major idFromMajor = majorDao.findById(idMajor);

                    if (idFromMajor != null) {
                        System.out.println("Curso: " + idFromMajor.getName());
                        List<Student> studentList = studentDao.findByMajor(idFromMajor);
                        for (Student obj : studentList) {
                            System.out.println(obj);
                        }
                    }
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
}

