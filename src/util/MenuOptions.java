package util;

public enum MenuOptions {
    ADICIONAR_CONTATO(1, "Adicionar Contato"),
    LISTAR_CONTATOS(2, "Listar Contatos"),
    REMOVER_CONTATO(3, "Remover Contato"),
    EDITAR_CONTATO(4, "Editar Contato"),
    SAIR(5, "Sair");
    private final int value;
    private final String description;

    MenuOptions(int value, String description) {
        this.value = value;
        this.description = description;
    }
    public static MenuOptions getMenuOption(int value){
        for(MenuOptions option : MenuOptions.values()){
            if(option.getValue() == value){
                return option;
            }
        }
        return null;
    }
    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
