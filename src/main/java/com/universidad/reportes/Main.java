package com.universidad.reportes;

import com.universidad.reportes.config.ConfiguracionReporte;
import com.universidad.reportes.exportador.GeneradorReporteService;
import com.universidad.reportes.exportador.abstractfactory.FabricaReporteExcel;
import com.universidad.reportes.exportador.abstractfactory.FabricaReporteHtml;
import com.universidad.reportes.exportador.abstractfactory.FabricaReportePdf;
import com.universidad.reportes.modelo.DatosReporte;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("==================================================================");
        System.out.println(" SISTEMA DE EXPORTACIÓN DE REPORTES ACADÉMICOS - UNIDAD 2 (UFPS)");
        System.out.println(" Estudiante: Jorge Martínez");
        System.out.println("==================================================================");

        // 1. Construir configuración compleja mediante Builder
        ConfiguracionReporte configPdf = new ConfiguracionReporte.Builder()
                .tamanoPagina("A4")
                .orientacion("VERTICAL")
                .textoEncabezado("Universidad Francisco de Paula Santander - Vicerrectoría")
                .incluirGraficos(true)
                .marcaAgua("COPIA OFICIAL")
                .numerarPaginas(true)
                .build();

        ConfiguracionReporte configExcel = new ConfiguracionReporte.Builder()
                .tamanoPagina("CARTA")
                .orientacion("HORIZONTAL")
                .textoEncabezado("UFPS - División de Sistemas y Estadística")
                .incluirGraficos(false)
                .build();

        // 2. Datos de prueba
        DatosReporte datos = new DatosReporte(
                "Consolidado de Calificaciones 2026-II",
                "Ing. Jonathan Rey",
                List.of(
                        "1156701A - Jorge Martínez - Calificación: 100/100",
                        "1156702B - Juan Pérez - Calificación: 92/100",
                        "1156703C - María Gómez - Calificación: 95/100"
                ),
                "Total estudiantes evaluados: 3. Aprobados: 100%."
        );

        var service = new GeneradorReporteService();

        // 3. Generación en PDF
        System.out.println(service.generarReporteCompleto(new FabricaReportePdf(), datos, configPdf));

        // 4. Generación en Excel
        System.out.println(service.generarReporteCompleto(new FabricaReporteExcel(), datos, configExcel));

        // 5. Generación en HTML
        System.out.println(service.generarReporteCompleto(new FabricaReporteHtml(), datos, configPdf));

        System.out.println("Demostración completada exitosamente.");
    }
}
