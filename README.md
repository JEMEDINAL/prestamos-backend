# Sistema de Gestión de Préstamos (Loan Management System)

Una aplicación backend robusta desarrollada en **Java 21** y **Spring Boot 3.4.1**, estructurada bajo los principios de **Arquitectura Hexagonal (Puertos y Adaptadores)**. El sistema está diseñado para gestionar solicitudes de préstamos de manera segura, eficiente y sin estado (stateless), permitiendo a los usuarios registrados solicitar montos y esperar la revisión, aprobación o rechazo por parte de los administradores.

## 🚀 Características Principales

* **Arquitectura Hexagonal (Ports and Adapters):** Desacoplamiento total del núcleo de dominio frente a frameworks externos, bases de datos o interfaces de usuario, facilitando las pruebas unitarias y la mantenibilidad.
* **Arquitectura Sin Estado (Stateless):** Autenticación y autorización gestionadas mediante tokens seguros, ideal para arquitecturas modernas y escalables.
* **Control de Acceso Basado en Roles (RBAC):** Implementado con **Spring Security**, diferenciando claramente entre usuarios regulares (`ROLE_USER`) y administradores (`ROLE_ADMIN`).
* **Gestión Transaccional Segura:** Uso extensivo de `@Transactional` en los casos de uso (puertos de entrada/salida) para garantizar la consistencia e integridad de los datos.
* **Caché en Memoria RAM:** Integración con **Ehcache** a través de adaptadores de infraestructura para optimizar el rendimiento y reducir la carga en la base de datos.

## 🛠️ Tecnologías Utilizadas

* **Lenguaje:** Java 21
* **Framework:** Spring Boot 3.4.1
* **Patrón Arquitectónico:** Arquitectura Hexagonal (Ports & Adapters)
* **Seguridad:** Spring Security (Stateless / JWT o equivalente)
* **Persistencia y Transacciones:** Spring Data JPA, Hibernate, `@Transactional`
* **Caché:** Ehcache
* **Control de Versiones y Build:** Maven / Gradle

## 📦 Estructura del Proyecto (Arquitectura Hexagonal)

El proyecto se divide principalmente en dos capas o mundos: el Dominio (Core) y la Infraestructura/Aplicación (Adaptadores).

```
com.tuempresa.prestamos
│
├── domain                  # 🟢 Núcleo de la aplicación (Sin dependencias externas de Spring)
│   ├── model               # Entidades de dominio puro (Loan, User, Status)
│   └── exception           # Excepciones de negocio
│
├── application             # 🟡 Capa de Casos de Uso (Puertos de Entrada)
│   ├── usecase             # Implementaciones de la lógica de negocio (@Transactional aquí)
│   └── port
│       ├── in              # Interfaces de entrada (Driving ports)
│       └── out             # Interfaces de salida / Repositorios (Driven ports)
│
└── infrastructure          # 🔵 Adaptadores (Adaptadores de Entrada y Salida)
    ├── input               # Controladores REST, Filtros de Spring Security, DTOs
    │   └── rest
    ├── output              # Persistencia JPA, Adaptadores de Ehcache, Repositorios Spring Data
    │   └── persistence
    └── config              # Configuraciones de Spring, Beans de seguridad y Ehcache
```

## ⚙️ Configuración y Requisitos Previos

Asegúrate de tener instalado en tu entorno de desarrollo:

* **JDK 21**
* **Maven** (o Gradle)
* Base de datos relacional compatible (H2 para desarrollo/pruebas, PostgreSQL/MySQL para producción).

### 1. Clonar el repositorio

```bash
git clone https://github.com/tu-usuario/sistema-prestamos.git
cd sistema-prestamos
```

### 2. Configurar `application.yml` o `application.properties`

Configura tu conexión a la base de datos y los parámetros de Ehcache en la capa de infraestructura:

```yaml
spring:
  application:
      name: loan-management-system
  datasource:
      url: jdbc:h2:mem:testdb
      driver-class-name: org.h2.Driver
      username: sa
      password: 
  jpa:
      hibernate:
          ddl-auto: update
      show-sql: true
```

### 3. Configuración de Ehcache

El sistema utiliza Ehcache para almacenar en memoria caché consultas frecuentes de usuarios o configuraciones dentro de los adaptadores de salida. Asegúrate de tener tu archivo `ehcache.xml` en la ruta `src/main/resources`.

## 🔌 Endpoints Principales (API REST Stateless)

### 👤 Sección de Usuarios (`ROLE_USER`)

* `POST /api/auth/login` - Autenticación y obtención del token de acceso (Adaptador de entrada REST).
* `POST /api/loans/request` - Solicitar un nuevo monto de préstamo (Invocando al caso de uso correspondiente).
* `GET /api/loans/my-requests` - Consultar el estado de las solicitudes del usuario autenticado.

### 🛡️ Sección de Administradores (`ROLE_ADMIN`)

* `GET /api/admin/loans/pending` - Listar todas las solicitudes pendientes de aprobación (optimizado con Ehcache en los adaptadores de persistencia).
* `PATCH /api/admin/loans/{id}/status` - Aprobar o rechazar una solicitud específica (protegido por `@Transactional` en el caso de uso).

## 🔒 Seguridad y Transaccionalidad

* **Sin Estado (Stateless):** La aplicación no almacena sesiones en el servidor (`SessionCreationPolicy.STATELESS`). Cada petición HTTP que ingresa por los adaptadores web debe incluir las credenciales de autorización correspondientes.
* **Seguridad en Operaciones Críticas:** Los casos de uso implementan `@Transactional` para la modificación de estados de préstamos, asegurando que si ocurre un fallo durante el proceso de aprobación/rechazo en la infraestructura, la base de datos realice un *rollback* automático sin afectar el dominio puro.

## 📄 Licencia

Este proyecto está bajo la licencia MIT. Siéntase libre de usarlo, modificarlo y adaptarlo.