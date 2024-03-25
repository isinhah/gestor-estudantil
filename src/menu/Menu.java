package menu;

import java.util.Scanner;

public class Menu {

    public Menu(Scanner scanner) {
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
}
