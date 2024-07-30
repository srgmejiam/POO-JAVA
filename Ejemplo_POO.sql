USE master
GO
IF EXISTS(SELECT D.name FROM sys.databases d WHERE D.name LIKE 'SISBD')
BEGIN
ALTER DATABASE SISBD SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
 DROP DATABASE SISBD;
END 
GO
CREATE DATABASE SISBD
GO 
USE SISBD
GO
CREATE TABLE Roles (
IdRol TINYINT PRIMARY KEY IDENTITY,
Rol VARCHAR(100) NOT NULL,
Activo BIT NOT NULL,
IdUsuarioRegistro INT NOT NULL,
FechaRegistro DATETIME NOT NULL,
IdUsuarioActualiza INT NULL,
FechaActualizacion DATETIME NULL
)
GO
CREATE TABLE Usuarios(
IdUsuario INT PRIMARY KEY IDENTITY,
Nombre VARCHAR(200) NOT NULL,
Login varchar(100) NOT NULL UNIQUE,
Password VARBINARY(MAX) NOT NULL,
Correo VARCHAR(500) NOT NULL UNIQUE,
IdRol TINYINT FOREIGN KEY REFERENCES Roles(IdRol),
Activo BIT NOT NULL DEFAULT(1),
IdUsuarioRegistro INT NOT NULL,
FechaRegistro DATETIME NOT NULL,
IdUsuarioActualiza INT NULL,
FechaActualizacion DATETIME NULL
)
GO

-- Stored Procedure para insertar un nuevo usuario
ALTER PROCEDURE InsertarUsuario
    @Nombre VARCHAR(200),
    @Login VARCHAR(100),
    @Password VARBINARY(MAX),
    @Correo VARCHAR(500),
    @IdRol TINYINT,
    @IdUsuarioRegistro INT=1
AS
BEGIN
    SET NOCOUNT ON;
    BEGIN TRY
        BEGIN TRANSACTION;

        INSERT INTO Usuarios (Nombre, Login, Password, Correo, IdRol, Activo, IdUsuarioRegistro, FechaRegistro)
        VALUES (@Nombre, @Login, @Password, @Correo, @IdRol, 1, @IdUsuarioRegistro, GETDATE());

            SELECT SCOPE_IDENTITY() AS Resultado;
        COMMIT TRANSACTION;  
    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION;
        SELECT 0 AS Resultado;
    END CATCH
END
GO
-- Stored Procedure para actualizar un usuario existente
ALTER PROCEDURE ActualizarUsuario
    @IdUsuario INT,
    @Nombre VARCHAR(200),
    @Login VARCHAR(100),
    @Password VARBINARY(MAX),
    @Correo VARCHAR(500),
    @IdRol TINYINT,
    @IdUsuarioActualiza INT=1,
    @CambiarPassword BIT = 0
AS
BEGIN
    SET NOCOUNT ON;
    BEGIN TRY
        BEGIN TRANSACTION;

              if(@CambiarPassword=1)
              BEGIN
                      UPDATE Usuarios
                      SET Nombre = @Nombre,
                          Login = @Login,
                          Password = @Password,
                          Correo = @Correo,
                          IdRol = @IdRol,
                          IdUsuarioActualiza = @IdUsuarioActualiza,
                          FechaActualizacion = GETDATE()
                      WHERE IdUsuario = @IdUsuario AND Activo = 1;
              END

              if(@CambiarPassword = 0)
              BEGIN 
                       UPDATE Usuarios
                      SET Nombre = @Nombre,
                          Login = @Login,
                          Correo = @Correo,
                          IdRol = @IdRol,
                          IdUsuarioActualiza = @IdUsuarioActualiza,
                          FechaActualizacion = GETDATE()
                      WHERE IdUsuario = @IdUsuario AND Activo = 1;
              END
        SELECT 1 AS Resultado;
        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION;
        SELECT 0 AS Resultado;
    END CATCH
END
GO
-- Stored Procedure para anular (desactivar) un usuario
ALTER PROCEDURE AnularUsuario
    @IdUsuario INT,
    @IdUsuarioActualiza INT = 1
AS
BEGIN
    SET NOCOUNT ON;
    BEGIN TRY
        BEGIN TRANSACTION;

        UPDATE Usuarios
        SET Activo = 0,
            IdUsuarioActualiza = @IdUsuarioActualiza,
            FechaActualizacion = GETDATE()
        WHERE IdUsuario = @IdUsuario AND Activo = 1;
        SELECT 1 AS Resultado;
        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION;
        SELECT 0 AS Resultado;
    END CATCH
END
GO
ALTER VIEW vUsuarios
AS
SELECT 
    u.IdUsuario,
    u.Nombre,
    u.Login,
    u.Correo,
    u.IdRol,
    R.Rol
FROM 
    Usuarios u
    INNER JOIN Roles r ON u.IdRol = R.IdRol
    WHERE u.Activo = 1;
GO
CREATE VIEW vRoles
AS
SELECT TOP 100 PERCENT
    IdRol,
    Rol
FROM 
    Roles WHERE Activo=1
    ORDER BY Roles.Rol ASC;
GO
