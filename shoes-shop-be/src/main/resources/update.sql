EXEC sp_rename 'OrderDetails.OrderDetailID', 'ID', 'COLUMN';
EXEC sp_rename 'Users.IsGoogleLogin','GoogleLogin',"COLUMN"
ALTER TABLE Collections
    ADD CreatedAt DATETIME DEFAULT GETDATE();
Alter TABLE Brands ADD Logo NVARCHAR(MAX)

Alter table ProductImages add IsThumbnail Bit DEFAULT 0
ALTER TABLE ProductDetails
    DROP COLUMN SKU;
