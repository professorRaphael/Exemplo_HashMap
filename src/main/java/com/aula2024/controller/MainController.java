package com.aula2024.controller;
import com.aula2024.classes.Estudante;
import com.aula2024.view.MainView;
import java.util.HashMap;
import java.util.Scanner;

public class MainController {
    private final MainView view;
    private final HashMap<Integer, Estudante> hm;
    private int chave;

    public MainController() {
        view = new MainView();
        hm = new HashMap<>();
        inicializarDados();
        chave = 4;
    }

    private void inicializarDados() {
        hm.put(1, new Estudante(1062021, "Raphael", 8.5f));
        hm.put(2, new Estudante(1062021, "Caroline", 10f));
        hm.put(3, new Estudante(2062020, "Gilson", 6f));
    }

    public void iniciar() {
        int opcao;
        do {
            opcao = view.exibirMenu();
            switch (opcao) {
                case 1:
                    cadastrarAluno();
                    break;
                case 2:
                    exibirAlunos();
                    break;
                case 3:
                    alterarNota();
                    break;
                case 4:
                    excluirAluno();
                    break;
            }
        } while (opcao != 0);
        view.closeScanner();
    }

    private void cadastrarAluno() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Digite a Matricula: ");
            int matricula = scanner.nextInt();

            System.out.println("Digite o nome: ");
            String nome = scanner.next();

            System.out.println("Digite a nota: ");
            float nota;
            while (true) {
                while (!scanner.hasNextFloat()) {
                    System.out.println("Por favor, digite um número decimal válido para a nota: ");
                    scanner.next(); // Limpa o buffer
                }
                nota = scanner.nextFloat();
                if (nota < 0 || nota > 10) {
                    System.out.println("A nota deve estar entre 0 e 10. Por favor, digite novamente: ");
                } else {
                    break;
                }
            }

            hm.put(chave++, new Estudante(matricula, nome, nota));
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao cadastrar o aluno: " + e.getMessage());
        }
    }

    private void exibirAlunos() {
        try {
            if (!hm.isEmpty()) {
                hm.forEach((key, value) -> {
                    System.out.println("Matricula: " + value.getMatricula());
                    System.out.println("Nome: " + value.getNome());
                    System.out.println("Nota: " + value.getNota());
                    System.out.println("-----------------------------");
                });
            } else {
                System.out.println("Não há alunos cadastrados.");
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao exibir os alunos: " + e.getMessage());
        }
    }

    private void alterarNota() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite o nome do aluno que deseja alterar a nota: ");
            String nomePesquisa = scanner.next();
            boolean alunoEncontrado = false;
            for (Estudante estudante : hm.values()) {
                if (estudante.getNome().equalsIgnoreCase(nomePesquisa)) {
                    float novaNota;
                    while (true) {
                        System.out.println("Digite a nova nota: ");
                        while (!scanner.hasNextFloat()) {
                            System.out.println("Por favor, digite um número decimal válido para a nota: ");
                            scanner.next(); // Limpa o buffer
                        }
                        novaNota = scanner.nextFloat();
                        if (novaNota < 0 || novaNota > 10) {
                            System.out.println("A nota deve estar entre 0 e 10. Por favor, digite novamente: ");
                        } else {
                            break;
                        }
                    }
                    estudante.setNota(novaNota);
                    alunoEncontrado = true;
                    break;
                }
            }
            if (!alunoEncontrado) {
                System.out.println("Aluno não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao alterar a nota do aluno: " + e.getMessage());
        }
    }

    private void excluirAluno() {
        try {
            Scanner scanner = new Scanner(System.in);
            exibirAlunos();
            System.out.println("Digite o ID do aluno que deseja excluir: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, digite um número inteiro válido para o ID do aluno: ");
                scanner.next(); // Limpa o buffer
            }
            int id = scanner.nextInt();
            Estudante alunoRemovido = hm.remove(id);
            if (alunoRemovido != null) {
                System.out.println("Aluno removido com sucesso:");
                System.out.println("Matricula: " + alunoRemovido.getMatricula());
                System.out.println("Nome: " + alunoRemovido.getNome());
                System.out.println("Nota: " + alunoRemovido.getNota());
            } else {
                System.out.println("Aluno não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao excluir o aluno: " + e.getMessage());
        }
    }

}
