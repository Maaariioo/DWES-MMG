package goya.daw2;

public class Pregunta {
    private String texto;
    private String[] opciones;
    private String respuestaCorrecta;

    public Pregunta(String texto, String[] opciones, String respuestaCorrecta) {
		super();
		this.texto = texto;
		this.opciones = opciones;
		this.respuestaCorrecta = respuestaCorrecta;
	}

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

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }
}
