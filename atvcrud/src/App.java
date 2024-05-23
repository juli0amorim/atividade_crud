import java.util.*;
import java.io.*;

// Classe do Livro
class Livro {
    String titulo;
    String autor;

    Livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Título: " + this.titulo + ", Autor: " + this.autor;
    }
}

class Main {
    static List<Livro> livros = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    // Menu de seleção
    public static void main(String[] args) {
        carregarDados();
        while (true) {
            System.out.println("\n╔═════════════════════════════╗");
            System.out.println("║      Menu de Seleção:       ║");
            System.out.println("╠═════════════════════════════╣");
            System.out.println("║ 1. Adicionar livro          ║");
            System.out.println("║ 2. Listar livros            ║");
            System.out.println("║ 3. Atualizar livro          ║");
            System.out.println("║ 4. Deletar livro            ║");
            System.out.println("║ 5. Sair                     ║");
            System.out.println("╚═════════════════════════════╝");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarLivro();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    atualizarLivro();
                    break;
                case 4:
                    deletarLivro();
                    break;
                case 5:
                    salvarDados();
                    System.exit(0);
            }
        }
    }

    //Menu para adicionar Livro
    static void adicionarLivro() {
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();
        System.out.print("Digite o nome do autor: ");
        String autor = scanner.nextLine();
        livros.add(new Livro(titulo, autor));
    }

    // Menu para listar Livros
    static void listarLivros() {
        for (int i = 0; i < livros.size(); i++) {
            System.out.println((i + 1) + ". " + livros.get(i));
        }
    }

    // Menu para atualizar os Livros
    static void atualizarLivro() {
        listarLivros();
        System.out.print("Escolha o número do livro para atualizar: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine(); // consome a linha restante
        System.out.print("Digite o novo título do livro: ");
        String titulo = scanner.nextLine();
        System.out.print("Digite o novo nome do autor: ");
        String autor = scanner.nextLine();
        livros.set(index, new Livro(titulo, autor));
    }

    // Menu para deletar o Livro
    static void deletarLivro() {
        listarLivros();
        System.out.print("Escolha o número do livro para deletar: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine(); // consome a linha restante
        livros.remove(index);
    }

    // Menu para deletar o Livro
    static void carregarDados() {
        try {
            File file = new File("livros.txt");
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String[] dados = fileScanner.nextLine().split(",");
                livros.add(new Livro(dados[0], dados[1]));
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado. Um novo arquivo será criado.");
        }
    }

    // Salvando dados
    static void salvarDados() {
        try {
            FileWriter writer = new FileWriter("livros.txt");
            for (Livro livro : livros) {
                writer.write(livro.titulo + "," + livro.autor + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao salvar os dados.");
        }
    }
}
