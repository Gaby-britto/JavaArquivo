package Service;

import java.util.List;
import java.util.Scanner;

import Models.Produto;
import Utils.GerenciadorDeProdutos;

public class HandleProdutos {
    Scanner sc = new Scanner(System.in);
    // Gerenciador
    GerenciadorDeProdutos gs = new GerenciadorDeProdutos();

    // Construtor vazio
    public HandleProdutos() {
        // Toda vez que a classe menu, for instanciada, o nosso arquivo sera verificado
        gs.verificaECria("produtos.txt");
    }

    public void criar() {
        System.out.println("Digite o nome:");
        String nome = sc.next();
        System.out.println("Digite o Preço");
        double preco = sc.nextDouble();
        System.out.println("Digite a quantidade: ");
        int quantidade = sc.nextInt();

        long id = getNextId();

        Produto u = new Produto(id, nome, preco, quantidade);
        gs.adicionarProdutos(u);
    }

    public void editar() {
        System.out.println("Digite o id do Produto: ");
        long id = sc.nextLong();
        System.out.println("Digite o novo Nome: ");
        String nome = sc.next();
        System.out.println("Digite o novo Preço: ");
        double preco = sc.nextDouble();
        System.out.println("Digite a quantidade: ");
        int quantidade = sc.nextInt();

        gs.editarProduto(id, nome, preco, quantidade);
    }

    public void listar() {
        gs.listarProdutos();
    }

    public void deletar() {
        System.out.println("Digite o ID do produto a ser deletado: ");
        long id = sc.nextLong();
        gs.deletarProduto(id);
    }

    public long getNextId() {
        List<Produto> produtos = gs.lerProduto();
        long maxId = 0;
        // forEach loop
        for (Produto produto : produtos) {
            long id = produto.getId();
            if (id > maxId) {
                maxId = id;
            }
        }
        // soma 1 ao último id
        return maxId + 1;
    }

    public void produtoEspecifico() {
        System.out.println("Digite o id do produto: ");
        long id = sc.nextLong();
        gs.listarUnicoProduto(id);
    }
    public void somar() {
    	gs.somarPrecos();
    }
    public void somarProdutos() {
    	gs.somarProdutos();
    }
}
