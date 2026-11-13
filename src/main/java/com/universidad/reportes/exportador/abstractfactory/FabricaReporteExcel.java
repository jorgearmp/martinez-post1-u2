package com.universidad.reportes.exportador.abstractfactory;

import java.util.List;

public class FabricaReporteExcel implements FabricaReporte {
    @Override
    public Encabezado crearEncabezado() {
        return (titulo, inst) -> String.format("<Worksheet ss:Name='%s'><Row><Cell><Data ss:Type='String'>%s - %s</Data></Cell></Row>", titulo, titulo, inst);
    }

    @Override
    public Cuerpo crearCuerpo() {
        return (filas, graficos) -> {
            var sb = new StringBuilder("<Table>\n");
            for (int i = 0; i < filas.size(); i++) {
                sb.append(String.format("  <Row ss:Index='%d'><Cell><Data ss:Type='String'>%s</Data></Cell></Row>\n", i + 2, filas.get(i)));
            }
            if (graficos) sb.append("  <Row><Cell><Comment>Gráfico dinámico ExcelPivot vinculado</Comment></Cell></Row>\n");
            sb.append("</Table>");
            return sb.toString();
        };
    }

    @Override
    public PiePagina crearPiePagina() {
        return (resumen, numerar) -> String.format("<Footer ss:Data='%s'/></Worksheet>", resumen);
    }
}
