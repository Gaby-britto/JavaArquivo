package Service;

import java.util.List;
import java.util.Scanner;

import Models.Usuario;
import Utils.GerenciadorDeUsuarios;

public class HandleMenu {

	Scanner sc = new Scanner(System.in);
	// Gerenciador
	GerenciadorDeUsuarios gs = new GerenciadorDeUsuarios ();
	
	// Construtor vazio
	public HandleMenu() {
		// Toda vez que a classe menu, for instanciada, o nosso arquivo sera verificado
		gs.verificaECria("usuarios.txt");
	}
	public void criar() {
		System.out.println("Digite o nome:");
		String nome = sc.next();
		System.out.println("Digite a senha: ");
		String senha = sc.next();
		
		int id = getNextid();
		
		Usuario u = new Usuario( id, nome, senha);
		gs.adicionarUsuraios(u);
	}
	public void editar() {
		System.out.println("Digite o id do usuário: ");
		int id = sc.nextInt();
		System.out.println("Digite o novo nome: ");
		String nome = sc.next();
		System.out.println("Digite a nova senha: ");
		String senha = sc.next();
		
	}
	public void listar() {
		gs.listarUsuarios();
	}
	public void deletar() {
		System.out.println("Digite o ID do usário a ser deletado: ");
		int id = sc.nextInt();
		gs.deletarusuario(id);
	}
	public int getNextid() {
		List<Usuario> usuarios = gs.lerUsuario();
		int maxId = 0;
		// for => forEach
		for(Usuario usuario : usuarios) {
			int id = usuario.getId();
			if (id > maxId) {
				// Lógica para descobrir último id
				maxId = id;
				// 
			}
		}
		// soma 1 + o ultimo
		return maxId + 1;
	}
	public void UsuarioEspecifico() {
		gs.listarUsuarios();
		System.out.println("Digite o id do usuário: ");
		int id = sc.nextInt();
		gs.listarUnicoUsuario(id);
		
	}
	public void login() {
		
		System.out.println("Digite o nome de usuário: ");
		String nome = sc.next();
		System.out.println("Digite a senha: ");
		String senha = sc.next();
		gs.login(nome, senha);
	}
	
	public void editarSenha() {
		gs.listarUsuarios();
		System.out.println("-------------------------");
		System.out.println("Digite o id do usuário: ");
		int id = sc.nextInt();
		System.out.println("Digite a senha anterior: ");
		String senha = sc.next();
		gs.alterarSenha(id, senha);
	}
}
