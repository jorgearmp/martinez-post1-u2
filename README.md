# ACTIVIDAD POST-CONTENIDO — UNIDAD 2
## EXPORTACIÓN DE REPORTES ACADÉMICOS: ELECCIÓN JUSTIFICADA DE PATRONES CREACIONALES
**Estudiante:** Jorge Armando Martínez Peinado (Cód. 1152162)  
**Curso:** Patrones de Diseño de Software (1156701-A)  
**Institución:** Universidad Francisco de Paula Santander (UFPS)  

---

## 1. DECISIONES DE DISEÑO Y JUSTIFICACIÓN ARQUITECTÓNICA

### DECISIÓN 1: ELECCIÓN ENTRE ABSTRACT FACTORY Y FACTORY METHOD
Para el problema de exportación de reportes académicos se evaluaron dos alternativas canónicas: **Factory Method** y **Abstract Factory**. Con base en las preguntas diagnósticas de la Guía de Contenido (Sección 7.1), se tomó la decisión formal de implementar **Abstract Factory**:

1. **¿El sistema crea un único tipo de producto o una familia de productos relacionados que deben mantenerse coherentes entre sí?**
   - *Respuesta y Diagnóstico:* Un documento académico formal no es un bloque plano monolítico; está compuesto por tres subsistemas visuales fuertemente acoplados por el formato destino: **Encabezado**, **Cuerpo** y **Pie de Página**. En PDF, el encabezado requiere comandos de posicionamiento vectorial y fuentes Helvetica; en Excel requiere etiquetas XML de celda y filas (`<Row><Cell>`); en HTML requiere marcado semántico (`<header>`, `<main>`, `<footer>`).
   - El riesgo fundamental no era instanciar una clase equivocada, sino el peligro de **inconsistencia entre piezas de familias distintas** (por ejemplo, ensamblar un cuerpo de celdas Excel con un encabezado vectorial de PDF).

2. **¿Por qué se descartó Factory Method para la estructura interna?**
   - Si se utilizara Factory Method, tendríamos un `crearExportador()` que devuelve un `ExportadorPdf`. Este exportador único se convertiría rápidamente en un objeto de gran tamaño que mezcla la lógica del renderizado de encabezados, tablas y pies de página.
   - Con **Abstract Factory**, la interfaz `FabricaReporte` expone `crearEncabezado()`, `crearCuerpo()` y `crearPiePagina()`. Se garantiza por construcción que una `FabricaReportePdf` jamás mezcle componentes con `FabricaReporteExcel`, alcanzando máxima cohesión e interoperabilidad. Para añadir en el futuro un formato como **CSV**, simplemente se implementa `FabricaReporteCsv` sin tocar una sola línea de código existente (OCP).

---

### DECISIÓN 2: PATRÓN BUILDER PARA CONFIGURACIÓN COMPLEJA
Para gestionar las múltiples opciones de presentación del reporte (orientación, tamaño de página, inclusión de gráficos, marcas de agua institucionales, colores corporativos), se implementó el patrón **Builder** en la clase `ConfiguracionReporte`:
- Evita la fragilidad y confusión de constructores con 7 parámetros (`telescoping constructors`).
- Asegura la **inmutabilidad** del objeto de configuración una vez emitido mediante `.build()`.
- Valida invariantes obligatorias antes de entregar la instancia.

---

### EVALUACIÓN CRÍTICA DEL PATRÓN SINGLETON
Se evaluó si la configuración de los reportes debía almacenarse en un Singleton global (`ConfiguracionGlobal.getInstance()`). Dicha propuesta fue **terminantemente rechazada** por las siguientes razones de ingeniería:
1. **Destrucción de la Concurrencia:** En una aplicación Spring Boot web donde múltiples profesores generan reportes simultáneamente con distintas configuraciones (uno horizontal sin gráficos, otro vertical con marca de agua), un Singleton mutable provocaría que las peticiones se sobreescribieran entre sí.
2. **Pérdida de Testabilidad:** Un Singleton global impide simular configuraciones aisladas en pruebas unitarias paralelas.
3. **Solución Adoptada:** Las instancias de `ConfiguracionReporte` son inmutables y se transfieren como argumentos de método (`GeneradorReporteService`), manteniendo los componentes sin estado (*stateless*) y seguros para ejecución multihilo (*thread-safe*).

---

## 2. INSTRUCCIONES DE EJECUCIÓN
```bash
mvn clean compile
mvn exec:java
```
