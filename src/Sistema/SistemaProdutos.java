package Sistema;

import java.util.Scanner;

import Service.HandleProdutos;

public class SistemaProdutos {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		HandleProdutos hm = new HandleProdutos();
		int opcao = 0;
		do {
			// \n
			System.out.println("-------------------");
			System.out.println("        MENU       ");
			System.out.println("-------------------");
			System.out.print("1 - Criar Produto \n2 - Editar Produto \n3 - Deletar Produto \n4 - Listar Produtos \n5 - Vizualisar Produto Único\n6 - Somar Valores\n7 - Somar Produtos \n9 - Sair\n");
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
				hm.produtoEspecifico();
				break;
			}
			case 6:{
				hm.somar();
				break;
			}
			case 7:{
				hm.somarProdutos();
				break;
			}
			default:
				System.out.println("Opção Inválida");
			}
			
		}while(opcao != 9);
		sc.close();
	}

}
