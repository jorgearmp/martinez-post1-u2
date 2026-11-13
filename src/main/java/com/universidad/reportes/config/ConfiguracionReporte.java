package com.universidad.reportes.config;

public class ConfiguracionReporte {
    private final String orientacion;       // VERTICAL, HORIZONTAL
    private final String tamanoPagina;       // A4, CARTA, OFICIO
    private final boolean incluirGraficos;
    private final boolean numerarPaginas;
    private final String textoEncabezado;
    private final String marcaAgua;
    private final String paletaColor;

    private ConfiguracionReporte(Builder builder) {
        this.orientacion = builder.orientacion;
        this.tamanoPagina = builder.tamanoPagina;
        this.incluirGraficos = builder.incluirGraficos;
        this.numerarPaginas = builder.numerarPaginas;
        this.textoEncabezado = builder.textoEncabezado;
        this.marcaAgua = builder.marcaAgua;
        this.paletaColor = builder.paletaColor;
    }

    public String getOrientacion() { return orientacion; }
    public String getTamanoPagina() { return tamanoPagina; }
    public boolean isIncluirGraficos() { return incluirGraficos; }
    public boolean isNumerarPaginas() { return numerarPaginas; }
    public String getTextoEncabezado() { return textoEncabezado; }
    public String getMarcaAgua() { return marcaAgua; }
    public String getPaletaColor() { return paletaColor; }

    @Override
    public String toString() {
        return String.format("ConfiguracionReporte[tamano=%s, orientacion=%s, encabezado='%s', graficos=%b, marcaAgua='%s']",
                tamanoPagina, orientacion, textoEncabezado, incluirGraficos, marcaAgua);
    }

    public static class Builder {
        private String orientacion = "VERTICAL";
        private String tamanoPagina = "A4";
        private boolean incluirGraficos = false;
        private boolean numerarPaginas = true;
        private String textoEncabezado = "Reporte Académico Oficial";
        private String marcaAgua = "CONFIDENCIAL";
        private String paletaColor = "AZUL_INSTITUCIONAL";

        public Builder orientacion(String orientacion) {
            this.orientacion = orientacion;
            return this;
        }

        public Builder tamanoPagina(String tamanoPagina) {
            this.tamanoPagina = tamanoPagina;
            return this;
        }

        public Builder incluirGraficos(boolean incluirGraficos) {
            this.incluirGraficos = incluirGraficos;
            return this;
        }

        public Builder numerarPaginas(boolean numerarPaginas) {
            this.numerarPaginas = numerarPaginas;
            return this;
        }

        public Builder textoEncabezado(String textoEncabezado) {
            this.textoEncabezado = textoEncabezado;
            return this;
        }

        public Builder marcaAgua(String marcaAgua) {
            this.marcaAgua = marcaAgua;
            return this;
        }

        public Builder paletaColor(String paletaColor) {
            this.paletaColor = paletaColor;
            return this;
        }

        public ConfiguracionReporte build() {
            // Validaciones de invariantes de negocio
            if (tamanoPagina == null || tamanoPagina.isBlank()) {
                throw new IllegalStateException("El tamaño de página es obligatorio.");
            }
            return new ConfiguracionReporte(this);
        }
    }
}
