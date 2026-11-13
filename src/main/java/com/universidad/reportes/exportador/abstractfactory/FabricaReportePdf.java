package com.universidad.reportes.exportador.abstractfactory;

import java.util.List;

public class FabricaReportePdf implements FabricaReporte {
    @Override
    public Encabezado crearEncabezado() {
        return (titulo, inst) -> String.format("[PDF-HEADER font='Helvetica-Bold' size=16]\n%s | %s\n----------------------------------------", titulo, inst);
    }

    @Override
    public Cuerpo crearCuerpo() {
        return (filas, graficos) -> {
            var sb = new StringBuilder("[PDF-BODY stream=binary]\n");
            filas.forEach(f -> sb.append("  * [Fila PDF] ").append(f).append("\n"));
            if (graficos) sb.append("  [PDF-CHART type='VectorVectorial'] Renderizando gráfico vectorial embebido...\n");
            return sb.toString();
        };
    }

    @Override
    public PiePagina crearPiePagina() {
        return (resumen, numerar) -> String.format("[PDF-FOOTER]\n%s%s", resumen, numerar ? " | Pág. 1 de 1" : "");
    }
}
