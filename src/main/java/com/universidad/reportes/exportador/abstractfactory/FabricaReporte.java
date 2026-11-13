package com.universidad.reportes.exportador.abstractfactory;

public interface FabricaReporte {
    Encabezado crearEncabezado();
    Cuerpo crearCuerpo();
    PiePagina crearPiePagina();
}
