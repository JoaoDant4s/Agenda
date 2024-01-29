package controller;
import service.ContactService;
import util.MenuOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final Scanner scan;
    private ContactService contactService;
    public Menu() throws Exception {
        this.scan = new Scanner(System.in);
        this.contactService = ContactService.getInstance();
    }
    public void newContactMenu(){
        System.out.println(">>>> Adicionar Contato <<<<");
        System.out.print("Digite o nome: ");
        scan.nextLine();
        String name = scan.nextLine();
        System.out.print("Digite o sobrenome: ");
        String lastName = scan.nextLine();
        System.out.print("Quantos números o contato possui? ");
        int qntNumbers = scan.nextInt();
        scan.nextLine();
        List<String> numbers = new ArrayList<>();
        for(int i = 1; i <= qntNumbers; i++){
            System.out.printf("Digite o %d° número: ", i);
            String number = scan.nextLine();
            numbers.add(number);
        }
        try{
            contactService.newContact(name, lastName, numbers);
            System.out.println("\nContato salvo com sucesso\n");
        } catch (Exception e){
            System.err.println("Erro: " + e.getMessage());
        }
    }
    public void listAgenda(){
        try{
            contactService.printAllContacts();
        } catch(Exception e){
            System.out.println("Erro ao listar contatos: " + e.getMessage());
        }
    }
    public void editContactMenu(){

    }

    public void deleteContactMenu(){
        System.out.println(">>>> Deletar Contato <<<<");
        System.out.print("Digite o ID do contato que quer remover: ");
        long id = scan.nextLong();
        try{
            contactService.deleteContact(id);
            System.out.println("Contato deletado com sucesso");
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
    public void printMainMenu(){
        System.out.println(">>>> Menu <<<<");
        for (MenuOptions option : MenuOptions.values()) {
            System.out.println(option.getValue() + " - " + option.getDescription());
        }
    }
    public boolean mainMenu() throws Exception {
        printMainMenu();
        boolean toContinue = true;
        System.out.print("Sua escolha: ");
        int choose = scan.nextInt();
        MenuOptions option = MenuOptions.getMenuOption(choose);
        if(option == null){
            unknownOption();
            return toContinue;
        }
        try{
            switch (option){
                case ADICIONAR_CONTATO -> newContactMenu();
                case LISTAR_CONTATOS -> listAgenda();
                case REMOVER_CONTATO -> System.out.println("Tentei sobrescrever o arquivo instanciando outro writer com o append false mas tá apagando o .txt inteiro quando encerro o programa");
                case EDITAR_CONTATO -> System.out.println("Not implemented yet");
                case SAIR -> {
                    toContinue = false;
                    System.out.println("Sessão encerrada");
                }
                default -> unknownOption();
            }
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return toContinue;
    }
    public void unknownOption(){
        System.out.println("A opção informada não é válida");
    }
    public void closeScanner(){
        this.scan.close();
    }
}
