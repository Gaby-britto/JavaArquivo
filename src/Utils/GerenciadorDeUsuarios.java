package Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Models.Produto;
import Models.Usuario;

public class GerenciadorDeUsuarios {
	Scanner sc = new Scanner(System.in);

	// Final é quando um dado não pode ser redefinida
	private static final String NOME_ARQUIVO = "usuarios.txt";
	
	// Verificar a Existencia do nosso banco e criar caso não exista
	public void verificaECria(String nomeArquivo) {
		// file => arquivo
		File arquivo = new File(nomeArquivo);
		
		// Verifica se o arquivo existe
		if(arquivo.exists()) {
			System.out.println("Banco Funcionando");
		}else {
			// Tente criar, caso erro, exibe o erro
			try {
				// Criar o novo arqiuivo
				arquivo.createNewFile();
				System.out.println("Arquivo criado com sucesso!");
			} catch (IOException e) {
				System.out.println("Ocorreu um erro ao criar o arquivo: " + e.getMessage());
			}
		}
	}
	
	public void adicionarUsuraios(Usuario usuario) {
		 // BufferedWrite => Escrever
		// FileWrite => Escrever no arquivo
		// BufferedWriter proporciona uma eficiente escrita
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(NOME_ARQUIVO, true))) {
			bw.write(usuario.toString()); // 1,gaby,123654
			bw.newLine();// nova linha no arquivo txt
			System.out.println("Usuário adicionado com sucesso!");
		}catch(IOException e){
			System.out.println("Ocorreu um erro ao escrever no arquivo: " + e.getMessage());
		}
	}
	
	public List<Usuario> lerUsuario(){
		List<Usuario> usuarios = new ArrayList<Usuario>();
		// Buffered, file, reader
		try(BufferedReader br = new BufferedReader(new FileReader(NOME_ARQUIVO))){
			String linha;// linha => 1;nome;senha
			// percorrer todas as linhas enquanto seja diferente de vazio
			while((linha = br.readLine()) != null) {
				String[] partes = linha.split(";");// dividir em três partes 
				// Adicionar usuarios a lista
				usuarios.add(new Usuario(Integer.parseInt(partes[0]), partes[1], partes[2]));
			}
		}catch(IOException e) {
			System.out.println("Ocorreu um erro ao ler o arquivo: " + e.getMessage());
		}
		return usuarios;
	}
	
	public void deletarusuario(int id) {
		List<Usuario> usuarios = lerUsuario();
		
		if(usuarios.removeIf(usuario -> usuario.getId() == id)) {
			// Reescrevendo arquivo com novos usuários e alterados
			reescreverArquivo(usuarios);
			System.out.println("Usuário deletado com sucesso!");
		}else {
			System.out.println("Usuário não encontrado");
		}
	}
	
	public void reescreverArquivo(List<Usuario> usuarios) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(NOME_ARQUIVO))){
			for(Usuario usuario : usuarios) {
				bw.write(usuario.toString());
				bw.newLine();
			}
		}catch(IOException e) {
			System.out.println("Ocorreu um erro ao rescrever o arquivo: " + e.getMessage());
		}
	}
	
	public void listarUsuarios() {
		List<Usuario> usuarios = lerUsuario();
		if(usuarios.isEmpty()) {
			System.out.println("Nenhum usuário cadastrado");
		}else {
			System.out.println("Lista de usuários");
			for(Usuario usuario : usuarios) {
				System.out.println("ID: " + usuario.getId() + "\n Nome: " + usuario.getNome() + "\n Senha: " + usuario.getSenha());
			} 
		}
	}

	public void editarUsuario( int id, String novoNome, String novaSenha) {
		List<Usuario> usuarios = lerUsuario();
		boolean encontrado = false;
		
		for(Usuario usuario : usuarios) {
			if(usuario.getId() == id) {
				usuario.setNome(novoNome);
				usuario.setSenha(novaSenha);
				encontrado = true;
				break;
			}
		}
		if(encontrado) {
			reescreverArquivo(usuarios);
			System.out.println("usuário editado com sucesso!");
		}else { 
			System.out.println("Usuário não encontrados");
		}
	}
	
	public void listarUnicoUsuario(int id) {
		List<Usuario> usuarios = lerUsuario();
		boolean encontrado = false;
		for(Usuario usuario : usuarios) {
			if(usuario.getId() == id) {
				System.out.println("ID: " + usuario.getId() + "\n Nome: " + usuario.getNome() + "\n Senha: " + usuario.getSenha());
				encontrado = true;
				break;
			}
		} 
		if(encontrado) {
			reescreverArquivo(usuarios);
			System.out.println("usuário editado com sucesso!");
		}else { 
			System.out.println("Usuário não encontrados");
		}
	}

	public void login(String nome, String senha) {
		List<Usuario> usuarios = lerUsuario();
		boolean encontrado = false;
		for(Usuario usuario : usuarios) {
			if(usuario.getNome().equals(nome) && usuario.getSenha().equals(senha)) {
				encontrado = true;
			}
		}
		if(encontrado) {
			reescreverArquivo(usuarios);
			System.out.println("Fazendo login...");
		}else { 
			System.out.println("Dados incorretos");
		}
	}
	
	public void alterarSenha(int id, String senha) {
		List<Usuario> usuarios = lerUsuario();
		boolean encontrado = false;
		for(Usuario usuario : usuarios) {
			if(usuario.getId() == id) {
				if(usuario.getSenha().equals(senha)) {
					System.out.println("Digite a nova senha: ");
					String novaSenha = sc.next();
					usuario.setSenha(novaSenha);
					encontrado = true;
				}
			}
		}
		if(encontrado) {
			reescreverArquivo(usuarios);
			
			System.out.println("Senha atualizada");
		}else { 
			System.out.println("Dados incorretos");
		}
	}
	
}
