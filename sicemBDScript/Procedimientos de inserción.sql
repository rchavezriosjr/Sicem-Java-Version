/*                                *
 *   Procedimientos de inserción  *
 *                                */
use sicem;


	-- Usuario
delimiter $$
create procedure Insertar_Usuario(
	in _ID varchar(15),
	in _Contraseña varchar(50),
	in _Nombre varchar(50),
	in _Apellido varchar(50),
	in _Foto blob,
	in _Estado int
)begin
	insert into Usuario
	values(
		_ID, 
		_contraseña, 
		_Nombre, 
		_Apellido, 
		_Foto, 
		_Estado, 
		date_format(now(), '%Y-%m-%d %H:%i:%s'),
		date_format(now(), '%Y-%m-%d %H:%i:%s')
		);
end $$


-- Permisos
delimiter $$
create procedure Insertar_Permisos(
	in _IDusuario varchar(15),
    in _directorioConsultar int,
    in _directorioEditar int,
    in _directorioCrear int,
    in _operacionesConsultar int,
    in _operacionesEditar int,
    in _operacionesCrear int,
    in _productosConsultar int,
    in _productosEditar int,
    in _productosCrear int,
    in _reportes int,
    in _accesoTotal int
)
begin
	insert into Permisos
    values(
		_IDusuario,
		_directorioConsultar,
		_directorioEditar,
		_directorioCrear,
		_operacionesConsultar,
		_operacionesEditar,
		_operacionesCrear,
		_productosConsultar,
		_productosEditar,
		_productosCrear,
		_reportes,
		_accesoTotal
    );
end $$


 -- Cliente
delimiter $$
create procedure Insertar_Cliente(
	in _ID varchar(25),
	in _Nombre varchar(75),
	in _NombreContacto varchar(50),
	in _TituloContacto varchar(35),
	in _Domicilio varchar(200),
	in _Ciudad varchar(35),
	in _Telefono varchar(25),
	in _Email varchar(50),
	in _Estado int
)begin
	insert into Cliente
	values (
		_ID,
		_Nombre,
		_NombreContacto,
		_TituloContacto, 
		_Domicilio,
		_Ciudad, 
		_Telefono, 
		_Email,
		_Estado,
		date_format(now(), '%Y-%m-%d %H:%i:%s')
	);
end $$



	-- Proveedor
delimiter $$
create procedure Insertar_Proveedor(
	in _ID int,
	in _Nombre varchar(75),
	in _NombreContacto varchar(50),
	in _TituloContacto varchar(35),
	in _Domicilio varchar(200),
	in _Ciudad varchar(35),
	in _Telefono varchar(25),
	in _Email varchar(50),
	in _Estado int
)begin
	insert into Proveedor
	values (
		_ID,
		_Nombre,
		_NombreContacto,
		_TituloContacto, 
		_Domicilio,
		_Ciudad, 
		_Telefono, 
		_Email,
		_Estado,
		date_format(now(), '%Y-%m-%d %H:%i:%s'));
end $$

	
	-- Empleado
delimiter $$
create procedure Insertar_Empleado(
	in _ID int,
	in _Nombres varchar(80),
	in _Apellidos varchar(80),
	in _DepartamentoID int,
	in _TituloLaboral varchar(35),
	in _FechaDeNacimiento date,
	in _FechaDeContratacion date,
	in _EstadoCivil int,
	in _Genero int,
	in _Domicilio varchar(200),
	in _Ciudad varchar(35),
	in _Telefono varchar(25),
	in _Cedula varchar(25),
	in _Email varchar(50),
	in _Observaciones text,
	in _ReportarA int,
	in _Foto blob,
	in _Estado int
)begin
	insert into Empleado
	values(
		_ID,
		_Nombres,
		_Apellidos,
		_DepartamentoID,
		_TituloLaboral,
		_FechaDeNacimiento,
		_FechaDeContratacion,
		_EstadoCivil,
		_Genero,
		_Domicilio,
		_Ciudad,
		_Telefono,
		_Cedula,
		_Email,
		_Observaciones,
		-ReportarA,
		_Foto,
		_Estado,
		date_format(now(), '%Y-%m-%d %H:%i:%s'));
end $$

	
	-- Departamento laboral
delimiter $$
create procedure Insertar_Departamento(
	in _ID int,
	in _Nombre varchar(35),
	in _NombreGrupo varchar(35),
    in _Estado int
)begin
	insert into Departamentos
	values(
		_ID,
		_Nombre,
		_NombreGrupo,
        _Estado,
        date_format(now(), '%Y-%m-%d %H:%i:%s'),
		date_format(now(), '%Y-%m-%d %H:%i:%s'));
end $$

	

	-- Producto
delimiter $$
create procedure Insertar_Producto(
	in _ID int,
	in _CategoriaID int,
	in _Nombre varchar(50),
	in _CantidadPorUnidad int,
	in _PrecioVenta decimal(18,2),
	in _Stock int,
	in _Descripcion varchar(250),
	in _Estado int
)begin
	insert into Producto
	values(
		_ID,
		_CategoriaID, 
		_Nombre,
		_CantidadPorUnidad, 
		_PrecioVenta, 
		_Stock, 
		_Descripcion, 
		_Estado,
		date_format(now(), '%Y-%m-%d %H:%i:%s'));
end $$

	
delimiter $$
create procedure Insertar_Historial_Precio_Producto(
	in _ProductoID int,
	in _Precio decimal(18,2)
)begin
	update HistorialPrecioProducto
		set FechaFinal = now()
		where FechaFinal = null;

	insert into HistorialPrecioProducto(
		ProductoID,
		FechaInicio,
		Precio
	)
	values(
		_ProductoID,
		now(),
		_Precio
	);
end $$

	
delimiter $$
create procedure Insertar_Historial_Costo_Producto(
	in _ProductoID int,
	in _Precio decimal(18,2)
)begin
	update HistorialCostoProducto
		set FechaFinal = now()
		where FechaFinal = null;


	insert into HistorialCostoProducto(
		ProductoID,
		FechaInicio,
		Precio
	)
	values(
		_ProductoID,
		now(),
		_Precio
	);
end $$



delimiter $$
create procedure Insertar_Historial_Entrada_Producto(
	in _ProductoID int,
	in _Cantidad int,
	in _CostoUnitario decimal(18,2)
)begin
	insert into HistorialEntradaProducto
	values(
		_ProductoID,
		_Cantidad,
		_CostoUnitario,
		date_format(now(), '%Y-%m-%d %H:%i:%s'));
end $$


	
	-- Categoria
delimiter $$
create procedure Insertar_Categoria(
	in _ID int,
	in _Nombre varchar(50),
	in _Descripcion varchar(250),
	in _Estado int
)begin
	insert into Categoria
	values(
		_ID,
		_Nombre, 
		_Descripcion, 
		_Estado,
		date_format(now(), '%Y-%m-%d %H:%i:%s'),
		date_format(now(), '%Y-%m-%d %H:%i:%s'));
end $$

	

	-- Compra
delimiter $$
create procedure Insertar_Compra(
	in _ID varchar(15),
	in _ProveedorID int,
	in _FechaCompra date,
	in _TipoPago int,
	in _Monto decimal(18,2)
)begin
	insert into Compra
	values(_ID, _ProveedorID, _FechaCompra, _TipoPago, _Monto, date_format(now(), '%Y-%m-%d %H:%i:%s'));
end $$

	

	-- Detalle Compra
delimiter $$
create procedure Insertar_Detalle_Compra(
	in _CompraID varchar(15),
	in  _ProductoID int,
	in _Cantidad int,
	in _CostoUnitario decimal(18,2),
	in _Total decimal(18,2)
)begin
	insert into Detalle_Compra
	values(_CompraID, _ProductoID, _Cantidad, _CostoUnitario, _Total, date_format(now(), '%Y-%m-%d %H:%i:%s'));
end $$

	

	-- Venta
delimiter $$
create procedure Insertar_Venta(
	in _ID varchar(15),
	in _ClienteID varchar(25),
	in _FechaVenta date,
	in _TipoPago int,
	in _TipoVenta int,
	in _SubTotal decimal(18,2),
	in _Impuesto decimal(18,2),
	in _Total decimal(18,2)
)begin
	insert into Venta
	values(
		_ID, 
        _ClienteID, 
        _FechaVenta, 
        _TipoPago, 
        _TipoVenta, 
        _SubTotal, 
        _Impuesto, 
        _Total, 
        date_format(now(), '%Y-%m-%d %H:%i:%s'));
end $$

	

	-- Detalle Venta
delimiter $$
create procedure Insertar_Detalle_Venta(
	in _VentaID varchar(15),
	in _ProductoID int,
	in _Cantidad int,
	in _PrecioUnitario decimal(18,2),
	in _Descuento decimal(18,2),
	in _Impuesto decimal(18,2),
	in _Total decimal(18,2)
)begin
	insert into Detalle_Venta
	values(
		_VentaID, 
        _ProductoID, 
        _Cantidad, 
        _PrecioUnitario, 
        _Descuento, 
        _Impuesto, 
        _Total, 
        date_format(now(), '%Y-%m-%d %H:%i:%s'));
end $$

