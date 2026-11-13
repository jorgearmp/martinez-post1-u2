package com.universidad.reportes.exportador.abstractfactory;

import java.util.List;

public interface Cuerpo {
    String renderizarCuerpo(List<String> filas, boolean incluirGraficos);
}
