/*                                     *
 *   Procedimientos de actualizaci�n   *
 *                                     */
 use sicem;
 

-- Usuario
delimiter $$
create procedure Actualizar_Usuario(
	in _ID varchar(15),
	in _Contraseña varchar(50),
	in _Nombre varchar(50),
	in _Apellido varchar(50),
	in _Foto blob,
	in _Estado int
)begin
	Update Usuario
		Set
			Contraseña = _contraseña,
			Nombre = _Nombre,
			Apellido = _Apellido,
			Foto = _Foto,
			Estado = _Estado,
			FechaModificacion = date_format(now(), '%Y-%m-%d %H:%i:%s')
	where ID = _ID;
end $$

	

-- Validar usuario
delimiter $$
create procedure Validar_Usuario(
	in _Usuario varchar(15),
	in _Contraseña varchar (50)
)begin
	Select 
		ID 
	from Usuario  
	where (ID =  _Usuario and Contraseña = _Contraseña) and Estado = 1;
end $$



-- Permisos
delimiter $$
create procedure Actualizar_Permisos(
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
	update Permisos
		set
			directorioConsultar = _directorioConsultar,
			directorioEditar = _directorioEditar,
			directorioCrear = _directorioCrear,
			operacionesConsultar = _operacionesConsultar,
			operacionesEditar = _operacionesEditar,
			operacionesCrear = _operacionesCrear,
			productosConsultar = _productosConsultar,
			productosEditar = _productosEditar,
			productosCrear = _productosCrear,
			reportes = _reportes,
			accesoTotal = _accesoTotal
	where IDusuario = _IDusuario;
end $$

	

-- Cliente
delimiter $$
create procedure Actualizar_Cliente(	
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
	Update Cliente 
		Set Nombre=_Nombre,
			NombreContacto=_NombreContacto,
			TituloContacto=_TituloContacto, 
			Domicilio=_Domicilio,
			Ciudad=_Ciudad,
			Telefono=_Telefono,
			Email=_Email,
			Estado=_Estado,
			FechaModificacion=date_format(now(), '%Y-%m-%d %H:%i:%s')
	where ID=_ID;
end $$

	

-- Proveedor
delimiter $$
create procedure Actualizar_Proveedor(
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
	Update Proveedor 
		Set Nombre=_Nombre,
			NombreContacto=_NombreContacto,
			TituloContacto=_TituloContacto, 
			Domicilio=_Domicilio,
			Ciudad=_Ciudad,
			Telefono=_Telefono,
			Email=_Email,
			Estado=_Estado,
			FechaModificacion=date_format(now(), '%Y-%m-%d %H:%i:%s')
	where ID=_ID;
end $$

	
delimiter $$
create procedure Actualizar_Empleado(
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
	update Empleado
		set Nombres=_Nombres,
			Apellidos=_Apellidos,
			DepartamentoID=_DepartamentoID,
			TituloLaboral=_TituloLaboral,
			FechaDeNacimiento=_FechaDeNacimiento,
			FechaDeContratacion=_FechaDeContratacion,
			EstadoCivil=_EstadoCivil,
			Genero=_Genero,
			Domicilio=_Domicilio,
			Ciudad=_Ciudad,
			Telefono=_Telefono,
			Cedula=_Cedula,
			Email=_Email,
			Observaciones=_Observaciones,
			ReportarA=_ReportarA,
			Foto=_Foto,
			Estado=_Estado,
			FechaModificacion=date_format(now(), '%Y-%m-%d %H:%i:%s')
		where ID = _ID;
end $$

	
delimiter $$
create procedure Actualizar_Departamento(
	in _ID int,
	in _Nombre varchar(35),
	in _NombreGrupo varchar(35),
    in _Estado int
)begin
	update Departamentos
		set Nombre=_Nombre,
			NombreGrupo=_NombreGrupo,
            Estado = _Estado,
			FechaModificacion=date_format(now(), '%Y-%m-%d %H:%i:%s')
		where ID=_ID;
end $$

	

-- Producto
delimiter $$
create procedure Actualizar_Producto(
	in _ID int,
	in _CategoriaID int,
	in _Nombre varchar(50),
	in _CantidadPorUnidad int,
	in _PrecioVenta decimal(18,2),
	in _Stock int,
	in _Descripcion varchar(250),
	in _Estado int
)begin
	Update Producto
		set 
			CategoriaID = _CategoriaID,
			Nombre = _Nombre,
			CantidadPorUnidad = _CantidadPorUnidad,
			PrecioVenta = _PrecioVenta,
			Stock = _Stock,
			Descripcion = _Descripcion,
			Estado = _Estado,
			FechaModificacion = date_format(now(), '%Y-%m-%d %H:%i:%s')
	where ID = _ID;
end $$

	

-- Categoria
delimiter $$
create procedure Actualizar_Categoria(
	in _ID int,
	in _Nombre varchar(50),
	in _Descripcion varchar(250),
	in _Estado int
)begin
	Update Categoria
		set 
			Nombre = _Nombre,
			Descripcion = _Descripcion,
			Estado = _Estado,
			FechaModificacion = date_format(now(), '%Y-%m-%d %H:%i:%s')
	where ID = _ID;
end $$

	

-- Compra
delimiter $$
create procedure Actualizar_Compra(
	in _ID varchar(15),
	in _ProveedorID int,
	in _FechaCompra date,
	in _TipoPago int,
	in _Monto decimal(18,2)
)begin
	Update Compra
		set 
			ProveedorID = _ProveedorID,
			FechaCompra = _FechaCompra,
			TipoPago = _TipoPago,
			Monto = _Monto,
			FechaModificacion = date_format(now(), '%Y-%m-%d %H:%i:%s')
	where ID = _ID;
end $$

	

-- Detalle Compra
delimiter $$
create procedure Actualizar_Detalle_Compra(
	in _CompraID varchar(15),
	in _ProductoID int,
	in _Cantidad int,
	in _CostoUnitario decimal(18,2),
	in _Total decimal(18,2)
)begin
	Update Detalle_Compra 
		set 
			Cantidad = _Cantidad,
			CostoUnitario = _CostoUnitario,
			Total = _Total,
			FechaModificacion = date_format(now(), '%Y-%m-%d %H:%i:%s')
	where CompraID = _CompraID and ProductoID = _ProductoID;
end $$

	

-- Venta
delimiter $$
create procedure Actualizar_Venta(
	in _ID varchar(15),
	in _ClienteID varchar(25),
	in _FechaVenta date,
	in _TipoPago int,
	in _TipoVenta int,
	in _SubTotal decimal(18,2),
	in _Impuesto decimal(18,2),
	in _Total decimal(18,2)
)begin
	Update Venta
		set
			ClienteID = _ClienteID,
			FechaVenta = _FechaVenta,
			TipoPago = _TipoPago,
			TipoVenta = _TipoVenta,
			SubTotal = _SubTotal,
			Impuesto = _Impuesto,
			Total = _Total,
			FechaModificacion = date_format(now(), '%Y-%m-%d %H:%i:%s')
	where ID = _ID;
end $$

	

-- Detalle Venta
delimiter $$
create procedure Actualizar_Detalle_Venta(
	in _VentaID varchar(15),
	in _ProductoID int,
	in _Cantidad int,
	in _PrecioUnitario decimal(18,2),
	in _Descuento decimal(18,2),
	in _Impuesto decimal(18,2),
	in _Total decimal(18,2)
)begin
	Update Detalle_Venta
		set
			Cantidad = _Cantidad,
			PrecioUnitario = _PrecioUnitario,
			Descuento = _Descuento,
			Impuesto = _Impuesto,
			Total = _Total,
			FechaModificacion = date_format(now(), '%Y-%m-%d %H:%i:%s')
	where VentaID = _VentaID and ProductoID = _ProductoID;
end $$

	
