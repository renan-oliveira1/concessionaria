package domain.model;

public enum Paises {
    EUA("Estados Unidos"),
    ARG("Argentina"),
    DE("Alemanha"),
    CL("Chile"),
    MX("México"),
    JP("Japão"),
    CN("China");

    private String descricao;

    Paises(String descricao) {
        this.descricao = descricao;
    }

    public static Paises getPaisByString(String string) {

        switch (string) {
            case "Estados Unidos":
                return EUA;
            case "Argentina":
                return ARG;
            case "Alemanha":
                return DE;
            case "Chile":
                return CL;
            case "México":
                return MX;
            case "Japão":
                return JP;
            case "China":
                return CN;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return descricao;
    }

}
