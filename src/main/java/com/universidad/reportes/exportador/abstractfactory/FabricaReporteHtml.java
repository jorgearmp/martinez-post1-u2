package com.universidad.reportes.exportador.abstractfactory;

import java.util.List;

public class FabricaReporteHtml implements FabricaReporte {
    @Override
    public Encabezado crearEncabezado() {
        return (titulo, inst) -> String.format("<header class='report-header'><h1>%s</h1><p class='badge'>%s</p></header>", titulo, inst);
    }

    @Override
    public Cuerpo crearCuerpo() {
        return (filas, graficos) -> {
            var sb = new StringBuilder("<main class='report-grid'><ul>\n");
            filas.forEach(f -> sb.append("  <li>").append(f).append("</li>\n"));
            sb.append("</ul>\n");
            if (graficos) sb.append("  <div class='chart-container'><canvas id='chartJs'></canvas></div>\n");
            sb.append("</main>");
            return sb.toString();
        };
    }

    @Override
    public PiePagina crearPiePagina() {
        return (resumen, numerar) -> String.format("<footer class='report-footer'><small>%s</small>%s</footer>", resumen, numerar ? " <span>Pág. 1</span>" : "");
    }
}
