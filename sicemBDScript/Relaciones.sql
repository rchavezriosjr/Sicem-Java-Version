use sicem;

alter table Permisos
add foreign key (IDusuario) references Usuario(ID);

alter table Empleado
add foreign key (DepartamentoID) references Departamentos(ID);

alter table Producto
add foreign key (CategoriaID) references Categoria(ID);

alter table HistorialPrecioProducto
add foreign key (ProductoID) references Producto(ID);

alter table HistorialCostoProducto
add foreign key (ProductoID) references Producto(ID);

alter table HistorialEntradaProducto
add foreign key (ProductoID) references Producto(ID);

alter table Compra 
add foreign key	(ProveedorID) references Proveedor(ID);

alter table Detalle_Compra
add foreign key (CompraID) references Compra(Id);

alter table Detalle_Compra
add foreign key (ProductoID) references Producto(ID);

alter table Venta
add foreign key (ClienteID) references Cliente(ID);

alter table Detalle_Venta
add foreign key (VentaID) references Venta(ID);

alter table Detalle_Venta
add foreign key (ProductoID) references Producto(ID);