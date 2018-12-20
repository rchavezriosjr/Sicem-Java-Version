create database sicem;

use sicem;

create table Usuario(
	ID varchar(15) primary key not null,
	Contraseña varchar(50),
	Nombre varchar(50),
	Apellido varchar(50),
	Foto blob,
	Estado int,
	FechaCreacion varchar(80),
	FechaModificacion varchar(80)
);

create table Permisos(
	IDusuario varchar(15) primary key,
    directorioConsultar int,
    directorioEditar int,
    directorioCrear int,
    operacionesConsultar int,
    operacionesEditar int,
    operacionesCrear int,
    productosConsultar int,
    productosEditar int,
    productosCrear int,
    reportes int,
    accesoTotal int
);

create table Cliente(
	ID varchar(25) primary key not null,
	Nombre varchar(75),
	NombreContacto varchar(50),
	TituloContacto varchar(35),
	Domicilio varchar(200),
	Ciudad varchar(35),
	Telefono varchar(25),
	Email varchar(50),
	Estado int,
	FechaModificacion varchar(80)
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
	FechaModificacion varchar(80)
);

create table Empleado(
	ID int primary key not null,
	Nombres varchar(80),
	Apellidos varchar(80),
	DepartamentoID int,
	TituloLaboral varchar(50),
	FechaDeNacimiento date,
	FechaDeContratacion date,
	EstadoCivil int,
	Genero int,
	Domicilio varchar(200),
	Ciudad varchar(35),
	Telefono varchar(25),
	Cedula varchar(25),
	Email varchar(50),
	Observaciones text,
	ReportarA int,
	Foto blob,
	Estado int,
	FechaModificacion varchar(80)
);

create table Departamentos(
	ID int primary key,
	Nombre varchar(35),
	NombreGrupo varchar(35),
    Estado int,
	FechaCreacion varchar(80),
	FechaModificacion varchar(80)
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
	FechaModificacion varchar(80)
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

create table HistorialEntradaProducto(
	ProductoID int,
	Cantidad int,
	CostoUnitario decimal(18,2),
	Fecha varchar(80)
);

create table Categoria(
	ID int primary key not null,
	Nombre varchar(50),
	Descripcion varchar(250),
	Estado int,
	FechaCreacion varchar(80),
	FechaModificacion varchar(80)
);

create table Compra(
	ID varchar(15) primary key not null,
	ProveedorID int,
	FechaCompra date,
	TipoPago int,
	Monto decimal(18,2),
	FechaModificacion varchar(80)
);

create table Detalle_Compra(
	CompraID varchar(15) not null,
	ProductoID int not null,
	Cantidad int not null,
	CostoUnitario decimal(18,2) not null,
	Total decimal(18,2),
	FechaModificacion varchar(80)
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
	FechaModificacion varchar(80)
);

create table Detalle_Venta(
	VentaID varchar(15) not null,
	ProductoID int not null,
	Cantidad int,
	PrecioUnitario decimal(18,2),
	Descuento decimal(18,2),
	Impuesto decimal(18,2),
	Total decimal(18,2),
	FechaModificacion varchar(80)
);