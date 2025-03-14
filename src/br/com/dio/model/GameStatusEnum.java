package br.com.dio.model;

public enum GameStatusEnum {

    NON_STARTED("Não iniciado"),
    INCOMPLETE("Incompleto"),
    COMPLETED("Completo");
    
    private String label;

    GameStatusEnum(final String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }


}
