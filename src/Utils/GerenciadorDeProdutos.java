package Utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Models.Produto;

public class GerenciadorDeProdutos {

    // Final é quando um dado não pode ser redefinido
    private static final String NOME_ARQUIVO = "produtos.txt";

    // Verificar a Existencia do nosso banco e criar caso não exista
    public void verificaECria(String nomeArquivo) {
        // file => arquivo
        File arquivo = new File(nomeArquivo);

        // Verifica se o arquivo existe
        if (arquivo.exists()) {
            System.out.println("--Banco Funcionando--");
        } else {
            // Tente criar, caso erro, exibe o erro
            try {
                // Criar o novo arquivo
                arquivo.createNewFile();
                System.out.println("Arquivo criado com sucesso!");
            } catch (IOException e) {
                System.out.println("Ocorreu um erro ao criar o arquivo: " + e.getMessage());
            }
        }
    }

    public void adicionarProdutos(Produto produto) {
        // BufferedWriter proporciona uma escrita eficiente
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOME_ARQUIVO, true))) {
            bw.write(produto.toString());
            bw.newLine(); // nova linha no arquivo txt
            System.out.println("Produto adicionado com sucesso!");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    public List<Produto> lerProduto() {
        List<Produto> produtos = new ArrayList<Produto>();
        // Buffered, file, reader
        try (BufferedReader br = new BufferedReader(new FileReader(NOME_ARQUIVO))) {
            String linha; // linha => 1;nome;senha
            // percorrer todas as linhas enquanto seja diferente de vazio
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";"); // dividir em três partes
                // Adicionar produtos à listam
                produtos.add(new Produto(Long.parseLong(partes[0]), partes[1], Double.parseDouble(partes[2]),Integer.parseInt(partes[3])));
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao ler o arquivo: " + e.getMessage());
        }
        return produtos;
    }

    public void deletarProduto(long id) {
        List<Produto> produtos = lerProduto();

        if (produtos.removeIf(produto -> produto.getId() == id)) {
            // Reescrevendo arquivo com novos produtos e alterações
            reescreverArquivo(produtos);
            System.out.println("Produto deletado com sucesso!");
        } else {
            System.out.println("Produto não encontrado");
        }
    }

    public void reescreverArquivo(List<Produto> produtos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOME_ARQUIVO))) {
            for (Produto produto : produtos) {
                bw.write(produto.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao reescrever o arquivo: " + e.getMessage());
        }
    }

    public void listarProdutos() {
        List<Produto> produtos = lerProduto();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado");
        } else {
            System.out.println("Lista de produtos");
            for (Produto produto : produtos) {
                System.out.println("ID: " + produto.getId() + "\n Nome: " + produto.getNome() + "\n Preço: "
                        + produto.getPreco() + "\n Quantidade: " + produto.getQuantidade());
            }
        }
    }

    public void editarProduto(long id, String novoNome, double novoPreco, int novaQuantidade) {
        List<Produto> produtos = lerProduto();
        boolean encontrado = false;

        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                produto.setNome(novoNome);
                produto.setPreco(novoPreco);
                produto.setQuantidade(novaQuantidade);
                encontrado = true;
                break;
            }
        }
        if (encontrado) {
            reescreverArquivo(produtos);
            System.out.println("Produto editado com sucesso!");
        } else {
            System.out.println("Produto não encontrado");
        }
    }

    public void listarUnicoProduto(long id) {
        List<Produto> produtos = lerProduto();
        boolean encontrado = false;
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                System.out.println("ID: " + produto.getId() + "\n Nome: " + produto.getNome() + "\n Preço: "
                        + produto.getPreco() + "\n Quantidade: " + produto.getQuantidade());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Produto não encontrado");
        }
    }
    
    public void somarPrecos() {
    	List<Produto> produtos = lerProduto();
    	int soma = 0;
    	  for (Produto produto : produtos) {
        		  soma += produto.getPreco() * produto.getQuantidade();  	  
    	  }
    	  System.out.println("A somas dos valores é: " + soma);
    }
    
    public void somarProdutos(){
    	List<Produto> produtos = lerProduto();
    	int soma = 0;
    	  for (Produto produto : produtos) {
        		   soma += produto.getQuantidade();  	  
    	  }
    	  System.out.println("A somas dos Produtos é: " + soma);
    }
}
