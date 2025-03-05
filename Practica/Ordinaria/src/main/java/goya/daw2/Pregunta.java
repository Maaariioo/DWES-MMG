package goya.daw2;

public class Pregunta {
    private String texto;
    private String[] opciones;
    private int correcta;

    // Constructor
    public Pregunta(String texto, String[] opciones, int correcta) {
        this.texto = texto;
        this.opciones = opciones;
        this.correcta = correcta;
    }

    // Getters y Setters
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String[] getOpciones() {
        return opciones;
    }

    public void setOpciones(String[] opciones) {
        this.opciones = opciones;
    }

    public int getCorrecta() {
        return correcta;
    }

    public void setCorrecta(int correcta) {
        this.correcta = correcta;
    }
}