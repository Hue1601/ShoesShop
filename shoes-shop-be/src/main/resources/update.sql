EXEC sp_rename 'OrderDetails.OrderDetailID', 'ID', 'COLUMN';
EXEC sp_rename 'Users.IsGoogleLogin','GoogleLogin',"COLUMN"
ALTER TABLE Collections
    ADD CreatedAt DATETIME DEFAULT GETDATE();
