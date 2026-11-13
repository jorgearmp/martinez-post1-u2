package com.universidad.reportes.exportador;

import com.universidad.reportes.config.ConfiguracionReporte;
import com.universidad.reportes.exportador.abstractfactory.FabricaReporte;
import com.universidad.reportes.modelo.DatosReporte;

public class GeneradorReporteService {
    public String generarReporteCompleto(FabricaReporte fabrica, DatosReporte datos, ConfiguracionReporte config) {
        var encabezado = fabrica.crearEncabezado();
        var cuerpo = fabrica.crearCuerpo();
        var pie = fabrica.crearPiePagina();

        var sb = new StringBuilder();
        sb.append("=== GENERANDO DOCUMENTO CON ").append(fabrica.getClass().getSimpleName()).append(" ===\n");
        sb.append("Configuración aplicada: ").append(config).append("\n");
        sb.append(encabezado.renderizarEncabezado(datos.titulo(), config.getTextoEncabezado())).append("\n");
        sb.append(cuerpo.renderizarCuerpo(datos.filasDatos(), config.isIncluirGraficos())).append("\n");
        sb.append(pie.renderizarPie(datos.resumenFinal(), config.isNumerarPaginas())).append("\n");
        sb.append("=== FIN DOCUMENTO ===\n");
        return sb.toString();
    }
}
