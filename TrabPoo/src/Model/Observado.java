package Model;

import View.Observador;

public interface Observado {
    void registraObservador(Observador observador);
    void removeObservador(Observador observador);
    void notificaObservadores();
}
