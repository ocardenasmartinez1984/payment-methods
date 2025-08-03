# Payment Methods Project

Este proyecto procesa un archivo JSON con métodos de pago y genera una salida.

## 🧰 Requisitos

- Java 17 o superior
- Las dependencias JAR necesarias están en la carpeta `libs/`.
- El archivo de entrada `entrega.json` debe estar disponible en el directorio raíz del proyecto o donde se indique.

## 🚀 Instrucciones de uso

Clona el repositorio:

```bash
git clone https://github.com/ocardenasmartinez1984/payment-methods.git
cd payment-methods
cd payment-methods-project
```

Compilar el proyecto:
```bash
javac -d out -cp "libs/*" @sources.txt
jar cfm payment-methods.jar MANIFEST.MF -C out/ .
```

Ejecutar el proyecto:
```bash
java -jar payment-methods.jar ../entrega.json
```

Las salidas son dos archivos en la carpeta output en la raiz del proyecto, dte.xml contiene todos los documentos en detalle y reporte.html contiene un resumen de los documentos.