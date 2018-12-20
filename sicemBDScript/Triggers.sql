use sicem;

-- Aumentar el stock a la hora de insertar un Detalle compra
delimiter $$
create trigger Aumentar_Stock_DC
after insert
on Detalle_Compra
for each row
begin
	update Producto
		set Producto.Stock = (Producto.Stock + new.Cantidad)
	where Producto.ID = new.ProductoID;
end $$


-- Disminuir el stock a la hora de insertar un Detalle venta
delimiter $$
create trigger Disminuir_Stock_DV
after insert
on Detalle_Venta
for each row
begin
	update Producto
		set Producto.Stock = (Producto.Stock - new.Cantidad) 
	where Producto.ID = new.ProductoID;
end $$


-- Actualiza el stock despues de actualizar detalle compra
delimiter $$
create trigger Actualiza_Stock_A_DC
after update
on Detalle_Compra
for each row
begin
	declare ActualizaStock int;
	set ActualizaStock = (select 
							       d.Cantidad 
							   from Detalle_Compra d
							   where 
							       d.CompraID = new.CompraID and d.ProductoID = new.ProductoID);

	Update Producto
		set Producto.Stock = Producto.Stock + (new.Cantidad - ActualizaStock)
	where Producto.ID = new.ProductoID;
end $$ 


-- Actualiza el stock despues de actualizar detalle venta
delimiter $$
create trigger Actualiza_Stock_A_DV
after update
on Detalle_Venta
for each row
begin
	declare ActualizaStock int;
	set ActualizaStock = (select 
								d.Cantidad 
							from Detalle_Venta d
							where 
								d.VentaID = new.VentaID and d.ProductoID = new.ProductoID);

	Update Producto
		set Producto.Stock = Producto.Stock - (new.Cantidad - ActualizaStock)
	where Producto.ID = new.ProductoID;
end $$


-- Insertar historial precio producto
delimiter $$
create trigger insert_precio_producto_history_trigger_insertEvent
after insert on Producto
for each row
begin
	call Insertar_Historial_Precio_Producto(new.ID, new.PrecioVenta);
end $$

delimiter $$
create trigger insert_precio_producto_history_trigger_updateEvent
after update on Producto
for each row
begin
	if old.PrecioVenta <> new.PrecioVenta then
		call Insertar_Historial_Precio_Producto(new.ID, new.PrecioVenta);
	end if;
end $$


-- Insertar historial costo producto / entrada producto
delimiter $$
create trigger costo_entrada_producto
after insert on Detalle_Compra
for each row
begin
	call Insertar_Historial_Costo_Producto(new.ProductoID, new.CostoUnitario);
    call Insertar_Historial_Entrada_Producto(new.ProductoID, new.Cantidad, new.CostoUnitario);
end $$