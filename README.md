# FITEC 🏋️‍♂️💻

**FITEC** es un sistema de gestión para una red de gimnasios con dos sucursales en Quito (Norte y Sur), implementando una base de datos distribuida que permite manejar información crítica como clientes, instructores, suscripciones y suplementos deportivos.  
Este proyecto fue desarrollado como parte de la materia de **Bases de Datos Distribuidas**, aplicando conceptos de **fragmentación, replicación** y **consistencia** en entornos distribuidos.

---

## ✨ Características clave

- 📍 Distribución de datos entre **QUITO_NORTE** y **QUITO_SUR**
- 🧩 Fragmentación horizontal por sucursal
- 🔁 Replicación de catálogos compartidos (suscripciones)
- 🧠 Gestión de operaciones localizadas y responsabilidades especializadas

---

## 📌 Índice de Contenidos

- [🏗 Arquitectura de la Base de Datos](#-arquitectura-de-la-base-de-datos)
- [📊 Modelo de Datos](#1-modelo-de-datos)
- [🧩 Convenciones de nombres](#-Convenciones-de-nombres-en-la-interfaz-gráfica-Scene Builder)
- [🌐 Estrategia de Distribución](#2-estrategia-de-distribución)
- [🧬 Fragmentación y Replicación](#3-fragmentación-y-replicación)
- [🎯 Funcionalidades Clave](#-funcionalidades-clave)
- [🔄 Operaciones Distribuidas](#operaciones-distribuidas)
- [🏢 Gestión de Sucursales](#gestión-de-sucursales)
- [🧾 Responsabilidades Especializadas](#responsabilidades-especializadas)
- [✅ Demostración de Funcionamiento](#-demostración-de-funcionamiento)
- [🛠 Herramientas Usadas](#-herramientas-usadas)
- [🧑🏻‍💻 Autores](#-autores)

---
## 📝 Estructura

📦 src  
┣ 📂 main  
┃ ┣ 📂 java  
┃ ┃ ┣ 📂 MetodosGlobales  
┃ ┃ ┗ 📂 ModuloFITEC  
┃ ┃ ┃ ┣ 📂 application  
┃ ┃ ┃ ┣ 📂 Controllers  
┃ ┃ ┃ ┣ 📂 DataBase  
┃ ┃ ┃ ┣ 📂 logic  
┃ ┃ ┃ ┃ ┗ 📂 DAOS
┃ ┃ ┃ ┣ 📜 module-info.java  
┃ ┃ ┃ ┣ 📂 resources  
┃ ┗ 📂 resources  
┃ ┃ ┃ ┣ 📂 ModuloFITEC  
┃ ┃ ┃ ┣ 📂 data  
┃ ┃ ┃ ┗ 📂 views  
┗ 📂 test 

---

## 🧩 Convenciones de nombres en la interfaz gráfica (Scene Builder)

### 🔘 Botones
- **Nombre del componente**: `button[NombreElemento]`
- **Método asociado**: `accion[NombreElemento]`

**Ejemplo**:
- `buttonConsultarCliente`
- `consultarCliente()`

---

### 📄 Campos de texto
- **Nombre del componente**: `text[NombreCampo]`
- **Método asociado (si aplica)**: `validar[NombreCampo]` o `obtener[NombreCampo]`

**Ejemplo**:
- `textNombreCliente`
- `validarNombreCliente()`  
- `obtenerNombreCliente()`

---

### 📋 Tablas (TableView)
- **Nombre del componente**: `table[NombreEntidad]`
- **Nombre de columna**: `col[NombreColumna]`
- **Método asociado**: `cargar[NombreEntidad]Tabla`

**Ejemplo**:
- `tableClientes`
- `colNombre`, `colCedula`, `colCorreo`
- `cargarClientesTabla()`

---

### 🎛️ ComboBox / ChoiceBox
- **Nombre del componente**: `combo[NombreElemento]` o `choice[NombreElemento]`
- **Método asociado**: `cargar[NombreElemento]Combo`

**Ejemplo**:
- `comboTipoDocumento`
- `cargarTipoDocumentoCombo()`

---

### 🧪 CheckBox / RadioButton
- **Nombre del componente**: 
  - `check[NombreElemento]`
  - `radio[NombreElemento]`
- **Método asociado**: `verificar[NombreElemento]Seleccionado`

**Ejemplo**:
- `checkAceptaTerminos`
- `radioMasculino`
- `verificarAceptaTerminosSeleccionado()`

---

### 🖊️ Labels
- **Nombre del componente**: `label[Descripcion]`

**Ejemplo**:
- `labelResultadoBusqueda`

---

## 🧑🏻‍💻 Autores

- **Mateo Simbaña** - Programador [@mateonicolasg](https://github.com/mateonicolasg)
- **Gregory Salazar** - Programador [@Gregory](https://github.com/GregorySD1707)
- **Fernando Huilca** - Programador [@FernandoHuilca](https://github.com/FernandoHuilca)
  
