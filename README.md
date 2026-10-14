# Microservicio de Pedidos — Tienda PC

## Descripción

Microservicio desarrollado con Java y Spring Boot para gestionar los pedidos de una tienda en línea de componentes de PC.

Permite registrar pedidos, consultar pedidos de clientes, buscar pedidos por estado, actualizar su estado y eliminarlos.

## Tecnologías utilizadas

- Java 21
- Spring Boot 4.0.8
- Spring Data JPA
- MySQL
- Maven
- Git y GitHub

## Configuración

- **Puerto:** 8085
- **Base de datos:** `tienda_pedidos_db`
- **URL base:** `http://localhost:8085/api/pedidos`

## Funcionalidades

- Crear pedidos con estado inicial `PENDIENTE`.
- Consultar todos los pedidos.
- Buscar pedidos por ID.
- Consultar pedidos de un usuario.
- Filtrar pedidos por estado.
- Consultar pedidos de un usuario según su estado.
- Actualizar el estado de un pedido.
- Eliminar pedidos.

## Endpoints REST

| Método | Endpoint | Descripción |
|---|---|---|
| POST | `/api/pedidos` | Crear pedido |
| GET | `/api/pedidos` | Listar pedidos |
| GET | `/api/pedidos/{id}` | Buscar por ID |
| GET | `/api/pedidos/usuario/{usuarioId}` | Buscar por usuario |
| GET | `/api/pedidos/estado/{estado}` | Buscar por estado |
| GET | `/api/pedidos/usuario/{usuarioId}/estado/{estado}` | Filtrar por usuario y estado |
| PUT | `/api/pedidos/{id}/estado` | Actualizar estado |
| DELETE | `/api/pedidos/{id}` | Eliminar pedido |

## Ejemplo para crear un pedido

**POST** `/api/pedidos`

```json
{
  "usuarioId": 1,
  "total": 250000
}
```

El sistema asigna automáticamente el estado inicial `PENDIENTE` y la fecha del pedido.

## Ejemplo para actualizar un estado

**PUT** `/api/pedidos/1/estado`

```json
{
  "estado": "ENVIADO"
}
```

## Base de datos

```sql
CREATE DATABASE IF NOT EXISTS tienda_pedidos_db;
```

La aplicación utiliza Spring Data JPA para gestionar la tabla `pedidos`.

## Ejecución

Configurar la variable de entorno `DB_PASSWORD` con la contraseña de MySQL.

En PowerShell:

```powershell
$env:DB_PASSWORD = "TU_CONTRASEÑA_MYSQL"
.\mvnw.cmd spring-boot:run
```

## Compilación

```powershell
.\mvnw.cmd clean package
```

## Estructura del proyecto

- `model`: entidades de la base de datos.
- `repository`: acceso a datos mediante JPA.
- `service`: lógica de negocio.
- `controller`: endpoints REST.
- `config`: configuraciones adicionales.

## Alcance actual

Los pedidos almacenan el identificador del usuario y el total de la compra. La comunicación automática con los microservicios de carrito, inventario y pagos todavía no está implementada.

## Proyecto académico

Este microservicio forma parte de una arquitectura de ocho microservicios para una tienda de componentes de PC.