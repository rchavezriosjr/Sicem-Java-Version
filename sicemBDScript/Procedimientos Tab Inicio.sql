use sicem;

delimiter $$
create procedure contadores_tab_inicio()
begin
	declare _ventas int;
	declare _clientes int;
	declare _proveedores int;
	declare _producto int;

	set _ventas = ( select count(ID) from Venta where month(FechaVenta) = month(now()) and year(FechaVenta) = year(now()) );
	set _clientes = ( select count(ID) from Cliente );
	set _proveedores = ( select count(ID) from Proveedor );
	set _producto = ( select count(ID) from Producto );

	select 
		_clientes as Cliente,
		_proveedores as Proveedor,
		_producto as Producto,
		_ventas as Venta;
	-- from Cliente c, Proveedor p, Producto pr--, Venta v
end $$

-- call contadores_tab_inicio;

delimiter $$
create procedure ultimas_ventas()
begin
	select
		ID,
		FechaVenta as Fecha,
		Total
	from Venta
	order by ID desc
    limit 5;
end $$

-- call ultimas_ventas();

delimiter $$
create procedure productos_mas_vendidos()
begin
	select
			dv.ProductoID as 'ID',
			p.Nombre as 'Nombre',
			count(dv.Cantidad) as 'Cantidad vendida'
		from Detalle_Venta dv
			inner join Venta v
				on v.ID = dv.VentaID
				inner join Producto p
					on p.ID = dv.ProductoID and dv.VentaID = v.ID
		where year(v.FechaVenta) = year(now()) and month(v.FechaVenta) = month(now())
		group by p.Nombre, dv.ProductoID
		order by 'Cantidad vendida' desc
        limit 5;
end $$

-- call productos_mas_vendidos();

delimiter $$
create procedure top_clientes()
begin
	select 
				c.ID as 'ID',
				c.Nombre as 'Nombre',
				round(sum(v.Total), 2) as 'Recaudación'
			from Venta v
				inner join Cliente c
					on v.ClienteID = c.ID
			where year(v.FechaVenta) = year(now())
			group by c.ID, c.Nombre
			order by 'Recaudación' desc
            limit 5;
end $$

-- call top_clientes();
