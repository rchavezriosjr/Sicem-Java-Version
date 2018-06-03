create database sicem;

use sicem;

create table Usuario(
	ID varchar(15) primary key not null,
	Contraseña varchar(50),
	Nombre varchar(50),
	Apellido varchar(50),
	FotoPerfil blob,
	Estado int,
	FechaCreacion date,
	FechaModificacion datetime
);

create table Cliente(
	ID varchar(25) primary key not null,
	NombreCliente varchar(75),
	NombreContacto varchar(50),
	TituloContacto varchar(35),
	Domicilio varchar(200),
	Ciudad varchar(35),
	Telefono varchar(25),
	Email varchar(50),
	Estado int,
	FechaModificacion datetime
);

create table Proveedor(
	ID int primary key not null,
	Nombre varchar(75),
	NombreContacto varchar(50),
	TituloContacto varchar(35),
	Domicilio varchar(200),
	Ciudad varchar(35),
	Telefono varchar(25),
	Email varchar(50),
	Estado int,
	FechaModificacion datetime
);

create table RH_Empleado(
	ID int primary key not null,
	Nombres varchar(80),
	Apellidos varchar(80),
	DepartamentoID int,
	TituloLaboral varchar(35),
	FechaDeNacimiento date,
	FechaDeContratacion date,
	EstadoCivil int,
	Genero int,
	Domicilio varchar(200),
	Ciudad varchar(35),
	Telefono varchar(25),
	Cedula varchar(25),
	Email varchar(50),
	HorasVacaciones int,
	HorasLicenciaEnfermedad int,
	Observaciones text,
	ReportarA int,
	Foto blob,
	Estado int,
	FechaModificacion datetime
);

create table RH_Departamentos(
	ID int primary key,
	Nombre varchar(35),
	NombreGrupo varchar(35),
	FechaModificacion datetime
);

create table Producto(
	ID int primary key not null,
	CategoriaID int,
	Nombre varchar(50),
	CantidadPorUnidad int,
	PrecioVenta decimal(18,2),
	Stock int,
	Descripcion varchar(250),
	Estado int,
	FechaModificacion datetime
);

create table HistorialPrecioProducto(
	ProductoID int,
	FechaInicio date,
	FechaFinal date,
	Precio decimal(18,2)
);

create table HistorialCostoProducto(
	ProductoID int,
	FechaInicio date,
	FechaFinal date,
	Precio decimal(18,2)
);

create table Bodega(
	ID int primary key,
	Nombre varchar(35),
	Almacenaje int,
	Comentarios text,
	Estado int,
	FechaModificacion datetime
);

create table Inventario(
	ID int primary key,
	ProductoID int,
	BodegaID int,
	Estante varchar(50),
	Cantidad int
);

create table Categoria(
	ID int primary key not null,
	Nombre varchar(50),
	Descripcion varchar(250),
	Estado int,
	FechaCreacion date,
	FechaModificacion datetime
);

create table Compra(
	ID varchar(15) primary key not null,
	ProveedorID int,
	FechaCompra date,
	TipoPago int,
	Monto decimal(18,2),
	FechaModificacion datetime
);

create table Detalle_Compra(
	CompraID varchar(15) not null,
	ProductoID int not null,
	Cantidad int not null,
	CostoUnitario decimal(18,2) not null,
	Total decimal(18,2),
	FechaModificacion datetime
);

create table Venta( 
	ID varchar(15) primary key not null,
	ClienteID varchar(25) not null,
	FechaVenta date not null,
	TipoPago int not null,
	TipoVenta int,
	SubTotal decimal(18,2),
	Impuesto decimal(18,2),
	Total decimal(18,2),
	FechaModificacion datetime
);

create table Detalle_Venta(
	VentaID varchar(15) not null,
	ProductoID int not null,
	Cantidad int,
	PrecioUnitario decimal(18,2),
	Descuento decimal(18,2),
	Impuesto decimal(18,2),
	Total decimal(18,2),
	FechaModificacion datetime
);