package controller;
import service.ContactService;
import util.MenuOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final Scanner scan;
    private final ContactService contactService;
    public Menu(){
        this.scan = new Scanner(System.in);
        this.contactService = new ContactService();
    }
    public void newContactMenu(){
        System.out.println(">>>>Adicionar Contato<<<<");
        System.out.print("Digite o nome: ");
        scan.nextLine();
        String name = scan.nextLine();
        System.out.print("\nDigite o sobrenome: ");
        String lastName = scan.nextLine();
        System.out.print("\nQuantos números o contato possui? ");
        int qntNumbers = scan.nextInt();
        scan.nextLine();
        List<String> numbers = new ArrayList<>();
        for(int i = 1; i <= qntNumbers; i++){
            System.out.printf("\nDigite o %d° número: ", i);
            String number = scan.nextLine();
            numbers.add(number);
        }
        try{
            contactService.newContact(name, lastName, numbers);
            System.out.println("Contato salvo com sucesso");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void listAgenda(){

    }
    public void editContactMenu(){

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
        int choose = scan.nextInt();
        MenuOptions option = MenuOptions.getMenuOption(choose);
        if(option == null){
            unknownOption();
            return toContinue;
        }
        System.out.println(option.getValue());
        try{
            switch (option){
                case ADICIONAR_CONTATO -> newContactMenu();
                case REMOVER_CONTATO -> {

                }
                case EDITAR_CONTATO -> {

                }
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
