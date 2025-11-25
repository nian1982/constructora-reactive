# REQUERIMIENTOS
* Java 21
* Gradle 8.7
* Postman/Curl
* JQ
* Docker (Base de datos postgres)

# INSTRUCCIONES
* Ejecutar docker compose
    > `docker compose up -d`
* Cambiar la configuración de __application.yml__ 
    > Cambiar el valor de `mode: never` a `mode: always` 
    > Si se quiere ejecutar nuevamente despues de una primera ejecucion, cambiar el valor de `mode: always` a `mode: never`
* Ejecutar gradle bootRun
    > `./gradlew :app-main:bootRun`
    > `o desde un editor de codigo con su correspondiente boton de ejecución`

# TEST SERVICIOS
## GET
* curl http://localhost:8080/api/tipos-construccion | jq 
* curl http://localhost:8080/api/construcciones | jq
* curl http://localhost:8080/api/materiales | jq
* curl http://localhost:8080/api/ordenes | jq
* curl http://localhost:8080/api/ordenes/fecha-fin-proyecto | jq

## POST
* curl -X POST http://localhost:8080/api/ordenes -H "Content-Type: application/json" -H "rol: Arquitecto" -d '{"coordenadaX": 10.0, "coordenadaY": 20.0, "construccionId": 1}'
## Validación con rol de creación
* curl -X POST http://localhost:8080/api/ordenes -H "Content-Type: application/json" -H "rol: Arquitecto" -d '{"coordenadaX": 30.0, "coordenadaY": 40.0, "construccionId": 9}'
## Validacion con otro rol
* curl -X POST http://localhost:8080/api/ordenes -H "Content-Type: application/json" -H "rol: Ingeniero" -d '{"coordenadaX": 70.0, "coordenadaY": 80.0, "construccionId": 9}'
## validación sin la cabecera de rol
* curl -X POST http://localhost:8080/api/ordenes -H "Content-Type: application/json" -d '{"coordenadaX": 90.0, "coordenadaY": 100.0, "construccionId": 9}'
## Validación de coordenadas existentes
* Ejecutar una solicitud con las mismas coordenadas de una solicitud existente
## VAlidacion de falta de materiales
* Ejecutar una solicitud con una construccion que requiere mas materiales de los disponibles
* En su defecto, ejecutar la misma solicitud varias veces hasta agotar los materiales

## Data insert
### Construcciones
```sql
INSERT INTO public.construcciones (nombre,dias) VALUES
	 ('Casa',3),
	 ('Lago',2),
	 ('Cancha de fútbol',1),
	 ('Edificio',6),
	 ('Gimnasio',2);
```
### Materiales
```sql
INSERT INTO public.materiales (nombre,sigla,cantidad) VALUES
	 ('Arena','Ar',440),
	 ('Madera','Ma',794),
	 ('Grava','Gr',307),
	 ('Cemento','Ce',130),
	 ('Adobe','Ad',580);
```
### Tipos de Construcción
```sql
INSERT INTO public.tipo_construccion (cantidad,construccion_id,material_id) VALUES
	 (100,7,8),
	 (50,7,1),
	 (90,7,2),
	 (20,7,3),
	 (100,7,4),
	 (50,8,8),
	 (60,8,1),
	 (80,8,2),
	 (10,8,3),
	 (20,8,4)
	 (20,9,8),
	 (20,9,1),
	 (20,9,2),
	 (20,9,3),
	 (20,9,4),
	 (200,10,8),
	 (100,10,1),
	 (180,10,2),
	 (40,10,3),
	 (200,10,4)
	 (50,11,8),
	 (25,11,1),
	 (45,11,2),
	 (10,11,3),
	 (50,11,4);
```
### Solicitudes
```sql
INSERT INTO public.solicitudes (coordenada_x,coordenada_y,fecha_solicitud,fecha_inicio,fecha_finalizacion,estado,construccion_id) VALUES
	 (15.0,20.0,'2025-11-24 20:50:10.393842','2025-11-25 20:50:10.393842','2025-11-28 20:50:10.393842','PENDIENTE',7),
	 (10.0,20.0,'2025-11-24 21:09:31.477685','2025-11-29 20:50:10.393842','2025-12-02 20:50:10.393842','PENDIENTE',7),
	 (30.0,40.0,'2025-11-24 21:31:05.157823','2025-12-03 20:50:10.393842','2025-12-04 20:50:10.393842','PENDIENTE',9),
	 (50.0,60.0,'2025-11-24 21:32:40.099797','2025-12-05 20:50:10.393842','2025-12-08 20:50:10.393842','PENDIENTE',7);
```


