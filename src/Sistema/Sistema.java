package Sistema;

import java.util.Scanner;

import Service.HandleMenu;

public class Sistema {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		HandleMenu hm = new HandleMenu();
		int opcao = 0;
		do {
			// \n
			System.out.print("1 - Criar Usuário \n2 - Editar Usuário \n3 - Deletar Usuário \n4 - Listar Usuário \n5 - Vizualisar Usuário Único \n6 - Login\n7 - Alterar Senha \n9 - Sair\n");
			opcao = sc.nextInt();
			
			switch(opcao) {
			case 1: {
				hm.criar();
				break;
			}
			case 2: {
				hm.editar();
				break;
			}
			case 3:{
				hm.deletar();
				break;
			}
			case 4:{
				hm.listar();
				break;
				
			}
			case 5:{
				hm.UsuarioEspecifico();
				break;
			}
			case 6:{
				hm.login();
				break;
			}
			case 7:{
				hm.editarSenha();
				break;
			}
			default:
				System.out.println("Opção Inválida");
			}
			
		}while(opcao != 9);
		sc.close();
	}

}
