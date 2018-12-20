/*                                *
 *   Procedimientos de búsqueda   *
 *                                */
use sicem;

-- Usuario
delimiter $$
create procedure Busqueda_Usuario(
	in _valor varchar(100),
	in _clave int
)begin
	if _clave = 0 then
		select 	* from Usuario 	where ID like concat('%',_valor,'%') or Nombre like concat('%',_valor,'%');
	elseif _clave = 1 then
			select * from Usuario where Apellido like concat('%',_valor,'%');
	end if;
end $$

	

-- Cliente
delimiter $$
create procedure Busqueda_Cliente(
	in _valor varchar(100),
	in _clave int
)begin
	if _clave = 0 then
		select * from Cliente where Nombre like concat('%',_valor,'%') or ID like concat('%',_valor,'%');
	elseif _clave = 1 then
			select * from Cliente where Email like concat('%',_valor,'%');
		elseif _clave = 2 then
				select * from Cliente where Domicilio like concat('%',_valor,'%');
			elseif _clave = 3 then
					select * from Cliente where Telefono like concat('%',_valor,'%');
	end if;
end $$

	

-- Proveedor
delimiter $$
create procedure Busqueda_Proveedor(
	in _valor varchar(100),
	in _clave int
)begin
	if _clave = 0 then
		select * from Proveedor where Nombre like concat('%',_valor,'%') or ID like concat('%',_valor,'%');
	elseif _clave = 1 then
			select * from Proveedor where Email like concat('%',_valor,'%');
		elseif _clave = 2 then
				select * from Proveedor where Domicilio like concat('%',_valor,'%');
			elseif _clave = 3 then
					select * from Proveedor where Telefono like concat('%',_valor,'%');
	end if;
end $$

	
delimiter $$
create procedure Busqueda_Empleado(
	in _valor varchar(100),
	in _clave int
)begin
	if _clave = 0 then
		select * from Empleado where (Nombres like concat('%',_valor,'%') or ID like concat('%',_valor,'%')) or Apellidos like concat('%',_valor,'%');
	elseif _clave = 1 then
			select * from Empleado where Email like concat('%',_valor,'%');
		elseif _clave = 2 then
				select * from Empleado where Domicilio like concat('%',_valor,'%');
			elseif _clave = 3 then
					select * from Empleado where Telefono like concat('%',_valor,'%');
	end if;
end $$

	
delimiter $$
create procedure Busqueda_Departamento(
	in _valor varchar(100),
	in _clave int
)begin
	if _clave = 0 then
		select * from Departamentos where Nombre like concat('%',_valor,'%') or ID like concat('%',_valor,'%');
	elseif _clave = 1 then
			select * from Departamentos where NombreGrupo like concat('%',_valor,'%');
	end if;
end $$

	
delimiter $$
create procedure Busqueda_Bodega(
	in _valor varchar(100),
	in _clave int
)begin
	if _clave = 0 then
		select * from Bodega where Nombre like concat('%',_valor,'%') or ID like concat('%',_valor,'%');
	elseif _clave = 1 then
			select * from Bodega where Almacenaje like concat('%',_valor,'%');
	end if;
end $$

	

-- Compras
delimiter $$
create procedure Busqueda_Compras(
	in _valor varchar(100),
	in _clave int
)begin
	if _clave = 0 then
		select * from Compra where ID like concat('%',_valor,'%');
	elseif _clave = 1 then 
			select * from Compra where ProveedorID like concat('%',_valor,'%');
		elseif _clave = 2 then
				select * from Compra where FechaCompra like concat('%',_valor,'%');
			elseif _clave = 3 then
					select * from Compra where Monto like concat('%',_valor,'%');
	end if;
end $$

	

-- Ventas
delimiter $$
create procedure Busqueda_Ventas(
	in _valor varchar(100),
	in _clave int
)begin
	if _clave = 0 then
		select * from Venta where ID like concat('%',_valor,'%');
	elseif _clave = 1 then
			select * from Venta where ClienteID like concat('%',_valor,'%');
		elseif _clave = 2 then
				select * from Venta where FechaVenta like concat('%',_valor,'%');
			elseif _clave = 3 then
					select * from Venta where Total like concat('%',_valor,'%');
	end if;
end $$

	

-- Productos
delimiter $$
create procedure Busqueda_Productos(
	in _valor varchar(100),
	in _clave int
)begin
	if _clave = 0 then
		select * from Producto where (Nombre like concat('%',_valor,'%') or ID like concat('%',_valor,'%')) and Estado = 1;
	elseif _clave = 1 then
			select * from Producto  where (CategoriaID like concat('%',_valor,'%')) and Estado = 1;
		elseif _clave = 2 then
				select * from Producto where (PrecioVenta like concat('%',_valor,'%')) and Estado = 1;
			elseif _clave = 3 then
					select * from Producto where (Stock like concat('%',_valor,'%')) and Estado = 1;
	end if;
end $$

	
delimiter $$
create procedure Busqueda_Productos_Todo(
	in _valor varchar(100),
	in _clave int
)begin
	if _clave = 0 then
		select * from Producto where (Nombre like concat('%',_valor,'%') or ID like concat('%',_valor,'%'));
	elseif _clave = 1 then
			select * from Producto  where (CategoriaID like concat('%',_valor,'%'));
		elseif _clave = 2 then 
				select * from Producto where (PrecioVenta like concat('%',_valor,'%'));
			elseif _clave = 3 then
					select * from Producto where (Stock like concat('%',_valor,'%'));
	end if;
end $$

	

-- Categorías
delimiter $$
create procedure Busqueda_Categoria(
	in _valor varchar(100)
)begin
		select * from Categoria where Nombre like concat('%',_valor,'%') or ID like concat('%',_valor,'%');
end $$

	
