package com.universidad.reportes.modelo;

import java.util.List;

public record DatosReporte(String titulo, String autor, List<String> filasDatos, String resumenFinal) {}
