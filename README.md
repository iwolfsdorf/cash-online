# Cash Online Backend
> Aplicacion para Examen Cash Online.

## Table de Contenidos

- [Prerequisitos](#Prerequisitos)
- [Instalacion](#instalacion)
- [Uso](#uso)
- [Carga Inicial](#Carga Inicial)

## Prerequisitos

Para levantar esta aplicacion es necesario tener:

- Java 1.8
- Eclipse
- MySQL

## Instalacion

Linux:

- Clonar codigo de Git (https://github.com/iwolfsdorf/cash-online.git)
- Crear una base de datos en MySQL

```
$ create database cash-online_db;
```

- Configurar el archivo application.yml segun la base de datos creada.
- Desde la IDE ejecutar desde la clase Application.java.

## Uso

La aplicacion cuenta con los siguientes servicios expuestos segun el modelo:

User:

	- Get /users/{id}  "Obtiene el usuario dado su id."
	
	- Delete /users/{id} "Elimina el usuario dado su id"
	
	- Post /users "Crea un usuario enviando un Json"
	En caso de error 415 agregar en el header Accept: text/plain y Content-Type: application/json

Loan: 

	- GET /loans?limit=1&offset=0&user_id=2 "Obtine una pagina de prestamos segun los parametros enviados. los parametros limit y offset son obligatorios mientras que user_id es opcional"
	
## Carga Inicial

Usuarios:

	insert into user (user_id, email, first_name, last_name) value(1, 'jgomez@gmail.com', 'Juan', 'Gomez');
	
	insert into user (user_id, email, first_name, last_name) value(2, 'amendez@gmail.com', 'Alicia', 'Mendez');
	
	insert into user (user_id, email, first_name, last_name) value(3, 'jSendra@gmail.com', 'Jorge', 'Sendra');
	
	insert into user (user_id, email, first_name, last_name) value(4, 'jLuaces@gmail.com', 'Jose', 'Luaces');
	
	
Prestamos:

	insert into loan (loan_id, total, user_id) values (1, 5000.00, 1);
	
	insert into loan (loan_id, total, user_id) values (2, 75000.00, 1);
	
	insert into loan (loan_id, total, user_id) values (3, 1000.50, 2);
	
	insert into loan (loan_id, total, user_id) values (4, 2215.80, 3);
	
	insert into loan (loan_id, total, user_id) values (5, 887978.00, 2);
	
	insert into loan (loan_id, total, user_id) values (6, 44568.11, 2);
