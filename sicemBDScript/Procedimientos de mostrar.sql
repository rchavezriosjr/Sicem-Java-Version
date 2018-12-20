/*                               *
 *   Procedimientos de mostrar   *
 *                               */

use sicem;


-- Usuarios
delimiter $$
create procedure Mostrar_Usuarios()
begin
	select * from Usuario;
end $$

	
delimiter $$
create procedure Detalle_Usuario(in _ID varchar(15))
begin
	select * from Usuario where ID = _ID;
end $$



-- Permisos
delimiter $$
create procedure Mostrar_Permisos(
	in _IDusuario varchar(15)
)
begin
	select * from Permisos where IDusuario = _IDusuario;
end $$

delimiter $$
create procedure Mostrar_Permisos_Directorio(
	in _IDusuario varchar(15)
)
begin
	select 
		directorioConsultar,
		directorioEditar,
		directorioCrear
    from Permisos where IDusuario = _IDusuario;
end $$

delimiter $$
create procedure Mostrar_Permisos_Operaciones(
	in _IDusuario varchar(15)
)
begin
	select 
		operacionesConsultar,
		operacionesEditar,
		operacionesCrear
    from Permisos where IDusuario = _IDusuario;
end $$

delimiter $$
create procedure Mostrar_Permisos_Productos(
	in _IDusuario varchar(15)
)
begin
	select 
		productosConsultar,
		productosEditar,
		productosCrear
    from Permisos where IDusuario = _IDusuario;
end $$

	

-- Clientes
delimiter $$
create procedure Mostrar_Clientes()
begin
	select * from Cliente;
end $$

	
delimiter $$
create procedure Detalle_Cliente(in _ID varchar(25))
begin
	select * from Cliente where ID = _ID;
end $$

	go

-- Proveedores
delimiter $$
create procedure Mostrar_Proveedores()
begin
	select * from Proveedor;
end $$

	
delimiter $$
create procedure Detalle_Proveedor(in _ID int)
begin
	select * from Proveedor where ID = _ID;
end $$

	
delimiter $$
create procedure Mostrar_Empleados()
begin
	select * from Empleado;
end $$

	
delimiter $$
create procedure Detalle_Empleado(in _ID int)
begin
	select * from Empleado where ID = _ID;
end $$

	
delimiter $$
create procedure Mostrar_Departamentos()
begin
	select * from Departamentos;
end $$

	
delimiter $$
create procedure Detalle_Departamento(in _ID int)
begin
	select * from Departamentos where ID = _ID;
end $$

	

-- Productos
delimiter $$
create procedure Mostrar_Productos()
begin
	select * from Producto where Estado = 1;
end $$

	
delimiter $$
create procedure Mostrar_Todo_Productos()
begin
	select * from Producto;
end $$

	
delimiter $$
create procedure Detalle_Producto(in _ID int)
begin
	select * from Producto where ID = _ID;
end $$

	
delimiter $$
create procedure Mostrar_Entrada_Producto(
	in _ProductoID int
)begin
	select * from HistorialEntradaProducto where ProductoID = _ProductoID;
end $$

	
delimiter $$
create procedure Mostrar_HistorialPrecioProducto(in _ID int)
begin
	select * from HistorialPrecioProducto h where h.ProductoID = _ID; 
end $$

	
delimiter $$
create procedure Mostrar_HistorialCostoProducto(in _ID int)
begin
	select * from HistorialCostoProducto h where h.ProductoID = _ID;
end $$

	
delimiter $$
create procedure Mostrar_Bodegas()
begin
	select * from Bodega;
end $$

	
delimiter $$
create procedure Detalle_Bodega(in _ID int)
begin
	select * from Bodega where ID = _ID;
end $$

	
delimiter $$
create procedure Mostrar_Inventario(in _ID int)
begin
	select * from Inventario where ProductoID = _ID;
end $$

	
delimiter $$
create procedure Detalle_Inventario(in _ID int)
begin
	select * from Inventario where ID = _ID;
end $$

	
delimiter $$
create procedure Remover_Inventario(in _ID int)
begin
	delete from Inventario where ID = _ID;
end $$

	

-- Categorias habilitadas
delimiter $$
create procedure Mostrar_Categorias()
begin
	select * from Categoria;
end $$

	
delimiter $$
create procedure Detalle_Categoria(in _ID int)
begin
	select * from Categoria where ID = _ID;
end $$

	

-- Compras
delimiter $$
create procedure Mostrar_Compras()
begin
	select * from Compra;
end $$

	
delimiter $$
create procedure CompraDetallada(in _ID varchar(15))
begin
	select
		c.ID,
		c.ProveedorID,
		p.Nombre,
		c.FechaCompra,
		c.TipoPago,
		c.Monto,
		c.FechaModificacion
	from Compra c 
		inner join Proveedor p 
			on p.ID = c.ProveedorID
	where c.ID = _ID;
end $$

	
delimiter $$
create procedure Mostrar_Detalle_Compra(in _ID varchar(15))
begin
	select 
		d.ProductoID as ID,
		p.Nombre as Producto,
		d.Cantidad as Cantidad,
		d.CostoUnitario as 'Costo unitario',
		d.Total as Total
	from Detalle_Compra d
		inner join Producto p
			on p.ID = d.ProductoID
	where d.CompraID = _ID;
end $$

	

-- Ventas
delimiter $$
create procedure Mostrar_Ventas()
begin
	select * from Venta;
end $$

	
delimiter $$
create procedure VentaDetallada(in _ID varchar(15))
begin
	select
		v.ID,
		v.ClienteID,
		c.NombreCliente,
		v.FechaVenta,
		v.TipoPago,
		v.TipoVenta,
		v.SubTotal,
		v.Impuesto,
		v.Total,
		v.FechaModificacion
	from Venta v
		inner join Cliente c 
			on c.ID = v.ClienteID
	where v.ID = _ID;
end $$

	
delimiter $$
create procedure Mostrar_Detalle_Venta(in _ID varchar(15))
begin
	select 
		d.ProductoID as ID,
		p.Nombre as Producto,
		d.Cantidad as Cantidad,
		d.PrecioUnitario as 'Precio unitario',
		d.Descuento as Descuento,
		d.Impuesto as Impuesto,
		d.Total as Total
	from Detalle_Venta d
		inner join Producto p
			on p.ID = d.ProductoID
	where d.VentaID = _ID;
end $$



delimiter $$
create procedure Reporte_ventas(in _inicio date, in _fin date)
begin 
	select
		v.ID,
        c.Nombre,
        v.TipoVenta,
		v.SubTotal,
		v.Impuesto,
		v.Total
	from Venta v
		inner join Clientes c
        on c.ID = v.ClienteID
	where 
		v.FechaVenta>= _inicio
		and
		v.FechaVenta <= _fin;
end $$

	
delimiter $$
create procedure Reporte_Compras(in _inicio date, in _fin date)
begin 
	select
		c.ID,
        p.Nombre,
        c.TipoPago,
		c.Monto
	from Compra c
		inner join Proveedor p
        on p.ID = c.ProveedorID
	where 
		FechaCompra >= _inicio
		and
		FechaCompra <= _fin;
end $$


delimiter $$
create procedure Existencia_Producto()
begin
	select
		p.ID,
        p.Nombre,
        c.Nombre,
        p.Stock
    from Producto p
		inner join Categoria c
		on c.ID = p.CategoriaID;
end $$


delimiter $$
create procedure Recaudacion_Producto(in _inicio date, in _fin date)
begin
	select
		p.ID,
        p.Nombre,
        c.Nombre as 'categoria',
        sum(dv.Cantidad) as 'unidades',
        sum(dv.Total) as 'recaudacion'
    from Venta v
     inner join Detalle_Venta dv
     on dv.VentaID = v.ID
		inner join Producto p
        on p.ID = dv.ProductoID
			inner join Categoria c
            on c.ID = p.CategoriaID
	where v.FechaVenta >= _inicio and v.FechaVenta <= _fin
    group by p.ID
    order by recaudacion desc;
end $$

/*
delimiter $$
create procedure Reporte_estado(in _mes int, in _año int)
begin 
	declare _sumav decimal(18,2);
	set _sumav = (select 
						sum(Total)
					from Venta 
					where month(FechaVenta) = _mes and year(FechaVenta) = _año);

	declare _sumac decimal(18,2);
	set _sumac = (select
						sum(Monto)
					from Compra 
					where 
						month(FechaCompra) = _mes
						and
						year(FechaCompra) = _año);

	select 
		_sumav as 'Total recaudación',
		_sumac as 'Total egresos',
		(_sumav - _sumac) as 'Utilidad';
end $$*/